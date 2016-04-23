
public class State {
	
	public static int currentStateID = 0;
	int stateID;

	
	public State(){
		this.stateID = currentStateID;
		stateID++;
	}

	public int getNrOfState(){
		return currentStateID;
	}
	
	public int getStateID(){
		return stateID;
	}
}
