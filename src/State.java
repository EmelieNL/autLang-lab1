
public class State {
	
	public static int currentStateID = 0;
	int stateID;

	
	public State(){
		this.stateID = currentStateID;
		currentStateID++;
	}

	public int getNrOfState(){
		return currentStateID;
	}
	
	public int getStateID(){
		return stateID;
	}
	
	@Override public String toString() {
		return new Integer(stateID).toString();
	}
}
