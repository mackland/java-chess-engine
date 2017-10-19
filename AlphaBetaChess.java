import javax.swing.*;

public class AlphaBetaChess{
    static String chessBoard[][] = {
        {"r","k","b","q","a","b","k","r"},
        {"p","p","p","p","p","p","p","p"},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {"P","P","P","P","P","P","P","P"},
        {"R","K","B","Q","A","B","K","R"}};
/*
    public static void main(String[] args){
        JFrame f = new JFrame("My Title Goes Here!");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserInterface ui = new UserInterface();
        f.add(ui);
        f.setSize(500, 500);
        f.setVisible(true);
    }
*/    
    //format of return will be: x1,y1,x2,y2,captured piece
    //e.g. 6,7,5,7," "
    public static String possibleMoves(){
        String list = "";

        for(int i = 0; i < 64; i++){
            switch(chessBoard[i/8][i%8]){
                case "P":
                    list += possibleP(i);
                    break;
                case "R":
                    list += possibleR(i);
                    break;
                case "K":
                    list += possibleK(i);
                    break;
                case "B":
                    list += possibleB(i);
                    break;
                case "Q":
                    list += possibleQ(i);
                    break;
                case "A":
                    list += possibleA(i);
                    break;
            }
        }    
        return list;
    }

    public static String possibleP(int i){
        String list = "";
        
        return list;
    }
    
    public static String possibleR(int i){
        String list = "";
        
        return list;
    }
    
    public static String possibleK(int i){
        String list = "";
        
        return list;
    }
    
    public static String possibleB(int i){

        String list = "";
        
        return list;
    }
    
    public static String possibleQ(int i){
        String list = "";
        
        return list;
    }
    
    public static String possibleA(int i){
        String list = "";
        
        return list;
    }


}
