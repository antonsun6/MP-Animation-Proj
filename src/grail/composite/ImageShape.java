package grail.composite;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.atomic.ABoundedShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.misc.ThreadSupport;

@PropertyNames({"X", "Y", "Width", "Height", "imageFileName"})
@EditablePropertyNames({"X", "Y", "Height", "Width", "ImageFileName"})
@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public class ImageShape extends ABoundedShape implements Image{
	/*
	 * Making sure we have no magic numbers
	 */
	static final int
			INITIAL_X = 40,
			INITIAL_Y = 40,
			INITIAL_WIDTH = 200,
			INITIAL_HEIGHT = 100;
//	static final String INITIAL_IMAGE_FILE_NAME = "shuttle2.jpg";
	/*
	 * Not all of these variables will be used in every case
	 */

	/*
	 * upper left corner
	 */


	String imageFileName;

	/*
	 *  Constructor
	 */
	public ImageShape() {
		x = INITIAL_X;
		y = INITIAL_Y;
//		Icon icon = new ImageIcon(imageFileName);
//		setHeight(icon.getIconHeight());
//		setWidth(icon.getIconWidth());
//		System.out.println ("Image Height:" + icon.getIconHeight() +
//				" Image width:" + icon.getIconWidth());

	}

	/*
	 * 	As we will soon see, these properties describe a rectangular bounded box
     */

	/*
	 * You will uncomment these later to make this into an image.
	 */
	@Override
	public String getImageFileName() {return imageFileName;}
	@Override
	public void setImageFileName(String newVal) {

		Icon icon = new ImageIcon(newVal);
		setHeight(icon.getIconHeight());
		setWidth(icon.getIconWidth());
		System.out.println ("Image Height:" + icon.getIconHeight() +
				" Image width:" + icon.getIconWidth());
		imageFileName = newVal;}

	public static void main(String[] args) {
		final long timeBetween = 1000;
		Image image = new ImageShape();
		image.setImageFileName("images/arthur.jpg");
    	OEFrame anOEFrame = ObjectEditor.edit(image);
    	ThreadSupport.sleep(timeBetween);
    	anOEFrame.refresh();
	}
}