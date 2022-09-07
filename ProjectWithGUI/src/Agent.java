import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Agent {
    Board B;
    Player player;
    Player enemy;
    point firstBest,secondBest;
    Map<point, ArrayList<point>> allMoves;
    Map<point, ArrayList<point>> allMovesEnemy;


    public Map<point, ArrayList<point>> getAllMoves() {
        return allMoves=new HashMap<>();
    }

    public point getFirstBest() {
        return firstBest;
    }

    public point getSecondBest() {
        return secondBest;
    }

    GameController gameController;
    boolean isPC=true;

    public Agent(Player player, GameController gameController,Player enemy) {
        this.player = player;
        this.enemy = enemy;
        this.gameController = gameController;
        this.B=this.gameController.B;
    }
    //board
    public int minimax(Board board,int depth, boolean isMaximizing, int alpha, int beta) {
        Board localBoard=new Board();
        localBoard.MainBoard.addAll(board.MainBoard);
        localBoard.lastInfo=board.lastInfo;
        int result=evaluate(localBoard);

        if(depth == 0 || gameController.checkWinner()!=0) {
            return result;
        }

        if(isMaximizing){
            point firstPoint=null, secondPoint=null;
            int maxEva= -1000000;
            //PC moves
            allMoves=gameController.checkMove(player);
            for ( Map.Entry<point, ArrayList<point>> key : allMoves.entrySet()) {
                for(int i=0;i<key.getValue().size();i++){
                    point p1=new point(key.getKey().row,key.getKey().col);
                    point p2=new point(key.getValue().get(i).row,key.getValue().get(i).col);
                    gameController.isMoved(localBoard,p1,p2,allMoves);

                    int score = minimax(localBoard,depth-1,false, alpha, beta);
                   // gameController.undoMove(p1,p2);
                    if(score > maxEva) {
                        maxEva = score;

                        firstPoint = p1;
                        secondPoint = p2;
                    }
                    alpha = Math.max(alpha,maxEva);
                    if(beta<=alpha) return maxEva;
                }
            }

            firstBest=firstPoint;
            secondBest=secondPoint;

            return maxEva;
        }
        else {
            int minEva=1000000;
            point firstPoint=null, secondPoint=null;

            allMovesEnemy=gameController.checkMove(enemy);
            //for each key in map.
            for ( Map.Entry<point, ArrayList<point>> key : allMovesEnemy.entrySet()) {
                for(int i=0;i<key.getValue().size();i++){
                    point p1=new point(key.getKey().row,key.getKey().col);
                    point p2=new point(key.getValue().get(i).row,key.getValue().get(i).col);
                    gameController.isMoved(localBoard,p1,p2,allMovesEnemy);
                   // allMovesEnemy=gameController.checkMove(enemy);
                    int score = minimax(localBoard,depth-1,true, alpha, beta);
                    //gameController.undoMove(p1,p2);
                    if(score < minEva) {
                        minEva = score;
                        firstPoint = p1;
                        secondPoint = p2;
                    }
                    beta = Math.min(beta,score);
                    if(beta<=alpha) return minEva;
                }
            }
            return minEva;
        }
    }
    public int evaluate(Board localBoard){
        // jump => 10
        // number of white points => number Of these points after me
        // move on goal direction => 10
        int countWhite=0;
        int total=0;
        if(localBoard.lastInfo!=null) {
            int jump = localBoard.lastInfo.secondPointRow - localBoard.lastInfo.startPointRow;
            int rowLast = localBoard.lastInfo.secondPointRow;
            int colLast = localBoard.lastInfo.secondPointCol;
            for (int i = rowLast + 1; i < localBoard.MainBoard.size(); i++) {
                for (int j = 0; j < localBoard.MainBoard.get(i).size(); j++) {
                    if (localBoard.MainBoard.get(i).get(j) == 'W') countWhite++;
                }
            }
            if (jump > 0) total += 10;
            total += (jump * 10);
            total += countWhite;
        }
        return total;
    }
}
