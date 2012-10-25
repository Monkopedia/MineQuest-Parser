package com.dreamcrushed.MineQuest.Parser;

public class FieldDefinition {
	public Type field;
	public String name;
	
	public FieldDefinition(Type field, String name) {
		this.field = field;
		this.name = name;
	}
	
	public boolean isLinkable() {
		return field == Type.TASK;
	}
}
