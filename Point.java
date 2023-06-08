public class Point {
  private char value;
  private int col;
  private int row;
  private boolean isVisited;
  private Point father;
  private boolean isPort;

  public Point(int col, int row, char value) {
    this.value = value;
    this.col = col;
    this.row = row;
    this.isPort = false;
  }

  public boolean getIsVisited() {
    return isVisited;
  }

  public void setIsVisited() {
    this.isVisited = true;
  }

  public Point getFather() {
    return father;
  }

  public void setFather(Point father) {
    this.father = father;
  }

  public void setIsPort() {
    this.isPort = true;
  }

  public boolean getIsPort() {
    return isPort;
  }

  public char getValue() {
    return value;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public String getKey() {
    return Integer.toString(this.col) + Integer.toString(this.row);
  }

}
