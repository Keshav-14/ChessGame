
package chessgame;

// actually i can merge isvalid() and isValidPath()!!

public abstract class Piece {
    protected char symbol;
    protected char color; // w --> white, b --> black
    protected int point;
    protected int x;
    protected int y;
    
    public char getColor(){
        return this.color;
    }
    
    public int getPoint(){
        return this.point;
    }
    
    public char getSymbol(){
        return this.symbol;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    abstract public boolean move(ChessBoard board, int x, int y);
    abstract protected boolean isValid(int x, int y);
    
    protected boolean canKill(ChessBoard board, int x, int y){
        Piece temp = board.getPiece(x, y);
        return temp != null && temp.getColor() != this.color;
    }
    
    protected void kill(ChessBoard board, int x, int y){
        // x, y => destination position
        board.setPiece(x, y, this); // new location
        board.setPiece(this.x, this.y, null); // old location -> null
        this.x = x;
        this.y = y;
        board.increamentPoints(this.color, this.point);
    }
    
    protected void changePosition(ChessBoard board, int newx, int newy){
        board.setPiece(newx, newy, this); // new location
        board.setPiece(this.x, this.y, null); // old location -> null
        this.x = newx;
        this.y = newy;
    }
}
