package com.dreamcrushed.MineQuest.Parser;

public enum Type {
	INTEGER(1),
	FLOAT(1),
	STRING(1),
	TASK(1),
	EVENT(1),
	BOOL(1),
	ILOC(3),
	FLOC(3),
	EVENTLIST(1),
	LONGLOC(5),
	;
	
	public int length;
	
	private Type(int l) {
		this.length = l;
	}

	public Object getValue(String str) {
		switch (this) {
		case INTEGER:
		case TASK:
		case EVENT:
			return Integer.parseInt(str);
		case FLOAT:
			return Float.parseFloat(str);
		case BOOL:
			return Boolean.parseBoolean(str);
		default:
			break;
		}
		return str;
	}
}
