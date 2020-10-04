package connecFour;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JOptionPane;

public class ConnectFourPlayer {
  String playerName;
  
  String PlayerBall = "";
  
  Color PlayerBallColor;
  
  public ConnectFourPlayer(String name, Color color) {
    this.playerName = name;
    this.PlayerBall = "a";
    this.PlayerBallColor = color;
  }
  
  public int OpenSlots() {
    int count = 0;
    for (int i = 0; i < ConnectFour.GameBoard.length; i++) {
      for (int k = 0; k < (ConnectFour.GameBoard[i]).length; k++) {
        if (isPosEmpty(i, k))
          if (i != 5 && !isPosEmpty(i + 1, k)) {
            count++;
          } else if (i == 5 && isPosEmpty(i, k)) {
            count++;
          }  
      } 
    } 
    return count;
  }
  
  public boolean SpotChecker(int col, int row) {
    if (isPosEmpty(col, row)) {
      if (col == 5)
        return true; 
      boolean checked = false;
      for (int i = col + 1; i <= 5; i++) {
        if (!isPosEmpty(i, row)) {
          checked = true;
        } else {
          checked = false;
          return false;
        } 
      } 
      if (checked)
        return true; 
    } else {
      System.out.println("not empty");
    } 
    return false;
  }
  
  public boolean PossibleWin(int[] col, int[] row) {
    int SignCount = 0;
    int SignCount2 = 0;
    int OppCount = 0;
    int OppCount2 = 0;
    boolean diffLslop = false;
    for (int row1 = 0; row1 < 3; row1++) {
      SignCount = 0;
      diffLslop = false;
      for (int col1 = 3; col1 >= 0; col1--) {
        if (ConnectFour.GameBoard[row1][col1] == this.PlayerBall) {
          SignCount++;
          int k = row1;
          for (int Lslope = col1 + 1; Lslope <= 6; Lslope++) {
            if ((row1 == 0 && col1 == 0 && Lslope <= 5) || (row1 == 1 && col1 == 1 && Lslope <= 5) || (row1 == 2 && col1 == 2 && Lslope <= 5)) {
              diffLslop = true;
              if (ConnectFour.GameBoard[++k][Lslope] == this.PlayerBall) {
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } else if ((row1 == 1 && col1 == 0 && Lslope <= 4) || (row1 == 2 && col1 == 1 && Lslope <= 4)) {
              diffLslop = true;
              if (ConnectFour.GameBoard[++k][Lslope] == this.PlayerBall) {
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } else if (row1 == 2 && col1 == 0 && Lslope <= 3) {
              diffLslop = true;
              if (ConnectFour.GameBoard[++k][Lslope] == this.PlayerBall) {
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } else if (!diffLslop) {
              if (ConnectFour.GameBoard[++k][Lslope] == this.PlayerBall) {
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } 
            if (SignCount == 3 && 
              SpotChecker(k + 1, Lslope + 1)) {
              col[0] = k + 1;
              row[0] = Lslope + 1;
              return true;
            } 
          } 
        } 
      } 
    } 
    SignCount = 0;
    int EmptCol = 0;
    int EmptRow = 0;
    boolean Emptpos = false;
    for (int j = 5; j >= 3; j--) {
      SignCount = 0;
      diffLslop = false;
      for (int col1 = 3; col1 >= 0; col1--) {
        SignCount = 0;
        int k = j;
        for (int Lslope = col1; Lslope <= 6; Lslope++) {
          if ((j == 5 && col1 == 0 && Lslope <= 5) || (j == 4 && col1 == 1 && Lslope <= 5) || (j == 3 && col1 == 2 && Lslope <= 5)) {
            diffLslop = true;
            if (ConnectFour.GameBoard[k][Lslope] == this.PlayerBall) {
              SignCount++;
            } else {
              SignCount = 0;
            } 
          } else if ((j == 4 && col1 == 0 && Lslope <= 4) || (j == 3 && col1 == 1 && Lslope <= 4)) {
            diffLslop = true;
            if (ConnectFour.GameBoard[k][Lslope] == this.PlayerBall) {
              SignCount++;
            } else {
              SignCount = 0;
            } 
          } else if (j == 3 && col1 == 0 && Lslope <= 3) {
            diffLslop = true;
            if (ConnectFour.GameBoard[k][Lslope] == this.PlayerBall) {
              SignCount++;
            } else {
              SignCount = 0;
            } 
          } else if (!diffLslop) {
            if (ConnectFour.GameBoard[k][Lslope] == this.PlayerBall) {
              SignCount++;
            } else {
              Emptpos = true;
              EmptCol = k;
              EmptRow = Lslope;
              SignCount = 0;
            } 
          } 
          if (SignCount == 3) {
            if (Lslope != 6 && 
              SpotChecker(k - 1, Lslope + 1)) {
              col[0] = k - 1;
              row[0] = Lslope + 1;
              return true;
            } 
            if (Emptpos && SpotChecker(EmptCol, EmptRow)) {
              col[0] = EmptCol;
              row[0] = EmptRow;
              return true;
            } 
          } 
          if (k > 0)
            k--; 
        } 
      } 
    } 
    int tempCol = -1;
    int tempRow = -1;
    int tempCol2 = -1;
    int tempRow2 = -1;
    boolean getEmptySlot = false;
    boolean getEmptySlot2 = false;
    for (int i = 0; i < ConnectFour.GameBoard.length; i++) {
      SignCount = 0;
      SignCount2 = 0;
      OppCount = 0;
      OppCount2 = 0;
      getEmptySlot = false;
      getEmptySlot2 = false;
      for (int k = 0; k < (ConnectFour.GameBoard[i]).length; k++) {
        if (ConnectFour.GameBoard[i][k] == this.PlayerBall) {
          SignCount++;
        } else if (isPosEmpty(i, k)) {
          tempCol = i;
          tempRow = k;
          getEmptySlot = true;
        } else {
          SignCount = 0;
          getEmptySlot = false;
        } 
        if (k <= 5) {
          if (ConnectFour.GameBoard[k][i] == this.PlayerBall) {
            SignCount2++;
          } else if (isPosEmpty(k, i)) {
            tempCol2 = k;
            tempRow2 = i;
            getEmptySlot2 = true;
          } else {
            SignCount2 = 0;
            getEmptySlot2 = false;
          } 
          if (getEmptySlot2 && SignCount2 == 3 && SpotChecker(tempCol2, tempRow2)) {
            col[0] = tempCol2;
            row[0] = tempRow2;
            return true;
          } 
          if (getEmptySlot && SignCount == 3 && SpotChecker(tempCol, tempRow)) {
            col[0] = tempCol;
            row[0] = tempRow;
            return true;
          } 
        } 
      } 
    } 
    return false;
  }
  
  public boolean PossibleLoss(int[] col, int[] row) {
    String OppBall = "";
    if (this.PlayerBall == "X") {
      OppBall = "O";
    } else {
      OppBall = "X";
    } 
    int SignCount = 0;
    int SignCount2 = 0;
    int OppCount = 0;
    int OppCount2 = 0;
    boolean diffLslop = false;
    for (int row1 = 0; row1 < 3; row1++) {
      SignCount = 0;
      diffLslop = false;
      for (int col1 = 3; col1 >= 0; col1--) {
        if (ConnectFour.GameBoard[row1][col1] == OppBall) {
          SignCount++;
          int k = row1;
          for (int Lslope = col1 + 1; Lslope <= 6; Lslope++) {
            if ((row1 == 0 && col1 == 0 && Lslope <= 5) || (row1 == 1 && col1 == 1 && Lslope <= 5) || (row1 == 2 && col1 == 2 && Lslope <= 5)) {
              diffLslop = true;
              if (ConnectFour.GameBoard[++k][Lslope] == OppBall) {
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } else if ((row1 == 1 && col1 == 0 && Lslope <= 4) || (row1 == 2 && col1 == 1 && Lslope <= 4)) {
              diffLslop = true;
              if (ConnectFour.GameBoard[++k][Lslope] == OppBall) {
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } else if (row1 == 2 && col1 == 0 && Lslope <= 3) {
              diffLslop = true;
              if (ConnectFour.GameBoard[++k][Lslope] == OppBall) {
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } else if (!diffLslop) {
              if (ConnectFour.GameBoard[++k][Lslope] == OppBall) {
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } 
            if (SignCount == 3 && 
              SpotChecker(k + 1, Lslope + 1)) {
              col[0] = k + 1;
              row[0] = Lslope + 1;
              return true;
            } 
          } 
        } 
      } 
    } 
    SignCount = 0;
    int EmptCol = 0;
    int EmptRow = 0;
    boolean Emptpos = false;
    for (int j = 5; j >= 3; j--) {
      SignCount = 0;
      diffLslop = false;
      for (int col1 = 3; col1 >= 0; col1--) {
        SignCount = 0;
        int k = j;
        for (int Lslope = col1; Lslope <= 6; Lslope++) {
          if ((j == 5 && col1 == 0 && Lslope <= 5) || (j == 4 && col1 == 1 && Lslope <= 5) || (j == 3 && col1 == 2 && Lslope <= 5)) {
            diffLslop = true;
            if (ConnectFour.GameBoard[k][Lslope] == OppBall) {
              SignCount++;
            } else {
              SignCount = 0;
            } 
          } else if ((j == 4 && col1 == 0 && Lslope <= 4) || (j == 3 && col1 == 1 && Lslope <= 4)) {
            diffLslop = true;
            if (ConnectFour.GameBoard[k][Lslope] == OppBall) {
              SignCount++;
            } else {
              SignCount = 0;
            } 
          } else if (j == 3 && col1 == 0 && Lslope <= 3) {
            diffLslop = true;
            if (ConnectFour.GameBoard[k][Lslope] == OppBall) {
              SignCount++;
            } else {
              SignCount = 0;
            } 
          } else if (!diffLslop) {
            if (ConnectFour.GameBoard[k][Lslope] == OppBall) {
              SignCount++;
            } else {
              Emptpos = true;
              EmptCol = k;
              EmptRow = Lslope;
              SignCount = 0;
            } 
          } 
          if (SignCount == 3) {
            if (Lslope != 6 && 
              SpotChecker(k - 1, Lslope + 1)) {
              col[0] = k - 1;
              row[0] = Lslope + 1;
              return true;
            } 
            if (Emptpos && SpotChecker(EmptCol, EmptRow)) {
              col[0] = EmptCol;
              row[0] = EmptRow;
              return true;
            } 
          } 
          if (k > 0)
            k--; 
        } 
      } 
    } 
    int tempCol = -1;
    int tempRow = -1;
    int tempCol2 = -1;
    int tempRow2 = -1;
    boolean getEmptySlot = false;
    boolean getEmptySlot2 = false;
    for (int i = 0; i < ConnectFour.GameBoard.length; i++) {
      SignCount = 0;
      SignCount2 = 0;
      OppCount = 0;
      OppCount2 = 0;
      getEmptySlot = false;
      getEmptySlot2 = false;
      for (int k = 0; k < (ConnectFour.GameBoard[i]).length; k++) {
        if (ConnectFour.GameBoard[i][k] == OppBall) {
          SignCount++;
        } else if (isPosEmpty(i, k)) {
          tempCol = i;
          tempRow = k;
          getEmptySlot = true;
        } else {
          SignCount = 0;
          getEmptySlot = false;
        } 
        if (k <= 5) {
          if (ConnectFour.GameBoard[k][i] == OppBall) {
            SignCount2++;
          } else if (isPosEmpty(k, i)) {
            tempCol2 = k;
            tempRow2 = i;
            getEmptySlot2 = true;
          } else {
            SignCount2 = 0;
            getEmptySlot2 = false;
          } 
          if (getEmptySlot2 && SignCount2 == 3 && SpotChecker(tempCol2, tempRow2)) {
            col[0] = tempCol2;
            row[0] = tempRow2;
            return true;
          } 
          if (getEmptySlot && SignCount == 3 && SpotChecker(tempCol, tempRow)) {
            col[0] = tempCol;
            row[0] = tempRow;
            return true;
          } 
        } 
      } 
    } 
    return false;
  }
  
  public void IntermedCPUMoves() {
    int[] Mcol = new int[1];
    int[] Mrow = new int[1];
    if (PossibleWin(Mcol, Mrow)) {
      OffenOpp(Mcol, Mrow);
    } else {
      RandPlay();
    } 
  }
  
  private void DeffenOpp(int[] col, int[] row) {
    ConnectFour.GameBoard[col[0]][row[0]] = this.PlayerBall;
    GBoardplay(col[0], row[0]);
  }
  
  public void CompepetiveCPUMoves() {
    int[] Mcol = new int[1];
    int[] Mrow = new int[1];
    if (PossibleWin(Mcol, Mrow)) {
      OffenOpp(Mcol, Mrow);
    } else if (PossibleLoss(Mcol, Mrow)) {
      DeffenOpp(Mcol, Mrow);
    } else {
      RandPlay();
    } 
  }
  
  private void OffenOpp(int[] col, int[] row) {
    ConnectFour.GameBoard[col[0]][row[0]] = this.PlayerBall;
    GBoardplay(col[0], row[0]);
  }
  
  public void RandPlay() {
    SecureRandom randomNumbers = new SecureRandom();
    int random = 1 + randomNumbers.nextInt(OpenSlots());
    int count = 0;
    int i;
    label23: for (i = 0; i < ConnectFour.GameBoard.length; i++) {
      for (int k = 0; k < (ConnectFour.GameBoard[i]).length; k++) {
        if (isPosEmpty(i, k)) {
          if (i != 5 && !isPosEmpty(i + 1, k)) {
            count++;
          } else if (i == 5 && isPosEmpty(i, k)) {
            count++;
          } 
          if (count == random) {
            ConnectFour.GameBoard[i][k] = this.PlayerBall;
            GBoardplay(i, k);
            break label23;
          } 
        } 
      } 
    } 
  }
  
  public boolean isPosEmpty(int col, int row) {
    if (ConnectFour.GameBoard[col][row] == " ")
      return true; 
    return false;
  }
  
  public void GBoardplay(int row, int col) {
    ConnectFour.button[row][col].setBackground(this.PlayerBallColor);
  }
  
  public boolean NextPlayerTurn(int pos) {
    try {
      int i = 0;
      int count = 0;
      while (count != 6 && isPosEmpty(i, pos - 1)) {
        count++;
        i++;
      } 
      if (isPosEmpty(i - 1, pos - 1)) {
        ConnectFour.GameBoard[i - 1][pos - 1] = this.PlayerBall;
        GBoardplay(i - 1, pos - 1);
        if (winner())
          return true; 
        return false;
      } 
      ConnectFour.GameBoard[i - 1][pos - 1] = this.PlayerBall;
      GBoardplay(i - 1, pos - 1);
      if (winner())
        return true; 
      return false;
    } catch (Exception e) {
      ConnectFour.countBack();
      System.out.println("Try again!!\n\n");
      JOptionPane.showMessageDialog(null, "Invalid Move " + this.playerName + "!");
      return false;
    } 
  }
  
  public boolean NextPlayerTurn() {
    Scanner reader = new Scanner(System.in);
    try {
      System.out.println("Hit X to quit Game\n\n\n" + this.playerName + " Enter a column number (1-7)");
      String tempPos = reader.nextLine();
      if (tempPos.equals("x")) {
        System.out.println("See you later!");
        return true;
      } 
      int pos = Integer.parseInt(tempPos);
      int i = 0;
      int count = 0;
      while (count != 6 && isPosEmpty(i, pos - 1)) {
        count++;
        i++;
      } 
      if (isPosEmpty(i - 1, pos - 1)) {
        ConnectFour.GameBoard[i - 1][pos - 1] = this.PlayerBall;
        return false;
      } 
      ConnectFour.GameBoard[i - 1][pos - 1] = this.PlayerBall;
      return false;
    } catch (Exception e) {
      System.out.println("Try again!!\n\n");
      reader.nextLine();
      return false;
    } 
  }
  
  public boolean winner() {
    Stack<Integer> highlightx = new Stack<>();
    Stack<Integer> highlight2x = new Stack<>();
    Stack<Integer> highlight3x = new Stack<>();
    Stack<Integer> highlighty = new Stack<>();
    Stack<Integer> highlight2y = new Stack<>();
    Stack<Integer> highlight3y = new Stack<>();
    int SignCount = 0;
    boolean diffLslop = false;
    int row;
    for (row = 0; row < 3; row++) {
      SignCount = 0;
      diffLslop = false;
      for (int col = 3; col >= 0; col--) {
        if (ConnectFour.GameBoard[row][col] == this.PlayerBall) {
          SignCount++;
          highlight3x.push(Integer.valueOf(row));
          highlight3y.push(Integer.valueOf(col));
          int tempCol = row;
          for (int Lslope = col + 1; Lslope <= 6; Lslope++) {
            if ((row == 0 && col == 0 && Lslope <= 5) || (row == 1 && col == 1 && Lslope <= 5) || (row == 2 && col == 2 && Lslope <= 5)) {
              diffLslop = true;
              if (ConnectFour.GameBoard[++tempCol][Lslope] == this.PlayerBall) {
                SignCount++;
                highlight3x.push(Integer.valueOf(tempCol));
                highlight3y.push(Integer.valueOf(Lslope));
              } else {
                SignCount = 0;
              } 
            } else if ((row == 1 && col == 0 && Lslope <= 4) || (row == 2 && col == 1 && Lslope <= 4)) {
              diffLslop = true;
              if (ConnectFour.GameBoard[++tempCol][Lslope] == this.PlayerBall) {
                SignCount++;
                highlight3x.push(Integer.valueOf(tempCol));
                highlight3y.push(Integer.valueOf(Lslope));
              } else {
                SignCount = 0;
              } 
            } else if (row == 2 && col == 0 && Lslope <= 3) {
              diffLslop = true;
              if (ConnectFour.GameBoard[++tempCol][Lslope] == this.PlayerBall) {
                SignCount++;
                highlight3x.push(Integer.valueOf(tempCol));
                highlight3y.push(Integer.valueOf(Lslope));
              } else {
                SignCount = 0;
              } 
            } else if (!diffLslop) {
              if (ConnectFour.GameBoard[++tempCol][Lslope] == this.PlayerBall) {
                SignCount++;
                highlight3x.push(Integer.valueOf(tempCol));
                highlight3y.push(Integer.valueOf(Lslope));
              } else {
                SignCount = 0;
              } 
            } 
            if (SignCount == 4) {
              paintWinner(highlight3x, highlight3y);
              System.out.print("Connect Four!! \n\n" + this.playerName + " Wins!!");
              JOptionPane.showMessageDialog(null, "Connect Four!!\n" + this.playerName + " WINS!");
              ConnectFour.printBoard();
              return true;
            } 
          } 
        } 
      } 
    } 
    SignCount = 0;
    for (row = 5; row >= 3; row--) {
      SignCount = 0;
      diffLslop = false;
      for (int col = 3; col >= 0; col--) {
        SignCount = 0;
        if (ConnectFour.GameBoard[row][col] == this.PlayerBall) {
          SignCount++;
          highlightx.push(Integer.valueOf(row));
          highlighty.push(Integer.valueOf(col));
          int tempCol = row;
          for (int Lslope = col + 1; Lslope <= 6; Lslope++) {
            if (tempCol > 0)
              tempCol--; 
            if ((row == 5 && col == 0 && Lslope <= 5) || (row == 4 && col == 1 && Lslope <= 5) || (row == 3 && col == 2 && Lslope <= 5)) {
              diffLslop = true;
              if (ConnectFour.GameBoard[tempCol][Lslope] == this.PlayerBall) {
                highlightx.push(Integer.valueOf(tempCol));
                highlighty.push(Integer.valueOf(Lslope));
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } else if ((row == 4 && col == 0 && Lslope <= 4) || (row == 3 && col == 1 && Lslope <= 4)) {
              diffLslop = true;
              if (ConnectFour.GameBoard[tempCol][Lslope] == this.PlayerBall) {
                highlightx.push(Integer.valueOf(tempCol));
                highlighty.push(Integer.valueOf(Lslope));
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } else if (row == 3 && col == 0 && Lslope <= 3) {
              diffLslop = true;
              if (ConnectFour.GameBoard[tempCol][Lslope] == this.PlayerBall) {
                highlightx.push(Integer.valueOf(tempCol));
                highlighty.push(Integer.valueOf(Lslope));
                SignCount++;
              } else {
                SignCount = 0;
              } 
            } else if (!diffLslop) {
              if (ConnectFour.GameBoard[tempCol][Lslope] == this.PlayerBall) {
                SignCount++;
                highlightx.push(Integer.valueOf(tempCol));
                highlighty.push(Integer.valueOf(Lslope));
              } else {
                SignCount = 0;
              } 
            } 
            if (SignCount == 4) {
              paintWinner(highlightx, highlighty);
              System.out.print("Connect Four!! \n\n" + this.playerName + " Wins!!\n");
              JOptionPane.showMessageDialog(null, "Connect Four!!\n" + this.playerName + " WINS!");
              ConnectFour.printBoard();
              return true;
            } 
          } 
        } 
      } 
    } 
    SignCount = 0;
    int highy = -1;
    int highx = -1;
    int SignCount2 = 0;
    for (int i = 0; i < ConnectFour.GameBoard.length; i++) {
      SignCount = 0;
      SignCount2 = 0;
      for (int k = 0; k < (ConnectFour.GameBoard[i]).length; k++) {
        if (ConnectFour.GameBoard[i][k] == this.PlayerBall) {
          SignCount++;
          highlightx.push(Integer.valueOf(i));
          highlighty.push(Integer.valueOf(k));
        } else {
          SignCount = 0;
        } 
        if (k <= 5)
          if (ConnectFour.GameBoard[k][i] == this.PlayerBall) {
            SignCount2++;
            highlight2x.push(Integer.valueOf(k));
            highlight2y.push(Integer.valueOf(i));
          } else {
            SignCount2 = 0;
          }  
        if (SignCount == 4 || SignCount2 == 4) {
          ConnectFour.printBoard();
          System.out.println(String.valueOf(this.playerName) + " Wins!!\n" + "Good Bye!");
          if (SignCount == 4) {
            paintWinner(highlightx, highlighty);
          } else {
            paintWinner(highlight2x, highlight2y);
          } 
          JOptionPane.showMessageDialog(null, "Connect Four!!\n" + this.playerName + " WINS!");
          ConnectFour.printBoard();
          return true;
        } 
      } 
    } 
    boolean isTie = true;
    int j;
    for (j = 0; j < ConnectFour.GameBoard.length; j++) {
      for (int k = 0; k < (ConnectFour.GameBoard[j]).length; k++) {
        if (isPosEmpty(j, k))
          isTie = false; 
      } 
    } 
    if (isTie) {
      System.out.println("It is a Tie!!");
      for (j = 0; j < ConnectFour.GameBoard.length; j++) {
        for (int k = 0; k < (ConnectFour.GameBoard[j]).length; k++)
          ConnectFour.button[j][k].setBackground(Color.GREEN); 
      } 
      JOptionPane.showMessageDialog(null, "Tie Game!");
      ConnectFour.printBoard();
      return true;
    } 
    return false;
  }
  
  private void paintWinner(Stack<Integer> highlightx, Stack<Integer> highlighty) {
    ConnectFour.button[((Integer)highlightx.pop()).intValue()][((Integer)highlighty.pop()).intValue()].setBackground(Color.green);
    ConnectFour.button[((Integer)highlightx.pop()).intValue()][((Integer)highlighty.pop()).intValue()].setBackground(Color.green);
    ConnectFour.button[((Integer)highlightx.pop()).intValue()][((Integer)highlighty.pop()).intValue()].setBackground(Color.green);
    ConnectFour.button[((Integer)highlightx.pop()).intValue()][((Integer)highlighty.pop()).intValue()].setBackground(Color.green);
  }
}
