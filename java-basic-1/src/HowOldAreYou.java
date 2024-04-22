import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class HowOldAreYou {

    public static void main(String[] args) { 

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			// BufferedReader というのは、データ読み込みのクラス(型)
			// クラスの変数を作るには、new を使う。

			// readLine() は、入出力エラーの可能性がある。エラー処理がないとコンパイルできない。
			//  Java では、 try{ XXXXXXXX }  catch(エラーの型 変数) { XXXXXXXXXXXXXXXXXX} と書く

			while(true){
		try {
			System.out.println("何歳ですか?");
			System.out.println("システムを終了する場合はqまたはeを入力せよ");
			String line = reader.readLine();

			if(line.equals("q") || line.equals("e")){
				System.out.println("システムを終了します");
				break;
			}

			int age = Integer.parseInt(line);

			if(age < 0 || age >= 120){
				System.out.println("年齢を再入力せよ");
				continue;
			}


			System.out.println("あなたは" + age + "歳ですね。");
			System.out.println("あなたは10年後、" + (age + 10) + "歳ですね。");
			System.out.println("2030年のあなたの年齢は" + (age + 6));

			int birthyear = calculatebirthyear(age);

			String era = findera(birthyear);

			if (era != null) {
				int eraYear = birthyear;
				switch (era) {
					case "明治":
						eraYear -= 1867;
						break;
					case "大正":
						eraYear -= 1911;
						break;
					case "昭和":
						eraYear -= 1925;
						break;
					case "平成":
						eraYear -= 1988;
						break;
					case "令和":
						eraYear -= 2018;
						break;
				}
				System.out.println("あなたの元号は" + era  + eraYear + "年");
		
				}
			}
		
			
		catch(IOException e) {
			System.out.println(e);
		}
	}
}


	public static int calculatebirthyear(int age){
		LocalDate now = LocalDate.now();
		int currentYear = now.getYear();
		return currentYear - age;
	}

	public static String findera(int birthyear){
		if (birthyear >= 1868 && birthyear < 1912) {
            return "明治";
        } else if (birthyear >= 1912 && birthyear < 1926) {
            return "大正";
        } else if (birthyear >= 1926 && birthyear < 1989) {
            return "昭和";
        } else if (birthyear >= 1989 && birthyear < 2019) {
            return "平成";
        } else if (birthyear >= 2019) {
            return "令和";
        } else {
            return null;
        }
	}
}
