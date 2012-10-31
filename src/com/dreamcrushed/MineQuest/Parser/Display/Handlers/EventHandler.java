package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.dreamcrushed.MineQuest.Parser.Task;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.EventLine;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class EventHandler extends TaskHandler {
	private JComboBox list;
	private Task current;
	int id;

	public EventHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		this(line, display, fDef, line.fields[display.index]);
	}

	public EventHandler(QuestLine line, LineDisplay display, FieldDefinition fDef, String eid) {
		super(line, display, fDef);
		String[] nStrings = new String[strings.length - 1];
		for (int i = 0; i < nStrings.length; i++) {
			nStrings[i] = strings[i];
		}
		strings = nStrings;
		tids.remove(tids.size() - 1);

		width = width / 2;
		try {
			id = Integer.parseInt(eid);
		} catch (NumberFormatException e) {
			id = -1;
		}
		current = null;
		for (int tid : tids) {
			for (EventLine l : display.parser.tasks.get(tid).events) {
				if (l.id == id) {
					current = display.parser.tasks.get(tid);
					break;
				}
			}
			if (current != null) break;
		}
		if (current == null) current = display.parser.tasks.get(0);
		start = strings[tids.indexOf(current.id)];
	}
	
	@Override
	public void createDisplay() {
		super.createDisplay();
		System.out.println("Creating id " + id);

		list = new JComboBox();
		list.setLocation(150 + width, display.y);
		list.setSize(width, 25);
		list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectEvent(list.getSelectedIndex());
			}
		});
		display.add(list);
		displayTask(current);
	}
	
	private void displayTask(Task current) {
		String[] es = new String[current.events.size()];
		int sindex = 0;
		int cid = id;
		list.setSelectedIndex(-1);
		list.removeAllItems();
		for (int i = 0; i < es.length; i++) {
			es[i] = current.events.get(i).name;
			if (current.events.get(i).id == cid) {
				sindex = i;
			}
			list.addItem(es[i]);
		}
		list.setSelectedIndex(sindex);
	}

	@Override
	protected void select(int selectedIndex) {
		current = display.parser.tasks.get(tids.get(selectedIndex));

		displayTask(current);
	}

	public void selectEvent(int selectedIndex) {
		try {
			id = current.events.get(selectedIndex).id;
		} catch (Exception e) {
			id = -1;
		}
	}

	@Override
	public void startSave() {

	}

	@Override
	public boolean save() {
		line.fields[index] = id + "";
		return true;
	}

	@Override
	public void completeSave() {

	}

}
