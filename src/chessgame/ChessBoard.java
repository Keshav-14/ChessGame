
package chessgame;

import java.util.Scanner;


public class ChessBoard {
    
    private Piece [][] board;
    private int whitePoint;
    private int blackPoint;
    public ChessBoard(){
        board = new Piece[8][8];
        for(int i = 0 ; i < 8 ; i++){
            this.board[1][i] = new Pawn('w', 1, i);
            this.board[6][i] = new Pawn('b', 6, i);
        }
    }
    
    public void displayBoard(){
        for(int i = 0; i < 8 ; i++){
            for(int j = 0; j < 8 ; j++){
                if(board[i][j] == null)
                    System.out.print(" * ");
                else 
                    System.out.print(" " + this.board[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }
    
    public Piece getPiece(int x, int y){
        return this.board[x][y];
    }
    
    public void setPiece(int x, int y, Piece p){
        this.board[x][y] = p;
    }
    
    private boolean isValidSquare(int x, int y){
        // check whether the square is occupied or not
        return this.board[x][y] == null;
    }
    
    private boolean isOpponent(char myColor, int x, int y){
        return myColor == this.board[x][y].getColor();
    }
    
    public boolean movePiece(int x, int y, int newx, int newy){
        
        // add auto promote prompt
        
        if(this.isValidSquare(newx, newy)){
            // the square is free
            this.board[newx][newy] = this.board[x][y];
            this.board[x][y] = null;
            return true;
        } else {
            // the square is occupied
            char myColor = board[x][y].getColor();
            if(isOpponent(myColor, newx, newy)){
                // same side so move not possible
                return false;
            } else {
                this.killPiece(myColor, x, y, newx, newy);
                return true;
            }
        }
    }
    
    private void killPiece(char myColor, int x, int y, int targetx, int targety){
        int point = this.board[x][y].getPoint();
        if(myColor == 'w'){
            this.whitePoint += point;
        } else {
            this.blackPoint += point;
        }
        this.board[targetx][targety] = this.board[x][y];
        this.board[x][y] = null;
    }
    
    private boolean canPromote(int x, int y){
        if(Pawn.class.isInstance(this.board[x][y])){
            char myColor = this.board[x][y].getColor();
            if(myColor == 'w'){
                return x == 7;
            } else {
                return x == 0;
            }
        }
        return false;
    }
    
    public void promotePiece(int x, int y){
        if(canPromote(x, y)){
            System.out.print("Promote to : ?");
            Scanner in = new Scanner(System.in);
            int ch = in.nextInt();
            switch(ch){
                case 1 -> {
                    // Queen : create new instance of Queen and delete the pawn object

                }
                case 2 -> {
                    // Rook

                }
                case 3 -> {
                    // Knight

                }
                case 4 -> {
                    // bishop

                }
            }
        }
    }
}
