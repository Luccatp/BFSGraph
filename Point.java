public class Point {
  private int col;
  private int row;
  private boolean isVisited;
  private Point father;
  private boolean isPort;

  public Point(int col, int row) {
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

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

}
