package com.dreamcrushed.MineQuest.Parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.FieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.BasicFieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.CompareHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.EditHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.EditListHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.EditTypeHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.EntityHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.EntityListHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.EventHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.EventListHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.EventTypeHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.IntList;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.ItemHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.ItemListHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.MultiItemHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.QuestFieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.RequirementHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.RequirementListHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.RequirementTypeHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.TargetHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.TargetListHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.TargetTypeHandler;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.TaskHandler;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public enum Type {
	TASK(1, TaskHandler.class),
	EVENTTYPE(1, EventTypeHandler.class),
	EVENT(1, EventHandler.class),
	EVENTLIST(1, EventListHandler.class),
	REQUIREMENT(1, RequirementHandler.class),
	REQUIREMENTLIST(1, RequirementListHandler.class),
	REQUIREMENTTYPE(1, RequirementTypeHandler.class), 
	EDIT(1, EditHandler.class),
	EDITLIST(1, EditListHandler.class),
	EDITTYPE(1, EditTypeHandler.class),
	TARGET(1, TargetHandler.class),
	TARGETLIST(1, TargetListHandler.class),
	TARGETTYPE(1, TargetTypeHandler.class), 
	ENTITY(1, EntityHandler.class),
	ENTITYLIST(1, EntityListHandler.class),
	INTEGER(1),
	INTEGERLIST(1, IntList.class),
	FLOAT(1),
	STRING(1),
	BOOL(1),
	ILOC(3),
	FLOC(3),
	FVEC(3),
//	TASKLIST(1), // Needs implementing - Low priority, not used yet
	LONGLOC(5),
	QUESTFIELD(1, QuestFieldHandler.class),
	ITEM(1, ItemHandler.class), 
	MITEM(1, MultiItemHandler.class), 
	ITEMLIST(1, ItemListHandler.class),
	T(1, null), // Target ignore
	IGNORE(1, null),
	COMPARE(1, CompareHandler.class),
	;
	
	final public int length;
	final public Class<? extends FieldHandler> handler;
	private final static Map<String, Type> types = new HashMap<String, Type>();
	
	private Type(int l) {
		this.length = l;
		handler = BasicFieldHandler.class;
	}

	private Type(int l, Class<? extends FieldHandler> handler) {
		this.length = l;
		this.handler = handler;
	}
	
	public static Type fromType(String name) {
		return types.get(name.toUpperCase());
	}
	
	public FieldHandler createHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		try {
			Constructor<? extends FieldHandler> constructor = handler.getConstructor(QuestLine.class, LineDisplay.class, FieldDefinition.class);
			
			return constructor.newInstance(line, display, fDef);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("Class: " + handler.getName());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean goodValue(String str) {
		try {
			switch (this) {
			case INTEGER:
			case TASK:
			case EVENT:
			case ILOC:
			case INTEGERLIST:
				Integer.parseInt(str);
				break;
			case FLOAT:
			case FLOC:
			case FVEC:
			case LONGLOC:
				Float.parseFloat(str);
				break;
			case BOOL:
				Boolean.parseBoolean(str);
				break;
			default:
				break;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getFieldTypes() {
		switch (this) {
		case INTEGER:
			return "An Integer";
		case TASK:
		case EVENT:
		case ILOC:
		case INTEGERLIST:
			return "Integers";
		case FLOAT:
			return "A Float";
		case FLOC:
		case FVEC:
		case LONGLOC:
			return "Floats";
		case BOOL:
			return "a Boolean";
		default:
			break;
		}
		return "String";
	}
	
	static {
		for (Type type : values()) {
			types.put(type + "", type);
		}
		types.put("INT", INTEGER);
		types.put("INTLIST", INTEGERLIST);
		types.put("DATE", IGNORE);
	}
}
