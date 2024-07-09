
package chessgame;


public class Rook extends Piece{
    
    public Rook(char color, int x, int y){
        this.symbol = 'r';
        this.color = color;
        this.point = 5;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean move(ChessBoard board, int x, int y) {
        
        if(!this.isValid(x, y))
            return false;
        if(!this.isValidPath(board, x, y))
            return false;
        
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

    private boolean isValidPath(ChessBoard chess, int x,int y){
        if(this.x - x == 0){
            // moving horizontally
            if(this.y > y){
                for(int i = this.y - 1 ; i > y ; i--){
                    if(chess.getPiece(x, i) != null){
                        return false;
                    }
                }
            } else {
                for(int i = this.y + 1 ; i < y ; i++){
                    if(chess.getPiece(x, i) != null){
                        return false;
                    }
                }
            }
            return true;
        } else {
            // moving vertically
            if(this.x > x){
                for(int i = this.x - 1 ; i > x ; i--){
                    if(chess.getPiece(i, y) != null){
                        return false;
                    }
                }
            } else {
                for(int i = this.x + 1 ; i < x ; i++){
                    if(chess.getPiece(i, y) != null){
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }
    
    @Override
    protected boolean isValid(int x, int y) {
        // diagonal movements are not allowed
        return (this.x - x == 0) ^ (this.y - y == 0);
    }

    @Override
    protected boolean canKill(ChessBoard board, int x, int y) {
        Piece temp = board.getPiece(x, y);
        return temp != null && temp.getColor() != this.color;
    }
}
