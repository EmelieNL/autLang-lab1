

public class Closure extends RegExp {
    public final RegExp r;
    public Closure(RegExp r) {
        this.r = r;
        hejFrom();
    }
    
    public RegExp getRFromClosure(){
    	return r;
    }
}
