public class Point {
  int col;
  int row;
  boolean visit;
  Point fatherPoint;
  boolean isPort;

  public Point(int col, int row) {
    this.col = col;
    this.row = row;
    this.isPort = false;
  }

  public boolean getVisit() {
    return visit;
  }

  public Point getFatherPoint() {
    return fatherPoint;
  }

  public void setIsPort() {
    this.isPort = true;
  }

  public boolean getIsPort() {
    return isPort;
  }
  
}
