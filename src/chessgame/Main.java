
package chessgame;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args){
        ChessBoard chess = new ChessBoard();
        Scanner in = new Scanner(System.in);
        
        while(true){
            chess.displayBoard();
            System.out.println("Enter the position of the pawn to move: ");
            int x = in.nextInt();
            int y = in.nextInt();
            Piece temp = chess.getPiece(x, y);
            System.out.print(temp.getX() + ", " + temp.getY());
            System.out.println(temp.getSymbol());
            System.out.print("Enter the new Location : ");
            x = in.nextInt();
            y = in.nextInt();
            
            System.out.println(temp.move(chess, x, y));
            System.out.println(temp.getX() + ", " + temp.getY());
        }
    }
}
