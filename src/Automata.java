import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Automata {
	//states, Alphabet , TransistionFunctions, startState, endState  
	HashSet<State> states = new HashSet<State>();
	HashSet<State> finalStates = new HashSet<State>();
	State initialState;
	Map<State, Map<Object, HashSet<State>>> transitions;
	HashSet<Character> alphabet;
	
	public Automata(){
		transitions = new HashMap<State, Map<Object, HashSet<State>>>();
	}
	
	public void addStartState(State start){
		initialState = start;
		addState(start);
	}
	
	public void addFinalState(State finalState){
		finalStates.add(finalState);
		addState(finalState);
	}
	
	public void addState(State state){
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
	
	public void addTransition(Object transObj, State from, State to){
		if(!states.contains(to)){
			states.add(to);
		}
		if (transitions.get(from) == null){
			HashSet<State> neighbours = new HashSet<State>();
			Map<Object,HashSet<State>> transes = new HashMap<Object, HashSet<State>>();
			transes.put(transObj, neighbours);
			transitions.put(from, transes);
			
		}

		Map a= transitions.get(from);
		a.put(transObj, to);

	}
}
