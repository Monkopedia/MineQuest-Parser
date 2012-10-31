package com.dreamcrushed.MineQuest.Parser.Display;

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

import com.dreamcrushed.MineQuest.Parser.EntityType;
import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Task;
import com.dreamcrushed.MineQuest.Parser.Type;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Definitions.QuestDefinition;
import com.dreamcrushed.MineQuest.Parser.Lines.EditLine;
import com.dreamcrushed.MineQuest.Parser.Lines.EnumeratedLine;
import com.dreamcrushed.MineQuest.Parser.Lines.EventLine;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;
import com.dreamcrushed.MineQuest.Parser.Lines.RequirementLine;
import com.dreamcrushed.MineQuest.Parser.Lines.TargetLine;

public class LineDisplay extends BasePage {

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
	private int startIndex;
	private int ioff;
	private int newId;

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
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public void setFields() {
		removeAll();

		y = 25;

		int x = 500;
		this.index = 0;
		this.startIndex = 0;
		if (line instanceof EnumeratedLine) {
			textFields = new JTextField[line.fields.length - 1];
			textFields[index] = textField(line.getName(), 150, y, x, 25);
			index++;
			y -= 25;
			label("Name");
			startIndex = 2;
			this.ioff = 1;
		} else {
			textFields = new JTextField[line.fields.length];
			this.ioff = 0;
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
		if (line instanceof EnumeratedLine) {
			line.name = textFields[0].getText();
			index++;
		}
		if (createTask) {
			newId = parser.allocateTaskId();
		}
		for (int i = startIndex; i < line.definition.fields.length; i++) {
			FieldDefinition fDef = line.definition.fields[i];
			if (fDef.field == Type.TASK) {
				textFields[index].setText(newId + "");
			}
			for (int j = 0; j < fDef.field.length; j++) {
				if (fDef.field.goodValue(textFields[index].getText())) {
					line.fields[index + ioff] = textFields[index]
							.getText();
					System.out.println("Setting " + fDef.name + " to "
							+ line.fields[index + ioff]);
					index++;
				} else {
					System.out.println(fDef.name + " must be "
							+ fDef.field.getFieldTypes() + "\n"
							+ textFields[index].getText() + " is not");
					new ErrorMessage(fDef.name + " must be "
							+ fDef.field.getFieldTypes(), 200,
							LineDisplay.this, frame.getX() + frame.getWidth() / 2,
							frame.getY() + frame.getHeight() / 2);
					return;
				}
			}
		}
		orig.copy(line);
		display.show(display.currentTask);
		if (createTask) {
			parser.tasks.put(newId, new Task(newId, false));
			parser.taskList.updateList();
		}
		frame.dispose();
	}

	private void showField(FieldDefinition fDef) {
		if (fDef.field == Type.IGNORE) return;
		if (fDef.field == Type.T) {
			textFields[index++] = new JTextField("T");
			return;
		}
		int ly = y;
		int x = 500 / fDef.field.length;

		switch (fDef.field) {
		case COMPARE:
			String[] comps = new String[] { "<", ">", ">=", "<=", "="};
			createStringSpinner(comps, line.fields[index + ioff]);
			break;
		case ENTITY:
			List<String> names = EntityType.names;
			String[] namesList = new String [names.size()];
			for (int i = 0; i < names.size(); i++) {
				namesList[i] = names.get(i);
			}
			createStringSpinner(namesList, line.fields[index + ioff]);
			break;
		case REQUIREMENTTYPE:
			createSpinner(parser.defs.requireDefs);
			break;
		case EDITTYPE:
			createSpinner(parser.defs.editDefs);
			break;
		case TARGETTYPE:
			createSpinner(parser.defs.targetDefs);
		case QUESTFIELD:
			createSpinner(parser.defs.questDefs);
			break;
		case EVENTTYPE:
			createSpinner(parser.defs.eventDefs);
			break;
		case TASK:
			showTask(fDef);
			break;
		default:
			for (int i1 = 0; i1 < fDef.field.length; i1++) {
				textFields[index] = textField(line.fields[index + ioff], x * (i1)
						+ 150, ly, x, 25);
				index++;
				y = ly;
			}
			break;
		}
		label(fDef.name + ":");
	}

	private void showTask(FieldDefinition fDef) {
		final String[] strings = new String[parser.tasks.size() + 1];
		int ind = 0;
		Set<Integer> keys = parser.tasks.keySet();
		final List<Integer> tids = new ArrayList<Integer>();
		int i = 0;
		for (int tid : keys) {
			strings[i] = tid + " " + parser.tasks.get(tid).name;
			tids.add(tid);
			if (line.fields[index + ioff].equals(tid + ""))
				ind = i;
			i++;
		}
		final int newId = parser.allocateTaskId();
		strings[i] = "New Task_" + newId;
		tids.add(newId);
		final int find = index + ioff;
		textFields[index] = new JTextField(line.fields[index + ioff]);
		index++;
		final JComboBox list = createSpinner(strings, 150, y, 500, 25, ind);

		list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createTask = (list.getSelectedIndex() == strings.length - 1);
				line.fields[find] = tids.get(list.getSelectedIndex()) + "";
				textFields[find - 1].setText(line.fields[find]);
			}
		});
	}

	private JComboBox createSpinner(String[] strings, int x, int y, int xs, int ys,
			int ind) {
		final JComboBox list = new JComboBox(strings);
		list.setLocation(x, y);
		list.setSize(xs, ys);
		list.setSelectedIndex(ind);
		add(list);
		return list;
	}

	private void createStringSpinner(final String[] comps, String start) {
		final String[] strings = comps;
		int ind = 0;
		for (int i = 0; i < strings.length; i++) {
			if (start.equals(strings[i]))
				ind = i;
		}
		final int find = index + ioff;
		textFields[index] = new JTextField(line.fields[index + ioff]);
		index++;
		final JComboBox list = new JComboBox(strings);
		list.setLocation(150, y);
		list.setSize(500, 25);
		list.setSelectedIndex(ind);
		list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				line.fields[find] = strings[list.getSelectedIndex()];
				textFields[find - ioff].setText(line.fields[find]);
				setFields();
			}
		});
		add(list);
	}

	private void createSpinner(final List<? extends QuestDefinition> questDefs) {
		final String[] strings = new String[questDefs.size()];
		int ind = 0;
		for (int i = 0; i < strings.length; i++) {
			strings[i] = questDefs.get(i).name;
			if (line.definition.name.equals(strings[i]))
				ind = i;
		}
		final int find = index + ioff;
		textFields[index] = new JTextField(line.fields[index + ioff]);
		index++;
		final JComboBox list = new JComboBox(strings);
		list.setLocation(150, y);
		list.setSize(500, 25);
		list.setSelectedIndex(ind);
		list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				line.fields[find] = strings[list.getSelectedIndex()];
				textFields[find - ioff].setText(line.fields[find]);
				line.setDefinition(questDefs.get(list
						.getSelectedIndex()));
				setFields();
			}
		});
		add(list);
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
