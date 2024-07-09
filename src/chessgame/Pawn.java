
package chessgame;


public class Pawn extends Piece{
    
    public Pawn(char color, int x, int y){
        this.symbol = 'p';
        this.color = color;
        this.point = 1;
        this.x = x;
        this.y = y;
    }
    
    @Override
    protected boolean isValid(int x, int y){
        // checks only valid moves, this does not check for a kill!
        if(Math.abs(this.y - y) != 0){
            return false;
        }
        
        int diff = Math.abs(this.x - x);
        if(this.y != y){
            // may be trying to kill
            return false;
        }
        if((this.x == 1 || this.x == 6 ) && diff == 2){
            return true;
        } else if(diff == 1){
            return true;
        }
        return false;
    }
    
    private boolean isGoingBack(int x){
        if(this.color == 'w'){
            return this.x > x; // going back
        } else {
            return this.x < x; // going back
        }
    }
    
    @Override
    public boolean move(ChessBoard board, int newx, int newy){
        
        // am i going back ?
        if(this.isGoingBack(newx))
            return false;
        
        // check if i can kill the piece
        if(this.canKill(board, newx, newy)){
            this.kill(board, newx, newy);
            return true;
        }
        
        // check for valid move
        if(isValid(newx, newy) && board.isFreeSquare(newx, newy)){
            this.changePosition(board, newx, newy);
        }
        return false;
    }
    
    @Override
    protected boolean canKill(ChessBoard board, int x, int y){
        
        if((y == this.y - 1 || y == this.y + 1) && !this.isGoingBack(x) && (this.x - 1 == x || this.x + 1 == x)){
            Piece temp = board.getPiece(x, y);
            if(temp != null && temp.getColor() != this.color){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void kill(ChessBoard board, int x, int y){
        // x, y => destination position
        board.setPiece(x, y, this); // new location
        board.setPiece(this.x, this.y, null); // old location -> null
        this.x = x;
        this.y = y;
        board.increamentPoints(this.color, this.point);
    }
}
