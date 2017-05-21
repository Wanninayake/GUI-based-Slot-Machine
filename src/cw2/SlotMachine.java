package cw2;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SlotMachine extends JFrame {
	static int clickCount = 0;

	public SlotMachine() {

		super("Slot Machine V1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);

		// to create layout
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		add(panel);

		//setting images for first reel
		ImageIcon img1 = new ImageIcon(getClass().getResource("images/bell.png"));
		JLabel lbl1 = new JLabel(img1);

		lbl1.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 100, 10);
		panel.add(lbl1, c);

		//setting images for second  reel
		ImageIcon img2 = new ImageIcon(getClass().getResource("images/cherry.png"));
		JLabel lbl2 = new JLabel(img2);
		lbl2.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 100, 10);
		panel.add(lbl2, c);

		//setting images for third reel
		ImageIcon img3 = new ImageIcon(getClass().getResource("images/lemon.png"));
		JLabel lbl3 = new JLabel(img3);
		lbl3.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 100, 10);
		panel.add(lbl3, c);

		// creating text field for show status
		JLabel txtStatus = new JLabel(" ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(10, 50, 10, 0);
		panel.add(txtStatus, c);

		// creating button for spin
		JButton btnSpin = new JButton("Spin");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(10, 0, 10, 0);
		panel.add(btnSpin, c);
		Reel reel1 = new Reel(lbl1);
		Reel reel2 = new Reel(lbl2);
		Reel reel3 = new Reel(lbl3);
		

		JLabel status = new JLabel();
		c.gridy = 2;
		c.gridx = 1;
		c.insets = new Insets(0, 10, 10, 10);
		panel.add(status, c);
		User user = new User(status, lbl1, lbl2, lbl3);
		user.setText();
		
		//creating button for add coin
		JButton btnAddCoin = new JButton("AddCoin");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 10, 10, 10);
		panel.add(btnAddCoin, c);
		btnAddCoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				user.setCredit();
				user.setText();
			}
		});

		//creating button for reset
		JButton btnReset = new JButton("Rest");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(0, 10, 10, 10);
		panel.add(btnReset, c);

		//creating button for bet one
		JButton btnBetOne = new JButton("Bet One");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 10, 0, 10);
		panel.add(btnBetOne, c);
		btnBetOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				user.setBet();
				user.setText();
			}
		});

		//creating button for the max bet
		JButton btnBetMax = new JButton("Bet Max");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		panel.add(btnBetMax, c);
		btnBetMax.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				user.setMaxBet();
				user.setText();
				btnBetOne.setEnabled(false);
			}
		});

		//creating button for statics
		JButton btnStatics = new JButton("Statistics");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		panel.add(btnStatics, c);
		btnStatics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				user.viewStatics(SlotMachine.this);

			}
		});
		//get values when the user uses the mouse to click
		lbl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				super.mouseClicked(mouseEvent);
				reel1.stopSpin();
				clickCount++;
				if (clickCount == 3) {
					txtStatus.setText(user.winning(reel1.getGenNumber(), reel2.getGenNumber(), reel3.getGenNumber()));
				}
			}
		});

		lbl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				super.mouseClicked(mouseEvent);

				reel2.stopSpin();
				clickCount++;
				if (clickCount == 3) {
					txtStatus.setText(user.winning(reel1.getGenNumber(), reel2.getGenNumber(), reel3.getGenNumber()));
				}
			}
		});

		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				super.mouseClicked(mouseEvent);

				reel3.stopSpin();
				clickCount++;
				if (clickCount == 3) {
					txtStatus.setText(user.winning(reel1.getGenNumber(), reel2.getGenNumber(), reel3.getGenNumber()));
				}
			}
		});

		btnSpin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (clickCount == 3) {
					txtStatus.setText("");
					reel1.timer.start();
					reel2.timer.start();
					reel3.timer.start();
					clickCount = 0;
					lbl1.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
					lbl2.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
					lbl3.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
					btnBetOne.setEnabled(true);
				} else {
					if (user.getBet() != 0) {
						reel1.start();
						reel2.start();
						reel3.start();

					} else {
						JOptionPane.showMessageDialog(null, "Please Bet First and spin", "", JOptionPane.WARNING_MESSAGE);
					}

				}

			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		SlotMachine slGame = new SlotMachine();
		// User user = new User();

	}
}
