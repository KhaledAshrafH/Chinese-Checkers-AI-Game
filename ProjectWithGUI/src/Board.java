import java.util.*;


/*

                ['B'],
             ['B','B'],
             ['B','B','B'],
             ['B','B','B','B'],
             ['W','W','W','W','W','W','W','W','W','W','W','W','W'],
             ['W','W','W','W','W','W','W','W','W','W','W','W'],
             ['W','W','W','W','W','W','W','W','W','W','W'],
             ['W','W','W','W','W','W','W','W','W','W'],
             ['W','W','W','W','W','W','W','W','W'],
             ['W','W','W','W','W','W','W','W','W','W'],
             ['W','W','W','W','W','W','W','W','W','W','W'],
             ['W','W','W','W','W','W','W','W','W','W','W','W'],
             ['W','W','W','W','W','W','W','W','W','W','W','W','W'],
             ['R','R','R','R'],
             ['R','R','R'],
             ['R','R'],
             ['R'],

* */
public class Board {

    public ArrayList<ArrayList<Character>> MainBoard;
    public static class LastInfo{
        int startPointRow,startPointCol,secondPointRow,secondPointCol;

        @Override
        public String toString() {
            return "LastInfo{" +
                    "startPointRow=" + startPointRow +
                    ", startPointCol=" + startPointCol +
                    ", secondPointRow=" + secondPointRow +
                    ", secondPointCol=" + secondPointCol +
                    '}';
        }

        public LastInfo(int startPointRow, int startPointCol, int secondPointRow, int secondPointCol) {
            this.startPointRow = startPointRow;
            this.startPointCol = startPointCol;
            this.secondPointRow = secondPointRow;
            this.secondPointCol = secondPointCol;
        }
    }
    LastInfo lastInfo;

    public void setLastInfo(LastInfo lastInfo) {
        this.lastInfo = lastInfo;
    }
    public Board()
    {
        MainBoard = new ArrayList<ArrayList<Character>>();
        //Num = 4
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','-','-','R','-','-','-','-','-','-','-','-','-','-','-','-')));//0
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','-','R','-','R','-','-','-','-','-','-','-','-','-','-','-')));//1
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','R','-','R','-','R','-','-','-','-','-','-','-','-','-','-')));//2
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','R','-','R','-','R','-','R','-','-','-','-','-','-','-','-','-')));//3
//
//        //Num 3 W 5
//        MainBoard.add(new ArrayList<>(List.of('W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W')));//4
//        MainBoard.add(new ArrayList<>(List.of('-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','G','-','W','-','W','-','W','-','W','-')));//5
//        MainBoard.add(new ArrayList<>(List.of('-','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','-')));//6
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','W','-','W','-','W','-','W','-','W','-','G','-','W','-','W','-','W','-','W','-','-','-')));//6
//
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','W','-','W','-','W','-','W','-','R','-','W','-','W','-','W','-','W','-','-','-','-')));//6
//        //Num = 2 w 6
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','-','-')));//6
//        MainBoard.add(new ArrayList<>(List.of('-','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','-')));//6
//        MainBoard.add(new ArrayList<>(List.of('-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-')));//5
//        MainBoard.add(new ArrayList<>(List.of('W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W')));//4
//
//        //Num = 1
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','G','-','G','-','G','-','G','-','-','-','-','-','-','-','-','-')));//3
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','G','-','G','-','G','-','-','-','-','-','-','-','-','-','-')));//2
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','-','G','-','G','-','-','-','-','-','-','-','-','-','-','-')));//1
//        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','-','-','G','-','-','-','-','-','-','-','-','-','-','-','-')));//0

        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','-','-','R','-','-','-','-','-','-','-','-','-','-','-','-')));//0
        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','-','R','-','R','-','-','-','-','-','-','-','-','-','-','-')));//1
        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','R','-','R','-','R','-','-','-','-','-','-','-','-','-','-')));//2
        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','R','-','R','-','R','-','R','-','-','-','-','-','-','-','-','-')));//3

        //Num 3 W 5
        MainBoard.add(new ArrayList<>(List.of('W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W')));//4
        MainBoard.add(new ArrayList<>(List.of('-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-')));//5
        MainBoard.add(new ArrayList<>(List.of('-','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','-')));//6
        MainBoard.add(new ArrayList<>(List.of('-','-','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','-','-')));//7

        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','-','-','-')));//8
        //Num = 2 w 6
        MainBoard.add(new ArrayList<>(List.of('-','-','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','-','-')));//9
        MainBoard.add(new ArrayList<>(List.of('-','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','-')));//10
        MainBoard.add(new ArrayList<>(List.of('-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-')));//11
        MainBoard.add(new ArrayList<>(List.of('W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W','-','W')));//12

        //Num = 1
        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','G','-','G','-','G','-','G','-','-','-','-','-','-','-','-','-')));//13
        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','G','-','G','-','G','-','-','-','-','-','-','-','-','-','-')));//14
        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','-','G','-','G','-','-','-','-','-','-','-','-','-','-','-')));//15
        MainBoard.add(new ArrayList<>(List.of('-','-','-','-','-','-','-','-','-','-','-','-','G','-','-','-','-','-','-','-','-','-','-','-','-')));//16
    }


    public void Print()
    {
//        String s="";
//        for (int i=0;i<MainBoard.size();i++){
//            int tmp = (13-MainBoard.get(i).size())/2;
//            for (int k=0;k<tmp;k++){
//                s+=" ";
//            }
//            for (int j=0;j<MainBoard.get(i).size();j++)
//            {
//                s+=MainBoard.get(i).get(j) + " ";
//            }
//            System.out.println(s);
//            s="";
//        }
        for (List<Character> list : MainBoard)
        {
            for (Character i : list) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
        //System.out.println(MainBoard.get(0).get(0));
    }
//    private char[][] MainBoard =
//    {
//            { 1, 2 }, { 3, 4 }
//    };
}
