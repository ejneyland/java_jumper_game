import java.util.*;
import java.io.*;

public class FileIO {
  private String fileName;

  public FileIO() {
    fileName = "";
  }

  public FileIO(String newFileName) {
    if (newFileName.endsWith(".txt") && newFileName.length() > 4) {
      setFileName(newFileName);
    } else {
      throw new IllegalArgumentException(
          "Invalid fileName. It must end with '.txt' and needs at least one character before the '.'");
    }
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String readFile() {
    String contents = "";
    if (fileName.trim().endsWith(".txt") && fileName.trim().length() > 4) {
      try {
        FileReader inputFile = new FileReader(fileName);
        Scanner parser = new Scanner(inputFile);
        while (parser.hasNextLine()) {
          contents += parser.nextLine() + "\n";
        }
        inputFile.close();
        parser.close();
      } catch (FileNotFoundException exception) {
        System.out.println(fileName + " not found");
      } catch (IOException exception) {
        System.out.println("An unexpected I/O error was encountered.");
      } catch (Exception exception) {
        System.out.println("An exception I/O error was encountered.");
      }
    } else {
      System.out.println("Please enter the correct fileName.");
    }
    return contents;
  }

  public void writeFile(String contents) {
    try {
      if (fileName.trim().endsWith(".txt") && fileName.trim().length() > 4) {
        PrintWriter writeBuildingsFile = new PrintWriter(fileName);
        writeBuildingsFile.println(contents);
        writeBuildingsFile.close();
      }
    } catch (IOException e) {
      System.out.println("There was a problem writing to the file");
    }
  }
}