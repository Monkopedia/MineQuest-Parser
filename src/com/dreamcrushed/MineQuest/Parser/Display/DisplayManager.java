package com.dreamcrushed.MineQuest.Parser.Display;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.dreamcrushed.MineQuest.Parser.MenuBarHandler;
import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.TaskList;


public class DisplayManager {
	
	private JFrame frame;
	private TaskList leftManager;
	private TaskDisplay taskDisplay;
	private FieldDisplay fieldDisplay;
	private int widths;
	private int heights;
	private int lsize;
	private QuestParser parser;
	
	public static void openParser(final QuestParser parser) {
		new DisplayManager(parser);
	}
	
	public DisplayManager(final QuestParser parser) {
		widths = 800;
		heights = 600;
		lsize = 300;

		taskDisplay = new TaskDisplay(parser, this);
		fieldDisplay = new FieldDisplay(parser, this);
		leftManager = new TaskList(parser, taskDisplay, fieldDisplay, lsize, heights);
		this.parser = parser;

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                
                fieldDisplay.show(null);
            }
        });
	}
	
    private void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("MineQuest Quest Writer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void display(Container right) {
//		try {
			setDisplay(leftManager, right, lsize, widths, heights);
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
	}

	public void setDisplay(Container left, Container right, int lsize, int w, int h) {
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

	public int getX() {
		return frame.getX();
	}

	public int getWidth() {
		return frame.getWidth();
	}

	public int getY() {
		return frame.getY();
	}

	public int getHeight() {
		return frame.getHeight();
	}
}