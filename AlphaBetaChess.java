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
    
    //keep track of king to avoid check/illegal move
    static int kingPositionC;
    static int kingPositionL;
    
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
        String oldPiece;
        int row = i/8;
        int col = i%8;

        for(int j = -1; j <= 1; j+=2){
            
            // look for possible captures (diagonal)
            try{
                if(Character.isLowerCase(chessBoard[row - 1][col + j].charAt(0)) &&
                        i>=16){
                    oldPiece = chessBoard[row - 1][col + j];
                    chessBoard[row][col] = " ";
                    chessBoard[row][col] = "P";
                    if(kingSafe()){
                        list = list + row + col + (row - 1) + (col + j) + oldPiece;
                    }
                    chessBoard[row][col] = "P";
                    chessBoard[row - 1][col + j] = oldPiece;
                }
            } catch(Exception e){}
            
            //promotion and capture 
            try{
                if(Character.isLowerCase(chessBoard[row - 1][col + j].charAt(0)) &&
                        i < 16){
                    String[] promotionPiece = {"Q", "R", "B", "K"};
                    
                    for(int k = 0; k < promotionPiece.length; k++){
                        oldPiece = chessBoard[row-1][col+j];
                        chessBoard[row][col] = " ";
                        chessBoard[row - 1][col + j] = promotionPiece[k];
                        if (kingSafe()){
                            // column1, column2, captured-piece, new-piece, P
                            list = list + col + (col + j) + oldPiece + promotionPiece[k] + "P";
                        }
                        chessBoard[row][col] = "P";
                        chessBoard[row-1][col+j] = oldPiece;
                    }

                    oldPiece = chessBoard[row - 1][col + j];
                    chessBoard[row][col] = " ";
                    chessBoard[row][col] = "P";
                    if(kingSafe()){
                        list = list + row + col + (row - 1) + (col + j) + oldPiece;
                    }
                    chessBoard[row][col] = "P";
                    chessBoard[row - 1][col + j] = oldPiece;
                }
            } catch(Exception e){}
        
        }
        //move 1 up
        try{
            if(" ".equals(chessBoard[row - 1][col]) && i>=16){
                    oldPiece = chessBoard[row - 1][col];
                    chessBoard[row][col] = " ";
                    chessBoard[row][col] = "P";
                    if(kingSafe()){
                        list = list + row + col + (row - 1) + (col) + oldPiece;
                    }
                    chessBoard[row][col] = "P";
                    chessBoard[row - 1][col] = oldPiece;
                
            }
        } catch(Exception e){}

       //promotion without capture 
       try{
            if(" ".equals(chessBoard[row - 1][col]) && i < 16){
                String[] promotionPiece = {"Q", "R", "B", "K"};
                    
                for(int k = 0; k < promotionPiece.length; k++){
                    oldPiece = chessBoard[row-1][col];
                    chessBoard[row][col] = " ";
                    chessBoard[row - 1][col] = promotionPiece[k];
                    if (kingSafe()){
                        // column1, column2, captured-piece, new-piece, P
                        list = list + col + (col) + oldPiece + promotionPiece[k] + "P";
                    }
                    chessBoard[row][col] = "P";
                    chessBoard[row-1][col] = oldPiece;
                }
            }
        } catch(Exception e){}
        
        // initial 2 row move    
        try{
            if(" ".equals(chessBoard[row - 1][col]) && " ".equals(chessBoard[row-2][col]) && i>=48){
                    oldPiece = chessBoard[row - 2][col];
                    chessBoard[row][col] = " ";
                    chessBoard[row][col] = "P";
                    if(kingSafe()){
                        list = list + row + col + (row - 2) + (col) + oldPiece;
                    }
                    chessBoard[row][col] = "P";
                    chessBoard[row - 2][col] = oldPiece;
                
            }
        } catch(Exception e){}

 
       return list;
    }
    
    public static String possibleR(int i){
        String list = "";
        String oldPiece;
        int temp = 1;
        int row = i/8;
        int col = i%8;
        for(int j = -1; j <= 1; j+=2){
            try{
                while(" ".equals(chessBoard[row][col + temp*j])){
                    oldPiece = chessBoard[row][col + temp*j];
                    chessBoard[row][col] = " ";
                    chessBoard[row][col + temp*j] = "R";
                    if(kingSafe()){
                        list = list + row + col + row + (col + temp*j) + oldPiece;
                    }
                    chessBoard[row][col] = "R";
                    chessBoard[row][col + temp*j] = oldPiece;
                    temp++;
                }
                if(Character.isLowerCase(chessBoard[row][col + temp*j].charAt(0))){
                    oldPiece = chessBoard[row][col + temp*j];
                    chessBoard[row][col] = " ";
                    chessBoard[row][col + temp*j] = "R";
                    if(kingSafe()){
                        list = list + row + col + row + (col + temp*j) + oldPiece;
                    }
                    chessBoard[row][col] = "R";
                    chessBoard[row][col + temp*j] = oldPiece;
                }
            } catch(Exception e) {}
            temp = 1;
            try{
                while(" ".equals(chessBoard[row + temp*j][col])){
                    oldPiece = chessBoard[row + temp*j][col];
                    chessBoard[row][col] = " ";
                    chessBoard[row + temp*j][col] = "R";
                    if(kingSafe()){
                        list = list + row + col + (row + temp*j) + col + oldPiece;
                    }
                    chessBoard[row][col] = "R";
                    chessBoard[row + temp*j][col] = oldPiece;
                    temp++;
                }
                if(Character.isLowerCase(chessBoard[row + temp*j][col].charAt(0))){
                    oldPiece = chessBoard[row + temp*j][col];
                    chessBoard[row][col] = " ";
                    chessBoard[row + temp*j][col] = "R";
                    if(kingSafe()){
                        list = list + row + col + (row + temp*j) + col + oldPiece;
                    }
                    chessBoard[row][col] = "R";
                    chessBoard[row + temp*j][col] = oldPiece;
                }
            } catch(Exception e) {}
            temp = 1; 
        }        
        return list;
    }
    
    public static String possibleK(int i){
        String list = "";
        String oldPiece;

        int row = i/8;
        int col = i%8;
        for(int j = -1; j <= 1; j+=2){
            for(int k = -1; k <= 1; k +=2){
                try{
                    if(Character.isLowerCase(chessBoard[row + j][col + k*2].charAt(0)) || " ".equals(chessBoard[row + j][col + k*2])){
                        oldPiece = chessBoard[row+j][col+2*k];
                        chessBoard[row][col] = " ";
                        chessBoard[row+j][col+2*k] = "K";
                        if(kingSafe()){
                            list = list + row + col + (row+j) + (col+2*k) + oldPiece;
                        }
                        chessBoard[row][col] = "K";
                        chessBoard[row+j][col+2*k] = oldPiece;
                    }
                } catch (Exception e){}
                try{
                    if(Character.isLowerCase(chessBoard[row + 2*j][col + k].charAt(0)) || " ".equals(chessBoard[row + 2*j][col + k])){
                        oldPiece = chessBoard[row+2*j][col+k];
                        chessBoard[row][col] = " ";
                        chessBoard[row+2*j][col+k] = "K";
                        if(kingSafe()){
                            list = list + row + col + (row+2*j) + (col+k) + oldPiece;
                        }
                        chessBoard[row][col] = "K";
                        chessBoard[row+2*j][col+k] = oldPiece;
                    }
                } catch (Exception e){}
 

            }
        }  
        return list;
    }
    
    public static String possibleB(int i){
         String list = "";
        
        String oldPiece;
        int row = i/8;
        int col = i%8;
        int temp = 1;
        for(int j = -1; j <= 1; j+=2){
            for(int k = -1; k <= 1; k+=2){
                try{
                    while(" ".equals(chessBoard[row + temp*j][col + temp*k])){
                        oldPiece = chessBoard[row + temp*j][col + temp*k];
                        chessBoard[row][col] = " ";
                        chessBoard[row + temp*j][col + temp*k] = "B";
                        if(kingSafe()){
                            list = list + row + col + (row+temp*j) + (col+temp*k) + oldPiece;
                        }
                        chessBoard[row][col] = "B";
                        chessBoard[row + temp*j][col + temp*k] = oldPiece;
                        temp++;
                    }
                    if(Character.isLowerCase(chessBoard[row+temp*j][col+temp*k].charAt(0))){
                        oldPiece = chessBoard[row + temp*j][col + temp*k];
                        chessBoard[row][col] = " ";
                        chessBoard[row + temp*j][col + temp*k] = "B";
                        if(kingSafe()){
                            list = list + row + col + (row+temp*j) + (col+temp*k) + oldPiece;
                        }
                        chessBoard[row][col] = "B";
                        chessBoard[row + temp*j][col + temp*k] = oldPiece;
                    }
                } catch(Exception e) {}
                temp = 1;
            }
        }
        return list;
   
    }
    
    public static String possibleQ(int i){
        String list = "";
        
        String oldPiece;
        int row = i/8;
        int col = i%8;
        int temp = 1;
        for(int j = -1; j <= 1; j++){
            for(int k = -1; k <= 1; k++){
                if(j!=0 || k!=0){
                    try{
                        while(" ".equals(chessBoard[row + temp*j][col + temp*k])){
                            oldPiece = chessBoard[row + temp*j][col + temp*k];
                            chessBoard[row][col] = " ";
                            chessBoard[row + temp*j][col + temp*k] = "Q";
                            if(kingSafe()){
                                list = list + row + col + (row+temp*j) + (col+temp*k) + oldPiece;
                            }
                            chessBoard[row][col] = "Q";
                            chessBoard[row + temp*j][col + temp*k] = oldPiece;
                            temp++;
                        }
                        if(Character.isLowerCase(chessBoard[row+temp*j][col+temp*k].charAt(0))){
                            oldPiece = chessBoard[row + temp*j][col + temp*k];
                            chessBoard[row][col] = " ";
                            chessBoard[row + temp*j][col + temp*k] = "Q";
                            if(kingSafe()){
                                list = list + row + col + (row+temp*j) + (col+temp*k) + oldPiece;
                            }
                            chessBoard[row][col] = "Q";
                            chessBoard[row + temp*j][col + temp*k] = oldPiece;
                        }
                    } catch(Exception e) {}
                temp = 1;
                }
            }
        }
        return list;
    }
    
    public static String possibleA(int i){
        String list = "";
        String oldPiece;
        int row = i/8;
        int col = i%8;
        for(int j = 0; j < 9; j++){
            if(j != 4) {
                try{
                    if(Character.isLowerCase(chessBoard[row - 1 + j/3][col - 1 + j%3].charAt(0)) || " ".equals(chessBoard[row-1+j/3][col-1+j%3])){
                        oldPiece = chessBoard[row - 1 + j/3][col - 1 + j%3];
                        chessBoard[row][col] = " ";
                        chessBoard[row - 1 + j/3][col - 1 + j%3] = "A";
                        int kingTemp = kingPositionC;
                        kingPositionC = i+(j/3)*8+j%3-9;
                        if(kingSafe()){
                            list = list + row + col + (row - 1 + j/3) + (col - 1 + j%3) + oldPiece;
                        }
                        chessBoard[row][col] = "A";
                        chessBoard[row - 1 + j/3][col - 1 + j%3] = oldPiece;
                        kingPositionC = kingTemp;
                    }
                } catch(Exception e) {

                }
            }
        }
        // need to add castling later!!
        return list;
    }
    
    //check if move makes king unsafe or not/in check
    public static boolean kingSafe(){
        //bishop & queen (diagonal)
        int temp = 1;
        for(int i = -1; i <= 1; i+=2){
            for(int j = -1; j <= 1; j+=2){
                try{
                    while(" ".equals(chessBoard[kingPositionC/8 + temp*i][kingPositionC + temp*j])){temp++;}
                    if(("b".equals(chessBoard[kingPositionC/8 + temp*i][kingPositionC%8 + temp*j])) || ("q".equals(chessBoard[kingPositionC/8 + temp*i][kingPositionC%8+temp*j])) ){
                    //danger
                    return false;
                    }
                } catch(Exception e){}
                temp = 1;
            }
        } 

        //rook
        for(int i = -1; i <= 1; i+=2){
            try{
                while(" ".equals(chessBoard[kingPositionC/8][kingPositionC%8 + temp*i])){
                    temp++;
                }
                     if(("r".equals(chessBoard[kingPositionC/8][kingPositionC%8 + temp*i])) || 
                        ("q".equals(chessBoard[kingPositionC/8][kingPositionC%8 + temp*i])) ){
                            //danger
                            return false;
                       }
            } catch(Exception e){}
            temp = 1;
             try{
                while(" ".equals(chessBoard[kingPositionC/8 + temp*i][kingPositionC%8])){
                    temp++;
                }
                     if(("r".equals(chessBoard[kingPositionC/8 + temp*i][kingPositionC%8])) || 
                        ("q".equals(chessBoard[kingPositionC/8 + temp*i][kingPositionC%8])) ){
                            //danger
                            return false;
                       }
            } catch(Exception e){}
            temp = 1;
    }

    //knight
    for(int i = -1; i <= 1; i+=2){
        for(int j = 0; j <= 1; j+=2){
            try{
                if("k".equals(chessBoard[kingPositionC/8 + i][kingPositionC%8+j*2])){
                    return false;
                }
            } catch(Exception e) {}
            try{
                if("k".equals(chessBoard[kingPositionC/8 + 2*i][kingPositionC%8+j])){
                    return false;
                }
            } catch(Exception e) {}

        }
    }

    //pawn
    if(kingPositionC >= 16){
        try{
            if("p".equals(chessBoard[kingPositionC/8 - 1][kingPositionC%8 - 1])){
                return false;
            }
        } catch(Exception e){}
        try{
            if("p".equals(chessBoard[kingPositionC/8 - 1][kingPositionC%8 + 1])){
                return false;
            }
        } catch(Exception e){}
    }
    
    //king
    for(int i = -1; i <= 1; i++){
        for(int j = 0; j <= 1; j++){
            if(i != 0 || j != 0){
                try{
                    if("a".equals(chessBoard[kingPositionC/8 + i][kingPositionC%8 + j])){
                        return false;
                    }
                } catch(Exception e) {}
            }
        }
    }


    //if no danger or flags
    return true;
    } 
    public static void main(String[] args){
        while(!"A".equals(chessBoard[kingPositionC/8][kingPositionC%8])) {kingPositionC++;}
        while(!"a".equals(chessBoard[kingPositionL/8][kingPositionL%8])) {kingPositionL++;}
        
        System.out.println(possibleMoves());
    }
}
