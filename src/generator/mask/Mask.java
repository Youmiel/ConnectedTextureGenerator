package generator.mask;

import java.util.HashSet;
import java.util.Iterator;

public class Mask {
	HashSet<Rectangle> rects;
	boolean invert = false;
	int size = 1;
	
	
	Mask(){
		this.rects = new HashSet<Rectangle>();
	}
	Mask(int size){
		if(size > 0) 
			this.size = size;
		this.rects = new HashSet<Rectangle>();
	}
	Mask(Mask mask){
		this.rects = mask.getRects();
		this.size = mask.size;
	}
	
	@SuppressWarnings("unchecked")
	protected HashSet<Rectangle> getRects(){
		return (HashSet<Rectangle>) rects.clone();
	}
	
	public Mask addRect(Rectangle r) {
		this.rects.add(r.scale(1/size));
		return this;
	}
	
	public Mask addRect(double x, double y, double offsetX, double offsetY) {
		this.rects.add(new Rectangle(x/size, y/size, offsetX/size, offsetY/size));
		return this;
	}
	
	public boolean isMask(double x, double y) {
		Iterator<Rectangle> i = this.rects.iterator();
		while(i.hasNext()) {
			if(i.next().insideRect(x,y)) { 
				return (true ^ this.invert);
			}
		}
		return (false ^ this.invert);
	}
	
	public Mask setSize(int size) {
		this.size = size;
		return this;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public Mask setInvert(boolean b) {
		this.invert = b;
		return this;
	}
	
	public boolean isInvert() {
		return this.invert;		
	}

}
