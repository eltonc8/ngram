import java.io.*;
import java.util.*;

public class Ngram {
  public static void main (String[] args) throws IOException {
    BufferedReader inputStream = null;
    GramTable gramTable = new GramTable();
    int gramSize = 3;

    try {
      inputStream = new BufferedReader(new FileReader("kasich_ohio_speech.txt"));

      String line;
      String[] words;
      int idx;
      while ((line = inputStream.readLine()) != null) {
        words = line.split("\\s+");
        idx = 0;
        while (idx + gramSize <= words.length) {
          gramTable.put( Arrays.copyOfRange(words, idx, idx + gramSize) );
          idx++;
        }
      }
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
    }

    ArrayList<String> paragraphs = new ArrayList<String>();
    ArrayList<String> paragraph;
    String[] frame;
    String nextWord;

    while(paragraphs.size() < 5){
      paragraph = new ArrayList<String>();
      paragraph.add(gramTable.returnWord(new String[]{}));
      paragraph.add(gramTable.returnWord(new String[]{paragraph.get(0)}));

      while(paragraph.size() < 100){
        frame = new String[]{ paragraph.get( paragraph.size() - 2), paragraph.get( paragraph.size() - 1) };
        nextWord = gramTable.returnWord(frame);
        if (nextWord == null) {
          paragraphs.add(String.join(" ", paragraph));
          break;
        } else {
          paragraph.add( gramTable.returnWord( frame ) );
        }
      }
    }

    System.out.println( String.join( "\n", paragraphs) );
  } // end main method
} // end class
