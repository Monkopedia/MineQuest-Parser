package com.dreamcrushed.MineQuest.Parser;

import java.util.HashMap;
import java.util.Map;

public enum Type {
	EVENTTYPE(1),
	INTEGER(1),
	INTEGERLIST(1),
	FLOAT(1),
	STRING(1),
	TASK(1),
	EVENT(1),
	BOOL(1),
	ILOC(3), // Needs Server Hook
	FLOC(3), // Needs Server Hook
	FVEC(3), // Needs Server Hook
	EVENTLIST(1), // Needs implementing
	TASKLIST(1), // Needs implementing - Low priority, not 
	LONGLOC(5), // Needs Server Hook
	QUESTFIELD(1),
	ITEM(1), // Needs implementing - Plus enum
	MITEM(1), // ^^
	ITEMLIST(1), // ^^
	REQUIREMENT(1), // Needs implementing - Plus requirements...
	REQUIREMENTLIST(1), // ^^
	REQUIREMENTTYPE(1), 
	EDIT(1), // Needs implementing 
	EDITLIST(1), // ^^
	EDITTYPE(1),
	TARGET(1), // Needs implementing 
	TARGETLIST(1), // ^^
	TARGETTYPE(1), 
	ENTITY(1), // Needs implementing - Plus enum
	ENTITYLIST(1), // ^^
	T(1), // Target
	IGNORE(1),
	COMPARE(1), // Needs implementing
	;
	
	final public int length;
	private final static Map<String, Type> types = new HashMap<String, Type>();
	
	private Type(int l) {
		this.length = l;
	}
	
	public static Type fromType(String name) {
		return types.get(name.toUpperCase());
	}

	public boolean goodValue(String str) {
		try {
			switch (this) {
			case INTEGER:
			case TASK:
			case EVENT:
			case ILOC:
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
