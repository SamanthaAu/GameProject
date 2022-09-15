import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class RunRobberRun//runs main and creates JFrame
{
  JFrame frame;
  public static void main(String[]args)//creates instance of Clue
  {
      RunRobberRun blast = new RunRobberRun();
      blast.run();  
  }       
  public void run()//creates and sets JFrame
  {
      frame = new JFrame("Run Robber Run");
      frame.setSize(600, 600); 
      frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
      frame.setLocation(0, 0); 
      frame.setResizable(false); 
      MasterPanel mp = new MasterPanel();
      frame.getContentPane().add(mp);
      frame.setResizable(true);
      frame.setVisible(true); 
  } 
class MasterPanel extends JPanel//base class that has cardLayout with all other classes on it
{
 Rectangle player;
 String[] names;
 int [] scores;
 int money;
 boolean stopTimer;
 boolean easy;
 boolean medium; 
 boolean hard;
 boolean storyComp;
 java.awt.CardLayout cL;
 HomePanel home;
 InstructionPanel instruction;
 LeaderPanel leader;
 GamePanel game;
 GamePanel1 game1;
 NamePanel name;
 LevelPanel level;
 EndPanel end;
 String HOME = "Home";
 String MODE = "Mode";
 String INSTRUCTION = "Instruction";
 String LEADER = "Leader";
 String TEXT = "Text";
 String GAME = "Game";
 String GAME1 = "Game1";
 String END = "End";
 String NAME = "Name";
 String LEVEL = "Level";
 int distance;
 String username;
 boolean[] levelNum;
 boolean[] pass;
 int levelPlaying;
 boolean win;
 int time;
 boolean alive;
 boolean one;
 boolean two;
 boolean three;
 boolean lose;
 Font font;
 Color blue;
 Color yellow;
 Color navy;
 public MasterPanel() //initiates above variables, initiates and adds each JPanel
 {
   blue = new Color(135,206,235);
   yellow = new Color(255,255,100);
   navy = new Color(0, 51, 102);
   font = new Font("Arial Narrow",Font.BOLD,30);
   money = 0;
   lose = false;
   one = two = three = false;
   alive = true;
   time= 0;
   win = false;
   levelPlaying = 1;
   pass = new boolean[9];
   pass[0] = true;
   for(int i=1 ; i<9 ; i++)
   {
    pass[i] = false; // true
   }
   levelNum = new boolean[9];
   for(int i=0 ; i<9 ; i++)
   {
     levelNum[i] = false; // true
   }
   easy = medium = hard = false;
   username = "";
   distance = 7;
   names = new String[5];
   scores = new int[5];
   cL = new java.awt.CardLayout();
   setLayout(cL);
   home = new HomePanel();
   add(home, HOME);
   instruction = new InstructionPanel();
   add(instruction, INSTRUCTION);
   leader = new LeaderPanel();
   add(leader, LEADER);
   game = new GamePanel();
   add(game, GAME);
   game1 = new GamePanel1();
   add(game1,GAME1);
   name = new NamePanel();
   add(name,NAME);
   level = new LevelPanel();
   add(level,LEVEL);
   end = new EndPanel();
   add(end,END);
 }
 public void colorButton(JButton button)//sets button's background to black and foreground to white
 {
  button.setBackground(Color.BLACK);
  button.setForeground(Color.WHITE);
  button.setOpaque(true);
  button.setBorderPainted(false);
 } 
 public void colorButton2(JButton button)//sets button's background to white and foreground to black
 {
  button.setBackground(Color.WHITE);
  button.setForeground(Color.BLACK);
  button.setOpaque(true);
  button.setBorderPainted(false);
 } 
 class HomePanel extends JPanel implements ActionListener//first panel to show up with buttons to other panels
 {
   JLabel titleLabel;
   JButton playButton;
   JButton instructionButton;
   JButton exitButton;
   JButton leaderButton;
   public void paintComponent(Graphics g)//draws robber and police images
   {
     super.paintComponent(g);
     setBackground(Color.WHITE);
     Image dark = new ImageIcon("dark.jpg").getImage();
     g.drawImage(dark,0,0,600,600,null);
     Image robber = new ImageIcon("robber.png").getImage();
     g.drawImage(robber,400,390,180,180,null);
     Image police = new ImageIcon("police.png").getImage();
     g.drawImage(police,20, 360,90,190,null);
   }
   public HomePanel()//initiates, changes size, adds buttons
   {
     setBackground(Color.BLACK);
     Font font = new Font("Arial Narrow",Font.BOLD, 50);
     titleLabel = new JLabel("RUN ROBBER RUN",JLabel.CENTER);
     titleLabel.setFont(font);
     titleLabel.setForeground(Color.WHITE);
     titleLabel.setPreferredSize(new Dimension(600,170));
     add(titleLabel);
     playButton = new JButton("Play");
     colorButton(playButton);
     playButton.setPreferredSize(new Dimension(300,50));
     playButton.addActionListener(this);
     add(playButton);
     instructionButton = new JButton("Instructions");
     colorButton(instructionButton);
     instructionButton.setPreferredSize(new Dimension(300,50));
     instructionButton.addActionListener(this);
     add(instructionButton);
     leaderButton = new JButton("Leaderboard");
     colorButton(leaderButton);
     leaderButton.setPreferredSize(new Dimension(300,50));
     leaderButton.addActionListener(this);
     add(leaderButton);
     exitButton = new JButton("Exit"); 
     colorButton(exitButton);
     exitButton.setPreferredSize(new Dimension(300,50));   
     exitButton.addActionListener(this);
     add(exitButton);
   }
   public void actionPerformed(ActionEvent e)//checks which button is pressed and shows corresponding JPanel
   {
     if(e.getActionCommand().equals("Play"))
     {
       cL.show(MasterPanel.this,NAME);
     }
     if(e.getActionCommand().equals("Instructions"))
       cL.show(MasterPanel.this,INSTRUCTION);
     if(e.getActionCommand().equals("Leaderboard"))
       cL.show(MasterPanel.this,LEADER);
     if(e.getActionCommand().equals("Exit"))
        System.exit(0);     
   }
 }
 class LevelPanel extends JPanel implements ActionListener//grid Layout that shows 9 buttons/ levels
 {
   JButton one;
   JButton two;
   JButton three;
   JButton four;
   JButton five;
   JButton six;
   JButton seven;
   JButton eight;
   JButton nine;
   JButton homeButton;
   JPanel grid;
   public LevelPanel()//initiates, changes size, adds buttons
   {
     setLayout(null);
     setBackground(navy);
     grid = new JPanel();
     grid.setBackground(navy);
     add(grid);
     grid.setBounds(0,0,600,510);
     grid.setLayout(new GridLayout(3,3,10,10));
     one = new JButton("One");
     setButton(one);
     two = new JButton("Two");
     setButton(two);
     three = new JButton("Three");
     setButton(three);
     four = new JButton("Four");
     setButton(four);
     five = new JButton("Five");
     setButton(five);
     six = new JButton("Six");
     setButton(six);
     seven = new JButton("Seven");
     setButton(seven);
     eight = new JButton("Eight");
     setButton(eight);
     nine = new JButton("Nine"); 
     setButton(nine);
     homeButton = new JButton("Home");
     homeButton.setBounds(20,520,100,30);
     homeButton.addActionListener(this);
     colorButton2(homeButton);
     add(homeButton,BorderLayout.SOUTH);
     createButton();
   }     
   public void createButton()//colors buttons according to whether it has been unlocked
   {
     if(pass[0])
       colorButton(one);
     if(pass[1])
       colorButton(two);
     else
       colorButton2(two);
     if(pass[2])
       colorButton(three);
     else
       colorButton2(three);
     if(pass[3])
       colorButton(four);
     else
       colorButton2(four);
     if(pass[4])
       colorButton(five);
     else
       colorButton2(five);
     if(pass[5])
       colorButton(six);
     else
       colorButton2(six);
     if(pass[6])
       colorButton(seven);
     else
       colorButton2(seven);
     if(pass[7])
       colorButton(eight);
     else
       colorButton2(eight);
     if(pass[8])
       colorButton(nine);
     else
       colorButton2(nine);
   }
   public void setButton(JButton button)//adds action listener and button
   {
     button.addActionListener(this);
     grid.add(button);
   }
   public void actionPerformed(ActionEvent e) //checks which button is pressed and shows corresponding level
   {
     if(e.getActionCommand().equals("Home"))
     {
       end.checkLeader(money);
       money = 0;
       levelPlaying = 1;
       pass[0] = true;
       for(int i=1 ; i<9 ; i++)
       {
         pass[i] = false; // true
       }
       level.createButton();
       cL.show(MasterPanel.this,HOME);
     }
     if(e.getActionCommand().equals("One") && pass[0])
     {
       easy = true;
       medium = false;
       hard = false;
       cL.show(MasterPanel.this,GAME);
       game.initiate();
       levelNum[0] = true;
       for(int i=0 ; i<9 ; i++)
       {
         if(i!=0)
           levelNum[i]=false;
       }
     }
     if(e.getActionCommand().equals("Two") && pass[1])
     {
       easy = true;
       medium = false;
       hard = false;
       cL.show(MasterPanel.this,GAME);
       game.initiate();
       levelNum[1] = true;
       for(int i=0 ; i<9 ; i++)
       {
         if(i!=1)
           levelNum[i]=false;
       }
     }
     if(e.getActionCommand().equals("Three") && pass[2])
     {
       easy = true;
       medium = false;
       hard = false;
       cL.show(MasterPanel.this,GAME);
       game.initiate();
       levelNum[2] = true;
       for(int i=0 ; i<9 ; i++)
       {
         if(i!=2)
           levelNum[i]=false;
       }
     }
     if(e.getActionCommand().equals("Four") && pass[3])
     {
       medium = true;
       easy = false;
       hard = false;
       cL.show(MasterPanel.this,GAME);
       game.initiate();
       levelNum[3] = true;
       for(int i=0 ; i<9 ; i++)
       {
         if(i!=3)
           levelNum[i]=false;
       }
     }
     if(e.getActionCommand().equals("Five") && pass[4])
     {
       medium = true;
       easy = false;
       hard = false;
       cL.show(MasterPanel.this,GAME);
       game.initiate();
       levelNum[4] = true;
       for(int i=0 ; i<9 ; i++)
       {
         if(i!=4)
           levelNum[i]=false;
       }
     }
     if(e.getActionCommand().equals("Six") && pass[5])
     {
       medium = true;
       easy = false;
       hard = false;
       cL.show(MasterPanel.this,GAME);
       game.initiate();
       levelNum[5] = true;
       for(int i=0 ; i<9 ; i++)
       {
         if(i!=5)
           levelNum[i]=false;
       }
     }
     if(e.getActionCommand().equals("Seven") && pass[6])
     {
       hard = true;
       easy = false;
       medium = false;
       cL.show(MasterPanel.this,GAME);
       game.initiate();
       levelNum[6] = true;
       for(int i=0 ; i<9 ; i++)
       {
         if(i!=6)
           levelNum[i]=false;
       }
     }
     if(e.getActionCommand().equals("Eight") && pass[7])
     {
       hard = true;
       easy = false;
       medium = false;
       cL.show(MasterPanel.this,GAME);
       game.initiate();
       levelNum[7] = true;
       for(int i=0 ; i<9 ; i++)
       {
         if(i!=7)
           levelNum[i]=false;
       }
     }
     if(e.getActionCommand().equals("Nine") && pass[8])
     {
       hard = true;
       easy = false;
       medium = false;
       cL.show(MasterPanel.this,GAME);
       game.initiate();
       levelNum[8] = true;
       for(int i=0 ; i<9 ; i++)
       {
         if(i!=8)
           levelNum[i]=false;
       }
     }
     for(int i=0 ; i<9 ; i++)
     {
     if(levelNum[i]==true)
       levelPlaying = i+1;
     }
   }
 }
 class NamePanel extends JPanel implements ActionListener//user enter username
 {
  JLabel instructionLabel;
  JButton playButton;
  JTextField usernameBar;
  JButton homeButton;
  JLabel invalidName;
  public void paintComponent(Graphics g)//draws background image
  {
    super.paintComponent(g);
    setBackground(Color.BLACK);
    Image prison = new ImageIcon("cell.jpg").getImage();
    g.drawImage(prison,0,0,600,600,null);
  }
  public NamePanel()//initiates, changes size, adds buttons
  {
   setLayout(null);
   setBackground(Color.BLACK);
   instructionLabel = new JLabel("Enter your name below");
   instructionLabel.setFont(font);
   instructionLabel.setBounds(20,50,500,50);
   instructionLabel.setForeground(Color.WHITE);
   add(instructionLabel);
   usernameBar = new JTextField("",20);
   usernameBar.setBounds(20,150,300,40);
   usernameBar.addActionListener(this);
   add(usernameBar);
   playButton = new JButton("Play");
   playButton.setBounds(330,150,100,40);
   playButton.addActionListener(this);
   colorButton(playButton);
   add(playButton);
   homeButton = new JButton("Home");
   homeButton.setBounds(20,520,100,30);
   colorButton2(homeButton);
   homeButton.addActionListener(this);
   add(homeButton);
   invalidName = new JLabel();
   invalidName.setBounds(25,180,100,50);
   invalidName.setForeground(Color.WHITE);
   invalidName.setVisible(false);
   add(invalidName);
  }
  public void actionPerformed(ActionEvent e)//checks which button is pressed and shows corresponding JPanel
  {
   username = usernameBar.getText()+"- ";
   if(e.getActionCommand().equals("Play"))
    {
     if(username.trim().equals("-"))
     {
       invalidName.setText("Invalid username");
       invalidName.setVisible(true);
     }
     else if(username.equals(names[0])||username.equals(names[1])||username.equals(names[2])||username.equals(names[3])||username.equals(names[4]))
     {
       invalidName.setText("Username Taken");
       invalidName.setVisible(true);
     }
     else
     {
       usernameBar.setText("");
       invalidName.setText("");
       cL.show(MasterPanel.this,LEVEL);
     }
    }
    if(e.getActionCommand().equals("Home"))
    {
     invalidName.setText("");
     cL.show(MasterPanel.this,HOME);
    }
  }
 }
 class InstructionPanel extends JPanel implements ActionListener //instructions for how to play game
 {
   JButton homeButton;
   JButton playButton;
   JTextArea instructionTA1;
   JTextArea instructionTA2;
   JTextArea instructionTA3;
   JLabel titleLabel;
   String[]instruction;
   String printLeft;
   String printRight;
   String printTop;
   JTextArea controls;
   JLabel control;
   public InstructionPanel()//initiates, changes size, adds buttons
   {
     printTop = "";
     printLeft = "";
     printRight = "";
     instruction = new String[24];
     setLayout(null);
     setBackground(Color.BLACK);
     titleLabel = new JLabel("Instructions");
     titleLabel.setFont(font);
     titleLabel.setForeground(Color.WHITE);
     titleLabel.setBounds(10,10,300,30);
     add(titleLabel);
     control = new JLabel("Controls");
     control.setFont(font);
     control.setForeground(Color.WHITE);
     control.setBounds(10,350,300,30);
     add(control);
     instructionTA1 = new JTextArea();
     instructionTA1.setBounds(10,150,250,170);
     instructionTA1.setForeground(Color.WHITE);
     instructionTA1.setOpaque(false);
     add(instructionTA1);
     instructionTA2 = new JTextArea();
     instructionTA2.setBounds(300,150,250,170);
     instructionTA2.setForeground(Color.WHITE);
     instructionTA2.setOpaque(false);
     add(instructionTA2);
     instructionTA3 = new JTextArea();
     instructionTA3.setBounds(10,50,600,150);
     instructionTA3.setForeground(Color.WHITE);
     instructionTA3.setOpaque(false);
     add(instructionTA3);
     controls = new JTextArea("Arrow keys to move              Space to jump from wall to wall\nA to aim\nS to shoot\nD to use powerup\n(shield/bomb)");
     controls.setBounds(150,410,400,90);
     controls.setForeground(Color.WHITE);
     controls.setOpaque(false);
     add(controls);
     homeButton = new JButton("Home");
     colorButton2(homeButton);
     homeButton.setBounds(20,520,100,30);
     homeButton.addActionListener(this);
     add(homeButton);
     playButton = new JButton("Play");
     playButton.setBounds(465,520,100,30);
     colorButton2(playButton);
     playButton.addActionListener(this);
     add(playButton);
     scanner();
   }
   public void paintComponent(Graphics g)//draws 4 arrow images
   {
     super.paintComponent(g);
     setBackground(Color.BLACK);
     Image prison = new ImageIcon("prison.jpg").getImage();
     g.drawImage(prison,0,0,600,600,null);
     Image up = new ImageIcon("arrowUp.png").getImage();
     g.drawImage(up,50,410,30,30,null);
     Image down = new ImageIcon("arrowDown.png").getImage();
     g.drawImage(down,50,450,30,30,null);
     Image left = new ImageIcon("arrowLeft.png").getImage();
     g.drawImage(left,10,450,30,30,null);
     Image right = new ImageIcon("arrowRight.png").getImage();
     g.drawImage(right,90,450,30,30,null);
   }
   public void scanner()//reads from Instructions.txt and puts into an array
   {
    File inFile = new File ("Instructions.txt"); 
    String inFileName = "Instructions.txt"; 
    String  value = ""; 
    Scanner input = null; 
       try
       { 
         input = new Scanner ( inFile );
       }
       catch ( FileNotFoundException e ) 
       { 
         System.err.println("Cannot find " + inFileName + " file.");  
         System.exit(1);
       }
       int i =0;
       while( input.hasNext() && i<24)
       { 
         value = input.nextLine();
         instruction[i] = value;
         if(i>4 && i<14)
           printLeft+=(value+"\n");
         else if(i>13 && i<24)
           printRight+=(value+"\n");
         else
           printTop+=(value+"\n");
         i++;
       }
    input.close();
    instructionTA1.setText(printLeft);
    instructionTA2.setText(printRight);
    instructionTA3.setText(printTop);
   }
   public void actionPerformed(ActionEvent e)//checks which button is pressed and shows corresponding JPanel
   {
     if(e.getActionCommand().equals("Home"))
       cL.show(MasterPanel.this,HOME);
     if(e.getActionCommand().equals("Play"))
       cL.show(MasterPanel.this,NAME);
   }
 }
 class LeaderPanel extends JPanel implements ActionListener//shows the top 5 players
 {
   JLabel titleLabel;
   JButton homeButton;
   JButton playButton;
   JTextArea boardTA;
   public void paintComponent(Graphics g)//draws medal next to 1st place and background
   {
     super.paintComponent(g);
     setBackground(Color.BLACK);
     Image prison = new ImageIcon("height.jpg").getImage();
     g.drawImage(prison,0,0,600,600,null);
     Image robber = new ImageIcon("robber.png").getImage();
     g.drawImage(robber,300,200,260,300,null);
     Image medal = new ImageIcon("1stPlace.png").getImage();
     g.drawImage(medal,40,95,60,60,null);
   }
  public LeaderPanel()//initiates, changes size, adds buttons
  {
   setLayout(null);
   Font fontB = new Font("Arial Narrow",Font.BOLD,40);
   setBackground(null);
   scanner();
   titleLabel = new JLabel("Most Wanted");
   titleLabel.setFont(fontB);
   titleLabel.setForeground(Color.BLACK);
   titleLabel.setBounds(150,10,300,50);
   add(titleLabel);
   boardTA = new JTextArea();
   boardTA.setFont(fontB);
   changeLeader();
   boardTA.setOpaque(false);
   boardTA.setForeground(Color.BLACK);
   boardTA.setBounds(100,105,400,300);
   add(boardTA);
   homeButton = new JButton("Home");
   colorButton2(homeButton);
   homeButton.setBounds(20,520,100,30);
   homeButton.addActionListener(this);
   add(homeButton);
   playButton = new JButton("Play");
   playButton.setBounds(465,520,100,30);
   colorButton2(playButton);
   playButton.addActionListener(this);
   add(playButton);
   scanner();
  }
   public void actionPerformed(ActionEvent e) // checks what buttons are pressed and goes to corresponding panel
   {
     if(e.getActionCommand().equals("Home"))
       cL.show(MasterPanel.this,HOME);
     if(e.getActionCommand().equals("Play"))
     {
       cL.show(MasterPanel.this,NAME);
     }
   }
   public void scanner()//reads in Leaderboard.txt and puts values into an array
   {
    File inFile = new File ("Leaderboard.txt"); 
    String inFileName = "Leaderboard.txt"; 
    String  value = ""; 
    Scanner input = null; 
       try
       { 
         input = new Scanner ( inFile );
       }
       catch ( FileNotFoundException e ) 
       { 
         System.err.println("Cannot find " + inFileName + " file.");  
         System.exit(1);
       }
       int i =0;
       while( input.hasNext() && i<5)
       { 
         value = input.nextLine();
         names[i] = value.substring(0,value.lastIndexOf('-')+2);
         scores[i] = Integer.parseInt((value.substring(value.lastIndexOf('-')+2)).trim());
         i++;
       }
    input.close();
   }
   public void append()//changes Leaderboard.txt to update top players
   {
    PrintWriter makesOutput = null; 
    String outFileName = new String ("Leaderboard.txt");
    File outFile = new File("Leaderboard.txt");
    try
    {  
      makesOutput = new PrintWriter(outFile); 
    }
    catch ( IOException e)
    { 
      System.err.println("Cannot create "+outFileName+  
                         " file to be written to.");
      System.exit(1);
    }
    for(int i = 0; i<5;i++)
    {
      makesOutput.println(names[i]+scores[i]);
    }
   makesOutput.close();
   }
   public void changeLeader()//changes the actual score on this panel
   {
     boardTA.setText(names[0]+scores[0]+"\n"+names[1]+scores[1]+"\n"+names[2]+scores[2]+"\n"+names[3]+scores[3]+"\n"+names[4]+scores[4]);
   }
 }
 class GamePanel extends JPanel implements KeyListener, MouseListener,ActionListener //gamePanel for main game
 { 
  PlanetMover planetMover;
  Timer planetTimer;
  Rectangle[] planet;
  Rectangle[]bombs;
  Rectangle player;
  int playerWidth;
  int playerHeight;
  double[] startX;
  double[] startY;
  double[] targetX;
  double[] targetY;
  double[] h;
  double[] w;
  double[] z;
  double[] x;
  double[] y;
  int[]x1;
  int[]x2;
  int[]y1;
  int[]y2;
  int[]shooterShootMult;
  int playerShootMult;
  int lives;
  int shootIndex1; int shootIndex2;
  int secs1; int secs2;
  int counter1; int counter2;
  boolean usingShooter1; boolean usingShooter2;
  boolean setTimer1; boolean setTimer2;
  boolean shot1; boolean shot2;
  boolean [] shooterShooting;
  boolean[][]directions;
  int[]moveUnit;
  boolean[]shooterMoving;
  boolean shield;
  boolean shootBackwards1; boolean shootBackwards2;
  int[]moveCounter;
  int[]shooterWidth;
  int[]shooterHeight;
  double angle;
  boolean playerShoot;
  int bulletTipX;
  int bulletTipY;
  int playerShootX;
  int playerShootY;
  boolean[] shooterDead;
  boolean[]bomb;
  int[]bombDiam;
  boolean[]bombOnce;
  boolean up;
  boolean down;
  boolean left;
  boolean right;
  boolean s;
  boolean a;
  boolean d;
  boolean found1;
  boolean found2;
  int numAlive;
  int mult;
  int shieldOn;
  int shieldOff;
  int wait;
  boolean allowShield;
  JButton homeButton;
  int loopCounter1;
  int loopCounter2;
  int i;
  boolean shoot2x;
  boolean powerShield;
  int playerRad;
  boolean powerBomb;
  Rectangle playerBomb;
  boolean expand;
  JButton shieldButton;
  JButton shoot2xButton;
  JButton bombButton;
  boolean bombTop;
 public GamePanel() //creates arrays and timers
 {
   shieldButton = new JButton("Shield");
   colorButton(shieldButton);
   shieldButton.addActionListener(this);
   shieldButton.setBounds(30,100,150,70);
   add(shieldButton);
   shoot2xButton = new JButton("Shoot x2");
   colorButton(shoot2xButton);
   shoot2xButton.addActionListener(this);
   shoot2xButton.setBounds(220,100,150,70);
   add(shoot2xButton);
   bombButton = new JButton("Bomb");
   colorButton(bombButton);
   bombButton.addActionListener(this);
   bombButton.setBounds(410,100,150,70);
   add(bombButton);
   setLayout(null);
   addMouseListener(this);
   addKeyListener(this);
   homeButton = new JButton("Home");
   homeButton.addActionListener(this);
   homeButton.setBounds(20,520,100,30);
   colorButton(homeButton);
   add(homeButton);
   bombOnce = new boolean[6];
   bombs = new Rectangle[6];
   bombDiam = new int[6];
   bomb = new boolean[6];
   shooterDead = new boolean[6];
   shooterWidth = new int[6];
   shooterHeight = new int[6];
   directions = new boolean[6][4];
   moveUnit = new int[6];
   shooterMoving = new boolean[6];
   moveCounter = new int[6];
   x1 = new int[6];
   x2 = new int[6];
   y1 = new int[6];
   y2 = new int[6];
   planet = new Rectangle[6];
   shooterShooting = new boolean[6];
   shooterShootMult = new int[6];
   targetX = new double[6];
   targetY = new double[6];
   startX = new double[6];
   startY = new double[6];
   h = new double[6];
   w = new double[6];
   z = new double[6];
   x = new double[6];
   y = new double[6];
   planetMover = new PlanetMover();
   planetTimer = new Timer(25, planetMover);
   setFocusable(true);
   requestFocus();
 }
 public void actionPerformed(ActionEvent e)//checks if home button and powerup buttons are pressed
 {
   if(e.getActionCommand().equals("Home"))
   {
       planetTimer.stop();
       end.checkLeader(money);
       money = 0;
       levelPlaying = 1;
       pass[0] = true;
       for(int i=1 ; i<9 ; i++)
       {
         pass[i] = false; // true
       }
       level.createButton();
       game.initiate();
       cL.show(MasterPanel.this,HOME);
   }
   if(e.getActionCommand().equals("Shield"))
   {
     powerShield = true;
   }
   if(e.getActionCommand().equals("Shoot x2"))
   {
     shoot2x = true;
   }
   if(e.getActionCommand().equals("Bomb"))
   {
     bombTop = true;
     powerBomb = true;
   }
   shieldButton.setVisible(false);
   shoot2xButton.setVisible(false);
   bombButton.setVisible(false);
   planetTimer.start();
   requestFocus();
 }
 public void initiate()//initiates variables
 {
   bombTop = false;
   planetTimer.stop();
   shieldButton.setVisible(true);
   shoot2xButton.setVisible(true);
   bombButton.setVisible(true);
   expand = false;
   powerBomb=false;
   playerRad=20;
   playerBomb = new Rectangle(0,0,playerRad,playerRad);
   powerShield=false;
   i=0;
   shoot2x = false;
   loopCounter1 = 0;
   loopCounter2 = 0;
   time=0;
   shieldOn = shieldOff = wait = 0;
   allowShield = true;
   if(levelNum[0] || levelNum[3] || levelNum[6])
   {
       for(int i=0 ; i<2 ; i++)
         shooterDead[i]=false;
   }
   if(levelNum[1] || levelNum[4] || levelNum[7])
   {
       for(int i=0 ; i<4 ; i++)
         shooterDead[i]=false;
   }
   mult=0;
   numAlive=0;
   found1 = found2 = false;
   up = false;
   down = false;
   left = false;
   right = false;
   s = false;
   a = false;
   d = false;
   player = new Rectangle(250,250,50,50);
   for(int i=0; i<6 ; i++)
   {
    for(int j=0 ; j<4 ; j++)
    {
        directions[i][j] = false;
    }
    shooterDead[i] = false;
    bomb[i] = false;
    bombDiam[i] = 0;
    shooterMoving[i]=false;
    moveCounter[i]=0;
    moveUnit[i]=0;
    shooterShootMult[i] = 1;
    shooterShooting[i] = false;
    startX[i]=0;
    startY[i]=0;
    targetX[i]=0;
    targetY[i]=0;
    h[i]=0;
    w[i]=0;
    z[i]=0;
    x[i]=0;
    y[i]=0;
    bombOnce[i] = false;
   }
   playerShootX = playerShootY = 0;
   bulletTipX = bulletTipY = 0;
   playerShoot = shootBackwards1 = shootBackwards2 = shield = shot1 = shot2 = setTimer1 = usingShooter1 = setTimer2 = usingShooter2 = false;
   playerShootMult = 0;
   angle = 0;
   lives = 10;
   playerWidth = playerHeight = 250;
   shooterWidth[0]=200; shooterHeight[0]=0;
   shooterWidth[1]=0; shooterHeight[1]=200;
   shooterWidth[2]=0; shooterHeight[2]=400;
   shooterWidth[3]=530; shooterHeight[3]=400;
   shooterWidth[4]=530; shooterHeight[4]=200;
   shooterWidth[5]=370; shooterHeight[5]=0;
   planet[0] = new Rectangle(shooterWidth[0],shooterHeight[0],50,50);
   planet[1] = new Rectangle(shooterWidth[1],shooterHeight[1],50,50);
   planet[2] = new Rectangle(shooterWidth[2],shooterHeight[2],50,50);
   planet[3] = new Rectangle(shooterWidth[3],shooterHeight[3],50,50);
   planet[4] = new Rectangle(shooterWidth[4],shooterHeight[4],50,50);
   planet[5] = new Rectangle(shooterWidth[5],shooterHeight[5],50,50);
   bombs[0] = new Rectangle(0,0,0,0);
   bombs[1] = new Rectangle(0,0,0,0);
   bombs[2] = new Rectangle(0,0,0,0);
   bombs[3] = new Rectangle(0,0,0,0);
   bombs[4] = new Rectangle(0,0,0,0);
   bombs[5] = new Rectangle(0,0,0,0);
 }
 class PlanetMover implements ActionListener//class for Timer
 {
   public void actionPerformed(ActionEvent e)//runs every time increment, calls methods to be ran
   {
     i++;
     if(playerWidth<0)
       playerWidth = 0;
     if(playerWidth>535)
       playerWidth = 535;
     if(playerHeight<0)
       playerHeight = 0;
     if(playerHeight>515)
       playerHeight = 515;
     numAlive = 6;
     for(int i=0 ; i<6 ; i++)
     {
       if(shooterDead[i]==true)
         numAlive--;
     }
       time++;
     if(levelNum[0] || levelNum[3] || levelNum[6])
     {
       for(int i=2 ; i<6 ; i++)
         shooterDead[i]=true;
     }
     if(levelNum[1] || levelNum[4] || levelNum[7])
     {
       for(int i=4 ; i<6 ; i++)
         shooterDead[i]=true;
     }
     if(powerBomb && expand)
     {
       playerRad+=5;
     }
     if(playerRad>=150)
     {
       powerBomb=false;
       expand=false;
     }
     if(shoot2x)
         playerShoot();
     if(i%2==0)
     {
       if(powerShield)
       {
         if(allowShield == false)
         {
           shieldOff++;
         }
         if(shieldOff>=wait && allowShield==false)
         {
           allowShield=true;
           shieldOff=0;
         }
         if(shieldOn>=100)
         {
           d = false;
           shield = false;
           allowShield = false;
           wait = 50;
           shieldOn = 0;
         }
       }
       if(medium || hard)
         move();
       if(hard)
         bomb();
       if(shoot2x==false)
         playerShoot();
       checkOffscreen();
       repaint();
       shoot();
       checkTwo();
     }
   }
 }
 public void playerShoot()//allows the user to shoot the police
 {
   if((int)(playerShootX+(20*Math.cos(angle)*(playerShootMult-1)))>580 ||(int)(playerShootY-(20*Math.sin(angle)*(playerShootMult-1)))<0 || (int)(playerShootX+(20*Math.cos(angle)*(playerShootMult-1))) <0 ||(int)(playerShootY-(20*Math.sin(angle)*(playerShootMult-1)))> 580)
   {
    playerShootMult = 0;
    playerShoot = false;
   }
   if(playerShoot)
       playerShootMult++;
   for(int i=0 ; i<6 ; i++)
   {
     if(bulletTipX>planet[i].getX() && bulletTipX<=planet[i].getX()+50 && bulletTipY>planet[i].getY() && bulletTipY<=planet[i].getY()+50)
     {
       shooterDead[i] = true;
     }
 }
   bulletTipX  = (int)(playerShootX+playerShootMult*40*Math.cos(angle));
     bulletTipY = (int)(playerShootY-playerShootMult*40*Math.sin(angle));
 }
 public void bomb()//checks and updates bombs
 {
   for(int i=0 ; i<6 ; i++)
   {
   if(shooterShootMult[i]>=Math.sqrt(h[i]*h[i]+w[i]*w[i])/10 && shooterShooting[i])
   {
       shooterShooting[i]=false;
       bomb[i] = true;
   }
     if(bomb[i])
     {
       bombDiam[i]+=5;
       bombs[i] = new Rectangle((int)targetX[i]+10-bombDiam[i]/2,(int)(600-targetY[i]+10-bombDiam[i]/2),bombDiam[i],bombDiam[i]);
     }
     double left = player.getX();
     double right = player.getX()+50;
     double up = player.getY();
     double down = player.getY()+50;
     if(bombs[i].contains(left,up) || bombs[i].contains(left,down) || bombs[i].contains(right,up) || bombs[i].contains(left,down))
     {
       if(bombOnce[i]==false && bombDiam[i]!=0)
       {
         lives--;
         bombOnce[i] = true;
       }
   }
     if(bombDiam[i]==100)
     {
       bombDiam[i] = 0;
       bomb[i] = false;
       if(i==shootIndex1)
       {
         shooterShootMult[shootIndex1] = 1;
         shooterShooting[shootIndex1] = false;
         usingShooter1 = false;
       }
       if(i==shootIndex2)
       {
         shooterShootMult[shootIndex2] = 1;
         shooterShooting[shootIndex2] = false;
         usingShooter2 = false;
       }
       if(bombOnce[i]==true)
         bombOnce[i] = false;
     }
   }
 }
  public void createPoints(int i)//creates equation of line for bullet
 {
   targetX[i] = player.getX();
   targetY[i] = 600-player.getY();
   startX[i] = planet[i].getX();
   startY[i] = 600-planet[i].getY();
   h[i] = startY[i]-targetY[i];
   w[i] = startX[i]-targetX[i];
   z[i] = Math.sqrt(h[i]*h[i] + w[i]*w[i]);
   x[i] = (10*w[i])/z[i];
   y[i] = (10*h[i])/z[i];
 }
 public void changePoints(int i)//moves points along line created in createPoints()
 {
   x1[i] = (int)(startX[i]-(shooterShootMult[i]-1)*x[i]);
   y1[i] = (int)((600-startY[i])+(shooterShootMult[i]-1)*y[i]);
   x2[i] =  (int)(startX[i]-shooterShootMult[i]*x[i]);
   y2[i] = (int)((600-startY[i]) + shooterShootMult[i]*y[i]);
 }
 public void shoot()//generates shots and calculates bouncing off for shield
 {
   for(int i=0 ; i<6 ; i++)
   {
       if(shooterShooting[i] && usingShooter1 && i==shootIndex1)
       {
         if(shootBackwards1)
           shooterShootMult[shootIndex1] --;
         else
         {
           shooterShootMult[shootIndex1]++;
           checkLives();
         }
         changePoints(shootIndex1);
       }
       if(shooterShooting[i] && usingShooter2 && i==shootIndex2)
       {
         if(shootBackwards2)
           shooterShootMult[shootIndex2] --;
         else
         {
           shooterShootMult[shootIndex2]++;
           checkLives();
         }
         changePoints(shootIndex2);
       }
     }
     if(usingShooter1==false && setTimer1==false)
     {
       secs1 = 10*((int)(Math.random()*2+1));
       setTimer1 = true;
     }
     if(usingShooter1 == false)
     {
         counter1++;
         if(counter1==secs1)
         {
           shootBackwards1 = false;
           counter1 = 0;
           setTimer1 = false;
           shootIndex1 = (int)(Math.random()*6);
           if(alive)
           {
             while(found1==false && loopCounter1<100)
             {
               loopCounter1++;
               shootIndex1 = (int)(Math.random()*6);
               if(shooterDead[shootIndex1]==false && shooterShooting[shootIndex1]==false)
                 found1 = true;
             }
           }
           if(found1)
             found1 = false;
           shooterShooting[shootIndex1] = true;
           createPoints(shootIndex1);
           changePoints(shootIndex1);
           usingShooter1 = true;
         }
     }
     if(numAlive>2)
     {
       if(usingShooter2==false && setTimer2==false)
       {
         secs2 = 10*((int)(Math.random()*2+1));
         setTimer2 = true;
       }
       if(usingShooter2 == false)
       {
         counter2++;
         if(counter2==secs2)
         {
           shootBackwards2 = false;
           counter2 = 0;
           setTimer2 = false;
           shootIndex2 = (int)(Math.random()*6);
           if(alive)
           {
             while(found2==false && loopCounter2<100)
             {
               loopCounter2++;
               shootIndex2 = (int)(Math.random()*6);
               if(shooterDead[shootIndex2]==false && shooterShooting[shootIndex2]==false)
                 found2 = true;
             }
           }
           if(found2)
             found2 = false;
           shooterShooting[shootIndex2] = true;
           createPoints(shootIndex2);
           changePoints(shootIndex2);
           usingShooter2 = true;
         }
       }
     }
 }
 public void checkOffscreen()//checks if bullets have gone offscreen
 {
   //if((int)(playerShootX+(20*Math.cos(angle)*(playerShootMult-1)))>600 ||(int)(playerShootY-(20*Math.sin(angle)*(playerShootMult-1)))<0 || (int)(playerShootX+(20*Math.cos(angle)*(playerShootMult-1))) <0 ||(int)(playerShootY-(20*Math.sin(angle)*(playerShootMult-1)))> 600)
   for(int i=0 ; i<6 ; i++)
   {
     if((x1[i]<0 || x1[i]>600 || x2[i]<0 || x2[i]>600 || y1[i]<0 || y1[i]>600 || y2[i]<0 || y2[i]>600))
     {
       if(i==shootIndex1)
       {
         loopCounter1 = 0;
         shooterShootMult[shootIndex1] = 1;
         shooterShooting[shootIndex1] = false;
         usingShooter1 = false;
         found1 = false;
       }
       if(i==shootIndex2)
       {
         loopCounter2 = 0;
         shooterShootMult[shootIndex2] = 1;
         shooterShooting[shootIndex2] = false;
         usingShooter2 = false;
         found2 = false;
       }
     }
}
 }
 public void checkLives()//checks if lives should be decreased
 {
   if(shot1==false && (x2[shootIndex1]+25)>=player.getX() && (x2[shootIndex1]+25)<=player.getX()+50 && (y2[shootIndex1]+25)>=player.getY() && (y2[shootIndex1]+25)<=player.getY()+50 && hard==false)
   {
     shot1 = true;
   }
   if(shot1 && !((x2[shootIndex1]+25)>=player.getX() && (x2[shootIndex1]+25)<=player.getX()+50 && (y2[shootIndex1]+25)>=player.getY() && (y2[shootIndex1]+25)<=player.getY()+50)&& hard==false )
   {
     shot1 = false;
     lives--;
   }
   if(shot2==false && (x2[shootIndex2]+25)>=player.getX() && (x2[shootIndex2]+25)<=player.getX()+50 && (y2[shootIndex2]+25)>=player.getY() && (y2[shootIndex2]+25)<=player.getY()+50&& hard==false)
   {
     shot2 = true;
   }
   if(shot2 && !((x2[shootIndex2]+25)>=player.getX() && (x2[shootIndex2]+25)<=player.getX()+50 && (y2[shootIndex2]+25)>=player.getY() && (y2[shootIndex2]+25)<=player.getY()+50)&& hard==false && levelNum[0]==false)
   {
      shot2 = false;
      lives--;
   }
   if(planet[0].contains(player) || planet[1].contains(player) || planet[2].contains(player) || planet[3].contains(player) || planet[4].contains(player) || planet[5].contains(player))
   {
     lives--;
   }
 }
 public void move()//decides random direction and distance for obstacles to move, calls movePlanets()
 {
  for(int i=0 ; i<6 ; i++)
  {
    if(shooterMoving[i]==false)
    {
      int randDirection = (int)(Math.random()*4);//R0 , U1 , L2 , D3
      if(randDirection==0)
        directions[i][0] = true;
      if(randDirection==1)
        directions[i][1] = true;
      if(randDirection==2)
        directions[i][2] = true;
      if(randDirection==3)
        directions[i][3] = true;
      moveUnit[i] = (int)(Math.random()*20+1);
      shooterMoving[i]=true;
    }
    if(moveCounter[i]==moveUnit[i] && moveCounter[i]!=0)
    {
      shooterMoving[i]=false;
      moveCounter[i]=0;
      for(int j=0 ; j<4 ; j++)
      {
        directions[i][j]=false;
      }
    }
  }
  movePlanets();
 }
 public void movePlanets()//moves obstacles and checks when move is done
 {
      for(int i=0 ; i<6 ; i++)
      {
        if(directions[i][0])
          shooterWidth[i]+=10;
        if(directions[i][1])
          shooterHeight[i]-=10;
        if(directions[i][2])
          shooterWidth[i]-=10;
        if(directions[i][3])
          shooterHeight[i]+=10;
        moveCounter[i]++;
        if(shooterWidth[i]<=0)
          shooterWidth[i]=0;
        if(shooterWidth[i]>=530)
          shooterWidth[i]=530;
        if(shooterHeight[i]<=0)
          shooterHeight[i]=0;
        if(shooterHeight[i]>=510)
          shooterHeight[i]=510;
      }
 }
 public void paintComponent(Graphics g)//paints lines, pictures
 {
   super.paintComponent(g);
   setBackground(Color.BLACK);
   Graphics2D g2 = (Graphics2D) g;
   g2.setStroke(new BasicStroke(3));
   Image street = new ImageIcon("street.jpg").getImage();
   g.drawImage(street, 0, 0, 600, 600,null);
   if(powerShield)
   {
     Image shieldPic = new ImageIcon("shield.png").getImage();
     g.drawImage(shieldPic,520,470,50,50,null);
   }
   if(shoot2x)
   {
     Image x2Pic = new ImageIcon("shoot2x.png").getImage();
     g.drawImage(x2Pic,520,470,50,50,null);
   }
   if(bombTop)
   {
     Image bombPic = new ImageIcon("bomb.png").getImage();
     g.drawImage(bombPic,520,470,50,50,null);
     if(powerBomb==false)
     {
       g.setColor(Color.RED);
       g.drawLine(520,465,570,515);
       g.drawLine(570,465,520,515);
     }
   }
   Image money = new ImageIcon("money.png").getImage();
   if(shooterDead[0] && shooterDead[1] && shooterDead[2] && shooterDead[3] && shooterDead[4] && shooterDead[5])
   {
     alive = false;
     g.drawImage(money, 85, 160, 60, 40,null);
     if(levelPlaying<9)
       pass[levelPlaying] = true;
     win = true;
     planetTimer.stop();
   }
   else if(lives<=0)
   {
     end.setText(0);
     cL.show(MasterPanel.this,END);
   }
   //Player
   g.setColor(Color.GRAY);
   Image robber = new ImageIcon("robber.png").getImage();
   player = new Rectangle(playerWidth,playerHeight,50,50); 
   g.setColor(yellow);
   if(powerBomb && expand)
   {
     playerBomb = new Rectangle((int)player.getX()+25-playerRad,(int)player.getY()+25-playerRad,playerRad*2,playerRad*2);
     g.drawOval((int)player.getX()+25-playerRad,(int)player.getY()+25-playerRad,playerRad*2,playerRad*2);
     for(int i=0 ; i<6 ; i++)
     {
       if(playerBomb.contains(planet[i]))
          shooterDead[i]=true;         
     }
   }
   g.drawImage(robber,playerWidth,playerHeight,50,50,null);
   g.setColor(yellow);
   if(playerShootMult==0)
     g.drawLine(playerWidth+25,playerHeight+25,(int)(25+playerWidth+40*Math.cos(angle)),(int)(25+playerHeight-40*Math.sin(angle)));
   else
   {
     g.drawLine((int)(playerShootX+(40*Math.cos(angle)*(playerShootMult-1))),(int)(playerShootY-(40*Math.sin(angle)*(playerShootMult-1))),bulletTipX,bulletTipY);
   }
   //shooters
   Image police = new ImageIcon("police.png").getImage();
   Image security = new ImageIcon("security.png").getImage();
   g.setColor(Color.DARK_GRAY);
   for(int j=0 ; j<6 ; j++)
   {
     if(shooterDead[j]==false)
     {
       if(j==0 || j==2 || j==4) 
       {
         g.drawImage(police, shooterWidth[j],shooterHeight[j],50,50,null);
       }
       if(j==1 || j==3 || j==5) 
       {
         g.drawImage(security,shooterWidth[j],shooterHeight[j],50,50,null);
       }
     }
       if(medium || hard)
       planet[j] = new Rectangle(shooterWidth[j],shooterHeight[j],50,50);
       if(shooterShooting[j])
         g.drawLine(x1[j]+25,y1[j]+25,x2[j]+25,y2[j]+25 );
       if(bomb[j])
       {
         Image boom = new ImageIcon("boom.png").getImage();
         g.drawImage(boom,(int)targetX[j]+25-bombDiam[j]/2,(int)(600-targetY[j]+25-bombDiam[j]/2),bombDiam[j],bombDiam[j],null);
       }
   }
   g.setColor(blue);
   if(shield)
     g.drawOval((int)player.getX()-20,(int)player.getY()-20,90,90);//15,15,50,50
   int digits = time/60/10+1;
   g.setColor(Color.BLACK);
   g.setFont(font);
   g.drawString(time/60+":"+time%60,525-digits*15,25);
   Image heart = new ImageIcon("heart.png").getImage();
   if(lives>=1)
   {
     g.drawImage(heart,550,530,20,20,null);
     if(lives>=2)
     {
        g.drawImage(heart,525,530,20,20,null);
        if(lives>=3)
        {
          g.drawImage(heart,500,530,20,20,null);
          if(lives>=4)
          {
            g.drawImage(heart,475,530,20,20,null);
            if(lives>=5)
            {
              g.drawImage(heart,450,530,20,20,null);
              if(lives>=6)
              {
                g.drawImage(heart,425,530,20,20,null);
                if(lives>=7)
                {
                  g.drawImage(heart,400,530,20,20,null);
                  if(lives>=8)
                  {
                    g.drawImage(heart,375,530,20,20,null);
                    if(lives>=9)
                    {
                      g.drawImage(heart,350,530,20,20,null);
                      if(lives==10)
                        g.drawImage(heart,325,530,20,20,null);
                    }
                  }
                }
              }
            }
          }
        }
     }
   }
 }
 public void keyPressed(KeyEvent e) //checks if keys pressed to move player, angle of shooting, shield,bomb, shoot
 {
    if (e.getKeyCode() == KeyEvent.VK_UP)
     playerHeight-=5;
   if (e.getKeyCode() == KeyEvent.VK_DOWN)
     playerHeight+=5;
   if (e.getKeyCode() == KeyEvent.VK_RIGHT)
     playerWidth+=5;
   if (e.getKeyCode() == KeyEvent.VK_LEFT)
     playerWidth-=5;
   if(e.getKeyChar()=='s')
   {  
     s = true;
   }
   if(e.getKeyChar()=='a')
   {
     a = true;
   }
   if(e.getKeyChar()=='d')
   {
     if(powerShield && allowShield)
     {
         d = true;
     }
     if(powerBomb)
     {
       expand = true;
     }
   }
 }
 public void checkTwo()//allows for two or more controls to be working at a time
 {
   if(d)
   {
     d = true;
     shield = true;
     shieldOn++;
     if(up)
       playerHeight-=5;
     if(down)
       playerHeight+=5;
     if(left)
       playerWidth-=5;
     if(right)
       playerWidth+=5;
     if(a && s==false)
     {
       a = true;
       angle+=0.3;
       if(angle>=2*Math.PI)
         angle = angle-2*Math.PI;
     }
     if(s && a==false)
     {
       s = true;
       playerShoot = true;
       playerShootX = (int)player.getX()+25;
       playerShootY = (int)player.getY()+25;
     }
   }
   if(shield && (x2[shootIndex1]+25)>=player.getX()-25 && (x2[shootIndex1]+25)<=player.getX()+80 && (y2[shootIndex1]+25)>=player.getY()-20 && (y2[shootIndex1]+25)<=player.getY()+80)
   {
       shootBackwards1 = true;
   }
   if(shield && (x2[shootIndex2]+25)>=player.getX()-20 && (x2[shootIndex2]+25)<=player.getX()+80 && (y2[shootIndex2]+25)>=player.getY()-20 && (y2[shootIndex2]+25)<=player.getY()+80)
   {
       shootBackwards2 = true;
   }
   if(a && d==false && s==false)
   {
     a = true;
     angle+=0.3;
     if(angle>=2*Math.PI)
       angle = angle-2*Math.PI;
   }
   if(s && a==false)
   {
     s = true;
     playerShoot = true;
     playerShootX = (int)player.getX()+25;
     playerShootY = (int)player.getY()+25;
   }
 }
 public void keyReleased(KeyEvent e)//checks if actions should be deactivated
 {
   if(e.getKeyChar()=='d' && allowShield)
   {
     shield = false;
     d = false;
     allowShield = false;
     wait = shieldOn/2;
     shieldOn = 0;
   }
   if(e.getKeyChar()=='a')
     a = false;
   if(e.getKeyChar()=='s')
     s = false;
    if (e.getKeyCode() == KeyEvent.VK_UP)
     up = false;
   if (e.getKeyCode() == KeyEvent.VK_DOWN)
     down = false;
   if (e.getKeyCode() == KeyEvent.VK_RIGHT)
     right = false;
   if (e.getKeyCode() == KeyEvent.VK_LEFT)
     left = false;
 }
 public void keyTyped(KeyEvent e){}//checks if keys are typed (not used)
 public void mousePressed(MouseEvent e)//checks if money is pressed
 {
   if(e.getX()>=85 && e.getX()<=145 && e.getY()>=160 && e.getY()<=200)
   {
     end.setText(lives);
     cL.show(MasterPanel.this,END);
     level.createButton();
   }
 }
 public void mouseClicked(MouseEvent e){}//checks if mouse is clicked (not used)
 public void mouseReleased(MouseEvent e){}//checks if mouse is released(not used)
 public void mouseExited(MouseEvent e){}//checks if mouse enters (not used)
 public void mouseEntered(MouseEvent e){}//checks if mouse exits (not used)
}
class GamePanel1 extends JPanel implements KeyListener,ActionListener //gamePanel two for mini game
{
  PlanetMover planetMover;
  Timer planetTimer;
  StarMover starMover;
  Timer starTimer;
  BombMover bombMover;
  Timer bombTimer;
  Rectangle planet1; Rectangle planet2; Rectangle planet3; Rectangle planet4; Rectangle planet5;
  Rectangle star1; Rectangle star2; Rectangle star3;
  Rectangle player;
  Rectangle bubble;
  boolean[]planetOnscreen;
  boolean[]starOnscreen;
  Rectangle bomb;
  int[]planetHeight;
  int[]starHeight;
  int[]starWidth;
  int bombHeight;
  int bubbleHeight;
  int bubbleWidth;
  int playerWidth;
  int[][]planetWall;
  int bubbleWall;
  boolean[]starSlideRight;
  boolean foundPlanet;
  boolean foundStar;
  boolean under200Planet;
  boolean under200Star;
  boolean playerJumping;
  boolean playerJumpRight;
  boolean hasBubble;
  int distance;
  int digits;
  int lives;
  int bombDiameter;
  int explode;
  String countdown;
  boolean bombDead;
  int bombSpeed;
  int bombWidth;
  int time;
  JButton homeButton;
  boolean hitOnce;
 public GamePanel1() //initiates arrays and timers
 {
   setLayout(null);
   homeButton = new JButton("Home");
   homeButton.addActionListener(this);
   homeButton.setBounds(20,520,100,30);
   colorButton(homeButton);
   add(homeButton);
   addKeyListener(this);
   planetWall= new int[5][2];
   planetOnscreen = new boolean[5];
   starOnscreen = new boolean[3];
   planetHeight = new int[5];
   starHeight = new int[3];
   starWidth = new int[3];
   starSlideRight = new boolean[3];
   planetMover = new PlanetMover();
   planetTimer = new Timer(50, planetMover);
   starMover = new StarMover();
   starTimer = new Timer(50, starMover);
   bombMover = new BombMover();
   bombTimer = new Timer(50, bombMover);
   setFocusable(true);
   initiate();
 }
  public void actionPerformed(ActionEvent e)//checks if home is pressed
 {
   if(e.getActionCommand().equals("Home"))
   {
       game.initiate();
       end.checkLeader(money);
       money = 0;
       levelPlaying = 1;
       pass[0] = true;
       planetTimer.stop();
       for(int i=1 ; i<9 ; i++)
       {
         pass[i] = false; // true
       }
       level.createButton();
       cL.show(MasterPanel.this,HOME);
   }
 }
 public void initiate()//sets variables
 {
   hitOnce = false;
   lose = false;
   if(one)
   {
     planetTimer.start();
   }
   if(two)
   {
     planetTimer.start();
     starTimer.start();
   }
   if(three)
   {
     planetTimer.start();
     starTimer.start();
     bombTimer.start();
   }
   distance = 0;
   lives = 3;
   bombSpeed = 25;
   countdown = "";
   bombDiameter = 20;
   hasBubble = playerJumping = playerJumpRight = bombDead = false;
   playerWidth= bubbleWidth = explode = 0;
   bubbleHeight = bombHeight = 800;
   under200Planet = under200Star = foundPlanet = foundStar = false;
   for(int i=0; i<5 ; i++)
   {
     planetOnscreen[i] = false;
     planetHeight[i] = 800;
   }
   for(int i=0; i<3 ; i++)
   {
     starOnscreen[i] = false;
     starHeight[i] = 800;
     starWidth[i] = 0;
     starSlideRight[i] = true;
   } 
 }
 public void planetCheckAvailable()//checks if all planetHeights are available to generate new police car
 {
   for(int k=0 ; k<5 && foundPlanet==false ; k++)
   {
     if(planetOnscreen[k]==false)
     {
       foundPlanet = true;
       planetOnscreen[k] = true;
       int random = (int)(Math.random()*5+1);
       planetHeight[k] = -100*random;
       int rand = (int)(Math.random()*2);
       planetWall[k][0] = rand;
       if(planetWall[k][0]==0)
           planetWall[k][1] = 0;
       else if(planetWall[k][0]==1)
           planetWall[k][1] = 542;
       }
     }
   foundPlanet = false;
 }
 public void starCheckAvailable()//checks if all starHeights are available
 {
   for(int k=0 ; k<3 && foundStar==false ; k++)
   {
     if(starOnscreen[k]==false)
     {
       foundStar = true;
       starOnscreen[k] = true;
       int random = (int)(Math.random()*5+1);
       starHeight[k] = -500*random;
       }
     }
   foundStar = false;
 }
 public void incrementation()//increases vertical and horizontal values of objects
 {
   //loop
    for(int i=0 ; i<5 ; i++)
   {
     if(planetHeight[i]>600)
     {
       planetOnscreen[i] = false;
     }
   }
   for(int i=0 ; i<3 ; i++)
   {
     if(starHeight[i]>600)
       starOnscreen[i] = false;
   }
   //incrementation
   for(int i=0 ; i<5 ; i++) // planet incrementation
   {
     planetHeight[i]+=25;
   }
     for(int i=0 ; i<3 ; i++) // star incrementation
     {
       starHeight[i]+=25;
       if(starWidth[i]>=570 && starSlideRight[i]==true)
         starSlideRight[i]=false;
       if(starWidth[i]<=0 && starSlideRight[i]==false)
         starSlideRight[i] = true;
       if(starSlideRight[i]==true)
         starWidth[i]+=10;
       else 
         starWidth[i]-=10;
   }
   bubbleHeight+=25; // bubble incrementation
   bombHeight +=bombSpeed; // bombHeight
   if(playerJumping == true) // player incrementation
   {
     if(playerJumpRight == true)
     {
       if(playerWidth>=500)
         playerJumping = false;
       playerWidth+=40;
     }
     else
     {
       if(playerWidth<=40)
         playerJumping = false;
       playerWidth-=40;
     }
   }
 }
 class PlanetMover implements ActionListener//timer for police cars
 {
   public void actionPerformed(ActionEvent e)//stuff in here ran every time increment
   {
     planetTimer.restart();
       distance++;
     int tempDist = distance;
     while(tempDist!=0)//check digits
     {
       digits++;
       tempDist = tempDist/10;
     }
     for(int i=0 ; i<5 ; i++)
     {
       if(planetHeight[i]<200)
         under200Planet = true;
     }
     if(under200Planet == false)
       planetCheckAvailable();
     under200Planet = false;
     if(bubbleHeight>600)
     {
       int random = (int)(Math.random()*5+1);
       bubbleHeight = -100*random;
       bubbleWall = (int)(Math.random()*2);
       if(bubbleWall==0)
         bubbleWidth=0;
       else
         bubbleWidth=547;       
     }
     incrementation();
     repaint();
   }
 }
  class StarMover implements ActionListener//timer for taxis
 {
   public void actionPerformed(ActionEvent e)//stuff in here ran every time increment
   {
     for(int i=0 ; i<3 ; i++)
     {
       if(starHeight[i]<200)
         under200Star = true;
     }
     if(under200Star == false)
       starCheckAvailable();
     under200Star = false;
   }
 }
 class BombMover implements ActionListener//timer for firetrucks
 {
  public void actionPerformed(ActionEvent e)//stuff in here ran every time increment
  {
   if(bombHeight>600)
   {
     hitOnce = false;
     bombDead = false;
     int randHeight = (int)(Math.random()*5+1);
     bombHeight = -400*randHeight;
     int randExplode = (int)(Math.random()*5+14);
     explode = randExplode*25;
     int randSpeed = (int)(Math.random()*7+3);
     bombSpeed = randSpeed*5;
     int randWidth = (int)(Math.random()*6);
     bombWidth = randWidth*50;
   } 
   if(bombHeight>=explode/6)
   {
     countdown = "5";
     bombDiameter = 30;
   }
   if(bombHeight>=2*explode/6)
   {
     countdown = "4";
     bombDiameter = 40;
   }
   if(bombHeight>=3*explode/6)
   {
     countdown = "3";
     bombDiameter = 65;
   }
   if(bombHeight>=4*explode/6)
   {
     countdown = "2";
     bombDiameter = 90;
   }
   if(bombHeight>=5*explode/6)
   {
     countdown = "1";
     bombDiameter = 125;
   }
   if(bombHeight >= explode)
   {
     bombDead = true;
     bombDiameter = 20;
     countdown = "";
   }
  }
 }
 public void paintComponent(Graphics g)//paints lines , images
 {
   super.paintComponent(g);
   setBackground(Color.GRAY);
   Graphics2D g2 = (Graphics2D) g;
   g2.setStroke(new BasicStroke(2));
   g.setColor(Color.YELLOW);
   g.fillRect(280,0,50,80);
   g.fillRect(280,100,50,100);
   g.fillRect(280,220,50,100);
   g.fillRect(280,340,50,100);
   g.fillRect(280,460,50,100);
   Font bombFont = new Font("Arial",Font.PLAIN,40);
   g.setFont(bombFont);
   //Bomb
   Image firetruck = new ImageIcon("fireTop.png").getImage();
   if(bombDead == false)
   {
     g.setColor(Color.ORANGE);
     g.drawImage(firetruck,bombWidth,bombHeight,bombDiameter,bombDiameter,null);
     bomb = new Rectangle(bombWidth,bombHeight,bombDiameter,bombDiameter);
     g.setColor(Color.WHITE);
     g.drawString(countdown,bombWidth+(bombDiameter-10)/2-5,bombHeight+bombDiameter/2+5);
   }
   // Bubble
   Image ambulance = new ImageIcon("ambulanceTop.png").getImage();
   g.setColor(Color.LIGHT_GRAY);
   g.drawImage(ambulance,bubbleWidth, bubbleHeight,35,35,null);
   bubble = new Rectangle(bubbleWidth, bubbleHeight,40,40);
   player = new Rectangle(playerWidth,400,20,20);
   if(bubble.contains(player) && hasBubble == false)
     hasBubble=true;
   // Stars
    Image taxi = new ImageIcon("taxiTop.png").getImage();
     g.setColor(Color.CYAN);
     g.drawImage(taxi, starWidth[0],starHeight[0],50,50,null);
     star1 = new Rectangle(starWidth[0],starHeight[0],55,55);
     //
     g.drawImage(taxi, starWidth[1],starHeight[1],50,50,null);
     star2 = new Rectangle(starWidth[1],starHeight[1],55,55);
     //
     g.drawImage(taxi, starWidth[2],starHeight[2],50,50,null);
     star3 = new Rectangle(starWidth[2],starHeight[2],55,55);
   // Planet
   Image police = new ImageIcon("policeTop.png").getImage();
   g.setColor(Color.WHITE);
   g.drawImage(police, planetWall[0][1],planetHeight[0],45,45,null);
   planet1 = new Rectangle(planetWall[0][1],planetHeight[0],40,40);
   //
   g.drawImage(police, planetWall[1][1],planetHeight[1],45,45,null);
   planet2 = new Rectangle(planetWall[1][1],planetHeight[1],40,40);
   //
   g.drawImage(police, planetWall[2][1],planetHeight[2],45,45,null);
   planet3 = new Rectangle(planetWall[2][1],planetHeight[2],40,40);
   //
   g.drawImage(police, planetWall[3][1],planetHeight[3],45,45,null);
   planet4 = new Rectangle(planetWall[3][1],planetHeight[3],40,40);
   //
   g.drawImage(police, planetWall[4][1],planetHeight[4],45,45,null);
   planet5 = new Rectangle(planetWall[3][1],planetHeight[3],40,40);
   // Player
   if(hasBubble==true)
   {
     g.setColor(blue);
     g.drawOval(playerWidth-10,390,30,30);
   }
   Image robber = new ImageIcon("robber.png").getImage();
   g.drawImage(robber,playerWidth-7,393,30,30,null);
   // Hearts
   Image heart = new ImageIcon("heart.png").getImage();
   if(lives>=1)
   {
     g.drawImage(heart,550,530,20,20,null);
     if(lives>=2)
     {
        g.drawImage(heart,525,530,20,20,null);
        if(lives>=3)
          g.drawImage(heart,500,530,20,20,null);
     }
   }
   g.setColor(Color.BLACK);
   g.setFont(font);
   g.drawString("Distance : "+distance,440-digits*15,25);
   digits = 0;
   if(distance>=500)
   {
     win = true;
     end.setText(lives);
     cL.show(MasterPanel.this,END);
   }
   if(planet1.contains(player) || planet2.contains(player) || planet3.contains(player) || planet4.contains(player) || planet5.contains(player) || star1.contains(player) || star2.contains(player) || star3.contains(player) || bomb.contains(player))
   {
     if(bomb.contains(player))
     {
       if(hitOnce==false)
       {
         hitOnce=true;
         if(hasBubble)
           hasBubble=false;
         else
           lives--;
       }
     }
     else if(hasBubble==true)
       hasBubble=false;
     else 
     {
         lives--;
     }
     if(lives<=0)
     {
       lose = true;
       end.setText(lives);
       cL.show(MasterPanel.this,END);
     }
   }
 }
 public void keyPressed(KeyEvent e) {} //checks if keys pressed (not used)
 public void keyReleased(KeyEvent e){} //checks if key released (not used)
 public void keyTyped(KeyEvent e)//checks if space is pressed to jump from side to side
 { 
  if ( e.getKeyChar() == ' ' )
  {
    if(playerJumping==false)
    {
    playerJumping = true;
    playerJumpRight = !playerJumpRight;
    repaint();
    }
  }
 }
}
 class EndPanel extends JPanel implements ActionListener//shows up when game is paused, player either loses or wins
 {
   JButton bailButton;
   JLabel loseLabel;
   JButton homeButton;
   JButton playButton;
   JLabel moneyLabel;
   JButton addButton;
   boolean sackBig;
   boolean sackSmall;
   boolean sadRobber;
   boolean happyRobber;
   boolean top;
   Font font;
   Font font2;
   boolean last;
   public void paintComponent(Graphics g)//draws money sack, jail bars, robber
   {
     super.paintComponent(g);
     setBackground(Color.WHITE);
     Image bars = new ImageIcon("bars.png").getImage();
     Image cash = new ImageIcon("money.png").getImage();
     if(sackBig)
     {
         Image bigSack = new ImageIcon("sack.png").getImage();
         g.drawImage(bigSack,100,100,350,350,null);
         g.drawImage(cash,300,400,200,100,null);
     }
     if(sackSmall)
     {
       Image smallSack = new ImageIcon("sackSmall.png").getImage();
       g.drawImage(smallSack,100,100,350,350,null);
     }
     if(sadRobber)
     {
       Image sadRob = new ImageIcon("sadRobber.png").getImage();
       g.drawImage(sadRob,120,100,400,400,null);
       g.drawImage(bars,100,100,400,400,null);
     }
     if(happyRobber)
     {
       g.drawImage(bars,100,100,400,400,null);
       Image happyRob = new ImageIcon("happyRobber.png").getImage();
       g.drawImage(happyRob,0,100,650,400,null);
       g.drawImage(cash,450,120,80,50,null);
     }
     if(money<0)
     {
       g.setColor(Color.BLACK);
       g.fillRect(0,200,600,200);
       g.setFont(font2);
       g.setColor(Color.WHITE);
       g.drawString("You are in debt",120,300);
     }
     if(top)
     {
       g.setColor(Color.BLACK);
       g.fillRect(0,200,600,200);
       g.setFont(font2);
       g.setColor(Color.WHITE);
       if(money>=scores[0])
         g.drawString("You Placed 1st Place",90,300);
       else if(money>=scores[1])
         g.drawString("You Placed 2nd Place",90,300);
       else if(money>=scores[2])
         g.drawString("You Placed 3rd Place",90,300);
       else if(money>=scores[3])
         g.drawString("You Placed 4th Place",90,300);
       else if(money>=scores[4])
         g.drawString("You Placed 5th Place",90,300);
     }
   }
   public EndPanel()//initiates, changes size, adds buttons
   {
     last = false;
     font2 = new Font("Arial Narrow",Font.BOLD,50);
     font = new Font("Arial Narrow",Font.BOLD,20);
     top = false;
     setLayout(null);
     setBackground(Color.WHITE);
     loseLabel = new JLabel("You Lose");
     loseLabel.setFont(font);
     loseLabel.setBounds(10,0,400,50);
     loseLabel.setForeground(Color.BLACK);
     add(loseLabel);
     playButton = new JButton("Replay");
     playButton.setBounds(470,500,100,50);
     playButton.addActionListener(this);
     colorButton(playButton);
     add(playButton);
     homeButton = new JButton("Home");
     homeButton.addActionListener(this);
     homeButton.setBounds(20,520,100,30);
     colorButton(homeButton);
     add(homeButton);
     bailButton = new JButton("Bail");
     bailButton.setBounds(200,500,150,50);
     bailButton.addActionListener(this);
     colorButton(bailButton);
     addButton = new JButton("Add");
     addButton.addActionListener(this);
     addButton.setBounds(200,500,150,50);
     colorButton(addButton);
     moneyLabel = new JLabel("Money : $"+money);
     moneyLabel.setFont(font);
     moneyLabel.setBounds(450,0,200,50);
     moneyLabel.setForeground(Color.BLACK);
     add(moneyLabel);
   }
   public void setText(int lives)//sets win or lose texts
   {
     if(win)
     {
       money+=100;
       money+=10*lives;
       loseLabel.setText("Congrats, you escaped the police!");
       playButton.setText("Continue");
       win = false;
       add(addButton,BorderLayout.CENTER);
       addButton.setVisible(true);
       sackSmall = true;
       sackBig = false;
       happyRobber = sadRobber = false;
       playButton.setVisible(false);
       repaint();
     }
     else 
     {
       loseLabel.setText("Oh no, you've been arrested!");
       playButton.setText("Replay");
       add(bailButton);
       bailButton.setVisible(true);
       sackBig = sackSmall = false;
       sadRobber = true;
       happyRobber = false;
       playButton.setVisible(false);
       repaint();
     }
   }
   public void checkLeader(int money)//checks if leader array should be updated with current player's money
     {
       boolean inserted = false;
       for(int i=0 ; i<5 &&  inserted==false ; i++)
       {
         if(money>=scores[i])
         {
           inserted = true;
           for(int j=4 ; j>=i+1 ; j--)
           {
             scores[j] = scores[j-1];
             names[j] = names[j-1];
           }
           scores[i] = money;
           names[i] = username;
         }
       }
       leader.append();
       leader.changeLeader();
 }
   public void actionPerformed(ActionEvent e)// checks which button is pressed and shows corresponding JPanel
   {
     if(e.getActionCommand().equals("Bail"))
     {
         money-=50;
         moneyLabel.setText("Money : $"+money);
         bailButton.setVisible(false);
         happyRobber = true;
         sadRobber= false;
         if(money>=0)
           playButton.setVisible(true);
         repaint();
     }
     if(e.getActionCommand().equals("Add"))
     {
         moneyLabel.setText("Money : $"+money);
         addButton.setVisible(false);
         sackBig = true;
         sackSmall = false;
         if(last==false)
           playButton.setVisible(true);
         if(levelNum[8] && money>=scores[4] && last)
           top = true;
         if(levelNum[8]==true)
           last = true;
         repaint();
     }
     if(e.getActionCommand().equals("Continue"))
     {
         if(levelPlaying==3)
         {
           one=true;
         }
         if(levelPlaying==6)
           two=true;
         if(levelPlaying==9)
           three=true;
         alive = true;
         if(levelPlaying==3 || levelPlaying==6 || levelPlaying==9)
         {
           cL.show(MasterPanel.this,GAME1);
           game1.requestFocus();
           game1.initiate();
         }
         else
           cL.show(MasterPanel.this,LEVEL);
         levelPlaying++;
     }
     if(e.getActionCommand().equals("Replay"))
     {
       if(lose)
       {
         cL.show(MasterPanel.this,GAME1);
         game1.requestFocus();
         game1.initiate();
       }
       else
         cL.show(MasterPanel.this,LEVEL);
     }
     if(e.getActionCommand().equals("Home"))
     {
       game.initiate();
       checkLeader(money);
       money = 0;
       levelPlaying = 1;
       pass[0] = true;
       for(int i=1 ; i<9 ; i++)
       {
         pass[i] = false; // true
       }
       level.createButton();
       cL.show(MasterPanel.this,HOME);
     }
   }
 }
}
}