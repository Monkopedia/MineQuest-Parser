package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import javax.swing.JTextField;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.ErrorMessage;
import com.dreamcrushed.MineQuest.Parser.Display.FieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class BasicControlledHandler extends FieldHandler {

	private JTextField[] textFields;
	String value;

	public BasicControlledHandler(QuestLine line, LineDisplay display, FieldDefinition fDef, String value) {
		super(line, display, fDef);
		this.value = value;
	}

	@Override
	public void createDisplay() {
		int x = 500 / fDef.field.length;
		int ly = display.y;
		textFields = new JTextField[1];
		
		textFields[0] = display.textField(value, 0
				+ 150, ly, x, 25);
		display.y = ly;
	}
	
	@Override
	public void tempSave() {
		value = textFields[0].getText();
	}

	@Override
	public void startSave() {
		
	}

	@Override
	public boolean save() {
		for (int j = 0; j < 1; j++) {
			if (fDef.field.goodValue(textFields[j].getText())) {
				value = textFields[0].getText();
			} else {
				System.out.println(fDef.name + " must be "
						+ fDef.field.getFieldTypes() + "\n"
						+ textFields[j].getText() + " is not");
				new ErrorMessage(fDef.name + " must be "
						+ fDef.field.getFieldTypes(), "Error", 200,
						display, display.frame.getX() + display.frame.getWidth() / 2,
						display.frame.getY() + display.frame.getHeight() / 2);
				return false;
			}
		}
		return true;
	}

	@Override
	public void completeSave() {
		
	}

}
