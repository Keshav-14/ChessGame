
package chessgame;

public class Bishop extends Piece {
    
    public Bishop(char color, int x, int y){
        this.symbol = 'b';
        this.color = color;
        this.point = 3;
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
        }
        
        if(board.isFreeSquare(x, y)){
            this.changePosition(board, x, y);
        }
        
        return false;
    }

    @Override
    protected boolean isValid(int x, int y) {
        int i, j;
        if(this.x < x && this.y < y){
            for(i = this.x, j = this.y; i <= 7 && j <= 7; i++, j++){
                if(i == x && j == y){
                    return true;
                }
            }
        }else if(this.x < x && this.y > y){
            for(i = this.x, j = this.y; i <= 7 && j >= 0; i++, j--){
                if(i == x && j == y){
                    return true;
                }
            }
        } else if(this.x > x && this.y > y){
            for(i = this.x, j = this.y ; i >= 0 && j <= 7 ; i--, j++){
                if(i == x && j == y){
                    return true;
                }
            }
        } else {
            for(i = this.x, j = this.y; i >= 0 && j >= 0 ; i--, j--){
                if(i == x && j == y){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPath(ChessBoard board, int x, int y) {
        int i, j;
        if(this.x < x && this.y < y){
            for(i = this.x, j = this.y; i < x && j < y; i++, j++){
                if(board.getPiece(i, j) != null){
                    return false;
                }
            }
        }else if(this.x < x && this.y > y){
            for(i = this.x, j = this.y; i < x && j > y; i++, j--){
                if(board.getPiece(i, j) != null){
                    return false;
                }
            }
        } else if(this.x > x && this.y > y){
            for(i = this.x, j = this.y ; i > x && j < y ; i--, j++){
                if(board.getPiece(i, j) != null){
                    return false;
                }
            }
        } else {
            for(i = this.x, j = this.y; i > x && j > y ; i--, j--){
                if(board.getPiece(i, j) != null){
                    return false;
                }
            }
        }
        return true;
    }
}
