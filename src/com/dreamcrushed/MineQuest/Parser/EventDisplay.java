package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class EventDisplay extends BasePage {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected JFrame frame;
	private int index;
	protected JTextField[] textFields;
	private EventLine line;
	private QuestParser parser;
	private TaskDisplay display;
	private EventLine orig;

	public EventDisplay(final EventLine orig, final QuestParser parser, final TaskDisplay display, int xL, int yL) throws Exception {
		this.line = new EventLine(orig);
		this.orig = orig;
		this.parser = parser;
		this.display = display;
		this.frame = new JFrame("Event Config");

		frame.setSize(800, 600);
		setLayout(null);
		setSize(800, 600);
		
		setFields();

		pack();
        frame.setContentPane(this);
        frame.setLocation(xL, yL);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	
	public void setFields() {
		removeAll();

		y = 25;
		textFields = new JTextField[line.fields.length - 1];

		int x = 500;
		this.index = 0;
		textFields[index] = textField(line.getName(), 150, y, x, 25);
		index++;
		y -= 25;
		label("Name");

		for (int i = 2; i < line.eDefinition.fields.length; i++) {
			showField(line.eDefinition.fields[i]);
		}

		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				index = 0;
				line.name = textFields[0].getText();
				index++;
				for (int i = 2; i < line.eDefinition.fields.length; i++) {
					FieldDefinition fDef = line.eDefinition.fields[i];
					for (int j = 0; j < fDef.field.length; j++) {
						if (fDef.field.goodValue(textFields[index].getText())) {
							line.fields[index+1] = textFields[index].getText();
							index++;
						} else {
							System.out.println(fDef.name + " must be " + fDef.field.getFieldTypes() + "\n" + textFields[index].getText() + " is not");
							new ErrorMessage(fDef.name + " must be " + fDef.field.getFieldTypes(), 200, EventDisplay.this, getX() + getWidth()/2, getY() + getHeight()/2);
							return;
						}
					}
				}
				orig.copy(line);
				display.show(display.currentTask);
				frame.dispose();
			}
		});
		button.setLocation(0, y);
		button.setSize(650, 25);
		add(button);

		frame.invalidate();
		revalidate();
		repaint();
	}

	private void showField(FieldDefinition fDef) {
		int ly = y;
		int x = 500 / fDef.field.length;

		if ((fDef.field != Type.EVENTTYPE)) {
			for (int i = 0; i < fDef.field.length; i++) {
				textFields[index] = textField(line.fields[index+1], x * (i) + 150, ly, x, 25);
				index++;
				y = ly;
			}
		} else {
			final String[] strings = new String[parser.eventDefs.size()];
			int ind = 0;
			for (int i = 0; i < strings.length; i++) {
				strings[i] = parser.eventDefs.get(i).name;
				if (line.fields[index + 1].equals(strings[i])) ind = i;
			}
			final int find = index + 1;
			textFields[index] = new JTextField(line.fields[index+1]);
			index++;
			final JComboBox list = new JComboBox(strings);
			list.setLocation(150, ly);
			list.setSize(500, 25);
			list.setSelectedIndex(ind);
			list.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					line.fields[find] = strings[list.getSelectedIndex()];
					line.setDefinition(parser.eventDefs.get(list.getSelectedIndex()), parser);
					setFields();
				}
			});
			add(list);
		}
		label(fDef.name + ":");
	}

	protected void pack() {
        //Create and set up the content pane.
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        this.setSize(500, 300);
        this.setPreferredSize(this.getSize());
        frame.setContentPane(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
}
