import java.util.*;
import java.io.FileWriter;

public class Game {
  private Building[] buildings;
  private Building currentBuilding;
  private Player player;
  private Random random;
  private boolean gameOver;
  private boolean playerWon;
  private int turn;
  private int fuelCellsFound;

  public Game() {
    player = new Player();
    buildings = new Building[15];
    currentBuilding = buildings[0];
    random = new Random();
    fuelCellsFound = 0;
    turn = 0;
    gameOver = false;
    playerWon = false;
  }

  public int calculateJumpCost(Building currentBuilding, Building nextBuilding) {
    int jumpCost = 1;
    int currentBuildingHeight = currentBuilding.getHeight();
    int nextBuildingHeight = nextBuilding.getHeight();

    try {
      if (currentBuilding == nextBuilding) {
        jumpCost += 0;
      } else if (currentBuildingHeight >= nextBuildingHeight) {
        jumpCost += currentBuildingHeight - nextBuildingHeight;
      } else if (nextBuildingHeight > currentBuildingHeight) {
        jumpCost += nextBuildingHeight - currentBuildingHeight;
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
      jumpCost = 0;
    } catch (Exception e) {
      System.out.println("Unable to calculate Jump Cost betweent current Building and next Building");
    }

    return jumpCost;
  }

  public void checkGameConditions() {
    try {
      if (currentBuilding.getHasExitPortal()) {
        if (!currentBuilding.getIsFrozen()) {
          setPlayerWon(true);
          setGameOver(true);
          writeOutcomeFile();
          System.out.println(
              "WINNER!! 🎈🎉🏆" +
                  "\nWELL DONE " + player.getName() + "!" +
                  "\nYou found the exit portal 🔮                              🤾🏼" +
                  "\nEvaded the Nowhere Police 🏃  🔦 👮                      ⚡︎" +
                  "\nKept your Jumper Device charged 🔋 🚀                  💥" +
                  "\nand ultimately, YOU ESCAPED THE NOWHERE DIMENSION!   🕳" +
                  "\n" +
                  "\n                                                🔮" +
                  "\n" +
                  "\n                    🏥 🏢 🏗 🏢 💒 🕍 🏢 🏪 🏢 🏬 🏤 🏢 🏣 ");
        } else {
          System.out.println(
              "This buildng is frozen, and so is the exit portal!!" +
                  "\nYou must wait until your next turn, before you and the portal" +
                  "\nare no longer frozen. 🧊❄️🧊");
        }
      } else if (getPlayer().getJumperDevice().getCurrentCharge() <= 0) {
        setGameOver(true);
        System.out.println(
            "OUT OF BATTERY 🪫" +
                "\nGAME OVER! 🚔 👮 🚨" +
                "\nYou couldn't evade us forever!" +
                "\nbut FOREVER is what you will spend, " +
                "\nin the NOWHERE DIMENSION! 🕳 ");
        System.out.println(
            "   ___________JAIL__________" +
                "\n   |__|__|__|__|__|__|__|__|" +
                "\n   |__|😥|__|__|__|__|__|__|" +
                "\n   |__|__|__|__|__|__|__|__|" +
                "\n   |__|__|__|__|__|__|__|__|" +
                "\n 👮|_________[]👮[]________|👮");
      }
    } catch (Exception e) {
      System.out.print("Error: " + e.getMessage());
    }
  }

  public void display() {
    String topLine = ("_").repeat(48);
    String bottomLine = ("-").repeat(48);
    System.out.println(topLine + "\n Welcome to the Nowhere Dimension, Java Jumper!\n" + bottomLine);
  }

  public void displayBuildings(Building[] buildings) {
    System.out.println(("---").repeat(5));
    for (int i = 0; i < buildings.length; i++) {
      System.out.println("BUILDING " + (i + 1));
      buildings[i].display();
    }
  }

  public void displayGameState(char moveChoice) {
    displayBuildings(buildings);
    System.out.println(("* * ").repeat(9));
    player.display();
    getCurrentBuilding().display();

    handleBuildingEffects(getCurrentBuilding());

    String actionReport = performMove(moveChoice);

    try {
      if (getTurn() == 0) {
        actionReport = "LETS GET JUMPING, JAVA JUMPER!";
        System.out.println(actionReport);
        System.out.println("~ Directly above is your Player and Current Building information.");
        System.out.println("~ Above that, is a list of the 15 buildings for the current turn.");
      } else {
        actionReport = performMove(moveChoice);
      }

      if (actionReport != null && !actionReport.isEmpty()) {
        System.out.println(actionReport);
      }
    } catch (Exception e) {
      System.out.println("Unable to print action Report.");
    }

    System.out.println(("---").repeat(5));
  }

  // Accessor to buildings
  public Building[] getBuildings() {
    return buildings;
  }

  public Building getCurrentBuilding() {
    int playerIndex = player.getCurrentPosition();
    Building currentBuilding = buildings[playerIndex];
    return currentBuilding;
  }

  public boolean getGameOver() {
    return gameOver;
  }

  public int getFuelCellsFound() {
    return fuelCellsFound;
  }

  // buildings[] size Accessor
  public int getNumberOfBuildings() {
    int numberOfBuildings = buildings.length;
    return numberOfBuildings;
  }

  public Player getPlayer() {
    return player;
  }

  public boolean getPlayerWon() {
    return playerWon;
  }

  // specific[i] building Accessor
  public Building getSpecificBuilding(int index) {
    if (getNumberOfBuildings() > index) {
      return buildings[index];
    } else {
      System.out.println("Building not found at index: " + index);
      return null;
    }
  }

  public int getTurn() {
    return turn;
  }

  public void handleBuildingEffects(Building building) {
    JumperDevice jumperDevice = getPlayer().getJumperDevice();

    if (building.getHasFuelCell()) {
      setFuelCellsFound();
      jumperDevice.setCurrentCharge(jumperDevice.getCurrentCharge() + 5);
      building.setHasFuelCell(false);
      System.out.println(player + " found a fuel cell. Jumper Device Charge: +5 points 🚀🎒🔋");
    }
    if (building.getHasWeb()) {
      jumperDevice.setCurrentCharge(jumperDevice.getCurrentCharge() - 5);
      System.out.println("You jumped on a webbed building! 🕸 🏢 🕸");
      System.out.println("Jumper Device Charge:  -5 points 🔋↓ 🪫");
    }
    if (building.getIsFrozen()) {
      turn++;
      System.out.println(player + " landed on a frozen building 🧊❄️🥶☃️🥶❄️🧊");
      System.out.println("You will miss a turn, whilst you defrost!");
    }
  }

  public void initialize() {
    loadBuildings();
    display();
    inputPlayerDetails(player);
    startMenu();
    displayBuildings(buildings);
    updateCurrentBuilding(player.getCurrentPosition());
    player.setJumpSpaces(getCurrentBuilding().getHeight());
  }

  public void inputPlayerDetails(Player player) {
    Input input = new Input();
    Validation valid = new Validation();
    boolean proceed = false;
    String name = "";

    do {
      try {
        name = input.acceptStringInput("Please enter your Player's name: ");
        if (valid.isBlank(name)) {
          System.out.println("Input cannot be empty. Please enter a valid name 🔴");
        } else if (!valid.isValidName(name)) {
          System.out.println("Name must include only letters, spaces and special characters: ', /, -, () 🔴");
        } else if (!valid.lengthWithinRange(name, 2, 15)) {
          System.out.println(
              "Name must be between " + valid.getMinLength() + " and " + valid.getMaxLength() + " characters long 🔴");
        } else {
          proceed = true;
          System.out.println(name + " ... successfully assigned 🟢");
        }
      } catch (Exception e) {
        System.out.println("🔴 Please enter a valid name");
      }
    } while (!proceed);

    player.setName(name);
  }

  public boolean isOver() {
    return gameOver || playerWon;
  }

  public void loadBuildings() {
    for (int i = 0; i < buildings.length; i++) {
      buildings[i] = new Building();
      buildings[i].setHeight(random.nextInt(5) + 1);
    }

    // Randomly set the exit portal, web, and frozen building
    int exitPortalBuildingIndex = random.nextInt(buildings.length);
    int webBuildingIndex = random.nextInt(buildings.length);
    int frozenBuildingIndex = random.nextInt(buildings.length);

    buildings[exitPortalBuildingIndex].setHasExitPortal(true);
    buildings[webBuildingIndex].setHasWeb(true);
    buildings[frozenBuildingIndex].setIsFrozen(true);
  }

  public String performMove(char moveChoice) {
    String actionReport = "";
    int playerIndex = player.getCurrentPosition();
    int nextIndex;
    int jumpSpaces = player.getJumpSpaces();
    int directionValue;
    int jumpCost;
    Building currentBuilding = getCurrentBuilding();
    Building nextBuilding;
    Player player = getPlayer();
    JumperDevice jumperDevice = player.getJumperDevice();

    try {
      if (moveChoice == 'L') {
        if (playerIndex - jumpSpaces >= 0) {
          directionValue = -(jumpSpaces);
          nextIndex = playerIndex + directionValue;
          nextBuilding = buildings[nextIndex];
          jumpCost = calculateJumpCost(currentBuilding, nextBuilding);
          player.setCurrentPosition(playerIndex + directionValue);
          jumperDevice.setCurrentCharge(jumperDevice.getCurrentCharge() - jumpCost);
          updateCurrentBuilding(playerIndex);
          actionReport = player.getName() + " jumped " + jumpSpaces + " buildings to the LEFT" +
              "\n" + "🏢" + (" -> 🏢").repeat(jumpSpaces);
        } else {
          actionReport = "Left Jump Unavailable";
        }
      } else if (moveChoice == 'R') {
        // if jumpspaces <= buildings to the right of currentBuilding
        if (player.getJumpSpaces() <= getNumberOfBuildings() - 1 - playerIndex) {
          directionValue = jumpSpaces; // jumps to the right are positive values
          nextIndex = playerIndex + directionValue;
          nextBuilding = buildings[nextIndex];
          jumpCost = calculateJumpCost(currentBuilding, nextBuilding);
          player.setCurrentPosition(playerIndex + directionValue);
          jumperDevice.setCurrentCharge(jumperDevice.getCurrentCharge() - jumpCost);
          updateCurrentBuilding(nextIndex);
          actionReport = player.getName() + " jumped " + jumpSpaces + " buildings to the RIGHT" +
              "\n" + "🏢" + (" -> 🏢").repeat(jumpSpaces);
        } else {
          actionReport = "Right Jump Unavailable";
        }
      } else if (moveChoice == 'S') {
        System.out.println("Staying put..");
        jumperDevice.setCurrentCharge(jumperDevice.getCurrentCharge() - 1);
        updateCurrentBuilding(playerIndex);
        actionReport = ("\n") +
            ("  ... " + " 🏢 " + " ...") +
            ("\n");
      } else if (moveChoice == 'X') {
        System.out.println("Exiting application..");
        System.exit(0);
      } else {
        actionReport = "No move was made.";
      }
    } catch (Exception e) {
      System.out.println("Invalid Selection. Please Enter one of the available choices.");
    }

    return actionReport;
  }

  public char promptPlayerMove() {
    Input input = new Input();
    System.out.println("MAKE YOUR MOVE: ");
    int leftJumpIndex = player.getCurrentPosition() - player.getJumpSpaces();
    int rightJumpIndex = player.getCurrentPosition() + player.getJumpSpaces();
    int stayJumpCost = 1;
    char moveChoice;

    if (leftJumpIndex >= 0) {
      int leftJumpCost = calculateJumpCost(currentBuilding, buildings[leftJumpIndex]);
      System.out.println(
          "* Enter L to jump " + player.getJumpSpaces() + " buildings to the Left (cost: " + leftJumpCost + ")");
    } else {
      System.out.println("⛔️ You are UNABLE to jump left " + player.getJumpSpaces() + " places");
    }

    if (rightJumpIndex < getNumberOfBuildings()) {
      int rightJumpCost = calculateJumpCost(currentBuilding, buildings[rightJumpIndex]);
      System.out.println(
          "* Enter R to jump " + player.getJumpSpaces() + " buildings to the Right (cost: " + rightJumpCost + ")");
    } else {
      System.out.println("⛔️ You are UNABLE to jump right " + player.getJumpSpaces() + " places");
    }

    System.out.println("* Enter S to stay on the current building (cost: " + stayJumpCost + ")");
    System.out.println("* X to exit");

    do {
      moveChoice = Character.toUpperCase(input.acceptCharInput("", 0));
    } while (moveChoice != 'L' && moveChoice != 'R' && moveChoice != 'S' && moveChoice != 'X');

    return moveChoice;
  }

  public void startGame() {
    initialize();

    char moveChoice = ' ';

    while (!isOver()) {
      displayGameState(moveChoice);
      moveChoice = promptPlayerMove();
      performMove(moveChoice);
      handleBuildingEffects(getCurrentBuilding());
      nextTurn();
      checkGameConditions();
    }
  }

  public void setFuelCellsFound() {
    this.fuelCellsFound += 1;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public void setPlayerWon(boolean playerWon) {
    this.playerWon = playerWon;
  }

  public void startMenu() {
    Input input = new Input();
    int[] choices = { 1, 2 };

    try {
      int userChoice = input.acceptIntegerInput(("-").repeat(48) + "\nEnter 1 to load game \nEnter 2 to exit\n" + ("-").repeat(48));
      if (userChoice == choices[1]) {
        System.out.println("Exiting application..");
        System.exit(0);
      } else if (userChoice == choices[0]) {
        System.out.println("Loading game..");
      } else {
        System.out.println("Invalid choice. Please enter either '1' or '2'.");
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid Input. Please enter one number from the available choices.");
    } catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage());
    }
  }

  public void updateCurrentBuilding(int playerIndex) {
    this.currentBuilding = buildings[playerIndex];
  }

  public void nextTurn() {
    turn++;
    Building fuelCellBuilding;

    // reset height, webbed and frozen buildings
    for (Building building : buildings) {
      building.setHeight(random.nextInt(5) + 1);
      building.setHasWeb(false);
      building.setIsFrozen(false);
    }

    // set new web and frozen buildings for this turn
    buildings[random.nextInt(buildings.length)].setHasWeb(true);
    buildings[random.nextInt(buildings.length)].setIsFrozen(true);

    // update fuel cells every multiple of 3
    if (turn % 3 == 0) {
      for (Building building : buildings) {
        building.setHasFuelCell(false);
      }

      int fuelCellCount = 0;
      do {
        fuelCellBuilding = getSpecificBuilding(random.nextInt(buildings.length));
        if (!fuelCellBuilding.getHasFuelCell()) {
          fuelCellBuilding.setHasFuelCell(true);
          fuelCellCount++;
        }
      } while (fuelCellCount < 5);
    }
  }

  public void writeOutcomeFile() {
    try {
      FileWriter writer = new FileWriter("outcome.txt");
      try {
        writer.write("Java Jumper Game Outcome");
        writer.write("Player: " + getPlayer().getName());
        writer.write("No. of turns played: " + getTurn());
        writer.write("No. of Fuel Cells found: " + getFuelCellsFound());
        writer.write(getPlayerWon() ? "YOU WON!" : "YOU LOST.");
      } finally {
        try {
          writer.close();
        } catch (Exception e) {
          System.out.println("Error in closing file! Exiting...");
        }
      }
    } catch (Exception e) {
      System.out.println("Error in writing to file! Exiting...");
    }
  }
}