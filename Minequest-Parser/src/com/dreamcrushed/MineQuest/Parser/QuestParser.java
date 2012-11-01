package com.dreamcrushed.MineQuest.Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dreamcrushed.MineQuest.Parser.Lines.EditLine;
import com.dreamcrushed.MineQuest.Parser.Lines.EventLine;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;
import com.dreamcrushed.MineQuest.Parser.Lines.RequirementLine;
import com.dreamcrushed.MineQuest.Parser.Lines.TargetLine;

public class QuestParser {
	private final static String progName = "MineQuest Quest Writer";
	private final static String version = "v0.1";
	protected String name;
	protected String filename;
	protected String last = "";
	
	public Map<Integer, EventLine> events = new HashMap<Integer, EventLine>();
	public Map<Integer, Task> tasks = new HashMap<Integer, Task>();
	public QuestDefinitionParser defs;
	public List<QuestLine> fields = new ArrayList<QuestLine>();
	public TaskList taskList;
	public Map<Integer, RequirementLine> requirements = new HashMap<Integer, RequirementLine>();
	public Map<Integer, EditLine> edits = new HashMap<Integer, EditLine>();
	public Map<Integer, TargetLine> targets = new HashMap<Integer, TargetLine>();
	
	public QuestParser(String filename) {
		defs = new QuestDefinitionParser("defs.json");
		if (filename != null) {
			setupQuest(filename);
		} else {
			Task task = getTask(0);
			task.name = "Start";
		}
	}
	
	protected boolean setupQuest(String filename) {
		this.name = filename;

		try {
			BufferedReader bis = new BufferedReader(new FileReader(filename));
			
			String line = "";
			int number = 0;

			try {
				while ((line = bis.readLine()) != null) {
					number++;
					if (!line.startsWith("#")) {
						String split[] = line.split(":");
						if (split != null && split.length > 1) { 
							parseLine(split);
						}
					}
					last = line;
				}
			} catch (Exception e) {
				System.out.println("Unable to load Quest Problem on Line " + number);
				System.out.println("  " + line);
				e.printStackTrace();

				bis.close();
				return false;
			}

			bis.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private void parseLine(String[] split) throws Exception {
		if (split[0].equals("Event")) {
			createEvent(split);
		} else if (split[0].equals("Task")) {
			createTask(split, false);
		} else if (split[0].equals("RepeatingTask")) {
			createTask(split, true);
		} else if (split[0].equals("Requirement")) {
			createRequirement(split);
		} else if (split[0].equals("Edit")) {
			createEdit(split);
		} else if (split[0].equals("Target")) {
			createTarget(split);
		} else {
			for (int i = 0; i < defs.questDefs.size(); i++) {
				if (defs.questDefs.get(i).name.equals(split[0])) {
					QuestLine ql = new QuestLine(defs.questDefs.get(i), split);
					if (last.startsWith("#")) {
						ql.name = last.replaceAll("#", "");
					}
					fields.add(ql);
					return;
				}
			}
			System.out.println("Unhandled field: " + split[0]);
		}
	}

	private void createTarget(String[] line) throws Exception {
		int id = Integer.parseInt(line[1]);
		String type = line[2];
		TargetLine newTarget;
		
		for (int i = 0; i < defs.targetDefs.size(); i++) {
			if (defs.targetDefs.get(i).name.replaceAll(" ", "").equals(type)) {
				newTarget = new TargetLine(defs.targetDefs.get(i), line, id);
				targets.put(id, newTarget);
				if (last.startsWith("#")) {
					newTarget.name = last.replaceAll("#", "");
				}
				return;
			}
		}
		System.out.println("Unhandled target: " + type);
	}

	private void createEdit(String[] line) throws Exception {
		int id = Integer.parseInt(line[1]);
		String type = line[2];
		EditLine newEdit;
		
		for (int i = 0; i < defs.editDefs.size(); i++) {
			if (defs.editDefs.get(i).name.replaceAll(" ", "").equals(type)) {
				newEdit = new EditLine(defs.editDefs.get(i), line, id);
				edits.put(id, newEdit);
				if (last.startsWith("#")) {
					newEdit.name = last.replaceAll("#", "");
				}
				return;
			}
		}
		System.out.println("Unhandled edit: " + type);
	}

	private void createRequirement(String[] line) throws Exception {
		int id = Integer.parseInt(line[1]);
		String type = line[2];
		RequirementLine newRequirement;
		
		for (int i = 0; i < defs.requireDefs.size(); i++) {
			if (defs.requireDefs.get(i).name.replaceAll(" ", "").equals(type)) {
				newRequirement = new RequirementLine(defs.requireDefs.get(i), line, id);
				requirements.put(id, newRequirement);
				if (last.startsWith("#")) {
					newRequirement.name = last.replaceAll("#", "");
				}
				return;
			}
		}
		System.out.println("Unhandled requirement: " + type);
	}

	private void createTask(String[] split, boolean b) throws Exception {
		int tid = Integer.parseInt(split[1]);
		Task task = getTask(tid);
		String[] ids = split[2].split(",");
		if (ids == null) {
			ids = new String[] { split[2] };
		}
		task.repeating = b;
		for (String sid : ids) {
			int id = Integer.parseInt(sid);
			if (events.get(id) != null) {
				task.add(events.get(id));
			} else {
				throw new Exception("Can't find event: " + id);
			}
		}
		if (last.startsWith("#")) {
			task.name = last.replaceAll("#", "");
			System.out.println("Found Task Name: " + task.name);
		}
	}

	public void createEvent(String line[]) throws Exception {
		int id = Integer.parseInt(line[1]);
		String type = line[2];
		EventLine newEvent;
		
		for (int i = 0; i < defs.eventDefs.size(); i++) {
			if (defs.eventDefs.get(i).name.replaceAll(" ", "").equals(type)) {
				newEvent = new EventLine(defs.eventDefs.get(i), line, id);
				if (defs.eventDefs.get(i).hasTask()) {
					newEvent.setNextEvents(getTask(defs.eventDefs.get(i).getTask(line)));
				}
				System.out.println("Adding event " + id);
				events.put(id, newEvent);
				if (last.startsWith("#")) {
					newEvent.name = last.replaceAll("#", "");
				}
				return;
			}
		}
		System.out.println("Unhandled event: " + type);
	}

	private Task getTask(int id) {
		if (id < 0) return null;
		if (tasks.get(id) == null) {
			tasks.put(id, new Task(id, false));
		}
		
		return tasks.get(id);
	}
	
	public void saveQuest(String filename) {
		PrintStream ps;
		try {
			ps = new PrintStream(filename);
			
			ps.println("#Quest Created with " + QuestParser.progName + " " + QuestParser.version);
			
			ps.println("\n\n#Quest Parameters");
			for (QuestLine ql : fields) {
				ql.print(ps);
			}
			
			ps.println("\n\n#Quest Requirements");
			for (QuestLine ql : requirements.values()) {
				ql.print(ps);
			}
			
			ps.println("\n\n#Quest Edits");
			for (QuestLine ql : edits.values()) {
				ql.print(ps);
			}
			
			ps.println("\n\n#Quest Targets");
			for (QuestLine ql : targets.values()) {
				ql.print(ps);
			}

			ps.println("\n\n#Quest Events");
			for (QuestLine ql : events.values()) {
				ql.print(ps);
			}

			ps.println("\n\n#Quest Tasks");
			for (Task task : tasks.values()) {
				task.print(ps);
			}
			
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void removeEvent(EventLine line) {
		events.remove(line);
		for (int tid : tasks.keySet()) {
			if (tasks.get(tid).events.contains(line)) {
				tasks.get(tid).events.remove(line);
			}
		}
	}

	public void removeField(QuestLine line) {
		fields.remove(line);
	}

	public int allocateEventId() {
		int maxId = 0;
		for (int id : events.keySet()) {
			if (id > maxId) maxId = id;
		}
		return maxId + 1;
	}

	public int allocateTaskId() {
		int maxId = 0;
		for (int id : tasks.keySet()) {
			if (id > maxId) maxId = id;
		}
		return maxId + 1;
	}

	public int allocateRequirementId() {
		int maxId = 0;
		for (int id : requirements.keySet()) {
			if (id > maxId) maxId = id;
		}
		return maxId + 1;
	}

	public int allocateEditId() {
		int maxId = 0;
		for (int id : edits.keySet()) {
			if (id > maxId) maxId = id;
		}
		return maxId + 1;
	}

	public int allocateTargetId() {
		int maxId = 0;
		for (int id : targets.keySet()) {
			if (id > maxId) maxId = id;
		}
		return maxId + 1;
	}
}
