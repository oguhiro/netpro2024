package thread;
    
    public class ExThredsMain{
        public static void main(String[] args){
            int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

            for(int number : numbers){
                squarecalculator calculator = new squarecalculator(number);
                Thread thread = new Thread(calculator);
                thread.start();
            }
        }
    }

