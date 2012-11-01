package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import javax.swing.JTextField;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.ErrorMessage;
import com.dreamcrushed.MineQuest.Parser.Display.FieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class BasicFieldHandler extends FieldHandler {

	private JTextField[] textFields;

	public BasicFieldHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		super(line, display, fDef);
	}

	@Override
	public void createDisplay() {
		int x = 500 / fDef.field.length;
		int ly = display.y;
		textFields = new JTextField[fDef.field.length];
		for (int i1 = 0; i1 < fDef.field.length; i1++) {
			textFields[i1] = display.textField(line.fields[index + i1], x * (i1)
					+ 150, ly, x, 25);
			display.y = ly;
		}
	}
	
	@Override
	public void tempSave() {
		for (int j = 0; j < fDef.field.length; j++) {
			line.fields[index + j] = textFields[j].getText();
		}
	}

	@Override
	public void startSave() {
		
	}

	@Override
	public boolean save() {
		for (int j = 0; j < fDef.field.length; j++) {
			if (fDef.field.goodValue(textFields[j].getText())) {
				line.fields[index + j] = textFields[j].getText();
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
