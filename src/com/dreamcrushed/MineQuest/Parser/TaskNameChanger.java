package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class TaskNameChanger extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private int y;

	public TaskNameChanger(final Task task, final TaskDisplay display, int x, int yL) {
		y = 25;
		this.frame = new JFrame("Set Task Name");
		frame.setLocation(x - 150, yL - 75);

		frame.setSize(300, 150);
		setLayout(null);
		setSize(frame.getSize());
		
		final JTextField textField = new JTextField(task.name);
		textField.setLocation(0, y);
		textField.setSize(300, 50);
		add(textField);
		y += 75;
		
		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				task.setName(textField.getText());
				display.show(task);
				frame.dispose();
			}
		});
		button.setLocation(0, y);
		button.setSize(300, 50);
		add(button);
		pack();
        
        frame.setContentPane(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	protected void pack() {
        //Create and set up the content pane.t
        this.setLayout(null);
//        this.setSize(500, 300);
        this.setPreferredSize(this.getSize());
        frame.setContentPane(this);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

}
