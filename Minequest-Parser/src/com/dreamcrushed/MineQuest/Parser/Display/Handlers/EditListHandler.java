package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.FieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class EditListHandler extends FieldHandler {
	
	private List<EditHandler> handlers;

	public EditListHandler(QuestLine line, LineDisplay display,
			FieldDefinition fDef) {
		super(line, display, fDef);
		String[] ids = line.fields[index].split(",");
		handlers = new ArrayList<EditHandler>();
		for (String id : ids) {
			handlers.add(new EditHandler(line, display, fDef, id));
		}
	}

	@Override
	public void createDisplay() {
		for (EditHandler handler : handlers) {
			handler.createDisplay();
			display.y += 25;
		}
		display.y -= 25;
		display.button("+", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				display.index = index;
				handlers.add(new EditHandler(line, display, fDef, "1"));
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
		line.fields[index] = handlers.get(0).id + "";
		for (int i = 1; i < handlers.size(); i++) {
			line.fields[index] += "," + handlers.get(i).id;
		}
	}

	@Override
	public void startSave() {
		
	}

	@Override
	public boolean save() {
		tempSave();
		return true;
	}

	@Override
	public void completeSave() {
		
	}

}
