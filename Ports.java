public class Ports implements Comparable<Ports> {
  int col;
  int row;
  int value;
  boolean visited;

  public Ports(int value, int col, int row) {
    this.col = col;
    this.row = row;
    this.value = value;
    this.visited = false;
  }

  public int getValue() {
    return this.value;
  }
  
  public int[] getPosition() {
    int[] position = new int[2];
    position[0] = this.col;
    position[1] = this.row;
    return position;
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
