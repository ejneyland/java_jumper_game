import java.util.Scanner;

public class Input 
{
  private Scanner scanner;

  public Input() 
  {
    // default constructor
    scanner = new Scanner(System.in);
  }

  // char input
  public char acceptCharInput(String prompt, int position) 
  {
    String input = acceptStringInput(prompt).trim();

    if (position >= 0 && position < input.length()) {
      return input.charAt(position);
    } else {
      throw new IllegalArgumentException("Invalid position for character input");
    }
  }

  // double input
  public double acceptDoubleInput(String prompt) 
  {
    return Double.parseDouble(acceptStringInput(prompt));
  }

  // integer input
  public int acceptIntegerInput(String prompt) 
  {
    return Integer.parseInt(acceptStringInput(prompt));
  }

  // String input
  public String acceptStringInput(String prompt) 
  {
    System.out.println(prompt);
    return scanner.nextLine();
  }
}