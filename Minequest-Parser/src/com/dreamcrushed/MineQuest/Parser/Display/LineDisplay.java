package com.dreamcrushed.MineQuest.Parser.Display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.Handlers.NameHandler;
import com.dreamcrushed.MineQuest.Parser.Lines.EditLine;
import com.dreamcrushed.MineQuest.Parser.Lines.EnumeratedLine;
import com.dreamcrushed.MineQuest.Parser.Lines.EventLine;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;
import com.dreamcrushed.MineQuest.Parser.Lines.RequirementLine;
import com.dreamcrushed.MineQuest.Parser.Lines.TargetLine;

public class LineDisplay extends BasePage {
	protected static final long serialVersionUID = 1L;
	public JFrame frame;
	public int index;
	protected List<FieldHandler> handlers;
	public QuestLine line;
	public QuestParser parser;
	public TaskDisplay display;
	private QuestLine orig;
	public int startIndex;

	public LineDisplay(final QuestLine orig, final QuestParser parser,
			final TaskDisplay display, int xL, int yL) throws Exception {
		if (orig instanceof EventLine) {
			this.line = new EventLine((EventLine) orig);
		} else if (orig instanceof RequirementLine) {
			this.line = new RequirementLine((RequirementLine) orig);
		} else if (orig instanceof EditLine) {
			this.line = new EditLine((EditLine) orig);
		} else if (orig instanceof TargetLine) {
			this.line = new TargetLine((TargetLine) orig);
		} else {
			this.line = new QuestLine(orig);
		}
		handlers = new ArrayList<FieldHandler>();
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
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public void reDraw() {
		removeAll();

		y = 25;
		
		for (FieldHandler handler : handlers) {
			if (handler.fDef != null) {
				label(handler.fDef.name + ":");
				y -= 40;
			}
			handler.createDisplay();
			if (handler.fDef != null) {
				y += 40;
			}
		}

		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				done();
			}
		});
		button.setLocation(0, y);
		button.setSize(650, 25);
		add(button);
		y += 30;

		button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		button.setLocation(0, y);
		button.setSize(650, 25);
		add(button);

		frame.invalidate();
		revalidate();
		repaint();
	}

	public void setFields() {
		for (FieldHandler handler : handlers) {
			handler.tempSave();
		}
		removeAll();
		handlers.clear();

		y = 25;

		if (line instanceof EnumeratedLine) {
			this.startIndex = 2;
			this.index = 2;
			NameHandler handler = new NameHandler(line, this);
			handler.createDisplay();
			handlers.add(handler);
		} else {
			this.index = 0;
			this.startIndex = 0;
		}

		for (int i = startIndex; i < line.definition.fields.length; i++) {
			showField(line.definition.fields[i]);
		}

		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				done();
			}
		});
		button.setLocation(0, y);
		button.setSize(650, 25);
		add(button);
		y += 30;

		button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		button.setLocation(0, y);
		button.setSize(650, 25);
		add(button);

		frame.invalidate();
		revalidate();
		repaint();
	}

	protected void delete() {
		new YesNoQuestion("Do you want to delete this?", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (line instanceof EventLine) {
					System.out.println("Removing Event");
					parser.removeEvent((EventLine)orig);
				} else if (orig instanceof RequirementLine) {
					parser.requirements.remove(((EnumeratedLine)orig).id);
				} else if (orig instanceof EditLine) {
					parser.edits.remove(((EnumeratedLine)orig).id);
				} else if (orig instanceof TargetLine) {
					parser.targets.remove(((EnumeratedLine)orig).id);
				} else {
					System.out.println("Removing Field");
					parser.removeField(orig);
				}
				display.show(display.currentTask);
				frame.dispose();
			}
		}, 200, LineDisplay.this, frame.getX() + frame.getWidth() / 2,
		frame.getY() + frame.getHeight() / 2);
	}

	protected void done() {
		index = 0;
		for (FieldHandler handler : handlers) {
			handler.startSave();
		}
		for (FieldHandler handler : handlers) {
			if (!handler.save()) {
				return;
			}
		}
		for (FieldHandler handler : handlers) {
			handler.completeSave();
		}
		orig.copy(line);
		display.show(display.currentTask);
		frame.dispose();
	}

	private void showField(FieldDefinition fDef) {
		if (fDef.field.handler == null) return;
		
		FieldHandler handler = fDef.field.createHandler(line, this, fDef);
		label(fDef.name + ":");
		y -= 40;
		handler.createDisplay();
		y += 40;
		handlers.add(handler);
		index += fDef.field.length;
	}

	protected void pack() {
		// Create and set up the content pane.
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		// this.setSize(500, 300);
		this.setPreferredSize(this.getSize());
		frame.setContentPane(this);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
