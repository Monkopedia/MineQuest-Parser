package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	private QuestLine line;
	private QuestParser parser;
	private TaskDisplay display;
	private QuestLine orig;
	private boolean createTask;

	public EventDisplay(final QuestLine orig, final QuestParser parser, final TaskDisplay display, int xL, int yL) throws Exception {
		this.line = new QuestLine(orig);
		this.orig = orig;
		this.parser = parser;
		this.display = display;
		this.frame = new JFrame("Event Config");
		createTask = false;

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

		for (int i = 2; i < line.definition.fields.length; i++) {
			showField(line.definition.fields[i]);
		}

		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				index = 0;
				line.name = textFields[0].getText();
				index++;
				for (int i = 2; i < line.definition.fields.length; i++) {
					FieldDefinition fDef = line.definition.fields[i];
					for (int j = 0; j < fDef.field.length; j++) {
						if (fDef.field.goodValue(textFields[index].getText())) {
							line.fields[index+1] = textFields[index].getText();
							System.out.println("Setting " + fDef.name + " to " + line.fields[index+1]);
							index++;
						} else {
							System.out.println(fDef.name + " must be " + fDef.field.getFieldTypes() + "\n" + textFields[index].getText() + " is not");
							new ErrorMessage(fDef.name + " must be " + fDef.field.getFieldTypes(), 200, EventDisplay.this, getX() + getWidth()/2, getY() + getHeight()/2);
							return;
						}
					}
				}
				if (createTask) {
					
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

		if ((fDef.field != Type.EVENTTYPE) && (fDef.field != Type.TASK)) {
			for (int i = 0; i < fDef.field.length; i++) {
				textFields[index] = textField(line.fields[index+1], x * (i) + 150, ly, x, 25);
				index++;
				y = ly;
			}
		} else if (fDef.field == Type.EVENTTYPE) {
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
					textFields[find - 1].setText(line.fields[find]);
					((EventLine)line).setDefinition(parser.eventDefs.get(list.getSelectedIndex()), parser);
					setFields();
				}
			});
			add(list);
		} else {
			final String[] strings = new String[parser.tasks.size()];
			int ind = 0;
			Set<Integer> keys = parser.tasks.keySet();
			final List<Integer> tids = new ArrayList<Integer>();
			int i = 0;
			for (int tid : keys) {
				strings[i] = tid + " " + parser.tasks.get(tid).name;
				tids.add(tid);
				if (line.fields[index + 1].equals(tid + "")) ind = i;
				i++;
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
				public void actionPerformed(ActionEvent arg0) {
					line.fields[find] = tids.get(list.getSelectedIndex()) + "";
					textFields[find - 1].setText(line.fields[find]);
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
