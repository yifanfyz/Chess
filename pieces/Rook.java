package pieces;

import board.Board;
import board.Cell;
import pieces.Pieces;

public class Rook extends Pieces {
    //private String type;
    private boolean hasMoved = false;
    public Rook(String type,boolean isWhite,boolean isKilled){
        super(type,isWhite,isKilled);
        //this.type = type;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    public boolean getHasMoved(){
        return hasMoved;
    }
    @Override
    public boolean rule(Board board, Cell start, Cell dest, String promote) {
        if (dest.getPiece()!=null&&dest.getPiece().isWhite() == this.isWhite()) {
            System.out.println("dest has same color as start");
            return false;
        }
        int absX = Math.abs(start.getRow() - dest.getRow());
        int absY = Math.abs(start.getCol() - dest.getCol());
        int x = dest.getRow() - start.getRow();
        int y = dest.getCol() - start.getCol();
        if((absX==0&&absY!=0)||(absX!=0&&absY==0)){//rook can only move vertically or horizontally
            //check if there is any piece in between
            boolean ifAnyThingInBetween = false;
            for(int i = 1;i < Math.abs(absX - absY);i++){
                if(y==0&&x>0){  //dest is below target
                    ifAnyThingInBetween = !board.getCell(start.getRow()+i,start.getCol()).getIfEmpty();
                }
                if(y==0&&x<0){  //dest is above target
                    ifAnyThingInBetween = !board.getCell(start.getRow()-i,start.getCol()).getIfEmpty();
                }
                if(x==0&&y>0){  //dest is on the right of target
                    ifAnyThingInBetween = !board.getCell(start.getRow(),start.getCol()+i).getIfEmpty();
                }
                if(x==0&&y<0){
                    ifAnyThingInBetween = !board.getCell(start.getRow(),start.getCol()-i).getIfEmpty();
                }
                if(ifAnyThingInBetween) break;
            }
            if(!ifAnyThingInBetween) {
                setHasMoved(true);
                return true;
            }
            else{
                System.out.println("something in between");
                return false;
            }
        }
        else{   //rook is not moving vertically or horizontally
            return false;
        }
    }
}
