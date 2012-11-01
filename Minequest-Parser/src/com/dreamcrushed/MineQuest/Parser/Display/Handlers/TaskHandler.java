package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dreamcrushed.MineQuest.Parser.Task;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class TaskHandler extends StringSpinnerHandler {

	protected int newId;
	protected boolean createTask = false;
	protected List<Integer> tids;

	public TaskHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		super(line, display, fDef, new ArrayList<String>(), "");
		strings = new String[display.parser.tasks.size() + 1];

		tids = new ArrayList<Integer>();
		int i = 0;
		Set<Integer> keys = display.parser.tasks.keySet();
		for (int tid : keys) {
			strings[i] = tid + " " + display.parser.tasks.get(tid).name;
			tids.add(tid);
			if (line.fields[index].equals(tid + ""))
				start = strings[i];
			i++;
		}
		newId = display.parser.allocateTaskId();
		strings[i] = "New Task_" + newId;
		tids.add(newId);
	}

	@Override
	protected void select(int selectedIndex) {
		createTask = (selectedIndex == strings.length - 1);
		line.fields[index] = tids.get(selectedIndex) + "";
	};

	@Override
	public void startSave() {
		if (createTask) {
			newId = display.parser.allocateTaskId();
		}
	}

	@Override
	public boolean save() {
		return true;
	}

	@Override
	public void completeSave() {
		if (createTask) {
			display.parser.tasks.put(newId, new Task(newId, false));
			display.parser.taskList.updateList();
		}
	}

}
