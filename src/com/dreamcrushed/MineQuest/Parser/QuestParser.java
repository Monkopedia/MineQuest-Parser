package com.dreamcrushed.MineQuest.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestParser {
//	private List<Quester> questers;
//	private List<QuestTask> tasks;
//	private List<Event> events;
//	private Location spawn;
//	private Party party;
//	private World world;
//	private CanEdit[] edits;
	private double start_x;
	private double start_y;
	private double start_z;
	private double end_x;
	private double end_y;
	private double end_z;
//	private List<NPCQuester> npcs;
//	private List<Target> targets;
	private String name;
	private String filename;
	private boolean repeatable;
	private boolean reset;
	private String edit_message;
//	private AreaPreserver areaPreserver;
	private boolean no_mobs;
	
	protected Map<Integer, EventLine> events = new HashMap<Integer, EventLine>();
	protected Map<Integer, Task> tasks = new HashMap<Integer, Task>();
	protected List<EventDefinition> eventDefs = new ArrayList<EventDefinition>();
	protected List<QuestDefinition> questDefs = new ArrayList<QuestDefinition>();
	protected List<QuestLine> fields = new ArrayList<QuestLine>();
	
	public QuestParser(String filename) {
		questDefs.add(new QuestDefinition("QuestArea", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "FieldName"),
				new FieldDefinition(Type.ILOC, "Start"),
				new FieldDefinition(Type.ILOC, "End"),
		}));
		questDefs.add(new QuestDefinition("Instance", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "FieldName"),
				new FieldDefinition(Type.INTEGER, "MaxInstances"),
				new FieldDefinition(Type.STRING, "InstanceName"),
				new FieldDefinition(Type.STRING, "OriginalName"),
				new FieldDefinition(Type.STRING, "Type"),
		}));
		questDefs.add(new QuestDefinition("Spawn", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "FieldName"),
				new FieldDefinition(Type.FLOC, "Location"),
		}));

		eventDefs.add(new EventDefinition("AreaEvent", new FieldDefinition[] {
					new FieldDefinition(Type.STRING, "Event"),
					new FieldDefinition(Type.INTEGER, "ID"),
					new FieldDefinition(Type.STRING, "EventName"),
					new FieldDefinition(Type.INTEGER, "Delay"),
					new FieldDefinition(Type.TASK, "NextIndex"),
					new FieldDefinition(Type.FLOC, "Location"),
					new FieldDefinition(Type.FLOAT, "Radius"),
			}));
		eventDefs.add(new EventDefinition("BlockEvent", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.INTEGER, "Delay"),
				new FieldDefinition(Type.ILOC, "Location"),
				new FieldDefinition(Type.INTEGER, "Type"),
		}));
		eventDefs.add(new EventDefinition("EntitySpawnerNoMove", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.INTEGER, "Delay"),
				new FieldDefinition(Type.FLOC, "Location"),
				new FieldDefinition(Type.STRING, "Creature"),
				new FieldDefinition(Type.BOOL, "Super"),
		}));
		eventDefs.add(new EventDefinition("EntitySpawnerCompleteNMEvent", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.INTEGER, "Delay"),
				new FieldDefinition(Type.TASK, "NextIndex"),
				new FieldDefinition(Type.STRING, "Events"),
		}));
		eventDefs.add(new EventDefinition("ExperienceAdd", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.STRING, "All"),
				new FieldDefinition(Type.INTEGER, "Delay"),
				new FieldDefinition(Type.INTEGER, "Exp"),
				new FieldDefinition(Type.INTEGER, "Class Experience"),
		}));
		eventDefs.add(new EventDefinition("ExperienceAdd", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.STRING, "All"),
				new FieldDefinition(Type.INTEGER, "Delay"),
				new FieldDefinition(Type.INTEGER, "Exp"),
				new FieldDefinition(Type.INTEGER, "Class Experience"),
				new FieldDefinition(Type.INTEGER, "Cubes"),
		}));
		eventDefs.add(new EventDefinition("QuestEvent", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.INTEGER, "Delay"),
				new FieldDefinition(Type.TASK, "NextIndex"),
		}));
		eventDefs.add(new EventDefinition("SingleAreaEvent", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.INTEGER, "Delay"),
				new FieldDefinition(Type.TASK, "NextIndex"),
				new FieldDefinition(Type.FLOC, "Location"),
				new FieldDefinition(Type.FLOAT, "Radius"),
		}));
		eventDefs.add(new EventDefinition("ArrowEvent", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.INTEGER, "Delay"),
				new FieldDefinition(Type.FLOC, "Start"),
				new FieldDefinition(Type.FLOC, "Vector"),
		}));
		eventDefs.add(new EventDefinition("CanEdit", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.FLOC, "Location"),
				new FieldDefinition(Type.TASK, "NextIndex"),
		}));
		eventDefs.add(new EventDefinition("HealthEntitySpawn", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.INTEGER, "Delay"),
				new FieldDefinition(Type.TASK, "NextIndex"),
				new FieldDefinition(Type.FLOC, "Location"),
				new FieldDefinition(Type.STRING, "Creature"),
				new FieldDefinition(Type.INTEGER, "Health"),
				new FieldDefinition(Type.BOOL, "Super"),
		}));
		eventDefs.add(new EventDefinition("CompleteQuestEvent", new FieldDefinition[] {
				new FieldDefinition(Type.STRING, "Event"),
				new FieldDefinition(Type.INTEGER, "ID"),
				new FieldDefinition(Type.STRING, "EventName"),
				new FieldDefinition(Type.INTEGER, "Delay"),
		}));
		if (setupQuest(filename)) {
//			for (Quester quester : party.getQuesters()) {
//				quester.setQuest(this, world);
//			}

//			MineQuest.getEventQueue().addEvent(new QuestEvent(this, 100, 0));
		}
	}
	
//	public QuestParser(String file, int id) {
//		if (setupQuest(file)) {
//			if (id != -1) {
//				for (Quester quester : party.getQuesters()) {
//					quester.setQuest(this, world);
//				}
//				MineQuest.getEventQueue().addEvent(new QuestEvent(this, 100, id));
//			}
//		}
//	}
	
	protected boolean setupQuest(String filename) {
//		this.questers = party.getQuesters();
//		this.party = party;
//		tasks = new ArrayList<QuestTask>();
//		events = new ArrayList<Event>();
//		edits = new CanEdit[0];
//		npcs = new ArrayList<NPCQuester>();
//		targets = new ArrayList<Target>();
		this.name = filename;
//		this.filename = filename;
//		this.repeatable = false;
//		this.reset = true;
//		this.no_mobs = false;
//
//		edit_message = "A Mystical Force is keeping you from Modifying the world!";
		try {
			BufferedReader bis;
			if (filename.equals("MineQuest/main.script")) {
				bis = new BufferedReader(new FileReader(filename));
			} else {
				bis = new BufferedReader(new FileReader(filename + ".quest"));
			}
			
			String line = "";
			int number = 0;
//			if ((questers.size() > 0) && (questers.get(0).getPlayer() != null)) {
//				world = questers.get(0).getPlayer().getWorld();
//			} else if (filename.equals("MineQuest/main.script")) {
//				world = MineQuest.getSServer().getWorlds().get(0);
//			}
//			spawn = null;
//			start_x = 0;
//			areaPreserver = null;
			try {
				while ((line = bis.readLine()) != null) {
					number++;
					String split[] = line.split(":");
//					if (split == null) split = new String[] {line};
					if (split != null && split.length > 1) { 
						parseLine(split);
					}
				}
			} catch (Exception e) {
				System.out.println("Unable to load Quest Problem on Line " + number);
				System.out.println("  " + line);
				e.printStackTrace();
	
				try {
//					issueNextEvents(-1);
				} catch (Exception e1) {
					System.out.println("Unable to unload events properly");
				}
				return false;
			}
//
//			if (world == null) {
//				world = MineQuest.getSServer().getWorlds().get(0);
			eventDefs.add(new EventDefinition("EntitySpawnerCompleteNMEvent", new FieldDefinition[] {
					new FieldDefinition(Type.STRING, "Event"),
					new FieldDefinition(Type.INTEGER, "ID"),
					new FieldDefinition(Type.STRING, "EventName"),
					new FieldDefinition(Type.INTEGER, "All"),
					new FieldDefinition(Type.INTEGER, "Delay"),
					new FieldDefinition(Type.INTEGER, "Exp"),
					new FieldDefinition(Type.INTEGER, "Class Experience"),
			}));
//			}
//			if (spawn == null) {
//				spawn = world.getSpawnLocation();
//			}
//			
////			for (QuestTask task : tasks) {
////				MineQuest.log("Task: " + task.getId());
////				for (Event event : task.getEvents()) {
////					MineQuest.log(event.getName());
////				}
////			}
//			
//			if (no_mobs) {
//				MineQuest.noMobs(world);
//			}
//			
			return true;
		} catch (Exception e) {
//			MineQuest.log("Unable to load Quest - Generic Error");
//			e.printStackTrace();
//			try {
//				issueNextEvents(-1);
//			} catch (Exception e1) {
//				MineQuest.log("Unable to unload events properly");
//			}
//			
			return false;
		}
	}
	
	private void parseLine(String[] split) throws Exception {
		if (split[0].equals("Event")) {
			createEvent(split);
//			if (split[2].equals("R")) {
//				events.add(RelativeEvent.newRelative(split, this));
//			} else if (split[2].equals("T")) {
//				events.add(TargetedEvent.newTargeted(split, this));
//			} else if (split[2].equals("I")) {
//				events.add(IdleEvent.newIdleEvent(this, split));
//			} else {
//				createEvent(split);
//			}
//		} else if (split[0].equals("PartyMinMax")) {
//			int min = Integer.parseInt(split[1]);
//			int max = Integer.parseInt(split[2]);
//			if (party.getQuesters().size() > max) {
//				(new MessageEvent(10, party, "This quest cannot have more than " + max + " people in a party")).activate(null);
//				MineQuest.log("Party was the wrong size");
//				throw new Exception();
//			}
//			if (party.getQuesters().size() < min) {
//				(new MessageEvent(10, party, "This quest cannot have less than " + min + " people in a party")).activate(null);
//				MineQuest.log("Party was the wrong size");
//				throw new Exception();
//			}
		} else if (split[0].equals("Task")) {
			createTask(split, false);
		} else if (split[0].equals("RepeatingTask")) {
			createTask(split, true);
//		} else if (split[0].contains("World") || split[0].contains("Instance")) {
//			createWorld(split);
//		} else if (split[0].equals("Spawn")) {
//			double x = Double.parseDouble(split[1]);
//			double y = Double.parseDouble(split[2]);
//			double z = Double.parseDouble(split[3]);
//			this.spawn = new Location(world, x, y, z);
//		} else if (split[0].equals("QuestArea")) {
//			start_x = Double.parseDouble(split[1]);
//			start_y = Double.parseDouble(split[2]);
//			start_z = Double.parseDouble(split[3]);
//			end_x = Double.parseDouble(split[4]);
//			end_y = Double.parseDouble(split[5]);
//			end_z = Double.parseDouble(split[6]);
//		} else if (split[0].equals("NPC")) {
//			String name = split[1];
//			Location location = new Location(world,
//					Double.parseDouble(split[2]),
//					Double.parseDouble(split[3]),
//					Double.parseDouble(split[4]),
//					Float.parseFloat(split[5]),
//					Float.parseFloat(split[6]));
//			npcs.add(new NPCQuester(name, NPCMode.QUEST_INVULNERABLE, world, location));
//			MineQuest.addQuester(npcs.get(npcs.size() - 1));
//			MineQuest.getQuester(name).setQuest(this, world);
//		} else if (split[0].equals("NPCV")) {
//			String name = split[1];
//			Location location = new Location(world,
//					Double.parseDouble(split[2]),
//					Double.parseDouble(split[3]),
//					Double.parseDouble(split[4]),
//					Float.parseFloat(split[5]),
//					Float.parseFloat(split[6]));
//			npcs.add(new NPCQuester(name, NPCMode.QUEST_VULNERABLE, world, location));
//			MineQuest.addQuester(npcs.get(npcs.size() - 1));
//			MineQuest.getQuester(name).setQuest(this, world);
//		} else if (split[0].equals("Target")) {
//			targets.add(Target.newTarget(split, this));
//		} else if (split[0].equals("Edit")) {
//			addCanEdit(CanEdit.makeCanEdit(split, world));
//		} else if (split[0].equals("Name")) {
//			name = split[1];
//		} else if (split[0].equals("Repeatable")) {
//			repeatable = Boolean.parseBoolean(split[1]);
//		} else if (split[0].equals("Reset")) {
//			reset = Boolean.parseBoolean(split[1]);
//		} else if (split[0].equals("NoMobs")) {
//			no_mobs = Boolean.parseBoolean(split[1]);
//		} else if (split[0].equals("EditMessage")) {
//			edit_message = split[1];
//		} else if (split[0].equals("AreaPreserve")) {
//			start_x = Double.parseDouble(split[1]);
//			start_y = Double.parseDouble(split[2]);
//			start_z = Double.parseDouble(split[3]);
//			end_x = Double.parseDouble(split[4]);
//			end_y = Double.parseDouble(split[5]);
//			end_z = Double.parseDouble(split[6]);
//			Location start = new Location(world, start_x, start_y, start_z);
//			Location end = new Location(world, end_x, end_y, end_z);
//			areaPreserver = new AreaPreserver(world, start, end);
		} else {
			for (int i = 0; i < questDefs.size(); i++) {
				if (questDefs.get(i).name.equals(split[0])) {
					fields.add(new QuestLine(questDefs.get(i), split));
					return;
				}
			}
			System.out.println("Unhandled field: " + split[0]);
		}
	}

	private void createTask(String[] split, boolean b) throws Exception {
		int tid = Integer.parseInt(split[1]);
		Task task = getTask(tid);
		String[] ids = split[2].split(",");
		if (ids == null) {
			ids = new String[] { split[2] };
		}
		for (String sid : ids) {
			int id = Integer.parseInt(sid);
			if (events.get(id) != null) {
				task.add(events.get(id));
			} else {
				throw new Exception("Can't find event: " + id);
			}
		}
//		System.out.println("Unhandled task: " + split[1]);
	}

	public void createEvent(String line[]) throws Exception {
		int id = Integer.parseInt(line[1]);
		String type = line[2];
		EventLine newEvent;
		
		for (int i = 0; i < eventDefs.size(); i++) {
			if (eventDefs.get(i).name.equals(type)) {
				newEvent = new EventLine(eventDefs.get(i), line, id);
				if (eventDefs.get(i).hasTask()) {
					newEvent.setNextEvents(getTask(eventDefs.get(i).getTask(line)));
				}
				events.put(id, newEvent);
				return;
			}
		}
		
//		LivingEntity entities[] = new LivingEntity[questers.size()];
//		int i = 0;
//		for (Quester quester : questers) {
//			entities[i++] = quester.getPlayer();
//		}
//		
//		System.out.println("Event: " + type);
//		if (type.equals("AreaEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			int index = Integer.parseInt(line[4]);
//			
//			Location loc = new Location(world, Double.parseDouble(line[5]), Double.parseDouble(line[6]), Double.parseDouble(line[7]));
//			double radius = Double.parseDouble(line[8]);
//			new_event = new AreaEvent(this, delay, index, party, loc, radius);
//		} else if (type.equals("SingleAreaEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			int index = Integer.parseInt(line[4]);
//			
//			Location loc = new Location(world, Double.parseDouble(line[5]), Double.parseDouble(line[6]), Double.parseDouble(line[7]));
//			double radius = Double.parseDouble(line[8]);
//			new_event = new SingleAreaEvent(this, delay, index, party, loc, radius);
//		} else if (type.equals("parseDouble")) {
//			int delay = Integer.parseInt(line[3]);
//			double x = Double.parseDouble(line[4]);
//			double y = Double.parseDouble(line[5]);
//			double z = Double.parseDouble(line[6]);
//			
//			new_event = new CreateBoatEvent(delay, world, x, y, z);
//		} else if (type.equals("MessageEvent")) {
//			int delay = Integer.parseInt(line[3]);
//
//			new_event = new MessageEvent(delay, party, line[4]);
//		} else if (type.equals("BlockEvent")) {
//			int delay = Integer.parseInt(line[3]);
//
//			Block block = world.getBlockAt(Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]));
//			
//			int mat = Integer.parseInt(line[7]);
//			
//			new_event = new BlockEvent(delay, block, Material.getMaterial(mat));
//		} else if (type.equals("AdvancedBlockEvent")) {
//			int delay = Integer.parseInt(line[3]);
//
//			Block block = world.getBlockAt(Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]));
//			
//			int mat = Integer.parseInt(line[7]);
//			
//			new_event = new AdvancedBlockEvent(delay, block, Material.getMaterial(mat), Byte.parseByte(line[8]));
//		} else if (type.equals("QuestEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			int index = Integer.parseInt(line[4]);
//			
//			new_event = new QuestEvent(this, delay, index);
//		} else if (type.equals("EntitySpawnerEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			String creature = line[7];
//			Location location = new Location(world, Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6]));
//			boolean superm;
//			if (line[8].equals("f")) {
//				superm = false;
//			} else {
//				superm = true;
//			}
//			new_event = new EntitySpawnerEvent(delay, location, CreatureType.fromName(creature), superm);
//		} else if (type.equals("EntitySpawnerNoMove")) {
//			int delay = Integer.parseInt(line[3]);
//			String creature = line[7];
//			Location location = new Location(world, Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6]));
//			boolean superm;
//			if (line[8].equals("f")) {
//				superm = false;
//			} else {
//				superm = true;
//			}
//			new_event = new EntitySpawnerNoMove(delay, location, CreatureType.fromName(creature), superm);			
//		} else if (type.equals("EntitySpawnerCompleteNMEvent")) {
//			long delay = Integer.parseInt(line[3]);
//			int index = Integer.parseInt(line[4]);
//			i = 0;
//			EntitySpawnerEvent[] eventss = new EntitySpawnerEvent[line[5].split(",").length];
//			for (String s : line[5].split(",")) {
//				if (getEvent(Integer.parseInt(s)) == null) {
//					MineQuest.log("Cannot Find Event: " + Integer.parseInt(s));
//					throw new Exception();
//				}
//				eventss[i++] = (EntitySpawnerEvent)getEvent(Integer.parseInt(s));
//			}
//
//			new_event = new EntitySpawnerCompleteNMEvent(this, delay, index, eventss);
//		} else if (type.equals("EntitySpawnerCompleteEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			i = 0;
//			EntitySpawnerEvent[] eventss = new EntitySpawnerEvent[line[4].split(",").length];
//			for (String s : line[4].split(",")) {
//				if (getEvent(Integer.parseInt(s)) == null) {
//					MineQuest.log("Cannot Find Event: " + Integer.parseInt(s));
//					throw new Exception();
//				}
//				eventss[i++] = (EntitySpawnerEvent)getEvent(Integer.parseInt(s));
//			}
//
//			new_event = new EntitySpawnerCompleteEvent(delay, eventss);
//		} else if (type.equals("ExperienceAdd") || type.equals("ExperienceEvent")) {
//			long delay = Integer.parseInt(line[3]);
//			int exp = Integer.parseInt(line[5]);
//			int class_exp = Integer.parseInt(line[6]);
//			int cubes = 0;
//			if (line.length >= 8) {
//				cubes = Integer.parseInt(line[7]);
//			}
//			if (!line[4].equals("all")) {
//				MineQuest.log("Warning: Options other than all are not supported for ExperienceAdd");
//			}
//			
//			new_event = new ExperienceEvent(delay, party, exp, class_exp, cubes);
//		} else if (type.equals("LockWorldTime")) {
//			long delay = Integer.parseInt(line[3]);
//			long time = Integer.parseInt(line[4]);
//			long time_2 = Integer.parseInt(line[5]);
//			
//			new_event = new LockWorldTime(delay, world, time, time_2);
//		} else if (type.equals("BlockCDEvent")) {
//			long delay = Integer.parseInt(line[3]);
//			long second_delay = Integer.parseInt(line[4]);
//			Location location = new Location(world,
//					Integer.parseInt(line[5]), Integer.parseInt(line[6]), Integer.parseInt(line[7]));
//			Block block = world.getBlockAt(location);
//			int idd = Integer.parseInt(line[8]);
//			
//			new_event = new BlockCDEvent(delay, second_delay, block, Material.getMaterial(idd));
//		} else if (type.equals("BlockDCEvent")) {
//			long delay = Integer.parseInt(line[3]);
//			long second_delay = Integer.parseInt(line[4]);
//			Location location = new Location(world,
//					Integer.parseInt(line[5]), Integer.parseInt(line[6]), Integer.parseInt(line[7]));
//			Block block = world.getBlockAt(location);
//			int idd = Integer.parseInt(line[8]);
//			
//			new_event = new BlockDCEvent(delay, second_delay, block, Material.getMaterial(idd));
//		} else if (type.equals("ArrowEvent")) {
//			long delay = Integer.parseInt(line[3]);
//			Location start = new Location(world,
//					Double.parseDouble(line[4]),
//					Double.parseDouble(line[5]),
//					Double.parseDouble(line[6])
//					);
//			Vector vector = new Vector(
//					Double.parseDouble(line[7]),
//					Double.parseDouble(line[8]),
//					Double.parseDouble(line[9])
//					);
//			
//			new_event = new ArrowEvent(delay, start, vector);
//		} else if (type.equals("CanEdit")) {
//			addCanEdit(CanEdit.makeCanEdit(line, world));
//			
//			return;
//		} else if (type.equals("PartyHealthEvent")) {
//			long delay = Integer.parseInt(line[3]);
//			double percent = Double.parseDouble(line[4]);
//			
//			new_event = new PartyHealthEvent(delay, party, percent);
//		} else if (type.equals("CancelEvent")) {
//			long delay = Integer.parseInt(line[3]);
//			String[] split_nums = new String[] {line[4]};
//			if (line[4].contains(",")) {
//				split_nums = line[4].split(",");
//			}
//			int[] nums = new int[split_nums.length];
//			
//			for (i = 0; i < split_nums.length; i++) {
//				nums[i] = Integer.parseInt(split_nums[i]);
//			}
//			
//			new_event = new CancelEvent(delay, this, nums);
//		} else if (type.equals("CompleteQuestEvent")) {
//			long delay = Integer.parseInt(line[3]);
//			
//			new_event = new CompleteQuestEvent(delay, this, party);
//		} else if (type.equals("HealthEntitySpawn")) {
//			long delay;
//			int task;
//			Location location;
//			int health;
//			CreatureType creature;
//			try {
//				creature = CreatureType.fromName(line[8]);
//				delay = Integer.parseInt(line[3]);
//				task = Integer.parseInt(line[4]);
//				location = new Location(world,
//						Double.parseDouble(line[5]),
//						Double.parseDouble(line[6]),
//						Double.parseDouble(line[7])
//						);
//				health = Integer.parseInt(line[9]);
//			} catch (Exception e2) {
//				MineQuest.log("Problem getting HealthEntitySpawner Parameters");
//				throw new Exception();
//			}
//			boolean stay = true;
//			if (line[10].equals("f")) {
//				stay = false;
//			}
//			 
//			new_event = new HealthEntitySpawn(this, delay, task, location, creature, health, stay);
//		} else if (type.equals("ExplosionEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			double x = Double.parseDouble(line[4]);
//			double y = Double.parseDouble(line[5]);
//			double z = Double.parseDouble(line[6]);
//			double radius = Double.parseDouble(line[7]);
//			int damage = Integer.parseInt(line[8]);
//			
//			new_event = new ExplosionEvent(delay, world, x, y, z, (float)radius, damage);
//		} else if (type.equals("WeatherEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			boolean hasStorm = Boolean.parseBoolean(line[4]);
//			int duration = Integer.parseInt(line[5]);
//			
//			new_event = new WeatherEvent(delay, world, hasStorm, duration);
//		} else if (type.equals("LightningEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			double x = Double.parseDouble(line[4]);
//			double y = Double.parseDouble(line[5]);
//			double z = Double.parseDouble(line[6]);
//			Location location = new Location(world, x, y, z);
//			
//			new_event = new LightningEvent(delay, location);
//		} else if (type.equals("KillEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			int task = Integer.parseInt(line[4]);
//			String[] kill_names = line[5].split(",");
//			int[] kills = new int[line[6].split(",").length];
//			if (kill_names.length != kills.length) {
//				MineQuest.log("Error: Unmatched Length of Names and Quantities");
//				throw new Exception();
//			}
//			for (String kill_name : kill_names) {
//				if (CreatureType.fromName(kill_name) == null) {
//					MineQuest.log("Error: Invalid Creature Name " + kill_name);
//					throw new Exception();
//				}
//			}
//			i = 0;
//			for (String count : line[6].split(",")) {
//				kills[i++] = Integer.parseInt(count);
//			}
//			if (kill_names.length == 0) {
//				MineQuest.log("Error: Cannot Have 0 Targets");
//				throw new Exception();
//			}
//			
//			new_event = new PartyKill(this, delay, task, party, kill_names, kills);
//		} else if (type.equals("DestroyEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			int task = Integer.parseInt(line[4]);
//			String[] destroy_names = line[5].split(",");
//			int[] destroys = new int[line[6].split(",").length];
//			if (destroy_names.length != destroys.length) {
//				MineQuest.log("Error: Unmatched Length of Names and Quantities");
//				throw new Exception();
//			}
//
//			for (String destroy_name : destroy_names) {
//				if (Material.getMaterial(destroy_name) == null) {
//					MineQuest.log("Error: Invalid Creature Name " + destroy_name);
//					throw new Exception();
//				}
//			}
//
//			i = 0;
//			for (String count : line[6].split(",")) {
//				destroys[i++] = Integer.parseInt(count);
//			}
//
//			if (destroy_names.length == 0) {
//				MineQuest.log("Error: Cannot Have 0 Targets");
//				throw new Exception();
//			}
//
//			new_event = new PartyDestroy(this, delay, task, party, destroy_names, destroys);
//		} else if (type.equals("CanEditPattern")) {
//			int delay = Integer.parseInt(line[3]);
//			int index = Integer.parseInt(line[4]);
//			String edit_s[] = line[5].split(",");
//			String flag_s[] = line[6].split(",");
//			if (edit_s.length != flag_s.length) {
//				MineQuest.log("Lengths of parameters must be equal");
//				throw new Exception();
//			}
//			CanEdit[] editors = new CanEdit[edit_s.length];
//			boolean[] flags = new boolean[edit_s.length];
//			for (i = 0; i < edit_s.length; i++) {
//				editors[i] = getCanEdit(Integer.parseInt(edit_s[i]));
//				flags[i] = Boolean.parseBoolean(flag_s[i]);
//			}
//			
//			new_event = new CanEditPattern(this, delay, index, editors, flags);
//		} else if (type.equals("QuestAvailableEvent")) {
//			int delay = Integer.parseInt(line[3]);
//			String quest = line[4];
//			
//			new_event = new QuestAvailableEvent(delay, quest, party);
//		} else {
			System.out.println("Unhandled event: " + type);
//			MineQuest.log("Unknown Event Type: " + type);
//			throw new Exception();
//		}
//		
//		
////		MineQuest.log("Added " + events.get(events.size() - 1).getName());
//		new_event.setId(id);
//		events.add(new_event);
	}
//
//	public void issueNextEvents(int index) {
//		if (index == -1) {
//			for (Quester quester : party.getQuesters()) {
//				if (quester != null) {
//					quester.clearQuest(reset);
//				}
//			}
//			
//			for (QuestTask task : tasks) {
//				if (task != null) {
//					task.clearEvents();
//				}
//			}
//			
//			for (Event event : events) {
//				if (event != null) {
//					event.cancelEvent();
//				}
//			}
//			
//			for (NPCQuester quester : npcs) {
//				if ((quester != null) && (quester.getHealth() > 0)) {
//					MineQuest.remQuester(quester);
//					quester.damage(200000);
//				}
//			}
//			
//			MineQuest.remQuest(this);
//			
//			if (areaPreserver != null) {
//				areaPreserver.resetArea();
//			}
//			
//			if (no_mobs) {
//				MineQuest.yesMobs(world);
//			}
//			
//			return;
//		} else if (index <= -2) {
//			return;
//		}
//		
//		for (QuestTask task : tasks) {
//			if (task.getId() == index) {
//				task.issueEvents();
//			}
//		}
//	}

	private Task getTask(int id) {
		if (id < 0) return null;
		if (tasks.get(id) == null) {
			tasks.put(id, new Task(id, false));
		}
		
		return tasks.get(id);
	}
}
