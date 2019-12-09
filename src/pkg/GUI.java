package pkg;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GUI extends JFrame {

	
    private JPanel north;
    private JPanel east;
    private JPanel south;
    private JPanel west;
    private JPanel cardDisplaySouth;
    private JPanel cardDisplayEast;
    private JPanel cardDisplayWest;
    private JPanel cardDisplayNorth;
    private JPanel centerNorth;
    private JPanel centerEast;
    private JPanel centerSouth;
    private JPanel centerWest;
    private JPanel centerCenter;
    private JPanel centerPanel1;
    private JPanel centerPanel2;
    private JPanel centerPanel3;
    private JPanel eCardRow1;
    private JPanel eCardRow2;
    private JPanel eCardRow3;
    private JPanel wCardRow1;
    private JPanel wCardRow2;
    private JPanel wCardRow3;

    
    private JLabel[] playerS, playerN, playerE, playerW;

   
    private JLabel l1, l2, l3, l4;

   
    private JLabel roundWinner;

    
    private JTable scoring;

   
    private JButton play;
    private JButton nextTurnButton;
    private JButton nextPlayerReady;

   
    private MyModel model;

   
    private Insets margin;

    
    private GridBagConstraints constraints;

    
    private Font font;

    
    private ImageIcon horizontalHandIcon, verticalHandIcon;

    
    private Image horizontalHandImage, sizedHorizontalHandImage, verticalHandImage, sizedVerticalHandImage;

    
    private Color bgColor;

    
    private ArrayList<CardButton> myHand0, myHand1, myHand2, myHand3;

   
    private ArrayList<Card> playedCards, middlePile;

   
    private Card winner;

    
    private Driver driver;

    
    private int southTricksTaken, eastTricksTaken, northTricksTaken, westTricksTaken, southTricksBid, eastTricksBid,
            westTricksBid, northTricksBid, tricksBidNS, tricksBidEW, ptsScoredNS, ptsScoredEW, scoreNS, scoreEW,
            sandbagsNS, sandbagsEW, totalBagsNS, totalBagsEW, turnNumber, nextPlayer, imageNum, numHumans, screenResNum;

    
    private boolean spadesPlayed;

    public GUI(int screenRes) {
        
        setLayout(new BorderLayout());

        
        initializeVariables(screenRes);

        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        
        initializeJTable();

        
        initializeNorthPanel();

        
        initializeEastPanel();

       
        initializeWestPanel();

       
        initializeSouthPanel();

        
        initializeCenterPanel();

       
       // initializeMenuBar();

        
        setBackgroundColor(bgColor);

        
        scoreNS = 0;
        scoreEW = 0;
        totalBagsNS = 0;
        totalBagsEW = 0;
    }

    
    public GUI(int screenRes, int totalScoreNS, int totalScoreEW, int bagsNS, int bagsEW) {
        
        setLayout(new BorderLayout());

       
        initializeVariables(screenRes);

        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }

        
        initializeJTable();

        
        initializeNorthPanel();

       
        initializeEastPanel();

       
        initializeWestPanel();

       
        initializeSouthPanel();

        
        initializeCenterPanel();

        
        //initializeMenuBar();

       
        setBackgroundColor(bgColor);

        
        scoreNS = totalScoreNS;
        scoreEW = totalScoreEW;
        totalBagsNS = bagsNS;
        totalBagsEW = bagsEW;
    }

	
    private void initializeVariables(int screenRes) {
       
        screenResNum = screenRes;

       
        driver = new Driver(screenRes);

        
        if (screenResNum == 1)
            font = new Font("Sans Serif", Font.PLAIN, 20);
        else
            font = new Font("Sans Serif", Font.BOLD, 20);

       
        horizontalHandIcon = new ImageIcon("src/PNG-cards-1.3/CardBack13.png");
        horizontalHandImage = horizontalHandIcon.getImage();
        verticalHandIcon = new ImageIcon("src/PNG-cards-1.3/CardBackSide13.png");
        verticalHandImage = verticalHandIcon.getImage();

        if (screenResNum == 1) {
            sizedHorizontalHandImage = horizontalHandImage.getScaledInstance(350, 100, Image.SCALE_FAST);
            sizedVerticalHandImage = verticalHandImage.getScaledInstance(100, 350, Image.SCALE_FAST);
        } else {
            sizedHorizontalHandImage = horizontalHandImage.getScaledInstance(700, 200, Image.SCALE_FAST);
            sizedVerticalHandImage = verticalHandImage.getScaledInstance(200, 700, Image.SCALE_FAST);
        }

        
        imageNum = 13;

      
        southTricksTaken = 0;
        eastTricksTaken = 0;
        northTricksTaken = 0;
        westTricksTaken = 0;

        southTricksBid = 0;
        westTricksBid = 0;
        northTricksBid = 0;
        eastTricksBid = 0;

       
        turnNumber = 0;

        
        nextPlayer = 0;

        tricksBidNS = 0;
        tricksBidEW = 0;
        ptsScoredNS = 0;
        ptsScoredEW = 0;
        sandbagsNS = 0;
        sandbagsEW = 0;

        numHumans = 0;

       
        if (driver.players[0] instanceof Human) {
            myHand0 = new ArrayList<>();
            numHumans++;
        }
        if (driver.players[1] instanceof Human) {
            myHand1 = new ArrayList<>();
            numHumans++;
        }
        if (driver.players[2] instanceof Human) {
            myHand2 = new ArrayList<>();
            numHumans++;
        }
        if (driver.players[3] instanceof Human) {
            myHand3 = new ArrayList<>();
            numHumans++;
        }

       
        winner = null;

        
        bgColor = Color.gray;

       
        constraints = new GridBagConstraints();

       
        margin = new Insets(0, 0, 0, 0);

       
        playedCards = new ArrayList<>(4);
        playedCards.add(null);
        playedCards.add(null);
        playedCards.add(null);
        playedCards.add(null);

       
        middlePile = new ArrayList<>();

       
        spadesPlayed = false;
    }

   
    private void initializeJTable() {
        model = new MyModel();
        scoring = new JTable(model);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        DefaultTableCellRenderer header = new DefaultTableCellRenderer();
        header.setHorizontalAlignment(SwingConstants.CENTER);
        center.setHorizontalAlignment(SwingConstants.CENTER);
        if (screenResNum == 1) {
            center.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            scoring.getTableHeader().setFont(new Font("Sans Serif", Font.BOLD, 15));
            scoring.getColumnModel().getColumn(0).setMinWidth(110);
            scoring.getColumnModel().getColumn(1).setMinWidth(120);
            scoring.getColumnModel().getColumn(2).setMinWidth(110);
        } else {
            center.setFont(font);
            scoring.getTableHeader().setFont(font);
            scoring.getColumnModel().getColumn(0).setPreferredWidth(170);
            scoring.getColumnModel().getColumn(1).setPreferredWidth(170);
            scoring.getColumnModel().getColumn(2).setPreferredWidth(170);
        }
        for (int x = 0; x < scoring.getColumnCount(); x++) {
            scoring.getColumnModel().getColumn(x).setCellRenderer(center);
        }
        scoring.setRowHeight(30);
        scoring.getTableHeader().setReorderingAllowed(false);
    }

    
    private void initializeNorthPanel() {
       
        north = new JPanel();
        add(north, BorderLayout.NORTH);
        north.setLayout(new GridBagLayout());
        GridBagConstraints cons1 = new GridBagConstraints();

        playerN = new JLabel[4];
        cardDisplayNorth = new JPanel();
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.gridx = 0;
        cons1.gridy = 0;
        cons1.gridheight = 4;
        north.add(cardDisplayNorth, cons1);

        
        playerN[0] = new JLabel(new ImageIcon(sizedHorizontalHandImage));
        cardDisplayNorth.add(playerN[0]);

       
        playerN[1] = new JLabel(driver.playerAry[2]);
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.gridx = 1;
        cons1.gridy = 0;
        cons1.gridheight = 1;
        north.add(playerN[1], cons1);

       
        playerN[2] = new JLabel("Tricks Bid: ");
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.gridx = 1;
        cons1.gridy = 1;
        north.add(playerN[2], cons1);

        playerN[3] = new JLabel("Tricks Taken: ");
        cons1.fill = GridBagConstraints.HORIZONTAL;
        cons1.gridx = 1;
        cons1.gridy = 2;
        north.add(playerN[3], cons1);

       
        for (JLabel aPlayerN : playerN) {
            aPlayerN.setFont(font);
        }
    }

    
    private void initializeEastPanel() {
        
        east = new JPanel();
        add(east, BorderLayout.EAST);
        east.setLayout(new GridBagLayout());
        GridBagConstraints cons2 = new GridBagConstraints();

        playerE = new JLabel[4];
        cardDisplayEast = new JPanel();
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.gridx = 0;
        cons2.gridy = 0;
        east.add(cardDisplayEast, cons2);

        
        playerE[0] = new JLabel(new ImageIcon(sizedVerticalHandImage));
        cardDisplayEast.add(playerE[0]);

        
        playerE[1] = new JLabel(driver.playerAry[3]);
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.gridx = 0;
        cons2.gridy = 1;
        east.add(playerE[1], cons2);

       
        playerE[2] = new JLabel("Tricks Bid: ");
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.gridx = 0;
        cons2.gridy = 2;
        east.add(playerE[2], cons2);

       
        playerE[3] = new JLabel("Tricks Taken: ");
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.gridx = 0;
        cons2.gridy = 3;
        east.add(playerE[3], cons2);

        
        eCardRow1 = new JPanel();
        eCardRow2 = new JPanel();
        eCardRow3 = new JPanel();

        // Set font for all JLabels in array
        for (JLabel aPlayerE : playerE) {
            aPlayerE.setFont(font);
        }
    }

   
    private void initializeWestPanel() {
        // West Panel
        west = new JPanel();
        add(west, BorderLayout.WEST);
        west.setLayout(new GridBagLayout());
        GridBagConstraints cons3 = new GridBagConstraints();

        playerW = new JLabel[4];
        cardDisplayWest = new JPanel();
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.gridx = 0;
        cons3.gridy = 3;
        west.add(cardDisplayWest, cons3);

       
        playerW[0] = new JLabel(new ImageIcon(sizedVerticalHandImage));
        cardDisplayWest.add(playerW[0]);

        
        playerW[1] = new JLabel(driver.playerAry[1]);
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.gridx = 0;
        cons3.gridy = 0;
        west.add(playerW[1], cons3);

       
        playerW[2] = new JLabel("Tricks Bid: ");
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.gridx = 0;
        cons3.gridy = 1;
        west.add(playerW[2], cons3);

       
        playerW[3] = new JLabel("Tricks Taken: ");
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.gridx = 0;
        cons3.gridy = 2;
        west.add(playerW[3], cons3);

        wCardRow1 = new JPanel();
        wCardRow2 = new JPanel();
        wCardRow3 = new JPanel();

        
        for (JLabel aPlayerW : playerW) {
            aPlayerW.setFont(font);
        }
    }

   
    private void initializeSouthPanel() {
        // South panel
        south = new JPanel();
        add(south, BorderLayout.SOUTH);
        south.setLayout(new GridBagLayout());
        GridBagConstraints cons4 = new GridBagConstraints();

        playerS = new JLabel[4];
        cardDisplaySouth = new JPanel();
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.gridx = 1;
        cons4.gridy = 0;
        cons4.gridheight = 4;
        south.add(cardDisplaySouth, cons4);

       
        playerS[0] = new JLabel(new ImageIcon(sizedHorizontalHandImage));
        cardDisplaySouth.add(playerS[0]);

        
        playerS[1] = new JLabel(driver.playerAry[0]);
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.gridx = 0;
        cons4.gridy = 0;
        cons4.gridheight = 1;
        south.add(playerS[1], cons4);

       
        playerS[2] = new JLabel("Tricks Bid: ");
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.gridx = 0;
        cons4.gridy = 1;
        south.add(playerS[2], cons4);

        playerS[3] = new JLabel("Tricks Taken: ");
        cons4.fill = GridBagConstraints.HORIZONTAL;
        cons4.gridx = 0;
        cons4.gridy = 2;
        south.add(playerS[3], cons4);

        // Set font for all JLabels in array
        for (JLabel player : playerS) {
            player.setFont(font);
        }
    }

    private void initializeCenterPanel() {
        // JPanels used to construct layout
        JPanel center = new JPanel();
        centerNorth = new JPanel();
        centerEast = new JPanel();
        centerSouth = new JPanel();
        centerWest = new JPanel();
        centerCenter = new JPanel();
        centerPanel1 = new JPanel();
        centerPanel2 = new JPanel();
        centerPanel3 = new JPanel();

        // Set the layout for all JPanels
        center.setLayout(new BorderLayout());
        centerNorth.setLayout(new BorderLayout());
        centerEast.setLayout(new BorderLayout());
        centerSouth.setLayout(new BorderLayout());
        centerWest.setLayout(new BorderLayout());
        centerCenter.setLayout(new GridLayout(1, 3));
        centerPanel1.setLayout(new GridBagLayout());
        centerPanel2.setLayout(new GridBagLayout());
        centerPanel3.setLayout(new GridBagLayout());

        // Add JPanels to display
        add(center, BorderLayout.CENTER);
        center.add(centerNorth, BorderLayout.NORTH);
        center.add(centerEast, BorderLayout.EAST);
        center.add(centerSouth, BorderLayout.SOUTH);
        center.add(centerWest, BorderLayout.WEST);
        center.add(centerCenter, BorderLayout.CENTER);
        centerCenter.add(centerPanel1);
        centerCenter.add(centerPanel2);
        centerCenter.add(centerPanel3);

       
        play = new JButton("Play!");
        
        play.setFont(font);
       
        if (screenResNum == 1)
            play.setPreferredSize(new Dimension(100, 50));
        else
            play.setPreferredSize(new Dimension(200, 100));
       
        play.addActionListener(new Play());
        
        centerPanel1.add(play);

       
        roundWinner = new JLabel("");
        
        if (screenResNum == 1)
            roundWinner.setFont(new Font("Sans Serif", Font.BOLD, 30));
        else
            roundWinner.setFont(new Font("Sans Serif", Font.BOLD, 40));
        
        centerPanel2.add(roundWinner);

        
        nextTurnButton = new JButton("Next Turn");
        
        nextTurnButton.setFont(font);
        
        if (screenResNum == 1)
            nextTurnButton.setPreferredSize(new Dimension(130, 50));
        else
            nextTurnButton.setPreferredSize(new Dimension(200, 100));
        
        nextTurnButton.addActionListener(new NextTurn());
        
        nextTurnButton.setVisible(false);
       
        centerPanel3.add(nextTurnButton);

        
        nextPlayerReady = new JButton("Next Player");
        
        nextPlayerReady.setFont(font);
       
        if (screenResNum == 1)
            nextPlayerReady.setPreferredSize(new Dimension(130, 50));
        else
            nextPlayerReady.setPreferredSize(new Dimension(200, 100));
       
        nextPlayerReady.addActionListener(new NextHumanPlayer());
       
        nextPlayerReady.setVisible(false);
        
        centerPanel2.add(nextPlayerReady);
    }

   
//    private void initializeMenuBar() {
//    
//        JMenuBar menuBar = new JMenuBar();
//        setJMenuBar(menuBar);
//
//    
//        JMenu file = new JMenu("File");
//        file.setFont(font);
//        file.setFont(font);
//        menuBar.add(file);
//        JMenuItem newGame = new JMenuItem("New Game");
//        newGame.setFont(font);
//        file.add(newGame);
//	
//        JMenuItem exit = new JMenuItem("Exit");
//        exit.setFont(font);
//        file.add(exit);
//
//       
//        JMenu settings = new JMenu("Settings");
//        settings.setFont(font);
//        menuBar.add(settings);
//        JMenu changeColor = new JMenu("Change Background Color...");
//        changeColor.setFont(font);
//        settings.add(changeColor);
//        JMenuItem grey = new JMenuItem("Grey");
//        grey.setFont(font);
//        JMenuItem blue = new JMenuItem("Blue");
//        blue.setFont(font);
//        JMenuItem green = new JMenuItem("Green");
//        green.setFont(font);
//        JMenuItem red = new JMenuItem("Red");
//        red.setFont(font);
//        changeColor.add(blue);
//        changeColor.add(grey);
//        changeColor.add(green);
//        changeColor.add(red);
//
//        // Add action listeners to all menu options
//        exit.addActionListener(new ExitEvent());
//        newGame.addActionListener(new NewGameEvent());
//        blue.addActionListener(new BlueEvent());
//        grey.addActionListener(new GreyEvent());
//        green.addActionListener(new GreenEvent());
//        red.addActionListener(new RedEvent());
//    }

	
    private void playableCards(ArrayList<CardButton> myHand, ArrayList<Card> pile) {
      
        if (pile == null || pile.size() == 0) {
            for (CardButton aMyHand : myHand) {
                if (aMyHand.getCard().getSuit() == Suit.SPADE && !spadesPlayed)
                    aMyHand.setEnabled(false);
                else
                    aMyHand.setEnabled(true);
            }
            return;
        }

       
        Card card1 = pile.get(0);
       
        int count = 0;

        
        for (CardButton aMyHand : myHand) {
            if (aMyHand.getCard().getSuit() != card1.getSuit()) {
                aMyHand.setEnabled(false);
                count++;
            }
        }

       
        if (count == myHand.size()) {
            for (CardButton aMyHand : myHand) {
                aMyHand.setEnabled(true);
            }
        }

    }

   
    private int pointsScored(int p1TricksBid, int p2TricksBid, int p1TricksTaken, int p2TricksTaken) {
        int score;
        if ((p1TricksTaken + p2TricksTaken) < (p1TricksBid + p2TricksBid))
            score = -(10 * (p1TricksBid + p2TricksBid));
        else if ((p1TricksTaken + p2TricksTaken) == (p1TricksBid + p2TricksBid))
            score = 10 * (p1TricksBid + p2TricksBid);
        else {
            score = (10 * (p1TricksBid + p2TricksBid))
                    + ((p1TricksTaken + p2TricksTaken) - (p1TricksBid + p2TricksBid));
        }

        return score;
    }

   
    private int sandBags(int p1TricksBid, int p2TricksBid, int p1TricksTaken, int p2TricksTaken) {
        int bags = 0;
        // If more tricks are taken than bid, number of over tricks = number of
        // sand bags
        if ((p1TricksTaken + p2TricksTaken) > (p1TricksBid + p2TricksBid))
            bags = (p1TricksTaken + p2TricksTaken) - (p1TricksBid + p2TricksBid);
        return bags;
    }

   
    private void setBackgroundColor(Color bgColor) {
        north.setBackground(bgColor);
        south.setBackground(bgColor);
        west.setBackground(bgColor);
        east.setBackground(bgColor);
        eCardRow1.setBackground(bgColor);
        eCardRow2.setBackground(bgColor);
        eCardRow3.setBackground(bgColor);
        wCardRow1.setBackground(bgColor);
        wCardRow2.setBackground(bgColor);
        wCardRow3.setBackground(bgColor);
        cardDisplaySouth.setBackground(bgColor);
        cardDisplayEast.setBackground(bgColor);
        cardDisplayWest.setBackground(bgColor);
        cardDisplayNorth.setBackground(bgColor);
        centerNorth.setBackground(bgColor);
        centerEast.setBackground(bgColor);
        centerSouth.setBackground(bgColor);
        centerWest.setBackground(bgColor);
        centerCenter.setBackground(bgColor);
        centerPanel1.setBackground(bgColor);
        centerPanel2.setBackground(bgColor);
        centerPanel3.setBackground(bgColor);
    }

    private void updateCardImages() {
        if (screenResNum == 1) {
            horizontalHandIcon = new ImageIcon("src/PNG-cards-1.3/CardBack" + --imageNum + ".png");
            horizontalHandImage = horizontalHandIcon.getImage();
            sizedHorizontalHandImage = horizontalHandImage.getScaledInstance((50 + imageNum * 25), 100,
                    Image.SCALE_FAST);

            verticalHandIcon = new ImageIcon("src/PNG-cards-1.3/CardBackSide" + imageNum + ".png");
            verticalHandImage = verticalHandIcon.getImage();
            sizedVerticalHandImage = verticalHandImage.getScaledInstance(100, (50 + imageNum * 25), Image.SCALE_FAST);
        } else {
            horizontalHandIcon = new ImageIcon("src/PNG-cards-1.3/CardBack" + --imageNum + ".png");
            horizontalHandImage = horizontalHandIcon.getImage();
            sizedHorizontalHandImage = horizontalHandImage.getScaledInstance((100 + imageNum * 50), 200,
                    Image.SCALE_FAST);

            verticalHandIcon = new ImageIcon("src/PNG-cards-1.3/CardBackSide" + imageNum + ".png");
            verticalHandImage = verticalHandIcon.getImage();
            sizedVerticalHandImage = verticalHandImage.getScaledInstance(200, (100 + imageNum * 50), Image.SCALE_FAST);
        }

        playerE[0].setIcon(new ImageIcon(sizedVerticalHandImage));
        playerS[0].setIcon(new ImageIcon(sizedHorizontalHandImage));
        playerW[0].setIcon(new ImageIcon(sizedVerticalHandImage));
        playerN[0].setIcon(new ImageIcon(sizedHorizontalHandImage));
    }

	
    public void display() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension d = new Dimension(1366, 768);
        setMinimumSize(d);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setTitle("Spades");
    }

    
    private class Play implements ActionListener {

       
        public void actionPerformed(ActionEvent e) {
          
            driver.dealHands();
           
            play.setVisible(false);
            
            PlayCard p = new PlayCard();

            // create bid
            southTricksBid = driver.players[0].getHand().getNumAces()+driver.players[0].getHand().getNumSpades();
            westTricksBid = driver.players[1].getHand().getNumAces()+driver.players[1].getHand().getNumSpades();
            northTricksBid = driver.players[2].getHand().getNumAces()+driver.players[2].getHand().getNumSpades();
            eastTricksBid = driver.players[3].getHand().getNumAces()+driver.players[3].getHand().getNumSpades();
            
            
            
            playerS[2].setText("Tricks Bid: " + southTricksBid);
            playerW[2].setText("Tricks Bid: " + westTricksBid);
            playerN[2].setText("Tricks Bid: " + northTricksBid);
            playerE[2].setText("Tricks Bid: " + eastTricksBid);

            // Calculates each team's number of tricks bid for use in scoring
            tricksBidNS = northTricksBid + southTricksBid;
            tricksBidEW = eastTricksBid + westTricksBid;

           
            if (driver.players[1] instanceof Human) {
                cardDisplayWest.setLayout(new GridBagLayout());
                GridBagConstraints cons = new GridBagConstraints();
                cons.gridx = 0;
                cons.gridy = 0;
                cardDisplayWest.add(wCardRow1, cons);
                cons.gridx = 0;
                cons.gridy = 1;
                cardDisplayWest.add(wCardRow2, cons);
                cons.gridx = 0;
                cons.gridy = 2;
                cardDisplayWest.add(wCardRow3, cons);
                for (int i = 0; i < 13; i++) {
                    CardButton button = new CardButton(driver.players[1].getHand().getCardAtPosition(i).getImage(),
                            driver.players[1].getHand().getCardAtPosition(i));
                    button.setMargin(margin);
                    button.addActionListener(p);
                    myHand1.add(i, button);
                    myHand1.get(i).setVisible(false);
                    if (i < 4) {
                        wCardRow1.add(myHand1.get(i));
                    } else if (i < 8) {
                        wCardRow2.add(myHand1.get(i));
                    } else
                        wCardRow3.add(myHand1.get(i));
                }
            }
            if (driver.players[2] instanceof Human) {
                for (int i = 0; i < 13; i++) {
                    CardButton button = new CardButton(driver.players[2].getHand().getCardAtPosition(i).getImage(),
                            driver.players[2].getHand().getCardAtPosition(i));
                    button.setMargin(margin);
                    button.addActionListener(p);
                    myHand2.add(i, button);
                    cardDisplayNorth.add(myHand2.get(i));
                    myHand2.get(i).setVisible(false);
                }
            }
            if (driver.players[3] instanceof Human) {
                cardDisplayEast.setLayout(new GridBagLayout());
                GridBagConstraints cons = new GridBagConstraints();
                cons.gridx = 0;
                cons.gridy = 0;
                cardDisplayEast.add(eCardRow1, cons);
                cons.gridx = 0;
                cons.gridy = 1;
                cardDisplayEast.add(eCardRow2, cons);
                cons.gridx = 0;
                cons.gridy = 2;
                cardDisplayEast.add(eCardRow3, cons);
                for (int i = 0; i < 13; i++) {
                    CardButton button = new CardButton(driver.players[3].getHand().getCardAtPosition(i).getImage(),
                            driver.players[3].getHand().getCardAtPosition(i));
                    button.setMargin(margin);
                    button.addActionListener(p);
                    myHand3.add(i, button);
                    myHand3.get(i).setVisible(false);
                    if (i < 4) {
                        eCardRow1.add(myHand3.get(i));
                    } else if (i < 8) {
                        eCardRow2.add(myHand3.get(i));
                    } else
                        eCardRow3.add(myHand3.get(i));
                }
            }
            if (driver.players[0] instanceof Human) {
                for (int i = 0; i < 13; i++) {
                    CardButton button = new CardButton(driver.players[0].getHand().getCardAtPosition(i).getImage(),
                            driver.players[0].getHand().getCardAtPosition(i));
                    button.setMargin(margin);
                    button.addActionListener(p);
                    myHand0.add(i, button);
                    cardDisplaySouth.add(myHand0.get(i));
                    myHand0.get(i).setVisible(false);
                }
            }

            int randomNum = (int) (Math.random() * 4);
            if (randomNum == 0) {
                driver.players[0].setTurnToPlay(true);
                if (driver.players[0] instanceof Human) {
                    playerS[0].setVisible(false);
                    for (int i = 0; i < 13; i++) {
                        myHand0.get(i).setVisible(true);
                    }
                    playableCards(myHand0, middlePile);
                } else {
                    CardButton b = new CardButton();
                    b.addActionListener(p);
                    b.doClick();
                }
            } else if (randomNum == 1) {
                driver.players[1].setTurnToPlay(true);
                if (driver.players[1] instanceof Human) {
                    playerW[0].setVisible(false);
                    for (int i = 0; i < 13; i++) {
                        myHand1.get(i).setVisible(true);
                    }
                    playableCards(myHand1, middlePile);
                } else {
                    CardButton b = new CardButton();
                    b.addActionListener(p);
                    b.doClick();
                }
            } else if (randomNum == 2) {
                driver.players[2].setTurnToPlay(true);
                if (driver.players[2] instanceof Human) {
                    playerN[0].setVisible(false);
                    for (int i = 0; i < 13; i++) {
                        myHand2.get(i).setVisible(true);
                    }
                    playableCards(myHand2, middlePile);
                } else {
                    CardButton b = new CardButton();
                    b.addActionListener(p);
                    b.doClick();
                }
            } else if (randomNum == 3) {
                driver.players[3].setTurnToPlay(true);
                if (driver.players[3] instanceof Human) {
                    playerE[0].setVisible(false);
                    for (int i = 0; i < 13; i++) {
                        myHand3.get(i).setVisible(true);
                    }
                    playableCards(myHand3, middlePile);
                } else {
                    CardButton b = new CardButton();
                    b.addActionListener(p);
                    b.doClick();
                }
            }
        }
    }

    private class PlayCard implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            CardButton b = (CardButton) e.getSource();

           
            while (turnNumber < 4) {
               
                if (driver.players[0].getTurnToPlay()) {
                  
                    if (driver.players[0] instanceof Human) {
                        Card c = b.getCard();
                        if (c.getSuit() == Suit.SPADE)
                            spadesPlayed = true;
                        playedCards.set(0, c);
                        middlePile.add(c);
                        l1 = new JLabel(b.getIcon());
                        cardDisplaySouth.remove(b);
                        myHand0.remove(b);
                        for (CardButton aMyHand0 : myHand0) {
                            aMyHand0.setVisible(false);
                        }
                        centerSouth.add(l1, BorderLayout.CENTER);
                        driver.players[0].getHand().remove(c);
                        playerS[0].setVisible(true);
                       
                    } else if (driver.players[0] instanceof Bot) {
                        Card c = ((Bot) driver.players[0]).playCard(middlePile);
                        c.setScreenRes(screenResNum);
                        if (c.getSuit() == Suit.SPADE)
                            spadesPlayed = true;
                        playedCards.set(0, c);
                        middlePile.add(c);
                        l1 = new JLabel(c.getImage());
                        centerSouth.add(l1, BorderLayout.CENTER);
                    }

                   
                    turnNumber++;
                    driver.players[0].setTurnToPlay(false);
                    driver.players[1].setTurnToPlay(true);
                   
                    if (driver.players[1] instanceof Human && turnNumber < 4) {
                        if (numHumans == 1) {
                            playerW[0].setVisible(false);
                            for (CardButton aMyHand1 : myHand1) {
                                aMyHand1.setVisible(true);
                                aMyHand1.setEnabled(true);
                            }
                            playableCards(myHand1, middlePile);
                            return;
                        } else {
                            nextPlayerReady.setVisible(true);
                            return;
                        }
                    }
                }
               
                if (driver.players[1].getTurnToPlay() && turnNumber < 4) {

                    if (driver.players[1] instanceof Human) {
                        Card c = b.getCard();
                        if (c.getSuit() == Suit.SPADE)
                            spadesPlayed = true;
                        playedCards.set(1, c);
                        middlePile.add(c);
                        l2 = new JLabel(b.getIcon());
                        for (int i = 0; i < wCardRow1.getComponentCount(); i++) {
                            if (((CardButton) wCardRow1.getComponent(i)).getCard().equals(c))
                                wCardRow1.remove(b);
                        }
                        for (int i = 0; i < wCardRow2.getComponentCount(); i++) {
                            if (((CardButton) wCardRow2.getComponent(i)).getCard().equals(c))
                                wCardRow2.remove(b);
                        }
                        for (int i = 0; i < wCardRow3.getComponentCount(); i++) {
                            if (((CardButton) wCardRow3.getComponent(i)).getCard().equals(c))
                                wCardRow3.remove(b);
                        }
                        myHand1.remove(b);
                        for (CardButton aMyHand1 : myHand1) {
                            aMyHand1.setVisible(false);
                        }
                        centerWest.add(l2, BorderLayout.CENTER);
                        driver.players[1].getHand().remove(c);
                        playerW[0].setVisible(true);
                    } else if (driver.players[1] instanceof Bot) {
                        Card c = ((Bot) (driver.players[1])).playCard(middlePile);
                        c.setScreenRes(screenResNum);
                        if (c.getSuit() == Suit.SPADE)
                            spadesPlayed = true;
                        playedCards.set(1, c);
                        middlePile.add(c);
                        l2 = new JLabel(c.getImage());
                        centerWest.add(l2, BorderLayout.CENTER);
                    }
                    turnNumber++;
                    driver.players[1].setTurnToPlay(false);
                    driver.players[2].setTurnToPlay(true);
                    if (driver.players[2] instanceof Human && turnNumber < 4) {
                        if (numHumans == 1) {
                            playerN[0].setVisible(false);
                            for (CardButton aMyHand2 : myHand2) {
                                aMyHand2.setVisible(true);
                                aMyHand2.setEnabled(true);
                            }
                            playableCards(myHand2, middlePile);

                            return;
                        } else {
                            nextPlayerReady.setVisible(true);
                            return;
                        }
                    }
                }
               
                if (driver.players[2].getTurnToPlay() && turnNumber < 4) {
                    if (driver.players[2] instanceof Human) {
                        Card c = b.getCard();
                        if (c.getSuit() == Suit.SPADE)
                            spadesPlayed = true;
                        playedCards.set(2, c);
                        middlePile.add(c);
                        l3 = new JLabel(b.getIcon());
                        cardDisplayNorth.remove(b);
                        myHand2.remove(b);
                        for (CardButton aMyHand2 : myHand2) {
                            aMyHand2.setVisible(false);
                        }
                        centerNorth.add(l3, BorderLayout.CENTER);
                        driver.players[2].getHand().remove(c);
                        playerN[0].setVisible(true);
                    } else if (driver.players[2] instanceof Bot) {
                        Card c = ((Bot) (driver.players[2])).playCard(middlePile);
                        c.setScreenRes(screenResNum);
                        if (c.getSuit() == Suit.SPADE)
                            spadesPlayed = true;
                        playedCards.set(2, c);
                        middlePile.add(c);
                        l3 = new JLabel(c.getImage());
                        centerNorth.add(l3, BorderLayout.CENTER);
                    }
                    turnNumber++;
                    driver.players[2].setTurnToPlay(false);
                    driver.players[3].setTurnToPlay(true);
                    if (driver.players[3] instanceof Human && turnNumber < 4) {
                        if (numHumans == 1) {
                            playerE[0].setVisible(false);
                            for (CardButton aMyHand3 : myHand3) {
                                aMyHand3.setVisible(true);
                                aMyHand3.setEnabled(true);
                            }
                            playableCards(myHand3, middlePile);
                            return;
                        } else {
                            nextPlayerReady.setVisible(true);
                            return;
                        }
                    }
                }
              
                if (driver.players[3].getTurnToPlay() && turnNumber < 4) {
                    if (driver.players[3] instanceof Human) {
                        Card c = b.getCard();
                        if (c.getSuit() == Suit.SPADE)
                            spadesPlayed = true;
                        playedCards.set(3, c);
                        middlePile.add(c);
                        l4 = new JLabel(b.getIcon());
                        for (int i = 0; i < eCardRow1.getComponentCount(); i++) {
                            if (((CardButton) eCardRow1.getComponent(i)).getCard().equals(c))
                                eCardRow1.remove(b);
                        }
                        for (int i = 0; i < eCardRow2.getComponentCount(); i++) {
                            if (((CardButton) eCardRow2.getComponent(i)).getCard().equals(c))
                                eCardRow2.remove(b);
                        }
                        for (int i = 0; i < eCardRow3.getComponentCount(); i++) {
                            if (((CardButton) eCardRow3.getComponent(i)).getCard().equals(c))
                                eCardRow3.remove(b);
                        }

                        myHand3.remove(b);
                        for (CardButton aMyHand3 : myHand3) {
                            aMyHand3.setVisible(false);
                        }
                        centerEast.add(l4, BorderLayout.CENTER);
                        driver.players[3].getHand().remove(c);
                        playerE[0].setVisible(true);
                    } else if (driver.players[3] instanceof Bot) {
                        Card c = ((Bot) (driver.players[3])).playCard(middlePile);
                        c.setScreenRes(screenResNum);
                        if (c.getSuit() == Suit.SPADE)
                            spadesPlayed = true;
                        playedCards.set(3, c);
                        middlePile.add(c);
                        l4 = new JLabel(c.getImage());
                        centerEast.add(l4, BorderLayout.CENTER);
                    }
                    turnNumber++;
                    driver.players[3].setTurnToPlay(false);
                    driver.players[0].setTurnToPlay(true);
                    if (driver.players[0] instanceof Human && turnNumber < 4) {
                        if (numHumans == 1) {
                            playerS[0].setVisible(false);
                            for (CardButton aMyHand0 : myHand0) {
                                aMyHand0.setVisible(true);
                                aMyHand0.setEnabled(true);
                            }
                            playableCards(myHand0, middlePile);
                            return;
                        } else {
                            nextPlayerReady.setVisible(true);
                            return;
                        }
                    }
                }
            } 
            winner = driver.determineWinner(middlePile);

           
            if (winner.equals(playedCards.get(0)))

            {
               
                if (driver.players[0] instanceof Human) {
                    roundWinner.setText(driver.playerAry[0] + " wins!");
                    
                } else {
                    roundWinner.setText("South Bot wins!");
                }
                playerS[3].setText("Tricks Taken: " + ++southTricksTaken);
                nextPlayer = 0;
                driver.players[0].setTurnToPlay(true);
                driver.players[1].setTurnToPlay(false);
                driver.players[2].setTurnToPlay(false);
                driver.players[3].setTurnToPlay(false);
            } else if (winner.equals(playedCards.get(1)))

            {
                if (driver.players[1] instanceof Human) {
                    roundWinner.setText(driver.playerAry[1] + " wins!");
                } else {
                    roundWinner.setText("West Bot wins!");
                }
                playerW[3].setText("Tricks Taken: " + ++westTricksTaken);
                nextPlayer = 1;
                driver.players[1].setTurnToPlay(true);
                driver.players[0].setTurnToPlay(false);
                driver.players[2].setTurnToPlay(false);
                driver.players[3].setTurnToPlay(false);
            } else if (winner.equals(playedCards.get(2)))

            {
                if (driver.players[2] instanceof Human) {
                    roundWinner.setText(driver.playerAry[2] + " wins!");
                } else {
                    roundWinner.setText("North Bot wins!");
                }
                playerN[3].setText("Tricks Taken: " + ++northTricksTaken);
                nextPlayer = 2;
                driver.players[2].setTurnToPlay(true);
                driver.players[1].setTurnToPlay(false);
                driver.players[0].setTurnToPlay(false);
                driver.players[3].setTurnToPlay(false);
            } else if (winner.equals(playedCards.get(3)))

            {
                if (driver.players[3] instanceof Human) {
                    roundWinner.setText(driver.playerAry[3] + " wins!");
                } else {
                    roundWinner.setText("East Bot wins!");
                }
                playerE[3].setText("TricksTaken: " + ++eastTricksTaken);
                nextPlayer = 3;
                driver.players[3].setTurnToPlay(true);
                driver.players[1].setTurnToPlay(false);
                driver.players[2].setTurnToPlay(false);
                driver.players[0].setTurnToPlay(false);
            }

            
            updateCardImages();

            
            nextTurnButton.setVisible(true);

        }
    }

   
    private class NextTurn implements ActionListener {

       
        public void actionPerformed(ActionEvent e) {
           
            nextTurnButton.setVisible(false);

           
            middlePile.clear();
            roundWinner.setText("");
            centerSouth.remove(l1);
            centerWest.remove(l2);
            centerNorth.remove(l3);
            centerEast.remove(l4);
            turnNumber = 0;
            playedCards.set(0, null);
            playedCards.set(1, null);
            playedCards.set(2, null);
            playedCards.set(3, null);

           
            if (driver.players[0].getHand().size() == 0) {
               
                model.setValueAt(((Integer) tricksBidNS).toString(), 0, 1);
                model.setValueAt(((Integer) (tricksBidEW)).toString(), 0, 2);
                model.setValueAt(((Integer) (southTricksTaken + northTricksTaken)).toString(), 1, 1);
                model.setValueAt(((Integer) (eastTricksTaken + westTricksTaken)).toString(), 1, 2);
                sandbagsNS = sandBags(northTricksBid, southTricksBid, northTricksTaken, southTricksTaken);
                sandbagsEW = sandBags(eastTricksBid, westTricksBid, westTricksTaken, eastTricksTaken);
                model.setValueAt(((Integer) sandbagsNS).toString(), 2, 1);
                model.setValueAt(((Integer) sandbagsEW).toString(), 2, 2);
                totalBagsNS += sandbagsNS;
                totalBagsEW += sandbagsEW;

                if (totalBagsNS >= 10) {
                    scoreNS -= 100;
                    totalBagsNS = 0;
                }
                if (totalBagsEW >= 10) {
                    scoreEW -= 100;
                    totalBagsEW = 0;
                }
                model.setValueAt(((Integer) totalBagsNS).toString(), 3, 1);
                model.setValueAt(((Integer) totalBagsEW).toString(), 3, 2);
                ptsScoredNS = pointsScored(northTricksBid, southTricksBid, northTricksTaken, southTricksTaken);
                ptsScoredEW = pointsScored(eastTricksBid, westTricksBid, westTricksTaken, eastTricksTaken);
                model.setValueAt(((Integer) ptsScoredNS).toString(), 4, 1);
                model.setValueAt(((Integer) ptsScoredEW).toString(), 4, 2);
                scoreNS += ptsScoredNS;
                scoreEW += ptsScoredEW;
                model.setValueAt(((Integer) scoreNS).toString(), 5, 1);
                model.setValueAt(((Integer) scoreEW).toString(), 5, 2);

                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel2.add(scoring.getTableHeader(), constraints);
                constraints.gridx = 0;
                constraints.gridy = 1;
                centerPanel2.add(scoring, constraints);

                JLabel endOfGameWinner;
                if (scoreNS >= 500) {
                    endOfGameWinner = new JLabel("Congratuations N & S! You win!");
                    endOfGameWinner.setFont(new Font("Sans Serif", Font.BOLD, 30));
                    constraints.gridx = 0;
                    constraints.gridy = 3;
                    centerPanel2.add(endOfGameWinner, constraints);
                } else if (scoreEW >= 500) {
                    endOfGameWinner = new JLabel("Congratuations E & W! You win!");
                    endOfGameWinner.setFont(new Font("Sans Serif", Font.BOLD, 30));
                    constraints.gridx = 0;
                    constraints.gridy = 3;
                    centerPanel2.add(endOfGameWinner, constraints);
                } else {
                    JButton nextHand = new JButton("Next Hand");
                    NextHand next = new NextHand();
                    if (screenResNum == 1)
                        nextHand.setPreferredSize(new Dimension(140, 50));
                    else
                        nextHand.setPreferredSize(new Dimension(200, 100));
                    nextHand.addActionListener(next);
                    nextHand.setFont(font);
                    constraints.gridx = 0;
                    constraints.gridy = 3;
                    centerPanel2.add(nextHand, constraints);
                }
                return;
            }

           
            if (nextPlayer == 0) {
                if (driver.players[0] instanceof Human) {
                    playerS[0].setVisible(false);
                    for (CardButton aMyHand0 : myHand0) {
                        aMyHand0.setVisible(true);
                    }
                    playableCards(myHand0, middlePile);
                    return;
                }
            } else if (nextPlayer == 1) {
                if (driver.players[1] instanceof Human) {
                    playerW[0].setVisible(false);
                    for (CardButton aMyHand1 : myHand1) {
                        aMyHand1.setVisible(true);
                    }
                    playableCards(myHand1, middlePile);
                    return;
                }
            } else if (nextPlayer == 2) {
                if (driver.players[2] instanceof Human) {
                    playerN[0].setVisible(false);
                    for (CardButton aMyHand2 : myHand2) {
                        aMyHand2.setVisible(true);
                    }
                    playableCards(myHand2, middlePile);
                    return;
                }
            } else if (nextPlayer == 3) {
                if (driver.players[3] instanceof Human) {
                    playerE[0].setVisible(false);
                    for (CardButton aMyHand3 : myHand3) {
                        aMyHand3.setVisible(true);
                    }
                    playableCards(myHand3, middlePile);
                    return;
                }
            }

            CardButton b = new CardButton();
            PlayCard p = new PlayCard();
            b.addActionListener(p);
            b.doClick();
        }
    }

   
    private class NextHand implements ActionListener {
       
        public void actionPerformed(ActionEvent e) {
            dispose();
            GUI gui = new GUI(screenResNum, scoreNS, scoreEW, totalBagsNS, totalBagsEW);
            gui.display();
        }
    }

   
    private class NextHumanPlayer implements ActionListener {

       
        public void actionPerformed(ActionEvent e) {
            nextPlayerReady.setVisible(false);
            if (driver.players[0].getTurnToPlay()) {
                playerS[0].setVisible(false);
                for (CardButton aMyHand0 : myHand0) {
                    aMyHand0.setVisible(true);
                    aMyHand0.setEnabled(true);
                }
                playableCards(myHand0, middlePile);
            } else if (driver.players[1].getTurnToPlay()) {
                playerW[0].setVisible(false);
                for (CardButton aMyHand1 : myHand1) {
                    aMyHand1.setVisible(true);
                    aMyHand1.setEnabled(true);
                }
                playableCards(myHand1, middlePile);
            } else if (driver.players[2].getTurnToPlay()) {
                playerN[0].setVisible(false);
                for (CardButton aMyHand2 : myHand2) {
                    aMyHand2.setVisible(true);
                    aMyHand2.setEnabled(true);
                }
                playableCards(myHand2, middlePile);
            } else if (driver.players[3].getTurnToPlay()) {
                playerE[0].setVisible(false);
                for (CardButton aMyHand3 : myHand3) {
                    aMyHand3.setVisible(true);
                    aMyHand3.setEnabled(true);
                }
                playableCards(myHand3, middlePile);
            }
        }

    }

   
//    private class ExitEvent implements ActionListener {
//       
//        public void actionPerformed(ActionEvent e) {
//            System.exit(0);
//        }
//    }
//
//   
//    private class BlueEvent implements ActionListener {
//       
//        public void actionPerformed(ActionEvent e) {
//            setBackgroundColor(new Color(0, 0, 255));
//        }
//    }
//
//   
//    private class GreyEvent implements ActionListener {
//      
//        public void actionPerformed(ActionEvent e) {
//            setBackgroundColor(Color.GRAY);
//        }
//    }
//
//   
//    private class GreenEvent implements ActionListener {
//       
//        public void actionPerformed(ActionEvent e) {
//            setBackgroundColor(new Color(0, 200, 0));
//        }
//    }
//
//   
//    private class RedEvent implements ActionListener {
//       
//        public void actionPerformed(ActionEvent e) {
//            setBackgroundColor(new Color(200, 0, 0));
//        }
//    }
//
//	
//    private class NewGameEvent implements ActionListener {
//       
//        public void actionPerformed(ActionEvent e) {
//            dispose();
//            GUI gui = new GUI(screenResNum);
//            gui.display();
//        }
//    }

	
    private class MyModel extends AbstractTableModel {
        
        String[] colNames = {"", "North & South", "East & West"};
        String[][] data = {{"Tricks Bid", "", ""}, {"Tricks Taken", "", ""}, {"Sand Bags", "", ""},
                {"Total Bags", "", ""}, {"Points Scored", "", ""}, {"Total Points", "", ""}};

       
        public String getColumnName(int col) {
            return colNames[col];
        }

       
        public int getColumnCount() {
            return colNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

      
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public void setValueAt(Object aValue, int row, int col) {
            data[row][col] = (String) aValue;
            fireTableCellUpdated(row, col);
        }
    }

}
