

public class OneOrMore extends RegExp {
    public final RegExp r;
    public OneOrMore(RegExp r) {
        this.r = r;
        hejFrom();
    }
    
    public RegExp getRFromOneOrMore(){
    	return r;
    }
}
