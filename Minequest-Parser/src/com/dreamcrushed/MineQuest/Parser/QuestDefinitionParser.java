package com.dreamcrushed.MineQuest.Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dreamcrushed.MineQuest.Parser.Definitions.EventDefinition;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Definitions.QuestDefinition;

public class QuestDefinitionParser {
	public List<EventDefinition> eventDefs = new ArrayList<EventDefinition>();
	public List<QuestDefinition> questDefs = new ArrayList<QuestDefinition>();
	public List<QuestDefinition> editDefs = new ArrayList<QuestDefinition>();
	public List<QuestDefinition> requireDefs = new ArrayList<QuestDefinition>();
	public List<QuestDefinition> targetDefs = new ArrayList<QuestDefinition>();
	
	public QuestDefinitionParser(String filename) {
		BufferedReader is;
		try {
			is = new BufferedReader(new FileReader(filename));
			String file = is.readLine();
			String line = is.readLine();
			while (line != null) {
				file = file + "\n" + line;
				if (line.contains("}")) {
			        JSONObject json = new JSONObject(file);
			        handleJSON(json);
					file = is.readLine();
				}
				line = is.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleJSON(JSONObject json) {
		List<String> names = json.names();
		FieldDefinition[] fDefs = new FieldDefinition[names.size()];
		
		for (int i = 0; i < fDefs.length; i++) {
			String name = names.get(i);
			fDefs[i] = new FieldDefinition(Type.fromType(json.getString(name)), name, "");
			if (fDefs[i].field == null) {
				System.out.println("Couldn't find type: " + json.getString(name));
			}
		}

		if (fDefs.length > 2) {
			switch (fDefs[2].field) {
			case EVENTTYPE:
				if ((fDefs.length > 3) && (fDefs[3].field == Type.T)) {
					eventDefs.add(new EventDefinition(fDefs[2].name + " (T)", fDefs));
				} else {
					eventDefs.add(new EventDefinition(fDefs[2].name, fDefs));
				}
				fDefs[2].name = "Event Type";
				break;
			case REQUIREMENTTYPE:
				requireDefs.add(new QuestDefinition(fDefs[2].name, fDefs));
				fDefs[2].name = "Requirement Type";
				break;
			case EDITTYPE:
				editDefs.add(new QuestDefinition(fDefs[2].name, fDefs));
				fDefs[2].name = "Edit Type";
				break;
			case TARGETTYPE:
				targetDefs.add(new QuestDefinition(fDefs[2].name, fDefs));
				fDefs[2].name = "Target Type";
				break;
			default:
				questDefs.add(new QuestDefinition(fDefs[0].name, fDefs));
				fDefs[0].name = "Field Type";
				break;
			}
		} else {
			questDefs.add(new QuestDefinition(fDefs[0].name, fDefs));
		}
	}
}
