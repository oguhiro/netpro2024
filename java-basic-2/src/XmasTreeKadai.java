public class XmasTreeKadai {

public static void main(String[] args){
    int X=Integer.parseInt(args[0]);
    int Y=Integer.parseInt(args[1]);

  int treewidth = 3;
  int treeHeight = 7;

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                // 中央に近い位置にアスタリスクを配置
                if (j >= X - i - 1 && j <= X + i - 1) {
                    System.out.print("*");
                }else if(j < X - i - 1 && j % 2 == 1){
                  System.out.print("+");
                  //System.out.print(" ");
                } else {
                    System.out.print(" ");
                }
            }
            // 各行を出力後に改行
            System.out.println();
        }

        //木の幹の作成
        int treeStart = (Y - treewidth) / 2;
        for(int i = 0;i < treeHeight;i++){
          for(int j = 0;j < Y;j++){
            if(j >= treeStart && j < treeStart + treewidth){
          System.out.print("*");
          }else{
            System.out.print(" ");
          }
        }
        System.out.println();
    }
}
}