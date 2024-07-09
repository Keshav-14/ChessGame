
package chessgame;

public class Knight extends Piece{
    
    public Knight(char color, int x, int y){
        this.symbol = 'q';
        this.color = color;
        this.x = x;
        this.y = y;
        this.point = 9;
    }

    @Override
    public boolean move(ChessBoard board, int x, int y) {
        
        if(!isValid(x, y)){
            return false;
        }
        if(this.canKill(board, x, y)){
            this.kill(board, x, y);
            return true;
        }
        
        if(board.isFreeSquare(x, y)){
            this.changePosition(board, x, y);
            return true;
        }
        return false;
    }

    @Override
    protected boolean isValid(int x, int y) {
        return ((x == this.x + 1 || x == this.x - 1) 
                && (y == this.y + 2 || y == this.y - 2))
                || ((x == this.x + 2 || x == this.x - 2) 
                && (y == this.y + 1 || y == this.y - 1));
    }
    
}
