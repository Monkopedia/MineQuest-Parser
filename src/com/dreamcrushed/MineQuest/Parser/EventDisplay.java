package com.dreamcrushed.MineQuest.Parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EventDisplay extends JPanel{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected JFrame frame;
	protected int y;

	public EventDisplay(EventLine line, QuestParser parser) {
		y = 25;
		this.frame = new JFrame("Event Config");

		frame.setSize(800, 600);
		setLayout(null);
		setSize(800, 600);
		
		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button.setLocation(0, y);
		button.setSize(225, 25);
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

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
}
