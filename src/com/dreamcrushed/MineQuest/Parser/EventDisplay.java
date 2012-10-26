package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

	public EventDisplay(final EventLine line, QuestParser parser) {
		this.line = line;
		y = 25;
		this.index = 0;
		this.frame = new JFrame("Event Config");

		frame.setSize(800, 600);
		setLayout(null);
		setSize(800, 600);
		textFields = new JTextField[line.fields.length - 1];

		int x = 500;
		textFields[index] = textField(line.name, 150, y, x, 25);
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
				for (int i = 2; i < line.eDefinition.fields.length; i++) {
					FieldDefinition fDef = line.eDefinition.fields[i];
					for (int j = 0; j < fDef.field.length; j++) {
						if (fDef.field.goodValue(textFields[index].getText())) {
							line.fields[index+1] = textFields[index].getText();
						} else {
							// TODO popup here with error
							return;
						}
					}
				}
				frame.dispose();
			}
		});
		button.setLocation(0, y);
		button.setSize(650, 25);
		add(button);
		pack();

        frame.setContentPane(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	private void showField(FieldDefinition fDef) {
		int ly = y;
		int x = 500 / fDef.field.length;
		for (int i = 0; i < fDef.field.length; i++) {
			textFields[index] = textField(line.fields[index+1], x * (i) + 150, ly, x, 25);
			index++;
			y = ly;
		}
		label(fDef.name + ":");
	}

	protected void pack() {
        //Create and set up the content pane.t
        this.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        this.setSize(500, 300);
        this.setPreferredSize(this.getSize());
        frame.setContentPane(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
}
