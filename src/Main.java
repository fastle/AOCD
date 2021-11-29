import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String [] symptom = new String[101];
    static String [] level = new String[101];
    static double [] ans = new double[101];
    static int diseaseSum = 1;
    static int tot;
    static ArrayList<Disease> disease = new ArrayList<>();
    static void print() {
        System.out.println(ans[1]);
    }
    public static void main(String[] args) {
        //绘制窗口
        Scanner in = new Scanner(System.in);
        int age = in.nextInt();String sex = in.next();
        Init init = new Init();
        //init.print();
        in.nextLine();
        while(in.hasNextLine()) {
            String tmp = in.nextLine();
            if(tmp.length() < 2)break;
            Cut sentence = new Cut(tmp,init.dictory1,init.dictory2);
            sentence.matchCut();
            symptom[++ tot] = sentence.fis();//症状
            level[tot] =sentence.sec();//程度
        }
        //读入病人情况,处理相关信息

        DiseaseOne one = new DiseaseOne();disease.add(one);System.out.println(1);
        for(int i = 1;i <= tot;i ++) {
            int now = one.match(symptom[i]);
            if(now != -1) {//如果大于设定阈值，返回true,进行程度上的匹配
                ans[1] += one.match1(level[i], now);//将概率计入这种病的最终概率
            }
        }
        //将simhash的值进行匹配


        //将信息进行匹配，得出反馈结果，将大于设定阈值的病情分析给出
        print();
        System.out.println(one);
        //打印结果，结束
    }
}
