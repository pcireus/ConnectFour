package connecFour;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectFour extends JFrame {
  private static int BoardSize = 6;
  
  static int count;
  
  boolean isWinner;
  
  boolean isTie;
  
  private static String Boardcolor;
  
  private static String Music = "";
  
  private static int PlayId;
  
  JPanel panel = new JPanel();
  
  static String[][] GameBoard = new String[BoardSize][BoardSize + 1];
  
  static CustomButton[][] button = new CustomButton[BoardSize][BoardSize + 1];
  
  static ConnectFour Game;
  
  static ConnectFourPlayer PlayerOne = new ConnectFourPlayer("Player 1", Color.BLACK);
  
  static ConnectFourPlayer PlayerTwo = new ConnectFourPlayer("Player 2", Color.RED);
  
  public static void setPlayer1name(String str) {
    PlayerOne.playerName = str;
  }
  
  public static void setPlayer1Ch(Color str) {
    PlayerOne.PlayerBallColor = str;
  }
  
  public static void setPlayer2Ch(Color str) {
    PlayerTwo.PlayerBallColor = str;
  }
  
  public static void setPlayer2name(String str) {
    PlayerTwo.playerName = str;
  }
  
  public static void countBack() {
    count--;
  }
  
  public ConnectFour(final int GameType) {
    setTitle("New Game");
    setSize(400, 400);
    setBackground(Color.pink);
    setVisible(true);
    PlayerOne.PlayerBall = "X";
    PlayerTwo.PlayerBall = "O";
    count = 0;
    this.isWinner = false;
    this.isTie = false;
    for (int i = 0; i < GameBoard.length; i++) {
      for (int k = 0; k < (GameBoard[i]).length; k++) {
        GameBoard[i][k] = " ";
        button[i][k] = new CustomButton(i, k);
        button[i][k].addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                if (!ConnectFour.this.isWinner) {
                  CustomButton cb = (CustomButton)e.getSource();
                  ConnectFour.count++;
                  if (GameType == 1) {
                    if (ConnectFour.this.isOdd(ConnectFour.count)) {
                      ConnectFour.this.isWinner = ConnectFour.PlayerOne.NextPlayerTurn(cb.getID());
                    } else {
                      ConnectFour.this.isWinner = ConnectFour.PlayerTwo.NextPlayerTurn(cb.getID());
                    } 
                    award();
                  } else if (GameType == 2) {
                    ConnectFour.this.isWinner = ConnectFour.PlayerOne.NextPlayerTurn(cb.getID());
                    if (!ConnectFour.this.isWinner) {
                      ConnectFour.count++;
                      ConnectFour.PlayerTwo.RandPlay();
                      ConnectFour.this.isWinner = ConnectFour.PlayerTwo.winner();
                    } 
                    award();
                  } else if (GameType == 3) {
                    ConnectFour.this.isWinner = ConnectFour.PlayerOne.NextPlayerTurn(cb.getID());
                    if (!ConnectFour.this.isWinner) {
                      ConnectFour.count++;
                      ConnectFour.PlayerTwo.IntermedCPUMoves();
                      ConnectFour.this.isWinner = ConnectFour.PlayerTwo.winner();
                    } 
                    award();
                  } else if (GameType == 4) {
                    ConnectFour.this.isWinner = ConnectFour.PlayerOne.NextPlayerTurn(cb.getID());
                    if (!ConnectFour.this.isWinner) {
                      ConnectFour.count++;
                      ConnectFour.PlayerTwo.CompepetiveCPUMoves();
                      ConnectFour.this.isWinner = ConnectFour.PlayerTwo.winner();
                    } 
                    award();
                  } else if (GameType == 5) {
                    do {
                      ConnectFour.this.isWinner = false;
                      ConnectFour.PlayerOne.CompepetiveCPUMoves();
                      ConnectFour.this.isWinner = ConnectFour.PlayerOne.winner();
                      if (ConnectFour.this.isWinner)
                        continue; 
                      ConnectFour.PlayerTwo.CompepetiveCPUMoves();
                      ConnectFour.this.isWinner = ConnectFour.PlayerTwo.winner();
                    } while (!ConnectFour.this.isWinner);
                    award();
                  } 
                } else {
                  System.exit(1);
                } 
              }
              
              private void award() {
                if (ConnectFour.this.isWinner)
                  ConnectFour.this.setVisible(false); 
              }
            });
        this.panel.add(button[i][k]);
      } 
    } 
    this.panel.setLayout(new GridLayout(BoardSize, BoardSize + 1));
    JInternalFrame inF = new JInternalFrame("New Game");
    inF.add(this.panel);
    inF.setTitle(String.valueOf(PlayerOne.playerName) + " VS " + PlayerTwo.playerName);
    inF.setSize(400, 400);
    inF.setVisible(true);
    add(inF);
  }
  
  public boolean isOdd(int num) {
    if (num % 2 != 0)
      return true; 
    return false;
  }
  
  public static void ClearBoard() {
    for (int i = 0; i < GameBoard.length; i++) {
      for (int k = 0; k < (GameBoard[i]).length; k++)
        GameBoard[i][k] = " "; 
    } 
  }
  
  public static int MainMenu() {
    System.out.println("Game Main Menu");
    System.out.println("1) New Game");
    System.out.println("2) Game Setting");
    System.out.println("3) Exit");
    Scanner reader = new Scanner(System.in);
    int choice = reader.nextInt();
    return choice;
  }
  
  public static int NewGameMenu() {
    System.out.println("Player Option:");
    System.out.println("1) Single Player");
    System.out.println("2) Multiplyer");
    System.out.println("3) Main Menu");
    Scanner reader = new Scanner(System.in);
    int choice = reader.nextInt();
    return choice;
  }
  
  public static int GameLevel() {
    System.out.println("Difficulty Level:");
    System.out.println("1) Easy");
    System.out.println("2) Intermediate");
    System.out.println("3) Competitive");
    System.out.println("4) return");
    Scanner reader = new Scanner(System.in);
    int choice = reader.nextInt();
    return choice;
  }
  
  public static int GameSetting() {
    System.out.println("Game Settings");
    System.out.println("1) Background & Celebration Music");
    System.out.println("2) Player Name");
    System.out.println("3) Checker Color");
    System.out.println("4) Game Board color");
    System.out.println("5) Default Settings");
    System.out.println("6) Return");
    Scanner reader = new Scanner(System.in);
    int choice = reader.nextInt();
    return choice;
  }
  
  public static void printBoard() {
    System.out.println(" \nGamebaord: \n");
    int colBar = 0;
    int rowBar = 0;
    for (int i = 0; i < GameBoard.length; i++) {
      colBar = 0;
      for (int k = 0; k < (GameBoard[i]).length; k++) {
        System.out.print(" " + GameBoard[i][k]);
        if (colBar < 6) {
          System.out.print(" | ");
          colBar++;
        } else {
          System.out.print("\n");
        } 
      } 
      if (rowBar < 5) {
        System.out.println("---------------------------------");
        rowBar++;
      } 
    } 
    System.out.println("\n");
  }
  
  public static void main(String[] args) {
    final JDesktopPane df = new JDesktopPane();
    JFrame window = new JFrame();
    window.add(df);
    window.setSize(640, 480);
    window.setTitle("Home");
    df.setBackground(Color.magenta);
    window.setDefaultCloseOperation(3);
    window.setVisible(true);
    JMenuBar jmb = new JMenuBar();
    jmb.setBackground(Color.green);
    window.setJMenuBar(jmb);
    JMenu newGame = new JMenu("New Game");
    JMenu gameSetting = new JMenu("Game Setting");
    JMenu exitGame = new JMenu("Exit Game");
    newGame.setMnemonic(78);
    gameSetting.setMnemonic(83);
    exitGame.setMnemonic(69);
    jmb.add(newGame);
    jmb.add(gameSetting);
    jmb.add(exitGame);
    JMenuItem newGameOption1 = new JMenuItem("MultiPlayer");
    JMenuItem newGameOption3 = new JMenuItem("AI VS AI");
    JMenu newGameOption2 = new JMenu("Single Player");
    newGame.add(newGameOption2);
    newGame.add(newGameOption1);
    newGame.add(newGameOption3);
    newGameOption1.setMnemonic(77);
    newGameOption2.setMnemonic(83);
    newGameOption3.setMnemonic(65);
    newGameOption1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ConnectFour.Game = new ConnectFour(1);
          }
        });
    JMenuItem multiOption1 = new JMenuItem("Easy");
    JMenuItem multiOption2 = new JMenuItem("Intermediate");
    JMenuItem multiOption3 = new JMenuItem("Competitive");
    newGameOption2.add(multiOption1);
    newGameOption2.add(multiOption2);
    newGameOption2.add(multiOption3);
    multiOption1.setMnemonic(69);
    multiOption2.setMnemonic(73);
    multiOption3.setMnemonic(67);
    multiOption1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ConnectFour.Game = new ConnectFour(2);
          }
        });
    multiOption2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ConnectFour.Game = new ConnectFour(3);
          }
        });
    multiOption3.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ConnectFour.Game = new ConnectFour(4);
          }
        });
    newGameOption3.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            ConnectFour.Game = new ConnectFour(5);
          }
        });
    JMenuItem gameSettingOption2 = new JMenuItem("Player Name");
    JMenu gameSettingOption3 = new JMenu("Checker Color");
    JMenuItem gamesettingp1 = new JMenuItem(ConnectFour.PlayerOne.playerName);
    JMenuItem gamesettingp2 = new JMenuItem(ConnectFour.PlayerTwo.playerName);
    gameSetting.add(gameSettingOption2);
    gameSetting.add(gameSettingOption3);
    gameSettingOption3.add(gamesettingp1);
    gameSettingOption3.add(gamesettingp2);
    JMenuItem exitOption = new JMenuItem("Exit Game");
    exitOption.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            System.exit(1);
          }
        });
    final JTextField name = new JTextField("Player 1", 10);
    final JTextField name2 = new JTextField("Player 2", 10);
    final JButton submit = new JButton("Submit");
    final JLabel vs = new JLabel(" VS ");
    gameSettingOption2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            final JInternalFrame inF2 = new JInternalFrame("Game Setting", true, true, true, true);
            inF2.setTitle("Game Setting");
            inF2.setSize(300, 100);
            inF2.setVisible(true);
            inF2.setBackground(Color.LIGHT_GRAY);
            JPanel setting = new JPanel();
            ActionListener submitting = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  ConnectFour.setPlayer1name(name.getText());
                  ConnectFour.setPlayer2name(name2.getText());
                  inF2.setVisible(false);
                }
              };
            inF2.add(setting);
            setting.add(name);
            setting.add(vs);
            setting.add(name2);
            setting.add(submit);
            submit.addActionListener(submitting);
            name.addActionListener(submitting);
            name2.addActionListener(submitting);
            df.add(inF2);
          }
        });
    ActionListener checkerColor = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          final JInternalFrame inF2 = new JInternalFrame("Game Setting", true, true, true, true);
          inF2.setTitle(String.valueOf(ConnectFour.PlayerOne.playerName) + "'s " + "checker color");
          inF2.setSize(400, 400);
          inF2.setVisible(true);
          inF2.setBackground(Color.LIGHT_GRAY);
          JPanel setting = new JPanel();
          final CustomButton[][] colorB = new CustomButton[4][2];
          for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 2; k++) {
              colorB[i][k] = new CustomButton(i, k);
              colorB[i][k].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      CustomButton cb = (CustomButton)e.getSource();
                      ConnectFour.setPlayer1Ch(colorB[cb.getRow()][cb.getCol()].getBackground());
                      inF2.setVisible(false);
                    }
                  });
              setting.add(colorB[i][k]);
            } 
          } 
          colorB[0][0].setBackground(Color.RED);
          colorB[0][1].setBackground(Color.PINK);
          colorB[1][0].setBackground(Color.YELLOW);
          colorB[1][1].setBackground(Color.BLACK);
          colorB[2][0].setBackground(Color.BLUE);
          colorB[2][1].setBackground(Color.cyan);
          colorB[3][0].setBackground(Color.CYAN);
          colorB[3][1].setBackground(Color.WHITE);
          df.add(inF2);
          setting.setLayout(new GridLayout(4, 2));
          inF2.add(setting);
        }
      };
    ActionListener checkerColor2 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          final JInternalFrame inF2 = new JInternalFrame("Game Setting", true, true, true, true);
          inF2.setTitle(String.valueOf(ConnectFour.PlayerTwo.playerName) + "'s " + "checker color");
          inF2.setSize(400, 400);
          inF2.setVisible(true);
          inF2.setBackground(Color.LIGHT_GRAY);
          JPanel setting = new JPanel();
          final CustomButton[][] colorB = new CustomButton[4][2];
          for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 2; k++) {
              colorB[i][k] = new CustomButton(i, k);
              colorB[i][k].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      CustomButton cb = (CustomButton)e.getSource();
                      ConnectFour.setPlayer2Ch(colorB[cb.getRow()][cb.getCol()].getBackground());
                      inF2.setVisible(false);
                    }
                  });
              setting.add(colorB[i][k]);
            } 
          } 
          colorB[0][0].setBackground(Color.RED);
          colorB[0][1].setBackground(Color.GREEN);
          colorB[1][0].setBackground(Color.YELLOW);
          colorB[1][1].setBackground(Color.BLACK);
          colorB[2][0].setBackground(Color.BLUE);
          colorB[2][1].setBackground(Color.PINK);
          colorB[3][0].setBackground(Color.CYAN);
          colorB[3][1].setBackground(Color.orange);
          df.add(inF2);
          setting.setLayout(new GridLayout(4, 2));
          inF2.add(setting);
        }
      };
    gamesettingp1.addActionListener(checkerColor);
    gamesettingp2.addActionListener(checkerColor2);
    exitGame.add(exitOption);
    int choice1 = 0;
    int choice2 = 0;
    int choice3 = 0;
    int choice4 = 0;
    boolean defaultPlayerName = true;
    ConnectFourPlayer PlayerOne = new ConnectFourPlayer("Player 1", Color.BLACK);
    ConnectFourPlayer PlayerTwo = new ConnectFourPlayer("Player 2", Color.RED);
    PlayerOne.PlayerBall = "X";
    PlayerTwo.PlayerBall = "O";
    do {
      boolean isWinner;
      choice1 = MainMenu();
      switch (choice1) {
        case 1:
          isWinner = false;
          do {
            boolean QuiteGame;
            choice2 = NewGameMenu();
            switch (choice2) {
              case 2:
                do {
                  boolean bool;
                  choice3 = GameLevel();
                  switch (choice3) {
                    case 1:
                      bool = false;
                      if (defaultPlayerName) {
                        PlayerOne.playerName = "Player 1";
                        PlayerTwo.playerName = "Computer";
                      } 
                      do {
                        isWinner = false;
                        bool = false;
                        bool = PlayerOne.NextPlayerTurn();
                        if (!bool) {
                          printBoard();
                          isWinner = PlayerOne.winner();
                        } 
                        if (isWinner || bool)
                          continue; 
                        PlayerTwo.RandPlay();
                        isWinner = PlayerTwo.winner();
                        printBoard();
                      } while (!isWinner && !bool);
                      ClearBoard();
                      break;
                    case 2:
                      bool = false;
                      if (defaultPlayerName) {
                        PlayerOne.playerName = "Player 1";
                        PlayerTwo.playerName = "Computer";
                      } 
                      do {
                        isWinner = false;
                        bool = false;
                        bool = PlayerOne.NextPlayerTurn();
                        if (!bool) {
                          printBoard();
                          isWinner = PlayerOne.winner();
                        } 
                        if (isWinner || bool)
                          continue; 
                        PlayerTwo.IntermedCPUMoves();
                        isWinner = PlayerTwo.winner();
                        printBoard();
                      } while (!isWinner && !bool);
                      ClearBoard();
                      break;
                    case 3:
                      bool = false;
                      if (defaultPlayerName) {
                        PlayerOne.playerName = "Player 1";
                        PlayerTwo.playerName = "Computer";
                      } 
                      do {
                        isWinner = false;
                        bool = false;
                        bool = PlayerOne.NextPlayerTurn();
                        if (!bool) {
                          printBoard();
                          isWinner = PlayerOne.winner();
                        } 
                        if (isWinner || bool)
                          continue; 
                        PlayerTwo.CompepetiveCPUMoves();
                        isWinner = PlayerTwo.winner();
                        printBoard();
                      } while (!isWinner && !bool);
                      ClearBoard();
                      break;
                    case 4:
                      break;
                    default:
                      System.out.println("Error");
                      break;
                  } 
                } while (choice3 != 4);
                break;
              case 1:
                printBoard();
                QuiteGame = false;
                if (defaultPlayerName) {
                  PlayerOne.playerName = "Player 1";
                  PlayerTwo.playerName = "Player 2";
                } 
                printBoard();
                isWinner = PlayerOne.winner();
                do {
                  isWinner = false;
                  QuiteGame = false;
                  QuiteGame = PlayerOne.NextPlayerTurn();
                  if (!QuiteGame) {
                    printBoard();
                    isWinner = PlayerOne.winner();
                  } 
                  if (isWinner || QuiteGame)
                    continue; 
                  QuiteGame = PlayerTwo.NextPlayerTurn();
                  isWinner = PlayerTwo.winner();
                  printBoard();
                } while (!isWinner && !QuiteGame);
                ClearBoard();
                break;
              case 3:
                break;
              default:
                System.out.println("Error");
                break;
            } 
          } while (choice2 != 3);
          break;
        case 2:
          do {
            Scanner read;
            choice4 = GameSetting();
            switch (choice4) {
              case 1:
                break;
              case 2:
                System.out.print("Enter First player Name: ");
                read = new Scanner(System.in);
                PlayerOne.playerName = read.nextLine();
                System.out.print("Enter Second player Name: ");
                PlayerTwo.playerName = read.nextLine();
                defaultPlayerName = false;
                break;
              case 3:
              case 4:
                break;
              case 5:
                defaultPlayerName = true;
                break;
              case 6:
                break;
              default:
                System.out.println("Error");
                break;
            } 
          } while (choice4 != 6);
          break;
        case 3:
          System.out.println("See you later");
          break;
        default:
          System.out.println("Error");
          break;
      } 
    } while (choice1 != 3);
  }
}
