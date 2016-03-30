import java.io.*;
import java.util.*;

public class Triagram {
  public static void main (String[] args) throws IOException {
    BufferedReader inputStream = null;
    GramTable gramTable = new GramTable();

    try {
      inputStream = new BufferedReader(new FileReader("sample_text.txt"));

      String line;
      String[] words;
      int idx;
      while ((line = inputStream.readLine()) != null) {
        words = line.split("\\s+");
        idx = 0;
        while (idx + 2 < words.length) {
          gramTable.put( Arrays.copyOfRange(words, idx, idx + 3) );
          idx++;
        }
      }
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
    }

    ArrayList<String> output = new ArrayList<String>();
    output.add(gramTable.returnWord(new String[]{}));
    output.add(gramTable.returnWord(new String[]{output.get(0)}));

    String[] frame;
    while(output.size() < 10){
      frame = new String[]{ output.get( output.size() - 2), output.get( output.size() - 1) };
      output.add( gramTable.returnWord( frame ) );
    }

    System.out.println( String.join( " ", output) );
  } // end main method
} // end class
