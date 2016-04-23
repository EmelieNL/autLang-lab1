import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;



public class DFABuilder {
	
	Automata eNFA = new Automata();
	
	public DFABuilder() throws Exception{
		REParser parser = new REParser();

		RegExp regex = parser.parse("aa");
		

		System.out.println(regex);
		State startState = new State();
		eNFA.addStartState(startState);
		
		State finalState = nextRegex(regex.getClass().getName(), regex, startState);
		eNFA.addFinalState(finalState);
		printNFA();
		
		
	}

	private State nextRegex(String regexOperator, RegExp regex, State previousFinal){
		System.out.println("nextRegex!");
		switch (regex.getClass().getName()){

		case "Concatenation":
			Concatenation con = (Concatenation) regex;
			System.out.println(con.getR1());
			RegExp r1 = con.getR1();
			State r1State = new State();
			Epsilon e = new Epsilon();
			eNFA.addTransition(e, previousFinal, r1State);
			
			State finalR1State = nextRegex(r1.getClass().getName(), r1, r1State);
			Epsilon e2 = new Epsilon();
			State r2StartState = new State();
			eNFA.addTransition(e2, finalR1State, r2StartState);
			RegExp r2 = con.getR2();
			State finalR2State = nextRegex(r2.getClass().getName(), r2, r1State);
			return finalR2State;

		case "Union":

		case "Closure":

		case "Litteral":
			Litteral litt = (Litteral) regex;
			State littState = new State();
			Epsilon eLitt = new Epsilon();
			eNFA.addTransition(eLitt, previousFinal, littState);
			State littFinal = new State();
			eNFA.addTransition(litt, littState, littFinal);
			return littFinal;

		case "OneOreMore":

		case "ZeroOrOne":

		case "Dot":

		}
		
		return null;
	}
	
	private void printNFA() {
		HashSet<State> states = eNFA.getStates();
		System.out.println("Initial state: " + eNFA.getInitialState().stateID);
		System.out.println("Numer of states: " + states.size());
		
		for (State s: states) {
			System.out.println("State: " + s.stateID);
		}
		//System.out.println("Final states: " + eNFA.getFinalsState());
	}
	
	
}
