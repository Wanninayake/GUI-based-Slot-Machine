package cw2;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JFrame {
	static private int wins = 0;
	private int credit;
	private int bet = 0;
	private int losses = 0;
	private JLabel lbl, lbl2, lbl3, lbl4;

	

	public User(JLabel lbl, JLabel lbl2, JLabel lbl3, JLabel lbl4) {
		credit = 10;
		this.lbl = lbl;
		this.lbl2 = lbl2;
		this.lbl3 = lbl3;
		this.lbl4 = lbl4;
	}

	public void setCredit() {
		credit++;
	}

	public int getCredit() {
		return credit;
	}

	public int getBet() {
		return bet;
	}

	public void setBet() {

		bet++;
		credit--;
	}

	public int getWins() {

		return wins;
	}

	public void setWins() {

		wins++;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses() {

		losses++;
	}

	public String winning(int reel1, int reel2, int reel3) {
		String status = "Try again";

		if (((reel1 == reel2) && (reel2 == reel3)) || reel1 == reel2 || reel2 == reel3 || reel1 == reel3) {
			// matching reels

			if ((reel1 == reel2) && (reel2 == reel3)) {
				lbl2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
				lbl3.setBorder(BorderFactory.createLineBorder(Color.red, 5));
				lbl4.setBorder(BorderFactory.createLineBorder(Color.red, 5));
				credit = credit + (Reel.symbol[reel1].getValue() * bet) * 2;

			} else if (reel1 == reel2) {
				lbl2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
				lbl3.setBorder(BorderFactory.createLineBorder(Color.red, 5));
				credit = credit + (Reel.symbol[reel1].getValue() * bet) * 2;

			} else if (reel2 == reel3) {
				lbl3.setBorder(BorderFactory.createLineBorder(Color.red, 5));
				lbl4.setBorder(BorderFactory.createLineBorder(Color.red, 5));
				credit = credit + (Reel.symbol[reel2].getValue() * bet) * 2;

			} else if (reel1 == reel3) {
				lbl2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
				lbl4.setBorder(BorderFactory.createLineBorder(Color.red, 5));
				credit = credit + (Reel.symbol[reel1].getValue() * bet) * 3;

			}
			status = "Win";
			setWins();
			bet = 0;
			setText();

		} else {
			setLosses();
			bet = 0;
			setText();
		}
		return status;
	}

	public void setText() {
		lbl.setText("Credits: " + credit);
		if (bet > 0) {
			lbl.setText("Credits: " + credit + " Bet: " + bet);
		}
	}

	public void setMaxBet() {
		bet = 3;
		credit = credit - 3;

	}

	public void viewStatics(JFrame frame) {
		JDialog stat = new JDialog(frame, true);
		stat.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JLabel lbl = new JLabel();
		stat.add(lbl);
		stat.setSize(200, 200);
		lbl.setText("number of Wins: " + getWins() + "\nNumber of losses: " + getLosses());
		stat.setVisible(true);

	}
}
