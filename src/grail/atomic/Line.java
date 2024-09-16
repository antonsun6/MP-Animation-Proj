package grail.atomic;

public interface Line {
	public int getX();
    public void setX(int newVal);
    public int getY();
    public void setY(int newVal);
    public int getWidth();
    public void setWidth(int newVal);
    public int getHeight() ;
    public void setHeight(int newVal);
    public void rotate(int unit);
    public void move(int moveX, int moveY);
}
