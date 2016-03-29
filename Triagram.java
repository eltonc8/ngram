import java.io.*;
import java.util.*;

public class Triagram {
  public static void main (String[] args) throws IOException {
    BufferedReader inputStream = null;

    try {
      inputStream = new BufferedReader(new FileReader("sample_text.txt"));

      String line;
      while ((line = inputStream.readLine()) != null) {
        System.out.println(line);
      }
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
    }
  } // end main method
} // end class
