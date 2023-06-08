public class Ports implements Comparable<Ports> {
  private int col;
  private int row;
  private int value;
  private boolean visited;

  public Ports(int value, int col, int row) {
    this.col = col;
    this.row = row;
    this.value = value;
    this.visited = false;
  }

  public int getValue() {
    return this.value;
  }

  public int getCol() {
    return this.col;
  }

  public int getRow() {
    return this.row;
  }

  public void setIsVisited() {
    this.visited = true;
  }

  public boolean getVisited() {
    return this.visited;
  }

  @Override
  public int compareTo(Ports anotherPort) {
    if (this.value < anotherPort.value) {
      return -1;
    }
    if (this.value > anotherPort.value) {
      return 1;
    }
    return 0;
  }

}
