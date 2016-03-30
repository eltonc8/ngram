import java.util.*;

public class GramTable {
  private Hashtable<String, GramTable> memory;

  public GramTable() {
    memory = new Hashtable<String, GramTable>();
  }

  public Void put(String[] inputs){
    if (inputs.length == 0) return null;
    String key = inputs[0];
    String[] remainders = Arrays.copyOfRange(inputs, 1, inputs.length);
    if (memory.get(key) == null) memory.put(key, new GramTable());
    GramTable next = memory.get(key);
    next.put(remainders);
    return null;
  }

  public GramTable get(String key){
    return memory.get(key);
  }

  public String returnWord(String[] inputs){
    if (inputs.length == 0) {
      Enumeration<String> keys = memory.keys();
      String output = null;
      int p = 1;
      while (keys.hasMoreElements()){
        if (p++ * Math.random() < 1) {
          output = keys.nextElement();
        } else {
          keys.nextElement();
        }
      }
      return output;
    } else {
      String key = inputs[0];
      String[] remainders = Arrays.copyOfRange(inputs, 1, inputs.length);
      GramTable next = memory.get(key);
      if (next == null) return null;
      return next.returnWord(remainders);
    }
  }
}
