
package chessgame;


public class Queen extends Piece{
    
    public Queen(char color, int x, int y){
        this.symbol = 'q';
        this.color = color;
        this.x = x;
        this.y = y;
        this.point = 9;
    }

    @Override
    public boolean move(ChessBoard board, int x, int y) {
        if(!isValid(x, y))
            return false;
        if(!isValidPath(board, x, y))
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

    @Override
    protected boolean isValid(int x, int y) {
        if((this.x - x == 0) ^ (this.y - y == 0)){
            return true;
        } else {
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
    }
    
    private boolean isValidPath(ChessBoard chess, int x, int y){
        if(this.x == x){
               // rook's horizontal movement
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
        } else if(this.y == y) {
            // rook's vertical movement
            if(this.x > x){
                for(int i = this.x - 1 ; i > x ; i--){
                    if(chess.getPiece(i, y) != null){
                        return false;
                    }
                }
                return true;
            } else {
                for(int i = this.x + 1 ; i < x ; i++){
                    if(chess.getPiece(i, y) != null){
                        return false;
                    }
                }
                return true;
            }
        } else {
            // bishop's movement
            int i, j;
            if(this.x < x && this.y < y){
                for(i = this.x, j = this.y; i < x && j < y; i++, j++){
                    if(chess.getPiece(i, j) != null){
                        return false;
                    }
                }
            }else if(this.x < x && this.y > y){
                for(i = this.x, j = this.y; i < x && j > y; i++, j--){
                    if(chess.getPiece(i, j) != null){
                        return false;
                    }
                }
            } else if(this.x > x && this.y > y){
                for(i = this.x, j = this.y ; i > x && j < y ; i--, j++){
                    if(chess.getPiece(i, j) != null){
                        return false;
                    }
                }
            } else {
                for(i = this.x, j = this.y; i > x && j > y ; i--, j--){
                    if(chess.getPiece(i, j) != null){
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
