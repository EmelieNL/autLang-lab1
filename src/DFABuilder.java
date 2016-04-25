

public class DFABuilder {
	
	Automata eNFA = new Automata();
	private static boolean DEBUG = true; 
	
	public DFABuilder() throws Exception{
		
		RegExp regex = REParser.parse("ab*");	
		
		State startState = new State();
		eNFA.addStartState(startState);
		
		State finalState = nextRegex(regex.getClass().getName(), regex, startState);
		eNFA.addFinalState(finalState);
		eNFA.printAutomataInfo();		
	}

	private State nextRegex(String regexOperator, RegExp regex, State previousFinal){
		if (DEBUG) System.out.println("Next to parse: " + regexOperator);
		switch (regex.getClass().getName()){

		case "Concatenation":
			Concatenation con = (Concatenation) regex;
			
			if (DEBUG) System.out.println("R1: " + con.getR1());
			RegExp r1 = con.getR1();		
			State finalR1State = nextRegex(r1.getClass().getName(), r1, previousFinal);
			
			if (DEBUG) System.out.println("R2: " + con.getR2());
			Epsilon e = new Epsilon();
			State r2StartState = new State();
			eNFA.addTransition(e, finalR1State, r2StartState);
			RegExp r2 = con.getR2();
			State finalR2State = nextRegex(r2.getClass().getName(), r2, r2StartState);
			return finalR2State;

		case "Union":
			Union un = (Union) regex;
			RegExp r1Union = un.r1; RegExp r2Union = un.r2;
			
			//create initial state for r1 and add edge 
			State initialR1UnionState = new State();
			Epsilon e1 = new Epsilon();
			eNFA.addTransition(e1, previousFinal, initialR1UnionState);
			State finalR1Union = nextRegex(r1Union.getClass().getName(), r1Union, initialR1UnionState);
			//same as above for r2
			State initialR2UnionState = new State();
			Epsilon e2 = new Epsilon();
			eNFA.addTransition(e2, previousFinal, initialR2UnionState);
			State finalR2Union = nextRegex(r2Union.getClass().getName(), r2Union, initialR2UnionState);
			
			//connect the two final states (in each automata above) to a single final state for the whole union
			State finalUnionState = new State();
			Epsilon e3 = new Epsilon();
			Epsilon e4 = new Epsilon();
			eNFA.addTransition(e3, finalR1Union, finalUnionState);
			eNFA.addTransition(e4, finalR2Union, finalUnionState);
			return finalUnionState;

		case "Closure":
			Closure clos = (Closure) regex;
			RegExp closR1 = clos.r;
			State closInitial = new State();
			Epsilon e5 = new Epsilon();
			eNFA.addTransition(e5, previousFinal, closInitial);
			
			State automataFinal = nextRegex(closR1.getClass().getName(), closR1, closInitial);
			
			State finalClosureState = new State();
			Epsilon e6 = new Epsilon(); Epsilon e7 = new Epsilon(); Epsilon e8 = new Epsilon();
			eNFA.addTransition(e6, previousFinal, finalClosureState);
			eNFA.addTransition(e7, automataFinal, finalClosureState);
			eNFA.addTransition(e8, automataFinal, closInitial);
			return finalClosureState;

		case "Litteral":
			Litteral litt = (Litteral) regex;
			State littFinal = new State();
			eNFA.addTransition(litt, previousFinal, littFinal); //littState was changed to previousFinal
			return littFinal;

		case "OneOreMore":
			OneOrMore oOreMore = (OneOrMore) regex;
			RegExp oneR1 = oOreMore.r;
			
			State oneR1Final= nextRegex(oneR1.getClass().getName(), oneR1, previousFinal);
			
			State oneOrMoreFinal = new State();
			Epsilon e9 = new Epsilon(); Epsilon e10 = new Epsilon();
			eNFA.addTransition(e9, oneR1Final, oneOrMoreFinal);
			eNFA.addTransition(e10, oneR1Final, previousFinal);
			return oneOrMoreFinal;

		case "ZeroOrOne":

		case "Dot":
		}		
		return null;
	}
}
