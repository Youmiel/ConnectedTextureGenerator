package mask;

public final class Rectangle {
	static final Rectangle DEFAULT_RECT = new Rectangle(0,0,0,0);
	
	public double originX = 0;
	public double originY = 0;
	public double offsetX = 0;
	public double offsetY = 0;
	Rectangle(double x, double y, double offsetX, double offsetY){
		if(x<0 || y<0 || offsetX<0 || offsetY<0) 
			return;
		this.originX = x;
		this.originY = y;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public Rectangle scale(double mul) {
		return new Rectangle(this.originX*mul, this.originY*mul, this.offsetX*mul, this.offsetY*mul);
	}
	
	public boolean insideRect(double x, double y) {
		return this.originX < x && this.offsetX > (x - this.originX) &&
				this.originY < y && this.offsetY > (y - this.originY);
	}
}
