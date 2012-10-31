package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Definitions.QuestDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.FieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class SpinnerHandler extends FieldHandler {

	private List<? extends QuestDefinition> questDefs;

	public SpinnerHandler(QuestLine line, LineDisplay display, FieldDefinition fDef, final List<? extends QuestDefinition> questDefs) {
		super(line, display, fDef);
		this.questDefs = questDefs;
	}

	@Override
	public void createDisplay() {
		final String[] strings = new String[questDefs.size()];
		int ind = 0;
		for (int i = 0; i < strings.length; i++) {
			strings[i] = questDefs.get(i).name;
			if (line.definition.name.equals(strings[i]))
				ind = i;
		}
		final int find = index;
		final JComboBox list = new JComboBox(strings);
		list.setLocation(150, display.y);
		list.setSize(500, 25);
		list.setSelectedIndex(ind);
		list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				line.fields[find] = strings[list.getSelectedIndex()];
				line.setDefinition(questDefs.get(list
						.getSelectedIndex()));
				display.setFields();
			}
		});
		display.add(list);
	}

	@Override
	public void startSave() {
		
	}

	@Override
	public boolean save() {
		return true;
	}

	@Override
	public void completeSave() {
		
	}

}
