package grail.atomic;

public interface ScalableRectangle extends BoundedShape{
	@Override
	public int getX();
	@Override
	public int getY();
	@Override
	public int getWidth();
	@Override
	public int getHeight();
	public void scale(int percentage);
}
