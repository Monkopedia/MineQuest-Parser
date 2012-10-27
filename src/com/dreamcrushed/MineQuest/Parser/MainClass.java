package com.dreamcrushed.MineQuest.Parser;

public class MainClass {
	public static void main(String[] args) {
		System.out.println("Test");
		DisplayManager.openParser(new QuestParser("test.quest"));
	}
}
