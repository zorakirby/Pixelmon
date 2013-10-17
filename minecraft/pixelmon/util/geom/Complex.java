package pixelmon.util.geom;

public class Complex {
	double x, i;
	
	public Complex(double x, double i){
		this.x = x;
		this.i = i;
	}
	
	public double abs(){
		return Math.sqrt(x*x + i*i);
	}
	
	
	public Complex add(Complex that){
		return new Complex(this.x + that.x, this.i + that.i);
	}
	
	public Complex sub(Complex that){
		return new Complex(this.x - that.x, this.i - that.i);
	}
	
	public Complex mul(Complex that){
		double real = this.x * that.x;
		double imagine  = this.x * that.i + this.i * that.x;
		double nega = -1 * this.i * that.i;
		return new Complex(real+nega, imagine);
	}
	
	public Complex div(Complex that){
		return null;//for now
	}
	
	public Complex sqr(){
		return mul(this);
	}

}
