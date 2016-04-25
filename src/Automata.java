import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Automata {
	// states, Alphabet , TransistionFunctions, startState, endState
	HashSet<State> states = new HashSet<State>();
	HashSet<State> finalStates = new HashSet<State>();
	State initialState;
	Map<State, Map<Object, HashSet<State>>> transitions;
	HashSet<Character> alphabet;

	public Automata() {
		transitions = new HashMap<State, Map<Object, HashSet<State>>>();
	}

	public void addStartState(State start) {
		initialState = start;
		addState(start);
	}

	public void addFinalState(State finalState) {
		finalStates.add(finalState);
		addState(finalState);
	}

	public void addState(State state) {
		states.add(state);
	}

	public State getInitialState() {
		return initialState;
	}

	public HashSet<State> getStates() {
		return states;
	}

	public HashSet<State> getFinalsState() {
		return finalStates;
	}

	public Map<State, Map<Object, HashSet<State>>> getTransitions() {
		return transitions;
	}

	public void addTransition(Object transObj, State from, State to) {
		if (!states.contains(to)) {
			states.add(to);
		}
		if (!transitions.containsKey(from)) {
			HashSet<State> neighbours = new HashSet<State>();
			Map<Object, HashSet<State>> transes = new HashMap<Object, HashSet<State>>();
			transes.put(transObj, neighbours);
			transitions.put(from, transes);
		}
		//NEEDED because of UNION where previousFinal already in transitions but no hashSet for new transObj
		if(transitions.containsKey(from) && !transitions.get(from).containsKey(transObj)){
			transitions.get(from).put(transObj, new HashSet<State>());
		}
		
		transitions.get(from).get(transObj).add(to);
	}

	public void printAutomataInfo() {
		System.out.println("Number of states: " + states.size());
		System.out.println("Initial state: " + initialState.stateID);

		System.out.print("States: { ");
		for (State s : states) {
			System.out.print(s.stateID + ", ");
		}
		System.out.println(" }");

		System.out.print("Final states: { ");
		for (State f : finalStates) {
			System.out.print(f.stateID + ", ");
		}
		System.out.println(" }");

		System.out.println("Transitions: ");
		for (State from : states) {
			if (transitions.containsKey(from)) {
				Map<Object, HashSet<State>> possibleTrans = transitions.get(from);
				for (Map.Entry<Object, HashSet<State>> entry : possibleTrans.entrySet()) {
					Object symbol = entry.getKey();
					HashSet<State> possibleToStates = entry.getValue();
					for (State to : possibleToStates) {
						System.out.println("\t" + from.toString() + " -> " + symbol.toString() + " -> " + to.toString());
					}
				}
			}
		}
	}

	public void printAutomata() {

	}
}
