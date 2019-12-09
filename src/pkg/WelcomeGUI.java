package pkg;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WelcomeGUI extends JFrame {

	private JPanel wholePage, panel1, panel2, panel3;


	private JButton p1button;
	private JButton p2button;
	private JButton p3button;
	private JButton p4button;

	
	private JTextField p1text, p2text, p3text, p4text;


	private JRadioButton screenRes1, screenRes2;

	private static String[] players;
	private Font font;
	private GridBagConstraints c1;
	private GridBagConstraints c3;


	private ButtonGroup group;

	private Insets i;

	private Color green;

	public WelcomeGUI() {

		initializeVariables();
	
		initializePlayer1(); //South Player

		
		initializePlayer2(); //West Player

		initializePlayer3(); //North Player 

		initializePlayer4(); //East player

		initializeButtons();

		setBackgroundColor();
	}
	
	
	private void initializeVariables() {
		c1 = new GridBagConstraints();
		GridBagConstraints c2 = new GridBagConstraints();
		c3 = new GridBagConstraints();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		wholePage = new JPanel();
		wholePage.setLayout(new GridBagLayout());
		add(wholePage);

		panel1.setLayout(new GridBagLayout());
		c2.gridx = 0;
		c2.gridy = 0;
		wholePage.add(panel1, c2);

		panel2.setLayout(new GridBagLayout());
		c2.gridx = 0;
		c2.gridy = 1;
		wholePage.add(panel2, c2);
		panel3.setLayout(new GridBagLayout());
		c1.gridx = 1;
		c1.gridy = 5;
		panel2.add(panel3, c1);

		players = new String[4];

		group = new ButtonGroup();

		i = new Insets(10, 20, 10, 20);
		font = new Font("Sans Serif", Font.BOLD, 30);

		Image img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("PNG-cards-1.3/Logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	private void initializePlayer1() {
	

		JLabel player1 = new JLabel("Player 1");
		player1.setFont(font);
		c1.gridx = 0;
		c1.gridy = 0;
		c1.insets = i;
		panel2.add(player1, c1);

		p1button = new JButton("Human");
		p1button.addActionListener(new HumanBotButton1());
		p1button.setFont(font);
		c1.gridx = 1;
		c1.gridy = 0;
		c1.insets = i;
		panel2.add(p1button, c1);

		p1text = new JTextField("Enter Name", 10);
		p1text.setHorizontalAlignment(JTextField.CENTER);
		p1text.setFont(font);
		c1.gridx = 2;
		c1.gridy = 0;
		c1.insets = i;
		panel2.add(p1text, c1);
	}

	
	private void initializePlayer2() {
		JLabel player2 = new JLabel("Player 2");
		player2.setFont(font);
		c1.gridx = 0;
		c1.gridy = 1;
		c1.insets = i;
		panel2.add(player2, c1);

		p2button = new JButton("   Bot   ");
		p2button.addActionListener(new HumanBotButton2());
		p2button.setFont(font);
		c1.gridx = 1;
		c1.gridy = 1;
		c1.insets = i;
		panel2.add(p2button, c1);

		p2text = new JTextField("Bot", 10);
		p2text.setHorizontalAlignment(JTextField.CENTER);
		p2text.setFont(font);
		p2text.setEditable(false);
		c1.gridx = 2;
		c1.gridy = 1;
		c1.insets = i;
		panel2.add(p2text, c1);
	}

	
	private void initializePlayer3() {
		JLabel player3 = new JLabel("Player 3");
		player3.setFont(font);
		c1.gridx = 0;
		c1.gridy = 2;
		c1.insets = i;
		panel2.add(player3, c1);

		p3button = new JButton("   Bot   ");
		p3button.addActionListener(new HumanBotButton3());
		p3button.setFont(font);
		c1.gridx = 1;
		c1.gridy = 2;
		c1.insets = i;
		panel2.add(p3button, c1);

		p3text = new JTextField("Bot", 10);
		p3text.setHorizontalAlignment(JTextField.CENTER);
		p3text.setFont(font);
		p3text.setEditable(false);
		c1.gridx = 2;
		c1.gridy = 2;
		c1.insets = i;
		panel2.add(p3text, c1);
	}

	
	private void initializePlayer4() {
		JLabel player4 = new JLabel("Player 4");
		c1.gridx = 0;
		c1.gridy = 3;
		c1.insets = i;
		player4.setFont(font);
		panel2.add(player4, c1);
		p4button = new JButton("   Bot   ");

		p4button.addActionListener(new HumanBotButton4());
		p4button.setFont(font);
		c1.gridx = 1;
		c1.gridy = 3;
		c1.insets = i;
		panel2.add(p4button, c1);

		p4text = new JTextField("Bot", 10);
		p4text.setFont(font);
		p4text.setHorizontalAlignment(JTextField.CENTER);
		p4text.setEditable(false);
		c1.gridx = 2;
		c1.gridy = 3;
		c1.insets = i;
		panel2.add(p4text, c1);
	}


	private void initializeButtons() {
		
		// play button
		JButton play = new JButton("  Play  ");
		play.addActionListener(new Play());
		play.setFont(font);
		c3.gridx = 1;
		c3.gridy = 0;
		c3.insets = i;
		panel3.add(play, c3);
	}


	private void setBackgroundColor() {
		wholePage.setBackground(Color.gray);
		panel1.setBackground(Color.gray);
		panel2.setBackground(Color.gray);
		panel3.setBackground(Color.gray);

	}

	
	private class HumanBotButton1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("Human")) {
				b.setText("   Bot   ");
				p1text.setEditable(false);
				p1text.setText("Bot");
			} else {
				b.setText("Human");
				p1text.setEditable(true);
				p1text.setText("Enter Name");
			}
		}
	}


	private class HumanBotButton2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("Human")) {
				b.setText("   Bot   ");
				p2text.setEditable(false);
				p2text.setText("Bot");
			} else {
				b.setText("Human");
				p2text.setEditable(true);
				p2text.setText("Enter Name");
			}
		}
	}


	private class HumanBotButton3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("Human")) {
				b.setText("   Bot   ");
				p3text.setEditable(false);
				p3text.setText("Bot");
			} else {
				b.setText("Human");
				p3text.setEditable(true);
				p3text.setText("Enter Name");
			}
		}
	}


	private class HumanBotButton4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("Human")) {
				b.setText("   Bot   ");
				p4text.setEditable(false);
				p4text.setText("Bot");
			} else {
				b.setText("Human");
				p4text.setEditable(true);
				p4text.setText("Enter Name");
			}
		}
	}

	


	private class Play implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (p1button.getText().equals("Bot"))
				players[0] = "Bot";
			else
				players[0] = p1text.getText();
			if (p2button.getText().equals("Bot"))
				players[1] = "Bot";
			else
				players[1] = p2text.getText();
			if (p3button.getText().equals("Bot"))
				players[2] = "Bot";
			else
				players[2] = p3text.getText();
			if (p4button.getText().equals("Bot"))
				players[3] = "Bot";
			else
				players[3] = p4text.getText();

			dispose();
			GUI gui = new GUI(1);
			gui.display();
		}
	}


	
	public void display() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension d = new Dimension(1366, 768);
		setMinimumSize(d);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setTitle("Welcome GUI");
	}


	public static String[] getPlayers() {
		return players;
	}


}
