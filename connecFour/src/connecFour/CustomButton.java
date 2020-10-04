package connecFour;

import javax.swing.JButton;

public class CustomButton extends JButton {
  private int id;
  
  private int col;
  
  private int row;
  
  public CustomButton(int i, int j) {
    this.id = j + 1;
    this.row = i;
    this.col = j;
  }
  
  public int getID() {
    return this.id;
  }
  
  public int getCol() {
    return this.col;
  }
  
  public int getRow() {
    return this.row;
  }
}
