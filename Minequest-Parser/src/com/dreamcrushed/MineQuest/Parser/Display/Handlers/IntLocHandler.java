package com.dreamcrushed.MineQuest.Parser.Display.Handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.dreamcrushed.ClientComm.LoginException;
import com.dreamcrushed.ClientComm.NetworkManager;
import com.dreamcrushed.ClientComm.Packets.IntLoc;
import com.dreamcrushed.ClientComm.Packets.IntLocReq;
import com.dreamcrushed.ClientComm.Packets.NoPlayerSet;
import com.dreamcrushed.MineQuest.Parser.Definitions.FieldDefinition;
import com.dreamcrushed.MineQuest.Parser.Display.ErrorMessage;
import com.dreamcrushed.MineQuest.Parser.Display.FieldHandler;
import com.dreamcrushed.MineQuest.Parser.Display.LineDisplay;
import com.dreamcrushed.MineQuest.Parser.Display.Message;
import com.dreamcrushed.MineQuest.Parser.Lines.QuestLine;

public class IntLocHandler extends FieldHandler {

	private JTextField[] textFields;

	public IntLocHandler(QuestLine line, LineDisplay display, FieldDefinition fDef) {
		super(line, display, fDef);
	}

	@Override
	public void createDisplay() {
		int x = 500 / fDef.field.length;
		int ly = display.y;
		textFields = new JTextField[fDef.field.length];
		for (int i1 = 0; i1 < fDef.field.length; i1++) {
			textFields[i1] = display.textField(line.fields[index + i1], x * (i1)
					+ 150, ly, x, 25);
			display.y = ly;
		}
		display.button("GET", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final NetworkManager networkManager = display.display.manager.networkManager;
				if (networkManager == null) {
					new ErrorMessage("Not Connected to Server", "Error", 200,
							display.frame, display.frame.getX() + display.frame.getWidth() / 2,
							display.frame.getY() + display.frame.getHeight() / 2);
					return;
				}
				final Message message = new Message("Getting next loc from Server", "Info", 200,
								display.frame, display.frame.getX() + display.frame.getWidth() / 2,
								display.frame.getY() + display.frame.getHeight() / 2);
				IntLoc.setLocListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						message.close();
						for (int i = 0; i < 3; i++) {
							textFields[i].setText(IntLoc.location[i] + "");
						}
					}
				});
				NoPlayerSet.setPlayerListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						message.close();
					}
				});
				try {
					networkManager.sendPacket(new IntLocReq());
				} catch (LoginException e1) {
					e1.printStackTrace();
				}
			}
		}, 650, 90, 25);
		display.y = ly;
	}
	
	@Override
	public void tempSave() {
		for (int j = 0; j < fDef.field.length; j++) {
			line.fields[index + j] = textFields[j].getText();
		}
	}

	@Override
	public void startSave() {
		
	}

	@Override
	public boolean save() {
		for (int j = 0; j < fDef.field.length; j++) {
			if (fDef.field.goodValue(textFields[j].getText())) {
				line.fields[index + j] = textFields[j].getText();
			} else {
				System.out.println(fDef.name + " must be "
						+ fDef.field.getFieldTypes() + "\n"
						+ textFields[j].getText() + " is not");
				new ErrorMessage(fDef.name + " must be "
						+ fDef.field.getFieldTypes(), "Error", 200,
						display, display.frame.getX() + display.frame.getWidth() / 2,
						display.frame.getY() + display.frame.getHeight() / 2);
				return false;
			}
		}
		return true;
	}

	@Override
	public void completeSave() {
		
	}

}
