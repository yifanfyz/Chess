package board;
import pieces.*;

/**
 * Cells represent each block in the chess board. Each board.Cell contain the coordinate
 * of row,col,col character such as 'a' and the name of the piece such as "wp","bk".
 * And each board.Cell could store a piece.
 * @author yl1160
 */
public class Cell implements Cloneable{
    private char black;     //if it's a black block
    private boolean ifEmpty;
    private int row;
    private int col;
    private char colC;
    private Pieces piece;    //role
    private String name;        //name is only for display
    private int enpassingRound;
    /*enpassingRound is set to one when Pawn move over this cell in its first step
    once enpassingRound is set to one, every round it increments.
    */
    public void setEnpassingRound(){
        this.enpassingRound = this.enpassingRound+ 1;
    }
    public void setEnpassingRound(int i){enpassingRound = i;}
    public int getEnpassingRound(){
        return enpassingRound;
    }
    public void resetEnpassingRound(){
        enpassingRound = 0;
    }
    public void setName(String name){
        this.ifEmpty = false;
        this.name = name;
    }
    public Cell(int r, int c, boolean isBlack){
        setRow(r);
        setCol(c);
        this.ifEmpty = true;
        if (isBlack){
            this.black = '#';
        }else{
            this.black =  ' ';
        }
    }
    //        public board.Cell(int r, int c, String n){
//            this.row = r;
//            setCol(c);
//            this.name = n;
//            this.ifEmpty = false;
//        }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col){
        this.col = col;
        if(col == 0) this.colC = 'a';
        if(col == 1) this.colC = 'b';
        if(col == 2) this.colC = 'c';
        if(col == 3) this.colC = 'd';
        if(col == 4) this.colC = 'e';
        if(col == 5) this.colC = 'f';
        if(col == 6) this.colC = 'g';
        if(col == 7) this.colC = 'h';
    }
    public void setPiece(Pieces name){
        this.ifEmpty = false;
        this.piece = name;
    }
    public void setIfEmpty(boolean ifEmpty){
        this.ifEmpty = ifEmpty;
    }
    public void removePiece(){
        this.ifEmpty = true;
        this.piece = null;
    }
    public void setBlack(char black){this.black = black;}
    public Pieces getPiece(){
        return piece;
    }
    public String getName(){
        return name;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public char getColC(){
        return colC;
    }
    public boolean getIfEmpty(){
        return ifEmpty;
    }
    public char getBlack(){
        return black;
    }
    @Override
    public Cell clone(){
        try {
            Cell cell = (Cell)super.clone();
            //cell.piece = piece.clone();
            return cell;
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }

}