package pieces;

import board.Board;
import board.Cell;
import pieces.Pieces;

public class Knight extends Pieces {
    private String type;
    public Knight(String type,boolean isWhite,boolean isKilled){
        super(type,isWhite,isKilled);
        //his.type = type;
    }

    @Override
    public boolean rule(Board board, Cell start, Cell dest, String promote) {
        if (dest.getPiece()!=null&&dest.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int x = Math.abs(start.getRow() - dest.getRow());
        int y = Math.abs(start.getCol() - dest.getCol());
        if(x * y == 2){
            //pieces.Knight can only move 1 * 2 or 2 * 1
            return true;
        }
        else{
            return false;
        }
    }
}
