

public class Concatenation extends RegExp {
    public RegExp r1, r2;
    public Concatenation(RegExp r1, RegExp r2) {
        this.r1 = r1;
        this.r2 = r2;
        hejFrom();
    }
    
    public RegExp getR1(){
    	return r1;
    }
    
    public RegExp getR2(){
    	return r2;
    }
}
