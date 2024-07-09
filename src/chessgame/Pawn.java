
package chessgame;


public class Pawn extends Piece{
    
    public Pawn(char color, int x, int y){
        this.symbol = 'p';
        this.color = color;
        this.point = 1;
        this.x = x;
        this.y = y;
    }
    
    // call like board[x][y].move()
    public void move(ChessBoard board, int x, int y){
        // check for valid move

        board.setPiece(this.x, this.y, null);
        board.setPiece(x, y, this);
    }
}
