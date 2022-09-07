import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*Board B = Board.getInstance();
        Player player1 = new Player(1);
        player1.setName("Ahmed");
        player1.setColor('R');

        Player player2 = new Player(4);
        player2.setName("Khaled");
        player2.setColor('G');

        GameController obj = new GameController();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                obj.Game(player1);
            } else {
                obj.Game(player2);
            }

        }*/

//        Board B = new Board();
//        Player player1 = new Player(1,B);
//        player1.setName("Khaled");
//        player1.setColor('G');
//        Player player2 = new Player(4,B);
//        player2.setName("PC");
//        player2.setColor('R');
//        GameController obj = new GameController(B);
//        Agent pc=new Agent(player2,obj,player1);
//        while(obj.checkWinner()==0){
//            if(obj.Game(player1)){
//                int result = obj.checkWinner();
//                if(result == 1) {
//                    System.out.println("You Win");
//                    break;
//                }
//                B.Print();
//                //pc.allMoves = obj.checkMove(player2);
//                pc.minimax(B,5,true,-1000,1000);
//                pc.allMoves = obj.checkMove(player2);
//                //obj.isMoved(B,pc.getFirstBest(),pc.getSecondBest(),pc.getAllMoves());
//                obj.undoMove(pc.getFirstBest(),pc.getSecondBest());
//
//                result = obj.checkWinner();
//                if(result == 2){
//                    System.out.println("Player 2 is win");
//                }
//                B.Print();
//            }
//        }


         new GUI();
    }
}
