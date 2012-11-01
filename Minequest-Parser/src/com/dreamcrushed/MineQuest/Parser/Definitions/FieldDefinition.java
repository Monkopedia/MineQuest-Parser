package com.dreamcrushed.MineQuest.Parser.Definitions;

import com.dreamcrushed.MineQuest.Parser.Type;

public class FieldDefinition {
	public Type field;
	public String name;
	public String description;
	
	public FieldDefinition(Type field, String name, String description) {
		this.field = field;
		this.name = name;
	}
	
	public boolean isLinkable() {
		return field == Type.TASK;
	}
}
