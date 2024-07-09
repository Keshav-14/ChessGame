
package chessgame;

import java.util.Scanner;


public class ChessBoard {
    
    private Piece [][] board;
    private int whitePoint;
    private int blackPoint;
    
    public ChessBoard(){
        board = new Piece[8][8];
        
        this.whitePoint = 0;
        this.blackPoint = 0;
        // arrange pawns
        for(int i = 0 ; i < 8 ; i++){
            this.board[1][i] = new Pawn('w', 1, i);
            this.board[6][i] = new Pawn('b', 6, i);
        }
        
        // arrange rooks
        this.board[0][0] = new Rook('w', 0, 0);
        this.board[0][7] = new Rook('w', 0, 7);

        this.board[7][7] = new Rook('b', 7, 7);
        this.board[7][0] = new Rook('b', 7, 0);
        
        // arrange Bishop
        this.board[0][2] = new Bishop('w', 0, 2);
        this.board[0][5] = new Bishop('w', 0, 5);
        this.board[7][2] = new Bishop('b', 7, 2);
        this.board[7][5] = new Bishop('b', 7, 5);
        
        // arrange Queen
        this.board[0][4] = new Queen('w', 0, 4);
        this.board[7][4] = new Queen('b', 7, 4);
        
        // arrange Knight
        this.board[0][1] = new Knight('w', 0, 2);
        this.board[0][6] = new Knight('w', 0, 6);
        this.board[7][1] = new Knight('b', 7, 2);
        this.board[7][6] = new Knight('b', 7, 6);
        
        // arrange King
        this.board[0][3] = new King('w', 0, 3);
        this.board[7][3] = new King('b', 7, 3);
    }
    
    public void displayBoard(){
        System.out.print("  ");
        for(int i = 0 ; i < 8 ; i++){
            System.out.print(" " + i + " ");
        }
        System.out.println();
        for(int i = 0; i < 8 ; i++){
            System.out.print(i + " ");
            for(int j = 0; j < 8 ; j++){
                if(board[i][j] == null)
                    System.out.print(" * ");
                else 
                    System.out.print(" " + this.board[i][j].getSymbol() + " ");
            }
            System.out.print(" "+ i);
            System.out.println();
        }
        System.out.print("  ");
        for(int i = 0 ; i < 8 ; i++){
            System.out.print(" " + i + " ");
        }
    }
    
    public void increamentPoints(char color, int points){
        if(color == 'w')
            this.whitePoint += points;
        else 
            this.blackPoint += points;
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
    
    public boolean isFreeSquare(int x, int y){
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
