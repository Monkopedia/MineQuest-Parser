package com.dreamcrushed.MineQuest.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONObject {
	
	public List<String> names;
	public Map<String, String> fields;

	public JSONObject(String file) {
		names = new ArrayList<String>();
		fields = new HashMap<String, String>();
		
		String s = file.substring(file.indexOf('{')+1, file.indexOf('}'));
		String[] fs = s.split(",");
		for (String f : fs) {
			if (f.contains(":")) {
				String[] pieces = f.split(":");
				String name = toString(pieces[0]);
				names.add(name);
				fields.put(name, toString(pieces[1]));
				System.out.println(f + "");
			}
		}
	}

	private String toString(String string) {
		String s = string.substring(string.indexOf('"'));
		
		return s.substring(0, s.indexOf('"'));
	}

	public List<String> names() {
		return names;
	}

	public String getString(String name) {
		return fields.get(name);
	}

}
