

public class DFABuilder {
	
	Automata eNFA = new Automata();
	private static boolean DEBUG = true; 
	
	public DFABuilder() throws Exception{
		
		//REParser parser = new REParser();
		RegExp regex = REParser.parse("aabb");	
		
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
			/*
			State r1InitialState = new State();
			Epsilon e = new Epsilon();
			eNFA.addTransition(e, previousFinal, r1InitialState);
			*/			
			State finalR1State = nextRegex(r1.getClass().getName(), r1, previousFinal);
			
			if (DEBUG) System.out.println("R2: " + con.getR2());
			Epsilon e2 = new Epsilon();
			State r2StartState = new State();
			eNFA.addTransition(e2, finalR1State, r2StartState);
			RegExp r2 = con.getR2();
			State finalR2State = nextRegex(r2.getClass().getName(), r2, r2StartState);
			return finalR2State;

		case "Union":

		case "Closure":

		case "Litteral":
			Litteral litt = (Litteral) regex;
			/*
			State littState = new State();
			Epsilon eLitt = new Epsilon();
			eNFA.addTransition(eLitt, previousFinal, littState);
			*/
			State littFinal = new State();
			eNFA.addTransition(litt, previousFinal, littFinal); //littState was changed to previousFinal
			return littFinal;

		case "OneOreMore":

		case "ZeroOrOne":

		case "Dot":
		}		
		return null;
	}
}
