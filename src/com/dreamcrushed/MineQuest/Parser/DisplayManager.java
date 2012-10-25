package com.dreamcrushed.MineQuest.Parser;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DisplayManager {
	
	private static JFrame frame;
	private static TaskDisplay leftManager;
	private static MainPage center;
	private static int widths;
	private static int heights;
	private static int lsize;
	
	public static void run(final QuestParser parser) {
		widths = 800;
		heights = 600;
		lsize = 200;

		center = new MainPage(parser);
		leftManager = new TaskDisplay(parser, center, lsize, heights);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI("testfile");

        		center.show(parser.tasks.get(0));
            }
        });
	}
	
    private static void createAndShowGUI(String filename) {
        //Create and set up the window.
        frame = new JFrame("MineQuest Quest Writer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        display();
    }
    
    private static void display() {
//		try {
			setDisplay(leftManager, center, lsize, widths, heights);
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
	}

	public static void setDisplay(Container left, Container right, int lsize, int w, int h) {
        frame.setSize(w, h);
        
        //Create and set up the content pane.
        Container container = new JPanel();
        
        container.setLayout(null);
        container.setSize(w, h);
        container.setPreferredSize(container.getSize());
        
        left.setSize(lsize, h);
        left.setLocation(0, 0);
        right.setLocation(lsize, 0);
        right.setSize(w - lsize, h);
        container.add(left);
        container.add(right);
        
        frame.setContentPane(container);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
