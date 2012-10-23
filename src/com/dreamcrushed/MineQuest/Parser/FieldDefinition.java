package com.dreamcrushed.MineQuest.Parser;

public class FieldDefinition {
	public Type[] fields;
	public String name;
	
	public FieldDefinition(Type field, String name) {
		this.fields = new Type[] { field };
		this.name = name;
	}

	public FieldDefinition(Type[] field, String name) {
		this.fields = field;
		this.name = name;
	}
}
