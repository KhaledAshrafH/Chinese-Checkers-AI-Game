import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board B = new Board();
        Player player1 = new Player(1,B);
        player1.setName("Khaled");
        player1.setColor('G');
        Player player2 = new Player(4,B);
        player2.setName("PC");
        player2.setColor('R');
        GameController obj = new GameController(B);
        Agent pc=new Agent(player2,obj,player1);
        Scanner input = new Scanner(System.in);
        System.out.println("Enter level of the game(1,3,5)");
        int levelOfGame = input.nextInt();
        while(obj.checkWinner()==0){
            if(obj.Game(player1)){
                int result = obj.checkWinner();
                if(result == 1) {
                    System.out.println("You Win");
                    break;
                }
                B.Print();
                pc.minimax(B,levelOfGame,true,-1000,1000);
                pc.allMoves = obj.checkMove(player2);
                obj.undoMove(pc.getFirstBest(),pc.getSecondBest());

                result = obj.checkWinner();
                if(result == 2){
                    System.out.println("Player 2 is win");
                }
                B.Print();
            }
        }


        // new GUI();
    }
}
