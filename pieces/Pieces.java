package pieces;

import board.Board;
import board.Cell;

/**
 * an abstract class for all piece types such as pieces.Bishop
 * @author Yifei Li, yl1160,
 *         Yifan Zhang, yz745
 *
 */
public abstract class Pieces implements Cloneable{
    private String type;
    private boolean isWhite;
    private boolean isKilled;
    private String promoteRole = null;
    /**
     * constructor of piece class that initialize a piece with the color status and capture status
     * @param isWhite isWhite indicate weather a piece is white or not
     * @param isKilled isKilled indicate weather a piece is killed or not
     * @author yifeili
     */
    public Pieces(String type,boolean isWhite,boolean isKilled){
        this.type = type;
        this.isWhite = isWhite;
        this.isKilled = isKilled;
    }
    public void setType(String type){this.type = type;}
    public String getType(){return type;}
    public void setWhite(boolean white) {
        isWhite = white;
    }
    public boolean isWhite(){
        return isWhite;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public boolean isKilled() {
        return isKilled;
    }

    /**
     * an abstract method that detect weather a movement is valid for certain piece.
     * @param board board pass the current game board
     * @param start start pass the starting cell for certain movement
     * @param dest  dest pass the end cell for certain movement
     * @return true if the movement is valid, false otherwise.
     */
    public abstract boolean rule(Board board, Cell start, Cell dest, String promote);
    public Pieces clone(){
        try {
            Pieces piece = (Pieces)super.clone();

            return piece;
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
    public String getPromoteRole() {
        return promoteRole;
    }
    public void setPromoteRole(String promoteRole) {
        this.promoteRole = promoteRole;
    }
}
