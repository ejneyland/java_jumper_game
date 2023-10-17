public class Building {
  // fields
  private int height;
  private boolean hasExitPortal;
  private boolean hasFuelCell;
  private boolean hasWeb;
  private boolean isFrozen;

  // default non-parameterised constructor
  public Building() {
    height = 0;
    hasExitPortal = false;
    hasFuelCell = false;
    hasWeb = false;
    isFrozen = false;
  }

  // parameterised building object constructor
  public Building(int height, boolean hasExitPortal, boolean hasFuelCell, boolean hasWeb, boolean isFrozen) {
    setHeight(height);
    setHasExitPortal(hasExitPortal);
    setHasFuelCell(hasFuelCell);
    setHasWeb(hasWeb);
    setIsFrozen(isFrozen);
  }
  
  // display method
  public void display() {
    System.out.println(
      "Height: " + height +
      "\nExit Portal 🔮: " + hasExitPortal +
      "\nFuel Cell 🔋: " + hasFuelCell +
      "\nWebbed 🕸 : " + hasWeb +
      "\nFrozen 🧊: " + isFrozen);
    System.out.println(("---").repeat(5));
  }
  
  // height Accessor
  public int getHeight() {
    return height;
  }
  
  // exit portal 🔮 Accessor
  public boolean getHasExitPortal() {
    return hasExitPortal;
  }
  
  // fuel cell 🔋 Accessor
  public boolean getHasFuelCell() {
    return hasFuelCell;
  }
  
  // web 🕸 Accessor
  public boolean getHasWeb() {
    return hasWeb;
  }

  // frozen ❄️ Accessor
  public boolean getIsFrozen() {
    return isFrozen;
  }
  
  // height Mutator
  public void setHeight(int height) {
    this.height = height;
  }
  
  // exit portal 🔮 Mutator
  public void setHasExitPortal(boolean hasExitPortal) {
    this.hasExitPortal = hasExitPortal;
  }
  
  // fuel cell 🔋 Mutator
  public void setHasFuelCell(boolean hasFuelCell) {
    this.hasFuelCell = hasFuelCell;
  }
  
  // web 🕸 Mutator
  public void setHasWeb(boolean hasWeb) {
    this.hasWeb = hasWeb;
  }

  // frozen ❄️ Mutator
  public void setIsFrozen(boolean isFrozen) {
    this.isFrozen = isFrozen;
  }

  // public static void main(String[] args) {
  //   // troubleshooting
  //   Game game = new Game();
  //   game.loadBuildings();
  
  //   Building[] buildings = game.getBuildings();
  
  //   for (int i = 0; i < buildings.length; i++) {
  //     System.out.println(("---").repeat(5));
  //     System.out.println("BUILDING " + i);
  //     buildings[i].display();
  //   }
  // }
}