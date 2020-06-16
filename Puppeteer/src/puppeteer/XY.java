package puppeteer;

public class XY
{
	int x;
	int y;
	
	public XY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void add(XY xy) {
		this.x += xy.getX();
		this.y += xy.getY();
	}
	
	public void subtract(XY xy) {
		this.x -= xy.getX();
		this.y -= xy.getY();
	}
	
	public XY sumOf(XY p1, XY p2) {
		return new XY(p1.getX() + p2.getX(), p1.getY() + p2.getY()); 
	}
	
}
