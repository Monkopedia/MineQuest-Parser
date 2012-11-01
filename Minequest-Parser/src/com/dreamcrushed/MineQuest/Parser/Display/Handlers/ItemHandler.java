package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.util.ArrayList;

import com.dreamcrushed.MineQuest.Parser.Material;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class ItemHandler extends StringSpinnerHandler {

	private ArrayList<Integer> tids;
	int id;

	public ItemHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		this(line, display, fDef, line.fields[display.index]);
	}

	public ItemHandler(QuestLine line, LineDisplay display, FieldDefinition fDef, String cid) {
		super(line, display, fDef, new ArrayList<String>(), "");
		strings = new String[Material.values().length];

		tids = new ArrayList<Integer>();
		int i = 0;
		try {
			id = Integer.parseInt(cid);
		} catch (NumberFormatException e) {
			id = 0;
		}
		for (Material mat : Material.values()) {
			int tid = mat.getId();
			strings[i] = mat.name() + " (" + tid + ")";
			tids.add(tid);
			if (tid == id)
				start = strings[i];
			i++;
		}
	}
	
	@Override
	protected void select(int selectedIndex) {
		id = tids.get(selectedIndex);
	}
	
	@Override
	public boolean save() {
		line.fields[index] = id + "";
		return true;
	}

}
