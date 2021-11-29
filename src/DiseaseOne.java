import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class DiseaseOne extends Disease{
    final private String name = "感冒";
    final private String [] key = {"咳嗽","发热","头晕"};
    final private int key_size = 3,levels = 3;
    final private double [][] key_level_probability = {{0.1,0.5,0.4},
                                                       {0.3,0.3,0.4},
                                                       {0.2,0.3,0.5}};
    private double [] w = {0.3,0.3,0.4};
    final private int [][] key_level = { {0,1,2,2,2},
                                         {0,1,2,2,2,2},
                                         {0,1,2,2}};
    private SimHash [] key_simhash = new SimHash[10];
    public DiseaseOne() {
        for(int i = 0;i < key_size;i ++) {
            key_simhash[i] = new SimHash(key[i],128);
            //System.out.println(key_simhash[i].simHash());
        }
    }
    public int match(String x) {//匹配症状
        int now = -1,mn = 30;
        SimHash hash1 = new SimHash(x,128);
        for(int i = 0;i < key_size;i ++) {
            int tmp = key_simhash[i].hammingDistance(hash1);
            if(tmp < 30) {
                if(mn > tmp){now = i;mn = tmp;}
            }
        }
        return now;
    }
    public double match1(String x,int now) {//匹配程度
        return key_level_probability[now][find(x)];//获得此症状的概率
    }
    private int pre_match(String s,String x) {//筛选
        int len = s.length(),last = 0,n = 0;
        SimHash hash1 = new SimHash(x,128);
        for(int i = 0;i < len;i ++) {
            if(s.charAt(i) == ' ') {
                String tmp = s.substring(last + 1,i - 1);
                SimHash hash2 = new SimHash(tmp,128);
                n ++;
                if(hash2.hammingDistance(hash1) < 30) {
                    return n;
                }
                last = i;
            }
        }
        return 0;
    }
    private int find(String x) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("level_dictionary.txt"));
            String dictionary = in.readLine();
            int line = pre_match(dictionary,x);
            int now_line = 0;
            while(dictionary.length() > 2) {
                now_line ++;
                if(now_line == line) {
                    dictionary = in.readLine();
                    return key_level[line][pre_match(dictionary,x)];
                }
                in.readLine();
            }
        }catch (IOException e) {
        }
        return 0;
    }
    public String toString() {
        String result = "";
        result += name + "\n";
        return result;
    }
}
