package com.dreamcrushed.MineQuest.Parser;

import java.util.HashMap;
import java.util.Map;

public enum Type {
	EVENTTYPE(1),
	INTEGER(1),
	FLOAT(1),
	STRING(1),
	TASK(1),
	EVENT(1),
	BOOL(1),
	ILOC(3),
	FLOC(3),
	FVEC(3),
	EVENTLIST(1),
	TASKLIST(1),
	LONGLOC(5),
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
	}
}
