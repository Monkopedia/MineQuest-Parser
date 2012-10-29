package com.dreamcrushed.MineQuest.Parser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class FieldDisplay extends TaskDisplay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7201742770929707586L;

	public FieldDisplay(QuestParser parser, DisplayManager manager) {
		super(parser, manager);
	}

	@Override
	public void show(final Task task) {
		this.removeAll();
		y = 5;
		JLabel l = label("Quest Parameters: ", 0, y, 300, 25);
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		y = 0;
		y += 10;
		
		for (final QuestLine line : parser.fields) {
			button(line.getName(), new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						new LineDisplay(line, parser, FieldDisplay.this, manager.getX(), manager.getY());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Click");
				}
			}, 300, 25);
			System.out.println(line.getName());
		}
		manager.display(this);
		repaint();
	}

}
