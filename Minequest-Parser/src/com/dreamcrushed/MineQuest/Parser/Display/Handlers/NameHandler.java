package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import javax.swing.JTextField;

import com.dreamcrushed.MineQuest.Parser.Display.FieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class NameHandler extends FieldHandler {

	private JTextField textField;

	public NameHandler(QuestLine line, LineDisplay display) {
		super(line, display, null);
	}

	@Override
	public void createDisplay() {
		textField = display.textField(line.getName(), 150, display.y, 500, 25);
		display.y -= 25;
		display.label("Name");
	}

	@Override
	public void startSave() {
		
	}

	@Override
	public boolean save() {
		line.name = textField.getText();
		return true;
	}

	@Override
	public void completeSave() {
		
	}

}
