
package chessgame;

public class Main {
    
    public static void main(String[] args){
        ChessBoard chess = new ChessBoard();
        
        chess.movePiece(1, 4, 3, 4);
        chess.displayBoard();
    }
}
