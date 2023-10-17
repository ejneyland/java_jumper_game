public class Validation {
  private int minLength;
  private int maxLength;

  public Validation() {
    // default constructor
    minLength = 0;
    maxLength = 0;
  }

  public int getMinLength() {
    return minLength;
  }

  public int getMaxLength() {
    return maxLength;
  }

  public void setMinLength(int newMinLength) {
    this.minLength = newMinLength;
  }

  public void setMaxLength(int newMaxLength) {
    this.maxLength = newMaxLength;
  }

  // blank?
  public boolean isBlank(String input) {
    return input == null || input.trim().isEmpty();
  }

  // validName?
  public boolean isValidName(String input) {
    // Allows: letters, whitespace, -, /, () and ''
    String pattern = "^[a-zA-Z\\s'/()\\-]+$";
    return input.matches(pattern);
  }

  // withinRange?
  public boolean lengthWithinRange(String input, int minLength, int maxLength) {
    setMinLength(minLength);
    setMaxLength(maxLength);
    if (input == null)
      return false;
    int length = input.trim().length();
    return length >= minLength && length <= maxLength;
  }
}