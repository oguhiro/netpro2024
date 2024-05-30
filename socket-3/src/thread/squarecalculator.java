package thread;

public class squarecalculator implements Runnable{
private int number;

    public squarecalculator(int number){
        this.number = number;
    }

    public void run(){
        int result = number * number;
        System.out.println(number + "は" + result);
    }

}
