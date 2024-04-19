public class IfTest2 {

    public static void main(String[] args) { 
		int x=Integer.parseInt(args[0]);
		if(x>=7){
			System.out.println(x+" is Big Number");
		}
	/*ここにelse if文を追加する。**/
	else if(x>=4 && x<=6){
        System.out.println(x + "is middle number");
    }
    else if(x>=1 && x<=3){
        System.out.println(x + "is small number");
    }

	}//main end
}//class end

