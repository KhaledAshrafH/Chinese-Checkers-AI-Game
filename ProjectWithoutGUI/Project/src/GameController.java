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
                               ['W','W','W','R','W','W','W','W','W','W','W','W'],
                            ['W','W','W','W','R','R','W','W','W','W','W','W','W'],
                                             ['W','W','R','R'],
                                               ['W','R','R'],
                                                 ['R','R'],
                                                   ['R'],

                                                    [0],
                                                   [0,1],
                                               ['0','1','2'],
                                              ['0','1','2','3'],
                            ['0','1','2','3','4','5','6','7','8','9','10','11','12'],
                             ['0','1','2','3','4','5','6','7','8','9','10','11'],
                               ['0','1','2','3','1','5','6','7','8','9','10'],
                                  ['0','1','2','3','4','5','6','7','8','9'],
                                    ['0','1','2','3','4','5','6','7','8'],
                                  ['0','1','2','3','4','5','6','7','8','9'],
                               ['0','1','2','3  ','4','5','6','7','8','9','10'],
                             ['0','1','2','3','4','5','6','7','8','9','10','11'],
                           ['0','1','2','3','4','5  ','6','7','8','9','10','11','12'],
                                             ['0','1','2','3'],
                                               ['0','1','2'],
                                                   [0,1],
                                                    [0],

                               - - - - - - - - - - - - R - - - - - - - - - - - -
                               - - - - - - - - - - - R - R - - - - - - - - - - -
                               - - - - - - - - - - R - R - R - - - - - - - - - -
                               - - - - - - - - - R - R - R - R - - - - - - - - -
                               W - W - W - W - W - W - W - W - W - W - W - W - W
                               - W - W - W - W - W - W - W - W - W - W - W - W -
                               - - W - W - W - W - W - W - W - W - W - W - W - -
                               - - - W - W - W - W - W - W - W - W - W - W - - -
                               - - - - W - W - W - W - W - W - W - W - W - - - -
                               - - - W - W - W - W - W - W - W - W - W - W - - -
                               - - W - W - W - W - W - W - W - W - W - W - W - -
                               - W - W - W - W - W - W - W - W - W - W - W - W -
                               W - W - W - W - W - W - W - W - W - W - W - W - W
                               - - - - - - - - - G - G - G - G - - - - - - - - -
                               - - - - - - - - - - G - G - G - - - - - - - - - -
                               - - - - - - - - - - - G - G - - - - - - - - - - -
                               - - - - - - - - - - - - G - - - - - - - - - - - -

                                                        R
                                                       R R
                                                      R R R
                                                     R R R R
                                            W W W W W W W W W W W W W
                                             W W W W W W W G W W W W
                                              W W W W W W W W W W W
                                               W W W W W G W W W W
                                                W W W W R W W W W
                                               W W W W W W W W W W
                                              W W W W W W W W W W W
                                             W W W W W W W W W W W W
                                            W W W W W W W W W W W W W
                                                     G G G G
                                                      G G G
                                                       G G
                                                        G
   */

import java.util.*;

class point {
    int row;
    int col;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public point(int row, int col) {
        this.row = row;
        this.col = col;
    }
    @Override
    public String toString() {
        return " [" + row +"," + col + "] ";
    }
}

public class GameController {
    public int count;
    Board B;
    Map<point,ArrayList<point>> map = new HashMap<>();
    ArrayList<point> validJump = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public GameController(Board b) {
        B = b;
    }

    public boolean Game(Player obj) {
        System.out.println("\n");
        B.Print();

        Map<point,ArrayList<point>> m=checkMove(obj);

        System.out.println("Available Moves");
        System.out.println(m);
        System.out.print("Row1 : ");
        int row = scan.nextInt();
        System.out.print("Col1 : ");
        int col = scan.nextInt();
        System.out.print("Row2 : ");
        int row2 = scan.nextInt();
        System.out.print("Col2 : ");
        int col2 = scan.nextInt();
        point temp1=new point(row,col);
        point temp2=new point(row2,col2);
        boolean checkValidMove=isMoved(B,temp1,temp2,m);
        if(checkValidMove)
        {
            return true;
        }
        else {
            System.out.println("Not Valid");
            return false;
        }
    }
    //get the selected move from the user and check whether the move is valid.
    //send the board.
    public boolean isMoved(Board localBoard,point p1,point p2,Map<point,ArrayList<point>> map){
        for (Map.Entry<point, ArrayList<point>> key : map.entrySet()) {
            if(key.getKey().getRow()==p1.row && key.getKey().getCol()==p1.col){
                for(int i=0 ;i<key.getValue().size();i++){
                    if(key.getValue().get(i).row==p2.row && key.getValue().get(i).col== p2.col){
                        // char c=  B.MainBoard.get(p1.row).get(p1.col);
//                        B.MainBoard.get(p1.row).get(p1.col)=B.MainBoard.get(p2.row).get(p2.col);
                        // i have the aaa
                        char a = localBoard.MainBoard.get(key.getKey().getRow()).get(key.getKey().getCol());
                        localBoard.MainBoard.get(key.getKey().getRow()).set(key.getKey().getCol(),'W');
                        localBoard.MainBoard.get(key.getValue().get(i).row).set(key.getValue().get(i).col,a);
                        Board.LastInfo lastInfo=new Board.LastInfo(p1.row,p1.col,p2.row,p2.col);
                        localBoard.setLastInfo(lastInfo);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void undoMove(point p1,point p2){
        char temp=B.MainBoard.get(p1.row).get(p1.col);
        B.MainBoard.get(p1.row).set(p1.col,B.MainBoard.get(p2.row).get(p2.col));
        B.MainBoard.get(p2.row).set(p2.col,temp);
    }
    public Map<point,ArrayList<point>> checkMove(Player obj) {
        ArrayList<point> pointResult ;
        map = new HashMap<>();

        for (int i=0;i<B.MainBoard.size();i++) {
            for(int j=0;j<B.MainBoard.get(i).size();j++){
                if(B.MainBoard.get(i).get(j)==obj.color) {
                    pointResult = validMoves(i,j);
                    validHops(i,j);
                    pointResult.addAll(0,validJump);
                    point p = new point(i,j);
                    map.put(p,pointResult);
                    validJump.clear();
                }
            }
        }
        return map;
    }

    public ArrayList<point> validMoves(int r,int c) {
        count=0;
        ArrayList<point> points = new ArrayList<>();
        if(c>1) {
            //Left
            if( B.MainBoard.get(r).get(c-2) != '-' && B.MainBoard.get(r).get(c-2) =='W') {
                point p = new point(r,c-2);
                points.add(p);
            }else count++;
        }
        if(c < 23) {
            //Right
            if( B.MainBoard.get(r).get(c+2) != '-' &&  B.MainBoard.get(r).get(c+2)=='W') {
                point p = new point(r,c+2);
                points.add(p);
            }else count++;
        }
        if(r>0) {
            //top Left
            if(c>0 && B.MainBoard.get(r-1).get(c-1) != '-' && B.MainBoard.get(r-1).get(c-1)=='W') {
                point p = new point(r-1,c-1);
                points.add(p);
            }else count++;
            //top right
            if( c < 24 && B.MainBoard.get(r-1).get(c+1) != '-' && B.MainBoard.get(r-1).get(c+1)=='W') {
                point p = new point(r-1,c+1);
                points.add(p);
            }else count++;
        }
        if(r<16) {
            //Bottom right
            if(c < 24 &&  B.MainBoard.get(r+1).get(c+1) != '-' && B.MainBoard.get(r+1).get(c+1) =='W') {
                point p = new point(r+1,c+1);
                points.add(p);
            }else count++;
            //Bottom Left
            if( c>0 && B.MainBoard.get(r+1).get(c-1) != '-' && B.MainBoard.get(r+1).get(c-1)=='W') {
                point p = new point(r+1,c-1);
                points.add(p);
            }else count++;
        }
        return points;
    }

    public void validHops(int r,int c) {
        validMoves(r,c);
        if(count == 0)return;

        for (int i=0;i<validJump.size();i++) if(validJump.get(i).row == r &&  validJump.get(i).col == c) return;

        if(B.MainBoard.get(r).get(c)=='W') {
            point p = new point(r,c);
            validJump.add(p);
        }
        if(c>3) {
            //Left
            if( B.MainBoard.get(r).get(c-2) != '-' && B.MainBoard.get(r).get(c-2) !='W')
                if(B.MainBoard.get(r).get(c-4) != '-' && B.MainBoard.get(r).get(c-4) =='W') validHops(r,c-4);
        }
        if(c < 21) {
            //Right
            if( B.MainBoard.get(r).get(c+2) != '-' &&  B.MainBoard.get(r).get(c+2)!='W')
                if( B.MainBoard.get(r).get(c+4) != '-' &&  B.MainBoard.get(r).get(c+4)=='W') validHops(r,c+4);
        }
        if(r>1) {
            //top Left
            if(c>1 && B.MainBoard.get(r-1).get(c-1) != '-' && B.MainBoard.get(r-1).get(c-1)!='W')
                if(B.MainBoard.get(r-2).get(c-2) != '-' && B.MainBoard.get(r-2).get(c-2)=='W') validHops(r-2,c-2);
            //top right
            if( c < 23 && B.MainBoard.get(r-1).get(c+1) != '-' && B.MainBoard.get(r-1).get(c+1)!='W')
                if(B.MainBoard.get(r-2).get(c+2) != '-' && B.MainBoard.get(r-2).get(c+2)=='W') validHops(r-2,c+2);
        }
        if(r<15) {
            //Bottom Right
            if(c < 23 &&  B.MainBoard.get(r+1).get(c+1) != '-' && B.MainBoard.get(r+1).get(c+1) !='W')
                if(B.MainBoard.get(r+2).get(c+2) != '-' && B.MainBoard.get(r+2).get(c+2) =='W') validHops(r+2,c+2);
            //Bottom Left
            if( c>1 && B.MainBoard.get(r+1).get(c-1) != '-' && B.MainBoard.get(r+1).get(c-1)!='W')
                if(B.MainBoard.get(r+2).get(c-2) != '-' && B.MainBoard.get(r+2).get(c-2)=='W') validHops(r+2,c-2);
        }
    }
    /*
                                - - - - - - - - - - - - R - - - - - - - - - - - -
                                - - - - - - - - - - - R - R - - - - - - - - - - -
                                - - - - - - - - - - R - R - R - - - - - - - - - -
                                - - - - - - - - - R - R - H - R - - - - - - - - -
                                W - W - W - W - W - W - W - W - W - W - W - W - W
                                - W - W - W - W - W - W - W - W - W - W - W - W -
                                - - W - W - W - W - W - R - W - W - W - W - W - -
                                - - - W - W - W - W - W - W - W - W - W - W - - -
                                - - - - W - W - W - W - W - W - W - W - W - - - -
                                - - - W - W - W - W - G - G - W - W - W - W - - -
                                - - W - W - W - W - G - R - G - W - W - W - W - -
                                - W - W - W - W - W - G - W - W - W - W - W - W -
                                W - W - W - W - W - W - W - W - W - W - W - W - W
                                - - - - - - - - - G - G - G - G - - - - - - - - -
                                - - - - - - - - - - G - G - G - - - - - - - - - -
                                - - - - - - - - - - - G - G - - - - - - - - - - -
                                - - - - - - - - - - - - G - - - - - - - - - - - -
    * */
    public int checkWinner(){
        // (1) player 1 is win
        // (2) player 2 is win
        // (0) continue game
        int count=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<B.MainBoard.get(i).size();j++){
                if(B.MainBoard.get(i).get(j) != '-' && B.MainBoard.get(i).get(j) == 'G') count++;
            }
        }
        if(count == 10) return 2;
        count=0;
        for(int i=13;i<17;i++){
            for(int j=0;j<B.MainBoard.get(i).size();j++){
                if(B.MainBoard.get(i).get(j) != '-' && B.MainBoard.get(i).get(j) == 'R') count++;
            }
        }
        if(count == 10) return 1;
        return 0;
    }
}