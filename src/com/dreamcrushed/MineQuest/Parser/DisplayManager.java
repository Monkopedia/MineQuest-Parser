package com.dreamcrushed.MineQuest.Parser;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class DisplayManager {
	
	private static JFrame frame;
	private static TaskList leftManager;
	private static TaskDisplay center;
	private static int widths;
	private static int heights;
	private static int lsize;
	private static QuestParser parser;
	
	public static void run(final QuestParser parser) {
		widths = 800;
		heights = 600;
		lsize = 200;

		center = new TaskDisplay(parser);
		leftManager = new TaskList(parser, center, lsize, heights);
		DisplayManager.parser = parser;

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
        frame.setJMenuBar(MenuBarHandler.createMenu(parser));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
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
