package grail.composite;

import java.beans.PropertyChangeEvent;

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

@PropertyNames({"X", "Y", "Height", "Width", "Text"})
@EditablePropertyNames({"X", "Y", "Height", "Width", "Text"})
@StructurePattern(StructurePatternNames.STRING_PATTERN)
public class StringShape extends ABoundedShape implements Text{


	static final String INITIAL_TEXT = "";
	static final String INITIAL_IMAGE_FILE_NAME = "shuttle2.jpg";
//
//
    String text = INITIAL_TEXT;
    String imageFileName = INITIAL_IMAGE_FILE_NAME;

	public StringShape() {
		Icon icon = new ImageIcon(imageFileName);
		setHeight(icon.getIconHeight());
		setWidth(icon.getIconWidth());
	}
	@Override
	public String getText() {return text;}
	@Override
	public void setText(String newVal) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "text", getText(), newVal);
		System.out.println(propertyChangeEvent);
		text = newVal;
		notifyAllObservers(propertyChangeEvent);

		}

	public static void main(String[] args) {
		final long timeBetween = 1000;
		Text text = new StringShape();
		text.setX(40);
		text.setY(80);
		text.setText("");
    	OEFrame anOEFrame = ObjectEditor.edit(text);
    	ThreadSupport.sleep(timeBetween);
    	anOEFrame.refresh();
	}
}
