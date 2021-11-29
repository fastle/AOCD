import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cut {//切分文本
    private String sentence,firstSentence,secondSentence;
    private ArrayList<String> dictory1 ;
    private ArrayList<String> dictory2 ;
    public Cut(String sentence, ArrayList<String> dictory1,ArrayList<String> dictory2) {
        this.sentence = sentence;
        this.dictory1 = dictory1;
        this.dictory2 = dictory2;
    }
    private boolean check(String x,int opt) {
        if(opt == 1) {
            for(int i = 0;i < dictory2.size();i ++) {
                //System.out.println(dictory2.get(i));
                if(x.equals(dictory2.get(i)))return true;
            }
            return false;
        }
        else {
            for(int i = 0;i < dictory1.size();i ++) {
                //System.out.println(dictory1.get(i) + " " + x);
                if(x.equals(dictory1.get(i)))return true;
            }
            return false;
        }
    }
    public void matchCut() {//正向最长匹配
        int now = sentence.length();
        int l = 0,r = sentence.length();
        while(now >= 0) {
            //System.out.println(now);
            if(check(sentence.substring(l,now),1)) {
                firstSentence = sentence.substring(l,now);
                break;
            }
            now --;
        }System.out.println(firstSentence);
        now = 0;
        while(now <= r) {
            if(check(sentence.substring(now,r),2)) {
                secondSentence = sentence.substring(now,r);
                break;
            }
            now ++;
        }System.out.println(secondSentence);
    }
    public String fis() {
        return firstSentence;
    }//
    public String sec() {
        return secondSentence;
    }//将程度分成若干等级
}
