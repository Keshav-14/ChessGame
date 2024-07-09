
package chessgame;

public class King extends Piece{
    
    public King(char color, int x, int y){
        this.symbol = 'k';
        this.color = color;
        this.point = Integer.MAX_VALUE; // once the king is defeated the game is over...
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean move(ChessBoard board, int x, int y) {
        if(!isValid(x, y))
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
        if(this.x == x){
            // horizontal movement
            return (this.y == y - 1 || this.y == y + 1);
        } else if(this.y == y){
            // vertical movement
            return (this.x == x - 1 || this.x == x + 1);
        } else {
            // diagonal movement
            if(this.x - 1 == x){
                if(this.y - 1 == y){
                    return true;
                } else if(this.y + 1 == y){
                    return true;
                }
                return false;
            } else if (this.x + 1 == x){
                if(this.y + 1 == y){
                    return true;
                } else if(this.x - 1 == x){
                    return true;
                }
                return false;
            }
            return false;
        }
    }
    
    public boolean kingOnCheck(ChessBoard board){
        // rook's search => determine check by rook or queen
        if(!this.rookPathSearch(board)){
            // search diagonal for bishop, queen and pawn
            if(!this.bishopPathSearch(board)){
                // search knight's path
                return this.knightPathSearch(board);
            }
        }
        return true;   // no check
    }
    
    private boolean rookPathSearch(ChessBoard board){
        int i;
        for(i = this.y ; i >= 0 ; i--){
            Piece temp = board.getPiece(this.x, i);
            if(temp != null && temp.getColor() != this.color && 
                    (temp.getSymbol() == 'r' || temp.getSymbol() == 'q')){
                return true;    // king is on check
            }
        }
        for(i = this.y ; i <= 7 ; i++){
            Piece temp = board.getPiece(this.x, i);
            if(temp != null && temp.getColor() != this.color && 
                    (temp.getSymbol() == 'r' || temp.getSymbol() == 'q')){
                return true;    // king is on check
            }
        }
        for(i = this.x ; i >= 0 ; i--){
            Piece temp = board.getPiece(this.x, i);
            if(temp != null && temp.getColor() != this.color && 
                    (temp.getSymbol() == 'r' || temp.getSymbol() == 'q')){
                return true;    // king is on check
            }
        }
        for(i = this.x ; i <= 7 ; i++){
            Piece temp = board.getPiece(this.x, i);
            if(temp != null && temp.getColor() != this.color && 
                    (temp.getSymbol() == 'r' || temp.getSymbol() == 'q')){
                return true;    // king is on check
            }
        }
        return false; // no check on king
    }

    private boolean bishopPathSearch(ChessBoard board) {
        int i, j;
        for(i = this.x + 1 , j = this.y + 1; i <= 7 && j <= 7; i++, j++){
            Piece temp = board.getPiece(i, j);
            if(temp != null && temp.getColor() != this.color && 
                    (temp.getSymbol() == 'b' || temp.getSymbol() == 'q')){
                return true;    // king is on check
            }
        }
        
        for(i = this.x + 1, j = this.y - 1; i <= 7 && j >= 0; i++, j--){
            Piece temp = board.getPiece(i, j);
            if(temp != null && temp.getColor() != this.color && 
                    (temp.getSymbol() == 'b' || temp.getSymbol() == 'q')){
                return true;    // king is on check
            }
        }
        
        for(i = this.x - 1, j = this.y + 1; i >= 0 && j <= 7; i--, j++){
            Piece temp = board.getPiece(i, j);
            if(temp != null && temp.getColor() != this.color && 
                    (temp.getSymbol() == 'b' || temp.getSymbol() == 'q')){
                return true;    // king is on check
            }
        }
        
        for(i = this.x - 1, j = this.y - 1; i >= 0 && j >= 0 ; i--, j--){
            Piece temp = board.getPiece(i, j);
            if(temp != null && temp.getColor() != this.color && 
                    (temp.getSymbol() == 'b' || temp.getSymbol() == 'q')){
                return true;    // king is on check
            }
        }

        return false; // no check on king
    }

    private boolean knightPathSearch(ChessBoard board) {
//        return ((x == this.x + 1 || x == this.x - 1) 
//                && (y == this.y + 2 || y == this.y - 2))
//                || ((x == this.x + 2 || x == this.x - 2) 
//                && (y == this.y + 1 || y == this.y - 1));
        Piece temp = board.getPiece(this.x + 1, this.y + 2);
        if(temp != null && temp.getColor() != this.color && 
                (temp.getSymbol() == 'k')){
            return true;    // king is on check
        }
        temp = board.getPiece(this.x + 1, this.y - 2);
        if(temp != null && temp.getColor() != this.color && 
            (temp.getSymbol() == 'k')){
            return true;    // king is on check
        }
        temp = board.getPiece(this.x - 1, this.y - 2);
        if(temp != null && temp.getColor() != this.color && 
            (temp.getSymbol() == 'k')){
            return true;    // king is on check
        }
        temp = board.getPiece(this.x - 1, this.y + 2);
        if(temp != null && temp.getColor() != this.color && 
            (temp.getSymbol() == 'k')){
            return true;    // king is on check
        }
        temp = board.getPiece(this.x + 2, this.y - 1);
        if(temp != null && temp.getColor() != this.color && 
            (temp.getSymbol() == 'k')){
            return true;    // king is on check
        }
        temp = board.getPiece(this.x + 2, this.y + 1);
        if(temp != null && temp.getColor() != this.color && 
            (temp.getSymbol() == 'k')){
            return true;    // king is on check
        }
        temp = board.getPiece(this.x - 2, this.y - 1);
        if(temp != null && temp.getColor() != this.color && 
            (temp.getSymbol() == 'k')){
            return true;    // king is on check
        }
        temp = board.getPiece(this.x - 2, this.y + 1);
        if(temp != null && temp.getColor() != this.color && 
            (temp.getSymbol() == 'k')){
            return true;    // king is on check
        }
        return false;
    }
}
