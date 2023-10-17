## Java Jumper Game, Monash Assignment
Ethan Neyland

This document includes:
- Introduction
- Gameplay and Specifications
- Installation and Launch instructions

#### Introduction
In the simple RPG game, Java Jumper, your character plays a teenager who is trapped in the Nowhere Dimension. As you can guess, this is not a place you want to be growing up in. However, it is possible to escape the dimension through a dimensional portal. Unfortunately, these portals only appear at the top of a random building once every week, and they are fiercely guarded by the Nowhere Police, who use them to shuttle things and people in and out of the Nowhere Dimension.
The objective of the game is for the player to guide their character out of the Nowhere Dimension towards the exit portal while avoiding the Nowhere Police. But the main question is how, since all buildings have their rooftop access restricted.
Fortunately for us, the Nowhere Underground has procured a jumper device. This device allows the wearer to create mini-portals that allow the wearer to ‘jump’ between endpoints. This device can allow the player to safely jump to the roofs of buildings and bypass the rooftop access restrictions. Unfortunately, not everything in life is perfect. While the device can be quite handy, it is not nearly powerful enough to create a portal equivalent to the escape portal. Its range is also limited. While the player’s character has managed to 'acquire' this device, the supplementary power supply was not obtained. As such, the device can only hold the power for a fixed number of jumps before its battery runs out. While the battery can be recharged via power cells found distributed on building rooftops, this would still require the jumper to jump to these buildings in order to obtain the required power cells.
The game is over when one of the following occurs:
- The player manages to jump to the building which has the exit portal on it.
- The player’s jumper device runs out of charge and is unable to jump anymore.

#### Gameplay and Specifications
The following criteria outlines the gameplay specifications your Java Jumper program will need to meet:

When the game begins, the system will read the file called ‘buildings.txt’. This file contains a list of buildings in the game that the player can jump to. Each building will have the following details:

- Height - each building is of a random height between 1 and 5 storeys high
- Exit Portal - only one building on the strip can contain the exit portal. During each run of the game, the exit portal does NOT change once assigned to a specific building.
- Fuel Cell - this indicates if the specific building has a fuel cell which can be found on the roof to recharge the jumper device. Each fuel cell can recharge the jumper device by 5 points.
- Web - this indicates if the building has been booby-trapped with a web setup by the Nowhere Police to catch jumpers. Each turn, a web can only be deployed on a single building. This changes after each turn in the game, and the web is randomly assigned to one of the buildings in the game.
- Freeze - this indicates a frozen building. A jumper that lands on a frozen building must skip a turn, as they cannot jump. Only a single building can be frozen at a time. If this happens to be the building with the exit portal, the player cannot use the exit portal in that turn while the building is frozen.


- When the game begins, it prompts the user to enter their name which must be between 3 and 12 characters respectively. Should the user enter a name that does not meet this criterion, the user is then asked to re-enter a value until the criteria has been met.
- Once the user has successfully entered their name, the game displays the buildings along with the indication for the exit portal, as well as the building on which fuel cells can be found. The maximum number of buildings that are available in the game for the player to jump to is 15. You are encouraged to work on a display mode for the game. The display outlined in the demo doesn’t have to be followed exactly if it appears too challenging to achieve.
- When the game begins, the player is always placed on top of the first building.
- The size of the building the player is on determines the number of jump spaces the player is able to jump. The player may also choose to jump forward or backward. As an example, if the current height of the building the player is on is 2, the player can either jump 2 buildings to the left or to the right. The game should validate the endpoints so the player cannot jump past the 15th building or before the first building.
- Each jump depletes the jumper device’s battery. The charge starts at 50% charge, which is equivalent to 10 points. 20 points is the maximum charge the jumper can hold. The cost for each jump is determined based on the height difference of the two buildings the player jumps between. Use the formula: [height of building 1 – height of building 2] + 1. Note: this is the absolute value of the subtraction, so the difference cannot be negative.
- The player is then given the choice to perform the following options:
  - Jump x buildings to the right (x is determined by the height of the current building)
  - Jump x buildings to the left (x is determined by the height of the current building)
  - Stay on the current building - this allows the player to skip a single turn. Doing this drains 1 charge point on the jumper device.
  - The player must jump the full x number of spaces, and not just a part of them. If the player cannot jump towards the exit portal, they can either jump backward or stay in place.


- After the player has made their selection the following occurs:
  - As this is Nowhere, building heights are not fixed. They change after each and every turn, with the player having no idea of this in advance. The height of the building the player lands on may not be the same as it was before the jump.
  - Fuel cells get completely drained after three turns and disappear. New fuel cells must be randomly placed every third turn. The maximum number of fuel cells which are available is 4. Should a fuel cell be consumed, if the next turn is not a multiple of 3, the cell is not replenished.
  - If a player jumps to a building with a fuel cell on it, the charge level of the device is automatically updated by 5 points for each fuel cell collected.
  - The Nowhere Police randomly change the building where the web booby-trap is located. If the player jumps to a building with a web booby-trap, it costs them 5 extra charge points on their jump device.
  - The game will also randomly select a building to be frozen. Only a single building can be frozen at a time.
  - If the player lands on the building with the exit portal, the game is over and the player wins.
  - If the player has run out of charge points on their jumper device and can no longer jump, the game is over and the player loses.


- Once the game is over, the final stats should be written to a file called ‘outcome.txt’. The game should write the number of turns played, the number of fuel cells found, and the win status of the game.

#### Installation and Launch instructions
If you don't already have an IDE, download Visual Studio Code or similar
- Mac: https://code.visualstudio.com/docs/setup/mac
- Windows: https://code.visualstudio.com/docs/setup/windows
- Linux: https://code.visualstudio.com/docs/setup/linux

If you don't already have Java installed, download it to your IDE:
- Install to VSCode: https://code.visualstudio.com/docs/languages/java
- OR
- Download Java: https://www.java.com/en/download/

Clone repository to your IDE:
- Clone repository instructions: https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository
- https clone url:  https://github.com/ejneyland/java_jumper_game.git

Launch the app:
- from the main directory (in terminal)
- compile all files: ```javac *.java```
- run the main class: ```java LaunchGame```

