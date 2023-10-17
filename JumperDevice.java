public class JumperDevice {
  private int currentCharge;
  private final int maxCharge = 20;
  
  public JumperDevice() {
    currentCharge = 10;
  }

  // public static void main(String[] args) {
  //   // for testing and troubleshooting
  //   JumperDevice jumperDevice = new JumperDevice();
  //   jumperDevice.display();
  // }
  
  public void display() {
    System.out.println("Current Charge: " + currentCharge + "/" + maxCharge);
  }

  // battery charge Accessor
  public int getCurrentCharge() {
    return currentCharge;
  }

  // maximum charge Accessor
  public int getMaxCharge() {
    return maxCharge;
  }

  // battery charge Mutator
  public void setCurrentCharge(int currentCharge) {
    this.currentCharge = currentCharge;
  }
}