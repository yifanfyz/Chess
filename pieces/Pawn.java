package pieces;

import board.Board;
import board.Cell;
import pieces.Pieces;

public class Pawn extends Pieces {
    //private String type;
    private boolean promoted = false;
    private boolean isFirstmove = true;


    public Pawn(String type, boolean isWhite, boolean isKilled) {
        super(type, isWhite, isKilled);
        //this.type = type;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public boolean getPromoted() {
        return promoted;
    }

    public void setFirstmove(boolean firstmove) {
        isFirstmove = firstmove;
    }

    public boolean getFirstmove() {
        return isFirstmove;
    }





    @Override
    public boolean rule(Board board, Cell start, Cell dest, String promote) {
        boolean returnValue;
        if (dest.getPiece() != null && dest.getPiece().isWhite() == this.isWhite()) {
            //dest has same color as start
            returnValue = false;
        }


        int x = dest.getRow() - start.getRow();             //pawn can only move forward
        int y = dest.getCol() - start.getCol();
        int absY = Math.abs(start.getCol() - dest.getCol());
        if (this.isWhite()) {
            //it's a white pawn, white pawn can only move from low row to high row
            if (x < 0 || absY > 1 || x > 2) {
                //pawn is moving backward or pawn moving 2 col at once or pawn move 2 plus cells at once
                returnValue =  false;
            } else {
                if (this.isFirstmove) {
                    //pawn's first move

                    if (absY == 0 && (x == 1 || x == 2)) {
                        if(x==1){       //check anything in between
                            if(!dest.getIfEmpty()) {
                                returnValue = false;
                            }
                            else{
                                this.setFirstmove(false);
                                returnValue = true;}
                        }
                        else { //x = 2
                            //in first move, if move forward, can't have any piece in between
                            if(!board.getCell(start.getRow()+1,start.getCol()).getIfEmpty()) {
                                returnValue =  false;}
                            else if(!dest.getIfEmpty()) {
                                returnValue =  false;}
                            else{
                                board.getCell(start.getRow()+1,start.getCol()).setEnpassingRound();
                                int row = start.getRow()+1;
                                System.out.println("row: "+row+"  col: "+start.getCol());
                                this.setFirstmove(false);
                                returnValue =  true;
                            }

                        }


                    } else if((absY == 1 && x == 1)) {
                        //capture diagnosisly

                        if(dest.getIfEmpty()){
                            returnValue = false;}
                        else{
                            returnValue =  true;}
                    }else{
                        returnValue =  false;
                    }


                }

                else {
                    //not first move
                    if ((absY == 1 && x == 1)||(absY==0&&x==1)) {  //valid move
                        if(absY == 1 ){    //斜着走
                            if(dest.getIfEmpty()){  //斜着走必须吃
                                if(dest.getEnpassingRound()==2){
                                    returnValue = true;
                                }
                                else{
                                    returnValue =  false;
                                }

                            }
                            else{
                                returnValue =  true;
                            }
                        }
                        else{   //absY==0&&x==1
                            if(dest.getIfEmpty()){
                                returnValue =  true;
                            }
                            else{
                                returnValue =  false;
                            }

                        }
                        if (dest.getRow() == 7) {
                            //pawn reaches the top row
                            if (promote == null) {
                                setPromoteRole("Queen");
                            } else {
                                if (promote.contains("N")) setPromoteRole("Knight");
                                if (promote.contains("B")) setPromoteRole("Bishop");
                                if (promote.contains("R")) setPromoteRole("Rook");

                            }

                            setPromoted(true);
                        }
                    } else {   //
                        returnValue =  false;
                    }

                }

            }
            return returnValue;

        } else {
            //black pawn can only move from high row to low row
            if (x > 0 || absY > 1 || x < -2) {
                //pawn is moving backward or pawn moving 2 col at once or pawn move 2 plus cells at once
                returnValue =  false;
            }
            if (this.isFirstmove) {
                if (absY == 0 && (x == -1 || x == -2)) {
                    if(x == -1){
                        if(!dest.getIfEmpty()) returnValue = false;
                        else{
                            this.setFirstmove(false);
                            returnValue = true;
                        }


                    }
                    else { //x = -2
                        if(!board.getCell(start.getRow()-1,start.getCol()).getIfEmpty()) returnValue = false;
                        else if(!dest.getIfEmpty()) returnValue = false;
                        else{
                            board.getCell(start.getRow()-1,start.getCol()).setEnpassingRound();
                            this.setFirstmove(false);
                            returnValue = true;
                        }

                    }

                }
                else if(absY == 1&&x == -1){
                    if(dest.getIfEmpty()){
                        returnValue = true;
                    }
                    else{
                        returnValue = false;
                    }
                }
                else {
                    returnValue = false;
                }
            }
            else {  //not black pawn's first move
                if ((absY == 1 && x == -1)||(absY==0&&x==-1)) {  //valid move
                    if(absY == 1){
                        if(dest.getIfEmpty()){  //斜着走必须吃
                            if(dest.getEnpassingRound()==2){
                                returnValue = true;
                            }
                            else{
                                returnValue =  false;
                            }

                        }
                        else{
                            returnValue =  true;
                        }
                    }
                    else{   //absY==0&&x==-1
                        if(dest.getIfEmpty()){
                            returnValue =  true;
                        }
                        else{
                            returnValue =  false;
                        }

                    }

                    if (dest.getRow() == 0) {
                        //pawn reaches the top row
                        if (promote == null) {
                            setPromoteRole("Queen");
                        } else {
                            if (promote.contains("N")) setPromoteRole("Knight");
                            if (promote.contains("B")) setPromoteRole("Bishop");
                            if (promote.contains("R")) setPromoteRole("Rook");

                        }
                        setPromoted(true);
                    }

                } else {   //invalid move
                    returnValue = false;
                }

            }
            return returnValue;
        }


    }
}
