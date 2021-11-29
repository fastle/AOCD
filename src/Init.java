import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Init {
    public ArrayList<String> dictory1 = new ArrayList<>();
    public ArrayList<String> dictory2 = new ArrayList<>();
    public Init() {
        try {
            BufferedReader in1 = new BufferedReader(new FileReader("D:\\JAVA\\java程序设计\\Analysis of common diseases\\src\\level_dictionary.txt"));
            BufferedReader in2 = new BufferedReader(new FileReader("D:\\JAVA\\java程序设计\\Analysis of common diseases\\src\\symptom_dictionary.txt"));
            String s = in1.readLine();
            while (s != null ){
                int last = -1;
                for (int i = 0; i < s.length();i ++) {
                    if (s.charAt(i) == ' ') {
                        dictory1.add(s.substring(last + 1, i));
                        last = i;
                    }
                }
                dictory1.add(s.substring(last + 1));
                s = in1.readLine();
            }
            s = in2.readLine();
            while (s != null) {
                int last = -1;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ' || s.charAt(i) == '\n') {
                        dictory2.add(s.substring(last + 1, i));
                        last = i;
                    }
                }
                dictory2.add(s.substring(last + 1));
                s = in1.readLine();
            }
        }catch (IOException e) {
        }
    }
    public void print() {
        for(String tmp : dictory1) {
            System.out.println(tmp);
        }
    }
}
