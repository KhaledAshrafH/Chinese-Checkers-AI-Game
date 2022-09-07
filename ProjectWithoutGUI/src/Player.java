
import java.util.ArrayList;
import java.util.List;

public class Player {
    public char color;

    public String name;

    public ArrayList<ArrayList<Character>> Triangle; //win

    public Player(int Num,Board B)
    {
        Triangle = new ArrayList<>();
        if (Num == 1)
        {
            Triangle.add(B.MainBoard.get(16));
            Triangle.add(B.MainBoard.get(15));
            Triangle.add(B.MainBoard.get(14));
            Triangle.add(B.MainBoard.get(13));
        }
        else if (Num == 4)
        {
            Triangle.add(B.MainBoard.get(0));
            Triangle.add(B.MainBoard.get(1));
            Triangle.add(B.MainBoard.get(2));
            Triangle.add(B.MainBoard.get(3));
        }
        else if (Num == 2 || Num ==6)
        {
            Triangle.add(B.MainBoard.get(12));
            Triangle.add(B.MainBoard.get(11));
            Triangle.add(B.MainBoard.get(10));
            Triangle.add(B.MainBoard.get(9));
        }else if (Num == 3 || Num ==5)
        {
            Triangle.add(B.MainBoard.get(7));
            Triangle.add(B.MainBoard.get(6));
            Triangle.add(B.MainBoard.get(5));
            Triangle.add(B.MainBoard.get(4));
        }
    }

    public ArrayList<ArrayList<Character>> PlayerBoard(){
        return Triangle;
    }
    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
