package com.dreamcrushed.MineQuest.Parser.Display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Task;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

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
		button("Add Field", new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					QuestLine line = new QuestLine(parser.fields.get(0));
					parser.fields.add(line);
					show(null);
					new LineDisplay(line, parser, FieldDisplay.this, manager.getX(), manager.getY());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 300, 25);
		manager.display(this);
		repaint();
	}

}
