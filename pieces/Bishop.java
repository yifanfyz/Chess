package pieces;

import board.Board;
import board.Cell;
import pieces.Pieces;

public class Bishop extends Pieces {
    private String type;
    public Bishop(String type,boolean isWhite,boolean isKilled){
        super(type,isWhite,isKilled);
        //this.type = type;
    }

    @Override
    public boolean rule(Board board, Cell start, Cell dest, String promote) {
        //the destination already have a piece that has a same color
        if (dest.getPiece()!=null&&dest.getPiece().isWhite() == this.isWhite()) {
            System.out.println("the destination already have a piece that has a same color");

            return false;
        }

        int absX = Math.abs(start.getRow() - dest.getRow());
        int absY = Math.abs(start.getCol() - dest.getCol());

        int x = dest.getRow() - start.getRow();
        int y = dest.getCol() - start.getCol();
        if (absX == absY) {//bishop can only move diagonal
            //check if any piece between the target and dest cell
            boolean ifAnyThingInBetween = false;
            //System.out.println(absX);
            for(int i = 1;i < absX;i++){
                if(x>0&&y>0){   //dest is at bottom right
                    ifAnyThingInBetween = !board.getCell(start.getRow()+i,start.getCol()+i).getIfEmpty();
                }
                if(x>0&&y<0){   //dest is at bottom left
                    ifAnyThingInBetween = !board.getCell(start.getRow()+i,start.getCol()-i).getIfEmpty();
                }
                if(x<0&&y>0){   //dest is at top right
                    ifAnyThingInBetween = !board.getCell(start.getRow()-i,start.getCol()+i).getIfEmpty();
                }
                if(x<0&&y<0){   //dest is at top left
                    ifAnyThingInBetween = !board.getCell(start.getRow()-i,start.getCol()-i).getIfEmpty();
                }
                //System.out.println(board.getCell(start.getRow()+i,start.getCol()+i).getIfEmpty());
                //System.out.println(board.getCell(2,4).getName());
                if(ifAnyThingInBetween) break;  //once something in the way, break
            }
            if(!ifAnyThingInBetween){   //nothing in between
                System.out.println("nothing in between");
                return true;
            }
            else{   //something in between
                System.out.println("something in between");
                return false;
            }

        }
        else{   //bishop is not moving diagonally
            System.out.println("bishop is not moving diagonally");
            return false;
        }
    }
}
