public class Player {
  private String name;
  private int currentPosition;
  private int jumpSpaces;
  private JumperDevice jumperDevice;

  public Player() {
    name = "";
    currentPosition = 0;
    jumpSpaces = 0;
    jumperDevice = new JumperDevice();
  }

  // public static void main(String[] args) {
  //   // for testing and troubleshooting
  // }

  // display method
  public void display() {
    int buildingNumber = getCurrentPosition() + 1;
    String currentBuilding = "BUILDING " + buildingNumber;
    System.out.println(
        "Player: " + getName() +
            "\nSpaces to Jump 🎲: " + getJumpSpaces() +
            "\nJumper Charge 🔋: " + getJumperDevice().getCurrentCharge() +
            "\nPosition (current): " +
            "\n" + currentBuilding + " 🏢" +
            "\n" + ("---").repeat(5));
  }

  // Getter / Accessor methods
  public String getName() {
    return name;
  }

  // current pos. Accessor
  public int getCurrentPosition() {
    return currentPosition;
  }

  // no. of jumps Accessor
  public int getJumpSpaces() {
    return jumpSpaces;
  }

  public JumperDevice getJumperDevice() {
    return jumperDevice;
  }

  // name Mutator
  public void setName(String name) {
    this.name = name;
  }

  // current pos. Mutator
  public void setCurrentPosition(int currentPosition) {
    this.currentPosition = currentPosition;
  }

  // no. of jumps Accessor
  public void setJumpSpaces(int jumpSpaces) {
    this.jumpSpaces = jumpSpaces;
  }

  @Override
  public String toString() {
    return getName();
  }
}