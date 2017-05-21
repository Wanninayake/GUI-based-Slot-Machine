package cw2;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Reel extends Thread {

	static Symbol[] symbol = new Symbol[6];
	boolean spin = true;
	private int genNumber;
	private JLabel label;

	// customized constructor
	public Reel(JLabel label) {
		setGenNumber(-1);
		this.label = label;
	}

	public int getGenNumber() {
		return genNumber;
	}

	public void setGenNumber(int genNumber) {
		this.genNumber = genNumber;
	}

	// initializing symbols
	public void addSymbol() {
		symbol[0] = new Symbol("images/bell.png", 6);
		symbol[1] = new Symbol("images/cherry.png", 2);
		symbol[2] = new Symbol("images/lemon.png", 3);
		symbol[3] = new Symbol("images/plum.png", 4);
		symbol[4] = new Symbol("images/redseven.png", 7);
		symbol[5] = new Symbol("images/watermelon.png", 5);
	}

	Timer timer;

	public void run() {
		addSymbol();

		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				spin(label);

			}
		});

		timer.start();
	}

	// method for spining
	public void spin(JLabel lbl) {

		// generate random numbers for reels
		genNumber = (int) Math.floor(Math.random() * 5);
		// assign images for reels
		lbl.setIcon(new ImageIcon(getClass().getResource(symbol[genNumber].getImage())));

	}

	// stop the spining
	public void stopSpin() {
		// stop reels
		synchronized (this) {
			spin = false;
			try {
				join();
				timer.stop();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
