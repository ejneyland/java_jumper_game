import java.util.Scanner;

public class Input 
{
  
  public Input() 
  {
    // default constructor
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
    Scanner scanner = new Scanner(System.in);
    System.out.println(prompt);
    String input = scanner.nextLine();
    scanner.close();
    return input;
  }
}