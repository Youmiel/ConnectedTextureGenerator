package mask;

import java.util.HashSet;
import java.util.Iterator;

public class Mask {
	HashSet<Rectangle> rects;
	int size = 1;
	
	Mask(){
		this.rects = new HashSet<Rectangle>();
	}
	Mask(int size){
		if(size > 0) 
			this.size = size;
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
				return true;
			}
		}
		return false;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
}
