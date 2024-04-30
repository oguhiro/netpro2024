import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyExceptionHoliday extends Exception{
    public static void main(String[] args) {

		MyExceptionHoliday my =new MyExceptionHoliday();

	}

	MyExceptionHoliday(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader というのは、データ読み込みのクラス(型)
		// クラスの変数を作るには、new を使う。

		// readLine() は、入出力エラーの可能性がある。エラー処理がないとコンパイルできない。
		//  Java では、 try{ XXXXXXXX }  catch(エラーの型 変数) { XXXXXXXXXXXXXXXXXX} と書く
        while(true){
		try {
			System.out.println("何日ですか?");
			String line = reader.readLine();
			int theday = Integer.parseInt(line);

			//5月の日付の範囲内か
			if(theday < 1 || theday > 31){
				System.out.println("無効な日付です");
				continue;
			}

			System.out.println("日付" + theday + "日ですね。");

			test(theday);

		}
		catch(IOException e) {
			System.out.println(e);
		} catch (NoHolidayException e) {
			e.printStackTrace();
		}
    }
	}

	void test(int theday) throws NoHolidayException{
		// 週末を休日として定義 (土曜=6, 日曜=7, その繰り返し)
        if (theday % 7 == 6 || theday % 7 == 0) {
            System.out.println("週末で、休日です！");
        } else {
            throw new NoHolidayException();
        }
	}

}
