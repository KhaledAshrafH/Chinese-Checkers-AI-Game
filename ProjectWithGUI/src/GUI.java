import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

public class GUI extends JFrame
{
    JRadioButton jRadioButton1;

    // Declaration of object of JRadioButton class.
    JRadioButton jRadioButton2;

    // Declaration of object of JRadioButton class.
    JRadioButton jRadioButton3;

    // Declaration of object of JButton class.
    JButton jButton;

    // Declaration of object of ButtonGroup class.
    ButtonGroup G1;

    // Declaration of object of  JLabel  class.
    JLabel L1;

    public static final int[] CCArray = {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};

    public static final int ROWS = 17;
    public static final int COLS = 13;//25
    public static final int CELL_SIZE = 45;
    public static final int CANVAS_WIDTH = CELL_SIZE * COLS+20;
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS+20;
    public static final int SYMBOL_STROKE_WIDTH = 4;
    public int Level=1;
    public static Seed pieceMoved;

    public class Points{
        int x;
        int y;
        Points(int X,int Y){x=X; y=Y;}
    }
    public static boolean playerClick = true;
    public static int[] firstMove = new int[2];
    public static int[] boardfirstMove = new int[2];
    private static final Color playerColor= new Color(128,0,255);
    private static final Color pcColor= new Color(0,204,102);
    private static final Color backgroundColor= new Color(255,255,255);
    public enum GameState {
        PLAYING, Player_WON, PC_WON
    }
    private GameState currentState;

    public enum Seed {
        EMPTY,  Player, Player0, Player1, Player2, Player3, Player4, Player5, Player6, Player7, Player8, Player9,
        PC, PC0, PC1, PC2, PC3, PC4, PC5, PC6, PC7, PC8, PC9, VALID, INVALID
    }

    public static Seed[] Player_ID = {Seed.Player0, Seed.Player1,Seed.Player2, Seed.Player3,Seed.Player4, Seed.Player5,
            Seed.Player6, Seed.Player7, Seed.Player8, Seed.Player9};

    public static Seed[] PC_ID = {Seed.PC0, Seed.PC1, Seed.PC2, Seed.PC3, Seed.PC4, Seed.PC5,
            Seed.PC6, Seed.PC7, Seed.PC8, Seed.PC9};

    private Seed currentPlayer;

    private final Seed[][] board;
    private Draw_Board Board;
    private JLabel statusBar=new JLabel();
    ArrayList<point>moves;
    GameController game;

    public Seed[][] getBoard() {
        return board;
    }

    GUI(){
        Board B = new Board();
        Player player1 = new Player(1,B);
        player1.setName("Khaled");
        player1.setColor('G');
        Player player2 = new Player(4,B);
        player2.setName("PC");
        player2.setColor('R');
        GameController obj = new GameController(B);
        Agent pc=new Agent(player2,obj,player1);
       // Map<point,ArrayList<point>> m=obj.checkMove(player1);

        moves=new ArrayList<>();
        Board = new Draw_Board();
        Board.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));



        Board.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                int rowSelected = mouseY / CELL_SIZE;
                int colSelected = mouseX / CELL_SIZE;
                int boardRow=rowSelected;
                int boardCol;
                if (rowSelected%2 == 1) {
                    double column = (mouseX - CELL_SIZE/2) / CELL_SIZE;
                    colSelected = (int) column;
                }
                if(rowSelected%2==0){
                    boardCol=colSelected*2;
                }else boardCol=colSelected*2+1;
                System.out.println("Column Selected = "+colSelected+"\trowSelected ="+rowSelected);

                if (currentState == GameState.PLAYING) {
                    //the marbles chosen to be played with.
                    if(playerClick) {
                        pieceMoved = checkPiece(board, currentPlayer, rowSelected, colSelected);
                        game=new GameController(B);

                        if (checkPresent(Player_ID, pieceMoved) || checkPresent(PC_ID, pieceMoved)) {
                            //what marble to move with.
                            if (!moves.isEmpty())moves.clear();
                            moves=game.move(boardRow,boardCol);
                            for (point p:moves) {
                                p.setCol(p.getCol()/2);
                            }
                            considerMoves(moves);
                            firstMove[0] = rowSelected;
                            firstMove[1] = colSelected;
                            boardfirstMove[0] = boardRow;
                            boardfirstMove[1] = boardCol;
                            playerClick = false;
                        } else {
                            pieceMoved = Seed.INVALID;
                            playerClick = true;
                        }

                    } else {
                        statusBar.setText("Computer's Turn");
                        deConsiderMoves();

                        if (moveVALID(moves, rowSelected, colSelected)) {
                            moves.clear();
                            Clear_Move(pieceMoved);
                            board[rowSelected][colSelected] = pieceMoved;
                            int[] finalMove = {rowSelected, colSelected};
                            currentState = updateGame(board, currentPlayer);
                            currentPlayer = (currentPlayer == Seed.Player) ? Seed.PC : Seed.Player;
                            //board update
                            Map<point,ArrayList<point>> m=obj.checkMove(player1);
                            point from=new point(boardfirstMove[0],boardfirstMove[1]);
                            point to=new point(boardRow,boardCol);
                            boolean checkValidMove=obj.isMoved(B,from,to,m);
                            repaint();
                            //AI
                            if (currentState == GameState.PLAYING) {
                                // pieceMove = AIMove(pieceMove, firstMove, finalMove);
                                pc.minimax(B,Level,true,-1000,1000);
                                pc.allMoves = obj.checkMove(player2);
                                obj.undoMove(pc.getFirstBest(),pc.getSecondBest());
                                int seedRowBest=pc.getFirstBest().getRow();
                                int seedColBest=0;
                                if(pc.getFirstBest().getCol()%2==0)  seedColBest=pc.getFirstBest().getCol()/2;
                                else seedColBest=(pc.getFirstBest().getCol()-1)/2;
                                Seed pieceMovedFrom= checkPiece(board, currentPlayer,seedRowBest ,seedColBest );
                                Clear_Move(pieceMovedFrom);
                                int selectedColSeed=0;
                                if(pc.getSecondBest().getCol()%2==0)  selectedColSeed=pc.getSecondBest().getCol()/2;
                                else selectedColSeed=(pc.getSecondBest().getCol()-1)/2;
                                board[pc.getSecondBest().getRow()][selectedColSeed]=pieceMovedFrom;
                                 currentPlayer = (currentPlayer == Seed.Player) ? Seed.PC : Seed.Player;
                                 playerClick=true;
                                repaint();
                                if (checkPresent(Player_ID, pieceMoved) || checkPresent(PC_ID, pieceMoved)) {
                                    // moves=game.move(rowSelected,colSelected);
                                    considerMoves(moves);
                                    firstMove[0] = rowSelected;
                                    firstMove[1] = colSelected;
                                    playerClick = false;
                                }
                                else {
                                    pieceMoved = Seed.INVALID;
                                    playerClick = true;
                                }
                            }
                        }
                        pieceMoved = Seed.INVALID;
                        playerClick = true;
                    }
                }
                else {
                    initGame();
                }
                repaint();
            }
        });

        statusBar = new JLabel("  ");
        statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
        JLabel L1 = new JLabel("Level ");
        // Initialization of object of "JRadioButton" class.
        jRadioButton1 = new JRadioButton();

        // Initialization of object of "JRadioButton" class.
        jRadioButton2 = new JRadioButton();
        jRadioButton3 = new JRadioButton();

        // Initialization of object of "JButton" class.
        jButton = new JButton("Click");
                    // Initialization of object of "ButtonGroup" class.
        G1 = new ButtonGroup();


        // setText(...) function is used to set text of radio button.
        // Setting text of "jRadioButton2".
        jRadioButton1.setText("Easy");

        // Setting text of "jRadioButton4".
        jRadioButton2.setText("Medium");
        jRadioButton3.setText("Hard");
        // Setting Bounds of "jRadioButton2".
        jRadioButton1.setBounds(20, 30, 70, 30);
        jRadioButton1.setBackground(Color.white);

        // Setting Bounds of "jRadioButton4".
        jRadioButton2.setBounds(20, 60, 70, 30);
        jRadioButton2.setBackground(Color.white);


        // Setting Bounds of "jRadioButton4".
        jRadioButton3.setBounds(20, 90, 70, 30);
        jRadioButton3.setBackground(Color.white);


        // Setting Bounds of "jButton".
        jButton.setBounds(100, 90, 80, 30);


        // Setting Bounds of JLabel "L2".
        L1.setBounds(10, 0, 170, 50);
        // "this" keyword in java refers to current object.

        // Adding "jRadioButton2" on JFrame.
        this.add(jRadioButton1);

        // Adding "jRadioButton4" on JFrame.
        this.add(jRadioButton2);
        this.add(jRadioButton3);

        // Adding "jButton" on JFrame.
        this.add(jButton);

        // Adding "jRadioButton1" and "jRadioButton3" in a Button Group "G2".
        G1.add(jRadioButton1);
        G1.add(jRadioButton2);
        G1.add(jRadioButton3);
        this.add(L1);
        jButton.addActionListener(new ActionListener() {
            // Anonymous class.

            public void actionPerformed(ActionEvent e)
            {

                if(jRadioButton1.isSelected()){
                    System.out.println("Easy");
                    Level=1;
                } else if (jRadioButton2.isSelected()) {
                    System.out.println("Medium");
                    Level=3 ;
                }else if(jRadioButton3.isSelected()){
                    System.out.println("Hard");
                    Level=5;
                }
            jButton.setVisible(false);
            jRadioButton1.setVisible(false);
            jRadioButton2.setVisible(false);
            jRadioButton3.setVisible(false);
            L1.setVisible(false);
            }
        });

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(Board, BorderLayout.CENTER);
        cp.add(statusBar, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle("Chinese Checkers");
        setVisible(true);

        board = new Seed[ROWS][COLS];
        initGame();

    }

    public void initGame() {
        int col;
        for (int row = 0; row < ROWS; row++) {
            col = ((COLS-1)/2) - ((CCArray[row] - (CCArray[row]%2))/2);
            for (int i = 0; i < CCArray[row]; i++) {
                board[row][col] = Seed.EMPTY;
                col++;
            }
            col = 0;
        }

        for (int row = 0; row < ROWS; row++) {
            for (col = 0; col < COLS; col++) {
                if (board[row][col] != Seed.EMPTY) {
                    board[row][col] = Seed.INVALID;
                }
            }
        }

        int count = 0;
        for (int row = 0; row < 4; row++) {
            col = ((COLS-1)/2) - ((CCArray[row] - (CCArray[row]%2))/2);
            for (int i = 0; i < CCArray[row]; i++) {
                board[row][col] = PC_ID[count];
                col++;
                count++;
            }
            col = 0;
        }

        count = 0;
        for (int row = ROWS - 1; row > (ROWS - 5); row--) {
            col = ((COLS-1)/2) - ((CCArray[row] - (CCArray[row]%2))/2);
            for (int i = 0; i < CCArray[row]; i++) {
                board[row][col] = Player_ID[count];
                col++;
                count++;
            }
            col = 0;
        }
        currentState = GameState.PLAYING;
        currentPlayer = Seed.Player;
    }

    public Seed checkPiece(Seed[][] boarding, Seed player, int rowSelected, int colSelected) {
        Seed selected = Seed.INVALID;
        if (player == Seed.Player) {
            for (int i = 0; i < Player_ID.length; i++) {
                if (boarding[rowSelected][colSelected] == Player_ID[i]) {
                    selected = Player_ID[i];
                }
            }
        } else if (player == Seed.PC) {
            for (int i = 0; i < PC_ID.length; i++) {
                if (boarding[rowSelected][colSelected] == PC_ID[i]) {
                    selected = PC_ID[i];
                }
            }
        }
        return selected;
    }

    public boolean moveVALID(ArrayList<point> moves, int rowSelected, int colSelected) {
        boolean possibility = false;
        for (int look = 0; look < moves.size(); look++) {
            if ((rowSelected == moves.get(look).row) && (colSelected == moves.get(look).col)) {
                possibility = true;
                break;
            }
        }
        return possibility;
    }


    public void Clear_Move(Seed piece) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (piece == board[row][col]) {
                    board[row][col] = Seed.EMPTY;
                }
            }
        }
    }
    public GameState updateGame(Seed[][] game, Seed theSeed) {
        GameState stateEnd = GameState.PLAYING;
        if (hasWon(game, theSeed)) {
            stateEnd = (theSeed == Seed.Player) ? GameState.Player_WON : GameState.PC_WON;
        }
        return stateEnd;
    }

    public boolean hasWon(Seed[][] winBoard, Seed seedEnd) {
        boolean winPotential = false;
        int track = 0;
        if (seedEnd == Seed.Player) {
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < winBoard[0].length; col++) {
                    if (checkPresent(Player_ID, winBoard[row][col])) {track++;}
                }
            }
        } else if (seedEnd == Seed.PC) {
            for (int row = winBoard.length-1; row > winBoard.length-5; row--) {
                for (int col = 0; col < winBoard[0].length; col++) {
                    if (checkPresent(PC_ID, winBoard[row][col])) {track++;}
                }
            }
        }

        if (track == 10) {
            winPotential = true;
        }

        return winPotential;
    }

    public void considerMoves(ArrayList<point> moves) {
        for (int i = 0; i < moves.size(); i++) {
            board[moves.get(i).row][moves.get(i).col] = Seed.VALID;
        }
    }

    public void deConsiderMoves() {
        for (int row = 0; row < board.length; row ++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == Seed.VALID) {
                    board[row][col] = Seed.EMPTY;
                }
            }
        }
    }

    public boolean checkPresent(Seed[] options, Seed match) {
        boolean check = false;
        for (int i = 0; i < options.length; i++) {
            if (match == options[i]) {
                check = true;
            }
        }
        return check;
    }


    public class Draw_Board extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(backgroundColor);
            //border
            g.setColor(Color.BLACK);
            int xVal;
            for (int yVal = 0; yVal < CCArray.length; yVal++) {
                xVal = 0;
                while (xVal < CCArray[yVal]) {
                    g.drawOval(((CANVAS_WIDTH/2) - (((CCArray[yVal]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yVal]) - (CCArray[yVal]%2))/2)) +
                            ((CELL_SIZE) * xVal), CELL_SIZE * yVal, CELL_SIZE, CELL_SIZE);
                    xVal++;
                }
            }
            ///here
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));
            for (int yVal = 0; yVal < ROWS; yVal++) {
                for (int xFill = 0; xFill < COLS; xFill++) {
                    int yPlot = yVal;
                    int xPlot = (((CCArray[yPlot] - (CCArray[yPlot]%2))/2) - 6) + xFill;


                    if (checkPresent(Player_ID, board[yVal][xFill])) {
                        g2d.setColor(playerColor);
                        g.drawOval(((CANVAS_WIDTH/2) - (((CCArray[yPlot]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yPlot]) - (CCArray[yPlot]%2))/2)) +
                                ((CELL_SIZE) * xPlot), CELL_SIZE * yPlot, CELL_SIZE, CELL_SIZE);
                        g.fillOval(((CANVAS_WIDTH/2) - (((CCArray[yPlot]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yPlot]) - (CCArray[yPlot]%2))/2)) +
                                ((CELL_SIZE) * xPlot), CELL_SIZE * yPlot, CELL_SIZE, CELL_SIZE);
                    }

                    if (checkPresent(PC_ID, board[yVal][xFill])) {
                        g2d.setColor(pcColor);
                        g.drawOval(((CANVAS_WIDTH/2) - (((CCArray[yPlot]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yPlot]) - (CCArray[yPlot]%2))/2)) +
                                ((CELL_SIZE) * xPlot), CELL_SIZE * yPlot, CELL_SIZE, CELL_SIZE);
                        g.fillOval(((CANVAS_WIDTH/2) - (((CCArray[yPlot]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yPlot]) - (CCArray[yPlot]%2))/2)) +
                                ((CELL_SIZE) * xPlot), CELL_SIZE * yPlot, CELL_SIZE, CELL_SIZE);
                    }

                    if (board[yVal][xFill] == Seed.VALID) {
                        g2d.setColor(Color.gray);
                        g.drawOval(((CANVAS_WIDTH/2) - (((CCArray[yPlot]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yPlot]) - (CCArray[yPlot]%2))/2)) +
                                ((CELL_SIZE) * xPlot), CELL_SIZE * yPlot, CELL_SIZE, CELL_SIZE);
                        g.fillOval(((CANVAS_WIDTH/2) - (((CCArray[yPlot]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yPlot]) - (CCArray[yPlot]%2))/2)) +
                                ((CELL_SIZE) * xPlot), CELL_SIZE * yPlot, CELL_SIZE, CELL_SIZE);
                    }


                    if (board[yVal][xFill] == Seed.EMPTY) {
                        g2d.setColor(Color.WHITE);
                        g.drawOval(((CANVAS_WIDTH/2) - (((CCArray[yPlot]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yPlot]) - (CCArray[yPlot]%2))/2)) +
                                ((CELL_SIZE) * xPlot), CELL_SIZE * yPlot, CELL_SIZE, CELL_SIZE);
                        g.fillOval(((CANVAS_WIDTH/2) - (((CCArray[yPlot]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yPlot]) - (CCArray[yPlot]%2))/2)) +
                                ((CELL_SIZE) * xPlot), CELL_SIZE * yPlot, CELL_SIZE, CELL_SIZE);
                        g2d.setColor(Color.BLACK);
                        g.drawOval(((CANVAS_WIDTH/2) - (((CCArray[yPlot]) % 2) * CELL_SIZE/2)) - (CELL_SIZE * (((CCArray[yPlot]) - (CCArray[yPlot]%2))/2)) +
                                ((CELL_SIZE) * xPlot), CELL_SIZE * yPlot, CELL_SIZE, CELL_SIZE);
                    }
                }
            }


            if (currentState == GameState.PLAYING) {
                statusBar.setForeground(Color.BLACK);
                if (currentPlayer == Seed.Player) {
                    statusBar.setText("Your Turn");
                } else {
                    statusBar.setText("Computer's Turn");
                }
            } else if (currentState == GameState.Player_WON) {
                statusBar.setForeground(playerColor);
                statusBar.setText("'Player' Won! Click to play again.");
            } else if (currentState == GameState.PC_WON) {
                statusBar.setForeground(pcColor);
                statusBar.setText("'PC' Won! Click to play again.");
            }
        }
    }
}