
package chessgame;

public class Piece {
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
}
