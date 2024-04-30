import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HeikinCKadai {
    public static void main(String[] args){

        Random random = new Random();
        int sum = 0;
        int scores[] = new int[100];
        ArrayList<Integer>highscores = new ArrayList<>();
        // 同様に、math
        for(int i = 0; i < 100;i++){
            Kamoku math = new Kamoku(random.nextInt(101));
            scores[i] = math.getScore();
            sum += scores[i];

            if(scores[i] >= 80){
                highscores.add(scores[i]);
            }
        }
        int ave = sum / 100;
        System.out.println("平均点は" + ave);
        System.out.println("以下合格者の点数です");
        
        //80点以上の点数の人を昇順にソート
        Collections.sort(highscores);

        for(int score: highscores){
            System.out.println(score);
        }
            }
}
