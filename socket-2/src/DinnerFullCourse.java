public class DinnerFullCourse {
    private Dish[] list = new Dish[5];

    public static void main(String[] args){
        DinnerFullCourse fullcourse = new DinnerFullCourse();
        fullcourse.eatAll();
    }

    DinnerFullCourse(){
        list[0] = new Dish();
        list[0].setName("特選シーザーサラダ");
        list[0].setValune(10);

        list[1] = new Dish();
        list[1].setName("銀シャリ");
        list[1].setValune(5);

        list[2] = new Dish();
        list[2].setName("梅干し");
        list[2].setValune(30);

        list[3] = new Dish();
        list[3].setName("佃煮");
        list[3].setValune(3);

        list[4] = new Dish();
        list[4].setName("ハンバーグ");
        list[4].setValune(10);
    }

    void eatAll(){
        System.err.print("たっかしーーー！今日の晩御飯は");
        for(Dish list: list){
            String str = list.getName() + "=" + list.getValune() + ",";
            System.err.print(str);
        }
        System.out.print("よ！");
    }

}
