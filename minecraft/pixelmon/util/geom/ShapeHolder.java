package pixelmon.util.geom;

import java.awt.Shape;
import java.util.ArrayList;

/**
 * @author Jack
 *
 * @param <T>
 */
public class ShapeHolder<T extends Shape>{
	protected Object a, b;
	
	public ShapeHolder(){}
	
	public ShapeHolder(T shapeA, T shapeB){
		this.a = shapeA;
		this.b = shapeB;
	}
	
	public Object getA(){
		return this.a;
	}
	
	public Object getB(){
		return this.b;
	}
	
	public void setA(T shape){
		this.a = shape;
	}
	
	public void setA(ShapeHolder<T> holder){
		this.a = holder;
	}
	
	public void setB(T shape){
		this.b = shape;
	}
	
	public void setB(ShapeHolder<T> holder){
		this.b = holder;
	}
	
	public void replace(Object original, T replacement){
		replace(original,(Object) replacement);
	}
	
	public void replace(Object original, ShapeHolder<T> replacement){
		replace(original,(Object) replacement); 
	}
	
	
	/**
	 * protected method shared by the 2 public methods, as to not allow anything other than instances of <code>T</code> or <code>ShapeHolder</code> to be stored into <code>a</code> or <code>b</code>.
	 */
	protected void replace(Object original, Object replacement){
		if(a == original)
			a = replacement;
		if(b == original)
			b = replacement;
	}
	
	public boolean onlyHoldsHolders(){
		return a instanceof ShapeHolder && b instanceof ShapeHolder;
	}
	public ArrayList<T> getAll(){
		ArrayList<T> result = new ArrayList();
		if(!(a instanceof ShapeHolder))
			result.add((T) a);
		if(a instanceof ShapeHolder)
			result.addAll(((ShapeHolder)a).getAll());
		
		if(!(b instanceof ShapeHolder))
			result.add((T)b);
		if(b instanceof ShapeHolder)
			result.addAll(((ShapeHolder)b).getAll());
		return result;
	}
	
	public ArrayList<ShapeHolder<T>> getInnermostHolders(){
		ArrayList<ShapeHolder<T>> result = new ArrayList();
		if(a instanceof ShapeHolder){
			ShapeHolder holderA = (ShapeHolder) a;
			if(!holderA.onlyHoldsHolders())
				result.add(holderA);
			else
				result.addAll(holderA.getInnermostHolders());
		}
		
		if(b instanceof ShapeHolder){
			ShapeHolder holderB = (ShapeHolder) b;
			if(!holderB.onlyHoldsHolders())
				result.add(holderB);
			else
				result.addAll(holderB.getInnermostHolders());
		}
		else
			result.add(this);
		return result;
	}
	
}
