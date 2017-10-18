import javax.swing.*;

public class AlphaBetaChess{
    static String chessBoard[][] = {
        {"r","k","b","q","a"," ","k","r"},
        {"p","p","p","p"," ","p","p","p"},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," ","P"," "," "," "},
        {" ","b"," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {"P","P","P"," ","P","P","P","P"},
        {"R","K","B","Q","A","B","K","R"}};

    public static void main(String[] args){
        JFrame f = new JFrame("My Title Goes Here!");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserInterface ui = new UserInterface();
        f.add(ui);
        f.setSize(500, 500);
        f.setVisible(true);
    }

}
