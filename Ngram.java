import java.io.*;
import java.util.*;

public class Ngram {
  public static void main (String[] args) throws IOException {
    BufferedReader inputStream = null;
    GramTable gramTable = new GramTable();
    int gramSize = 3;
    String line, nextWord;
    String[] words;

    try {
      inputStream = new BufferedReader(new FileReader("kasich_ohio_speech.txt"));

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

    ArrayList<String> paragraph, paragraphs = new ArrayList<String>();

    while(paragraphs.size() < 5){
      paragraph = new ArrayList<String>();
      nextWord = "";
      while(!(nextWord.matches("^[A-Z0-9].*"))) nextWord = gramTable.returnWord(new String[]{});

      paragraph.add(nextWord);
      paragraph.add(gramTable.returnWord(new String[]{nextWord}));

      while(paragraph.size() < 360){
        words = new String[]{ paragraph.get( paragraph.size() - 2), paragraph.get( paragraph.size() - 1) };
        nextWord = gramTable.returnWord(words);
        if (nextWord == null) {
          paragraphs.add(String.join(" ", paragraph));
          break;
        } else {
          paragraph.add( gramTable.returnWord( words ) );
        }
      }
    }

    System.out.println( String.join( "\n", paragraphs) );
  } // end main method
} // end class
