// Kids Game

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class KidsGame extends JFrame implements ActionListener {
	JPanel cards = new JPanel();
	JPanel panelA = new JPanel();
	JPanel panelB = new JPanel();
	JPanel panelC = new JPanel();
	JPanel panelD = new JPanel();
	JPanel panelE = new JPanel();
	JButton btnAnswer1 = new JButton();
	JButton btnAnswer2 = new JButton();
	JLabel lblCorrectAnswer = new JLabel();
	JLabel lblStar = new JLabel();
	ImageIcon imgIcon1 = new ImageIcon("img/Correct.png");
	ImageIcon imgIcon11 = new ImageIcon(imgIcon1.getImage().getScaledInstance(150, 137, java.awt.Image.SCALE_SMOOTH));
	JLabel lblChibiCorrect = new JLabel(imgIcon11);
	ImageIcon imgIcon2 = new ImageIcon("img/Wrong.png");
	ImageIcon imgIcon21 = new ImageIcon(imgIcon2.getImage().getScaledInstance(182, 148, java.awt.Image.SCALE_SMOOTH));
	JLabel lblChibiWrong = new JLabel(imgIcon21);
	JLabel lblCorrectWrong = new JLabel();

	StarRasing sr = new StarRasing();
	Random random = new Random();
	int drawing = 0, shapes = 0, choice = 0;
	String choiceShape = "";
	boolean correcter = false;

	Color color1 = Color.decode("#B3E6FF");					//blue (background)
	Color color2 = Color.decode("#FFD4FF");					//pastel pink
	Color color3 = Color.decode("#00BF6F"); 				//green (button)
	Color color4 = Color.decode("#007BFF"); 				//blue (button)
	Color color5 = Color.decode("#FFC107"); 				//orange (button)
	Color color6 = Color.decode("#EEEEEE"); 				//dirty white

	public static void main(String[] args) {
		KidsGame kg = new KidsGame();
		kg.myComponents(kg.getContentPane());
		kg.setSize(500, 350);
		kg.setLocationRelativeTo(null);
		kg.setVisible(true);
	}

	public KidsGame() {
		setTitle("Shapes Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void myComponents(final Container pane) {
		thePanelA();

		cards = new JPanel(new CardLayout());
		cards.add(panelA, "panelA");
		cards.add(panelB, "panelB");
		cards.add(panelC, "panelC");
		cards.add(panelD, "panelD");
		cards.add(panelE, "panelE");
		cards.setVisible(true);
		pane.add(cards, BorderLayout.CENTER);

		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, "panelA");
	}

	public void actionPerformed(ActionEvent e) {
		cards.setVisible(true);

		if (e.getActionCommand().equals("Start") || e.getActionCommand().equals("Main")) {
			drawing = 1;
			repaint();

			thePanelB();

			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, "panelB");
		} else if (e.getActionCommand().equals("Name") || e.getActionCommand().equals("Next")) {
			shapes = random.nextInt(4) + 1;
			String basicShapes[] = {"Circle", "Square", "Rectangle", "Triangle"};
			choice = random.nextInt(2) + 1;
			int x = random.nextInt(4);

			drawing = 2;
			repaint();
			thePanelC();

			if (shapes == 1)
				choiceShape = basicShapes[0];
			else if (shapes == 2)
				choiceShape = basicShapes[1];
			else if (shapes == 3)
				choiceShape = basicShapes[2];
			else if (shapes == 4)
				choiceShape = basicShapes[3];

			while (choiceShape == basicShapes[x])
				x = random.nextInt(4);

			if (choice == 1) {
				btnAnswer1.setText(choiceShape);
				btnAnswer2.setText(basicShapes[x]);
			} else if (choice == 2) {
				btnAnswer1.setText(basicShapes[x]);
				btnAnswer2.setText(choiceShape);
			}

			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, "panelC");
		} else if (e.getActionCommand().equals("Answer1") || e.getActionCommand().equals("Answer2")) {
			shapes = 0;
			drawing = 3;
			repaint();

			thePanelD();
			lblCorrectAnswer.setText("It is a " + choiceShape);

			if (e.getActionCommand().equals("Answer1")) {
				if (choice == 1) {
					lblStar.setText("You got 1 star");
					lblChibiCorrect.setVisible(true);
					lblCorrectWrong.setText("CORRECT!");
					correcter = true;
				} else if (choice == 2) {
					lblStar.setText("");
					lblChibiWrong.setVisible(true);
					lblCorrectWrong.setText("WRONG!");
				}
			} else if (e.getActionCommand().equals("Answer2")) {
				if (choice == 1) {
					lblStar.setText("");
					lblChibiWrong.setVisible(true);
					lblCorrectWrong.setText("WRONG!");
				} else if (choice == 2) {
					lblStar.setText("You got 1 star");
					lblChibiCorrect.setVisible(true);
					lblCorrectWrong.setText("CORRECT!");
					correcter = true;
				}
			}

			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, "panelD");
		} else if (e.getActionCommand().equals("Star")) {
			if (sr.getTotalStar() >= 1) {		
				thePanelE();

				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, "panelE");
			} else {
				JOptionPane.showMessageDialog(cards, "You haven't earned a star yet.");
			}
		}

		if (e.getActionCommand().equals("Next") || e.getActionCommand().equals("Main")) {
			if (correcter == true)
				sr.setTotalStar(sr.getTotalStar() + 1);

			correcter = false;
		}
	}

	// Starting panel
	public void thePanelA() {
		ImageIcon imgIcon1 = new ImageIcon("img/Welcome.png");
		ImageIcon imgIcon2 = new ImageIcon("img/Logo.png");
		imgIcon1 = new ImageIcon(imgIcon1.getImage().getScaledInstance(175, 157, java.awt.Image.SCALE_SMOOTH));
		imgIcon2= new ImageIcon(imgIcon2.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH));
		JLabel lblChibi1 = new JLabel(imgIcon1);
		JLabel lblChibi2 = new JLabel(imgIcon2);
		JButton btnStart = new JButton("START");

		lblChibi1.setBounds(65, 40, 175, 157);
		lblChibi2.setBounds(175, -5, 250, 250);

		btnStart.setBackground(color3);
		btnStart.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnStart.setBounds(185, 215, 120, 50);
		btnStart.setFocusable(false);
		btnStart.setFont(new Font("Comic Sans MS", 1, 17));
		btnStart.setForeground(Color.white);
		btnStart.setActionCommand("Start");
		btnStart.addActionListener(this);

		panelA.setBackground(color1);
		panelA.setLayout(null);
		panelA.add(lblChibi1, BorderLayout.CENTER);
		panelA.add(lblChibi2);
		panelA.add(btnStart);
	}

	// Main menu panel
	public void thePanelB() {
		JButton btnGame1 = new JButton("Name the Shapes");
		JButton btnGame2 = new JButton("See Star Collection");
		ImageIcon imgIcon = new ImageIcon("img/Main.png");
		imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(105, 153, java.awt.Image.SCALE_SMOOTH));
		JLabel lblChibiMain = new JLabel(imgIcon);

		btnGame1.setBackground(color4);
		btnGame1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnGame1.setBounds(75, 140, 190, 50);
		btnGame1.setFocusable(false);
		btnGame1.setFont(new Font("Comic Sans MS", 1, 17));
		btnGame1.setForeground(Color.white);
		btnGame1.setActionCommand("Name");
		btnGame1.addActionListener(this);

		btnGame2.setBackground(color5);
		btnGame2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnGame2.setBounds(75, 200, 190, 50);
		btnGame2.setFocusable(false);
		btnGame2.setFont(new Font("Comic Sans MS", 1, 17));
		btnGame2.setForeground(Color.white);
		btnGame2.setActionCommand("Star");
		btnGame2.addActionListener(this);

		lblChibiMain.setBounds(320, 100, 105, 153);

		panelB.setBackground(color1);
		panelB.setLayout(null);
		panelB.add(btnGame1);
		panelB.add(btnGame2);
		panelB.add(lblChibiMain);
	}

	// Naming the shape panel
	public void thePanelC() {
		ImageIcon imgIcon = new ImageIcon("img/Ask.png");
		imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH));
		JLabel lblChibiAsk = new JLabel(imgIcon);

		lblChibiAsk.setBounds(20, 50, 170, 170);

		btnAnswer1.setBackground(color4);
		btnAnswer1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnAnswer1.setBounds(100, 230, 120, 45);
		btnAnswer1.setFocusable(false);
		btnAnswer1.setFont(new Font("Comic Sans MS", 1, 16));
		btnAnswer1.setForeground(Color.white);
		btnAnswer1.setActionCommand("Answer1");
		btnAnswer1.addActionListener(this);

		btnAnswer2.setBackground(color5);
		btnAnswer2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnAnswer2.setBounds(265, 230, 120, 45);
		btnAnswer2.setFocusable(false);
		btnAnswer2.setFont(new Font("Comic Sans MS", 1, 16));
		btnAnswer2.setForeground(Color.white);
		btnAnswer2.setActionCommand("Answer2");
		btnAnswer2.addActionListener(this);

		panelC.setBackground(color1);
		panelC.setLayout(null);
		panelC.add(lblChibiAsk);
		panelC.add(btnAnswer1);
		panelC.add(btnAnswer2);
	}

	// After answering a shape panel
	public void thePanelD() {
		JButton btnNext = new JButton("Next");
		JButton btnMain = new JButton("Main");

		lblCorrectAnswer.setBounds(260, 60, 150, 26);
		lblCorrectAnswer.setFont(new Font("Comic Sans MS", 1, 16));
		lblStar.setBounds(260, 90, 150, 26);
		lblStar.setFont(new Font("Comic Sans MS", 1, 16));

		btnNext.setBackground(color3);
		btnNext.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnNext.setBounds(260, 137, 130, 45);
		btnNext.setFocusable(false);
		btnNext.setFont(new Font("Comic Sans MS", 1, 15));
		btnNext.setForeground(Color.white);
		btnNext.setActionCommand("Next");
		btnNext.addActionListener(this);

		btnMain.setBackground(color5);
		btnMain.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnMain.setBounds(260, 193, 130, 45);
		btnMain.setFocusable(false);
		btnMain.setFont(new Font("Comic Sans MS", 1, 15));
		btnMain.setForeground(Color.white);
		btnMain.setActionCommand("Main");
		btnMain.addActionListener(this);

		lblChibiCorrect.setBounds(90, 50, 150, 137);
		lblChibiCorrect.setVisible(false);
		lblChibiWrong.setBounds(70, 50, 182, 148);
		lblChibiWrong.setVisible(false);

		lblCorrectWrong.setBounds(92, 187, 130, 50);
		lblCorrectWrong.setFont(new Font("Comic Sans MS", 1, 18));
		lblCorrectWrong.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrectWrong.setVerticalAlignment(SwingConstants.CENTER);

		panelD.setBackground(color1);
		panelD.setLayout(null);
		panelD.add(lblCorrectAnswer);
		panelD.add(lblStar);
		panelD.add(btnNext);
		panelD.add(btnMain);
		panelD.add(lblChibiCorrect);
		panelD.add(lblChibiWrong);
		panelD.add(lblCorrectWrong);
	}
	
	// Star collection panel
	public void thePanelE() {
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(color5);
		btnBack.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnBack.setBounds(365, 225, 80, 45);
		btnBack.setFocusable(false);
		btnBack.setFont(new Font("Comic Sans MS", 1, 15));
		btnBack.setForeground(Color.white);
		btnBack.setActionCommand("Main");
		btnBack.addActionListener(this);

		int s = sr.getTotalStar(), base = 1, b = -1, xCoordinate = 40,  yCoordinate = 40;

		panelE.setBackground(color1);
		panelE.setLayout(null);

		if (s >= 15) {								// If stars reached 15, then it will turn to a giant star with number of stars collected beside it
			ImageIcon imgIcon = new ImageIcon("img/Star.png");
			imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(140, 130, java.awt.Image.SCALE_SMOOTH));
			JLabel lblStar = new JLabel(imgIcon);
			JLabel lblTimes = new JLabel("x " + s);

			lblStar.setBounds(100, 70, 140, 130);
			lblTimes.setBounds(260, 110, 150, 50);
			lblTimes.setFont(new Font("Comic Sans MS", 1, 27));

			panelE.removeAll();
			panelE.add(lblStar);
			panelE.add(lblTimes);
		} else if (s <= 14) {
			while (base <= s) {					
				ImageIcon imgIcon = new ImageIcon("img/Star.png");
				imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(70, 65, java.awt.Image.SCALE_SMOOTH));
				JLabel lblStar = new JLabel(imgIcon);

				if (base == 6 || base == 11) {
					b = -1;
				}

				// Five (5) stars only per row. The next stars will be placed beneath the first row until the third row
				if (base >= 1 && base <= 5) {
					lblStar.setBounds(((xCoordinate * (b + 2)) + 2), yCoordinate, 70, 75);
					b += 2;
				} else if (base >= 6 && base <= 10) {
					lblStar.setBounds(((xCoordinate * (b + 2)) + 2), (yCoordinate * 3), 70, 75);
					b += 2;
				} else if (base >= 11 && base <= 14) {
					lblStar.setBounds(((xCoordinate * (b + 2)) + 2), (yCoordinate * 5), 70, 75);
					b += 2;
				}

				panelE.add(lblStar);
				base++;
			}
		}

		panelE.add(btnBack);
	}

	public void correct() {
		if (correcter == true)
			sr.setTotalStar(sr.getTotalStar() + 1);
	}

	public void paint(Graphics g) {
		super.paint(g);

		if (drawing == 1) {									// Main menu
			g.setColor(color6);
			g.fillOval(70, 80, 220, 70);
			g.fillOval(290, 125, 20, 20);
			g.fillOval(315, 140, 10, 10);

			g.setColor(Color.BLACK);
			g.drawOval(70, 80, 220, 70);
			g.drawOval(290, 125, 20, 20);
			g.drawOval(315, 140, 10, 10);

			g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			g.drawString("What do you want to do?", 85, 120);
		} else if (drawing == 2) {							// Naming the shape
			g.setColor(color6);
			g.fillOval(210, 50, 200, 60);
			g.fillOval(185, 90, 20, 20);
			g.fillOval(170, 110, 10, 10);

			g.setColor(Color.BLACK);
			g.drawOval(210, 50, 200, 60);
			g.drawOval(185, 90, 20, 20);
			g.drawOval(170, 110, 10, 10);

			g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			g.drawString("What shape is this?", 235, 85);
		} else if (drawing == 3) {							// After answering a shape
			g.setColor(Color.BLACK);
			g.drawRect(95, 218, 140, 50);
			g.drawRect(96, 219, 138, 48);
		}

		if (shapes == 1) {									// Circle shape
			g.setColor(color3);
			g.fillOval(270, 140, 80, 80);

			g.setColor(Color.BLACK);
			g.drawOval(270, 140, 80, 80);
			g.drawOval(269, 139, 82, 82);
		} else if (shapes == 2) {
			g.setColor(color3);								// Square shape
			g.fillRect(270, 140, 80, 80);

			g.setColor(Color.BLACK);
			g.drawRect(270, 140, 80, 80);
			g.drawRect(269, 139, 82, 82);
		} else if (shapes == 3) {							// Reactangle shape
			g.setColor(color3);
			g.fillRect(240, 140, 140, 80);

			g.setColor(Color.BLACK);
			g.drawRect(240, 140, 140, 80);
			g.drawRect(239, 139, 142, 82);
		} else if (shapes == 4) {							// Triangle shape
			int[] xPoints1 = {265, 355, 310};
			int[] yPoints1 = {220, 220, 142};
			int[] xPoints2 = {264, 356, 310};
			int[] yPoints2 = {221, 221, 141};

			g.setColor(color3);
			g.fillPolygon(xPoints1, yPoints1, 3);

			g.setColor(Color.BLACK);
			g.drawPolygon(xPoints1, yPoints1, 3);
			g.drawPolygon(xPoints2, yPoints2, 3);
		}
	}
}

// Storage of data
class StarRasing {
	private int totalStar;

	public void setTotalStar(int i) {
		totalStar = i;
	}
	public int getTotalStar() {
		return totalStar;
	}

	public StarRasing() {}
}