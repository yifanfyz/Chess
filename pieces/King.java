package pieces;

import board.Board;
import board.Cell;
import pieces.Pieces;

public class King extends Pieces {

    private boolean hasMoved = false;
    private boolean castlingDone = false; //king can only castling once
    public King(String type,boolean isWhite,boolean isKilled){
        super(type,isWhite,isKilled);

    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    public boolean isCastlingDone() {
        return castlingDone;
    }
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }
    public boolean getHasMoved(){
        return this.hasMoved;
    }
    @Override
    public boolean rule(Board board, Cell start, Cell dest, String promote) {
        if (dest.getPiece()!=null&&dest.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int absX = Math.abs(start.getRow() - dest.getRow());
        int absY = Math.abs(start.getCol() - dest.getCol());

        if(absX + absY == 1){
            //pieces King can move one step at a time
            setHasMoved(true);
            return true;
        }
        else if(absX==0&&absY==2&&!hasMoved&&!castlingDone){      //check if this is a castling move
            System.out.println("this is a castling move");
            if(isValidCastling(board,start,dest)){          //valid Castling move
                //this.setCastlingDone(true);
                return true;
            }
            else{                                           //invalid castling move
                return false;
            }
        }
        else{
            return false;
        }
    }
    public boolean isValidCastling(Board board,Cell start,Cell dest){
        int y = dest.getCol() - start.getCol();
        boolean rookInP = false;
        boolean anythingInBetween = false;
        //check if rook is in corresponding position
        if(y==2&&board.getCell(dest.getRow(),dest.getCol()+1).getPiece().getType().equals("Rook")){
            //right castling
            System.out.println("right castling");
            rookInP = true;
            for(int i = 1;i < 3;i++){
                anythingInBetween = !board.getCell(start.getRow(),start.getCol()+i).getIfEmpty();
                if(anythingInBetween) break;
            }
        }
        else if(y==-2&&board.getCell(dest.getRow(),dest.getCol()-2).getPiece().getType().equals("Rook")){
            //left castling
            rookInP = true;
            for(int i = 1;i < 4;i++){
                anythingInBetween = !board.getCell(start.getRow(),start.getCol()-i).getIfEmpty();
                if(anythingInBetween) break;
            }
        }
        if(rookInP&&!anythingInBetween){    //rook is in position, and nothing in between
            System.out.println("can castling");
            setCastlingDone(true);
            setHasMoved(true);
            return true;
        }
        else{   //rook not in correct position
            return false;
        }


    }

}
