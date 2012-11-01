package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.FieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class StringSpinnerHandler extends FieldHandler {

	protected String[] strings;
	protected String start;
	String current;
	protected int width;
	boolean set = true;

	public StringSpinnerHandler(QuestLine line, LineDisplay display,
			FieldDefinition fDef, List<String> names, String start) {
		super(line, display, fDef);
		this.strings = new String[names.size()];
		for (int i = 0; i < names.size(); i++) {
			strings[i] = names.get(i);
		}
		this.start = start;
		width = 500;
	}

	public StringSpinnerHandler(QuestLine line, LineDisplay display,
			FieldDefinition fDef, String[] strings, String start) {
		super(line, display, fDef);
		this.strings = strings;
		this.start = start;
		width = 500;
	}

	@Override
	public void createDisplay() {
		int ind = 0;
		for (int i = 0; i < strings.length; i++) {
			if (start.equals(strings[i]))
				ind = i;
		}
		final JComboBox list = new JComboBox(strings);
		list.setLocation(150, display.y);
		list.setSize(width, 25);
		list.setSelectedIndex(ind);
		list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				select(list.getSelectedIndex());
			}
		});
		display.add(list);
	}

	protected void select(int selectedIndex) {
		current = strings[selectedIndex];
		if (set) {
			display.setFields();
		}
	}
	
	@Override
	public void tempSave() {
		line.fields[index] = current;
	}

	@Override
	public void startSave() {
		
	}

	@Override
	public boolean save() {
		line.fields[index] = current;
		return true;
	}

	@Override
	public void completeSave() {
		
	}

}
