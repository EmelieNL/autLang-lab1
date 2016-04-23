

public class Litteral extends RegExp {
    public final Character c;
    public Litteral(Character c) {
        this.c = c;
        //hejFrom();
    }
    
    public Character getLitteral(){
    	return c;
    }
    
	@Override public String toString() {
		return c.toString();
	}
}
