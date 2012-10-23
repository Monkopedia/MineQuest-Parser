package com.dreamcrushed.MineQuest.Parser;

public enum Type {
	INTEGER,
	FLOAT,
	STRING;
	
	public Object getValue(String str) {
		switch (this) {
		case INTEGER:
			return Integer.parseInt(str);
		case FLOAT:
			return Float.parseFloat(str);
		}
		return str;
	}
}
