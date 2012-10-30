package com.dreamcrushed.MineQuest.Parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
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
					System.out.println("Parsing " + file);
			        JSONObject json = new JSONObject(file);
			        handleJSON(json);
					file = is.readLine();
				}
				line = is.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleJSON(JSONObject json) {
		List<String> names = json.names();
		FieldDefinition[] fDefs = new FieldDefinition[names.size()];
		
		for (int i = 0; i < fDefs.length; i++) {
			String name = names.get(i);
			System.out.println("Setting Field " + i + " " + name);
			fDefs[i] = new FieldDefinition(Type.fromType(json.getString(name)), name, "");
		}

		switch (fDefs[0].field) {
		case EVENTTYPE:
			eventDefs.add(new EventDefinition(fDefs[2].name, fDefs));
			break;
		case REQUIREMENTTYPE:
			requireDefs.add(new QuestDefinition(fDefs[2].name, fDefs));
			break;
		case EDITTYPE:
			editDefs.add(new QuestDefinition(fDefs[2].name, fDefs));
			break;
		case TARGETTYPE:
			targetDefs.add(new QuestDefinition(fDefs[2].name, fDefs));
			break;
		default:
			questDefs.add(new QuestDefinition(fDefs[0].name, fDefs));
			break;
		}
		System.out.println("Adding " + fDefs[0].name + " " + fDefs[2].name);
	}
}
