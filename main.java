import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test {
    private static String path = "English.txt";

    public static void main(String[] args) throws IOException {
        
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);

        int thechar;
        StringBuffer sb = new StringBuffer();
        HashMap<String, Integer> wordList = new HashMap<String, Integer>();
        
        while ((thechar = isr.read()) != -1) {
            char letter = (char) thechar;
            if ((letter >= 'a' && letter <= 'z')
                    || (letter >= 'A' && letter <= 'Z')) {
                sb.append(letter);
            } else if (sb.length() != 0) {
                String theword = new String(sb);
                if (wordList.containsKey(theword)) {
                    wordList.put(theword, wordList.get(theword) + 1);
                } else {
                    wordList.put(theword, 1);
                }
                sb.delete(0, sb.length());
            }
        }
        isr.close();
        
        List<Map.Entry<String, Integer>> words = new ArrayList<Map.Entry<String, Integer>>(
                wordList.entrySet());
        
        Collections.sort(words, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                return -(o1.getValue() - o2.getValue());
            }
        });
        System.out.println("读取的文件中出现频率最多的十个单词是：");
        int i = 0;
        for (Map.Entry<String, Integer> node : words) {
            if (i < 10) {
                System.out.println(node.getKey() + " : " + node.getValue());
            } else {
                break;
            }
            i++;
        }
    }

}
