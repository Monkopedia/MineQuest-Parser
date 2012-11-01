package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.util.ArrayList;

import javax.swing.JTextField;

import com.dreamcrushed.MineQuest.Parser.Material;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class MultiItemHandler extends StringSpinnerHandler {

	private ArrayList<Integer> tids;
	int id;
	int quantity;
	private JTextField textField;

	public MultiItemHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		super(line, display, fDef, new ArrayList<String>(), "");
		strings = new String[Material.values().length];

		String[] split = line.fields[display.index].split(",");
		tids = new ArrayList<Integer>();
		int i = 0;
		try {
			id = Integer.parseInt(split[0]);
		} catch (NumberFormatException e) {
			id = 0;
		}
		try {
			quantity = Integer.parseInt(split[1]);
		} catch (Exception e) {
			quantity = 1;
		}
		for (Material mat : Material.values()) {
			int tid = mat.getId();
			strings[i] = mat.name() + " (" + tid + ")";
			tids.add(tid);
			if (tid == id)
				start = strings[i];
			i++;
		}
		width = width / 2;
	}
	
	@Override
	public void createDisplay() {
		super.createDisplay();
		textField = display.textField(quantity + "", width + 150, display.y, width, 25);	
	}
	
	@Override
	public void tempSave() {
		try {
			quantity = Integer.parseInt(textField.getText());
		} catch (Exception e) {
			quantity = 1;
		}
	}
	
	@Override
	protected void select(int selectedIndex) {
		id = tids.get(selectedIndex);
	}
	
	@Override
	public boolean save() {
		quantity = Integer.parseInt(textField.getText());
		line.fields[index] = id + "," + quantity;
		return true;
	}

}
