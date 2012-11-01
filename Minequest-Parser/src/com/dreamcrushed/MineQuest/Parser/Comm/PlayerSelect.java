package com.dreamcrushed.MineQuest.Parser.Comm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.dreamcrushed.ClientComm.LoginException;
import com.dreamcrushed.ClientComm.Packets.GetPlayers;
import com.dreamcrushed.ClientComm.Packets.PlayerList;
import com.dreamcrushed.ClientComm.Packets.SetPlayer;
import com.dreamcrushed.MineQuest.Parser.QuestParser;
import com.dreamcrushed.MineQuest.Parser.Display.BasePage;
import com.dreamcrushed.MineQuest.Parser.Display.DisplayManager;
import com.dreamcrushed.MineQuest.Parser.Display.ErrorMessage;
import com.dreamcrushed.MineQuest.Parser.Display.Message;

public class PlayerSelect extends BasePage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9079944569138708368L;
	private JFrame frame;
	private Message message;

	public PlayerSelect(final DisplayManager display, QuestParser questParser) {
		if (display.networkManager == null) {
			new ErrorMessage("Not Connected to Server", "Error", 200,
					display.frame, display.frame.getX() + display.frame.getWidth() / 2,
					display.frame.getY() + display.frame.getHeight() / 2);
			return;
		}
		try {
			this.message = new Message("Getting Players from Server", "Info", 200,
					display.frame, display.frame.getX() + display.frame.getWidth() / 2,
					display.frame.getY() + display.frame.getHeight() / 2);
			
			PlayerList.setPlayerListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Callback Received");
					message.close();
					int x = display.frame.getX() + display.frame.getWidth() / 2;
					int yL = display.frame.getY() + display.frame.getHeight() / 2;
					y = 25;
					PlayerSelect.this.frame = new JFrame("Select Hostname:Port");
					frame.setLocation(x - 150, yL - 225);

					frame.setSize(300, 450);
					setLayout(null);
					setSize(frame.getSize());
					
					if (arg0.getActionCommand() != null) {
						String[] names = arg0.getActionCommand().split(",");
						for (final String name : names) {
							JLabel l = label(name, 50, y, 200, 25);
							l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							y += 5;
							l.addMouseListener(new MouseListener() {
								@Override
								public void mouseReleased(MouseEvent arg0) 	{	}
								@Override
								public void mousePressed(MouseEvent arg0) 	{	}
								@Override
								public void mouseExited(MouseEvent arg0) 	{	}
								@Override
								public void mouseEntered(MouseEvent arg0) 	{	}
								@Override
								public void mouseClicked(MouseEvent arg0) {
									try {
										display.networkManager.sendPacket(new SetPlayer(name));
									} catch (LoginException e) {
										e.printStackTrace();
									}
									frame.dispose();
								}
							});
						}
					}
					
					JButton button = new JButton("Cancel");
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							frame.dispose();
						}
					});
					button.setLocation(0, y);
					button.setSize(300, 50);
					add(button);
					pack();
			        
			        frame.setContentPane(PlayerSelect.this);

			        //Display the window.
			        frame.pack();
			        frame.setVisible(true);
				}
			});
			display.networkManager.sendPacket(new GetPlayers());
		} catch (LoginException e) {
			e.printStackTrace();
		}
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
