package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.FieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class IntList extends FieldHandler {
	
	private List<BasicControlledHandler> handlers;

	public IntList(QuestLine line, LineDisplay display,
			FieldDefinition fDef) {
		super(line, display, fDef);
		String[] ids = line.fields[index].split(",");
		handlers = new ArrayList<BasicControlledHandler>();
		for (String id : ids) {
			handlers.add(new BasicControlledHandler(line, display, fDef, id));
		}
	}

	@Override
	public void createDisplay() {
		for (BasicControlledHandler handler : handlers) {
			handler.createDisplay();
			display.y += 25;
		}
		display.y -= 25;
		display.button("+", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				display.index = index;
				handlers.add(new BasicControlledHandler(line, display, fDef, "1"));
				display.reDraw();
			}
		}, 650, 45, 25);
		display.button("-", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (handlers.size() > 1) {
					handlers.remove(handlers.size()-1);
					display.reDraw();
				}
			}
		}, 695, 45, 25);
	}
	
	@Override
	public void tempSave() {
		handlers.get(0).tempSave();
		line.fields[index] = handlers.get(0).value;
		for (int i = 1; i < handlers.size(); i++) {
			handlers.get(i).tempSave();
			line.fields[index] += "," + handlers.get(i).value;
		}
	}

	@Override
	public void startSave() {
		
	}

	@Override
	public boolean save() {
		if (!handlers.get(0).save()) return false;
		line.fields[index] = handlers.get(0).value;
		for (int i = 1; i < handlers.size(); i++) {
			if (!handlers.get(i).save()) return false;
			line.fields[index] += "," + handlers.get(i).value;
		}
		return true;
	}

	@Override
	public void completeSave() {
		
	}

}
