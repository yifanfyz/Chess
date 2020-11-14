package chess;
import board.*;
import pieces.Bishop;
import pieces.Knight;
import pieces.Queen;
import pieces.Rook;

import java.util.Scanner;

/**
 * Chess class represent the actual game include initialize an empty board and manage the game play
 */
public class Chess {
    /**
     * ifValidMove method return a boolean value that indicate whether a command is valid
     * @param table table passes through the current game board
     * @param target cell that need to be moved
     * @param dest  destination cell
     * @param isWhite boolean value that indicate the color of player
     * @return boolean value that indicate whether a command is valid
     */
    public static boolean ifValidMove(Board table, String target, String dest,boolean isWhite, String promote){
        //relate input string to actual cell
        char TtempC = target.charAt(0);
        int TtempR = Integer.parseInt(String.valueOf(target.charAt(1)))-1;
        char DtempC = dest.charAt(0);
        int DtempR = Integer.parseInt(String.valueOf(dest.charAt(1)))-1;

        Cell targetCell = table.getCell(TtempR,TtempC-'a'+1-1);
        Cell destCell = table.getCell(DtempR,DtempC-'a'+1-1);
        //check if the target cell color match
        if(isWhite == targetCell.getPiece().isWhite()){

            return targetCell.getPiece().rule(table,targetCell,destCell,promote);
        }
        else{
            System.out.println("move a wrong color");
            return false;      //move a wrong color piece
        }

    }
    public static void boardCopy(Board table,Board target){
        int size = 8;
        for(int i = 0;i < size;i++){
            for(int j = 0;j < size;j++){
                //Cell temp = new Cell();
            }
        }
    }
    public static void move(Board table, String target, String dest){
        char TtempC = target.charAt(0);
        int TtempR = Integer.parseInt(String.valueOf(target.charAt(1)))-1;
        char DtempC = dest.charAt(0);
        int DtempR = Integer.parseInt(String.valueOf(dest.charAt(1)))-1;


        Cell targetCell = table.getCell(TtempR,TtempC-'a'+1-1).clone();
        Cell destCell = table.getCell(DtempR,DtempC-'a'+1-1).clone();

        table.setCell(targetCell,DtempR,DtempC-'a'+1-1);
        table.getCell(DtempR,DtempC-'a'+1-1).setCol(DtempC-'a'+1-1);
        table.getCell(DtempR,DtempC-'a'+1-1).setRow(DtempR);
        table.getCell(DtempR,DtempC-'a'+1-1).setBlack(destCell.getBlack());
        table.getCell(DtempR,DtempC-'a'+1-1).setEnpassingRound(destCell.getEnpassingRound());

        System.out.println("row:"+table.getCell(DtempR,DtempC-'a'+1-1).getRow());
        System.out.println("col:"+table.getCell(DtempR,DtempC-'a'+1-1).getCol());
        System.out.println(table.getCell(DtempR,DtempC-'a'+1-1).getIfEmpty());
        table.getCell(TtempR,TtempC-'a'+1-1).removePiece();
        if(table.getCell(DtempR,DtempC-'a'+1-1).getPiece().getType().equals("Pawn")){
            //check if the pawn get promoted
            if(table.getCell(DtempR,DtempC-'a'+1-1).getPiece().getPromoteRole()!=null){
                String promotion = table.getCell(DtempR,DtempC-'a'+1-1).getPiece().getPromoteRole();
                boolean isWhite = table.getCell(DtempR,DtempC-'a'+1-1).getPiece().isWhite();
                if(promotion.equals("Queen")){
                    table.getCell(DtempR,DtempC-'a'+1-1).setPiece(new Queen("Queen",isWhite,false));
                    if(isWhite) {
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("wQ");
                    }
                    else{
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("bQ");
                    }
                }
                if(promotion.equals("Knight")){
                    table.getCell(DtempR,DtempC-'a'+1-1).setPiece(new Knight("Knight",isWhite,false));
                    if(isWhite) {
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("wN");
                    }
                    else{
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("bN");
                    }
                }
                if(promotion.equals("Bishop")){
                    table.getCell(DtempR,DtempC-'a'+1-1).setPiece(new Bishop("Bishop",isWhite,false));
                    if(isWhite) {
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("wB");
                    }
                    else{
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("bB");
                    }
                }
                if(promotion.equals("Rook")){
                    table.getCell(DtempR,DtempC-'a'+1-1).setPiece(new Rook("Rook",isWhite,false));
                    if(isWhite) {
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("wR");
                    }
                    else{
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("bR");
                    }
                }
            }
            //check if empassing happened
            if(table.getCell(DtempR,DtempC-'a'+1-1).getEnpassingRound()==2){
                //empassing
                boolean isWhite = table.getCell(DtempR,DtempC-'a'+1-1).getPiece().isWhite();
                if(isWhite){    //last move was black move
                    table.getCell(DtempR-1,DtempC-'a'+1-1).removePiece();
                    table.getCell(DtempR-1,DtempC-'a'+1-1).resetEnpassingRound();
                }
                else{       //last move was white move
                    table.getCell(DtempR+1,DtempC-'a'+1-1).removePiece();
                    table.getCell(DtempR+1,DtempC-'a'+1-1).resetEnpassingRound();
                }

            }

        }
        if(table.getCell(DtempR,DtempC-'a'+1-1).getPiece().getType().equals("King")){
            //check if castling

            int stepMove = Math.abs(DtempC-'a'+1-1-(TtempC-'a'+1-1));
            int y = DtempC-'a'+1-1-(TtempC-'a'+1-1);
            if(stepMove==2){
                //castling, check direction
                System.out.println("castling");
                if(y == 2){       //right cast

                    Cell castRook = table.getCell(DtempR,DtempC-'a'+1-1+1).clone(); //rook copy
                    Cell destTemp = table.getCell(DtempR,DtempC-'a'+1-1-1).clone(); //dest copy
                    table.setCell(castRook,DtempR,DtempC-'a'+1-1-1);
                    table.getCell(DtempR,DtempC-'a'+1-1-1).setRow(DtempR);
                    table.getCell(DtempR,DtempC-'a'+1-1-1).setCol(DtempC-'a'+1-1-1);
                    table.getCell(DtempR,DtempC-'a'+1-1-1).setBlack(destTemp.getBlack());
                    table.getCell(DtempR,DtempC-'a'+1-1+1).removePiece();
                }
                else{   //y = -2, left cast
                    Cell castRook = table.getCell(DtempR,DtempC-'a'+1-1-2).clone();
                    Cell destTemp = table.getCell(DtempR,DtempC-'a'+1-1+1).clone();
                    table.setCell(castRook,DtempR,DtempC-'a'+1-1+1);
                    table.getCell(DtempR,DtempC-'a'+1-1+1).setRow(DtempR);
                    table.getCell(DtempR,DtempC-'a'+1-1+1).setCol(DtempC-'a'+1-1+1);
                    table.getCell(DtempR,DtempC-'a'+1-1+1).setBlack(destTemp.getBlack());
                    table.getCell(DtempR,DtempC-'a'+1-1-2).removePiece();
                }
            }
        }

    }
    public static void empasingUpdate(Board table){
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                if(table.getCell(i,j).getEnpassingRound()==2||table.getCell(i,j).getEnpassingRound()==1) table.getCell(i,j).setEnpassingRound();
                if(table.getCell(i,j).getEnpassingRound()>=3) table.getCell(i,j).resetEnpassingRound();
                System.out.print(table.getCell(i,j).getEnpassingRound());
            }
            System.out.println();
        }
    }
    public static boolean ifCheck(Board table,boolean whiteMove){
        return false;
    }
    public static boolean ifCheckmate(Board table,boolean whiteMove){
        return false;
    }
    public static void pawnPromote(){

    }
    public static void main(String[] args) {
        Board board = new Board();
        board.show();

        String userInput = "";
        Scanner in;
        String target = "";
        String dest = "";
        String promoteRole = null;
        String drawHolder =null;
        boolean ifValid;
        boolean ifCheck = false;
        boolean ifCheckmate = false;
        boolean ifDraw = false;
        boolean whiteMove = true;
        boolean selfCheck;
        while(!ifCheckmate) {
            selfCheck = false;
            target = "";
            dest = "";
            promoteRole = "";
            in = new Scanner(System.in);
            userInput = in.nextLine();
            int m1 = userInput.indexOf(' ');
            int m2 = userInput.indexOf(' ', m1 + 1);
            int m3 = userInput.indexOf(' ',m2 + 1); //only happen when promote and draw
            if(m1<0) {
                //player resign
                promoteRole = userInput;
            }
            else{
                //at least 2 input
                target = userInput.substring(0, m1);         //only one input, either draw, or resign
                if (m2 < 0) {
                    //only 2 input
                    //no promotion or default promotion
                    dest = userInput.substring(m1 + 1);
                    promoteRole = null;
                }
                else {
                    //at least 3 input
                    //promote pawn to a specific piece or ask for draw
                    if(m3<0){   //only 3 input
                        dest = userInput.substring(m1 + 1, m2);
                        promoteRole = userInput.substring(m2 + 1);
                    }
                    else{   //4 input, promote and draw happen same time
                        dest = userInput.substring(m1 + 1, m2);
                        promoteRole = userInput.substring(m2 + 1, m3);
                        drawHolder = userInput.substring(m3 + 1);
                    }

                }
            }
            if (promoteRole != null){
                if (promoteRole.contains("resign")) {
                    if (whiteMove) {          //white resign, black win
                        System.out.println("Black wins");
                    } else {                   //black resign, white win
                        System.out.println("White wins");
                    }
                    break;
                }
                if(drawHolder == null){
                    if (promoteRole.contains("draw")) {
                        if (!ifDraw) ifDraw = true;
                            //promoteRole = null;

                        else {       //the previous player said draw, game end
                            System.out.println("Result:draw");
                            break;
                        }
                    }
                    else{   //only three input, and the third input is not draw, ifDraw reset
                        ifDraw = false;
                    }
                }
                else{   //drawHolder != null, drawHolder can only contain "draw"
                    if(!ifDraw) ifDraw = true;
                    else{
                        System.out.println("Result:draw");
                        break;
                    }
                }
            }
            else{   //promoteRole is null, only 2 input, ifDraw reset
                ifDraw = false;
            }

            //if promoteRole work as a third input that contains "draw", promoteRole will be reset
            if(promoteRole != null&&promoteRole.contains("draw")) promoteRole = null;


            System.out.println("whiteMove: "+whiteMove);
            ifValid = ifValidMove(board,target,dest,whiteMove,promoteRole);
            if(!ifValid){                            //invalid command
                System.out.println("Illegal move, try again");
            }

            else{
                /*create a copy of current board, if player's move
                put himself in check, print illegal move and reverse the move
                Board tempBoard = new Board(board);
                boardCopy(board,tempBoard);
                tempBoard.getCell(0,0).setName("test");
                tempBoard.show();*/
                System.out.println("valid move");
                move(board,target,dest);
                empasingUpdate(board);
                /*after each move, if the move will turn own King into check,
                move should be reversed and printout Illegal move.*/
                selfCheck = ifCheck(board,!whiteMove);
                if(selfCheck){
                    //board = tempBoard;
                    //System.out.println("Illegal move, try again");
                }


                else{
                    board.show();
                    if(whiteMove){
                        System.out.println("White's move:"+ target +"\t"+ dest);
                    }
                    else{
                        System.out.println("Black's move:"+ target +"\t"+ dest);
                    }
                    ifCheck = ifCheck(board,whiteMove);
                    if(ifCheck){   //check if Check
                        ifCheckmate = ifCheckmate(board,whiteMove);
                        if(ifCheckmate){   //check if Checkmate
                            System.out.println("Checkmate");
                            if(whiteMove) System.out.println("White Wins!");
                            else{
                                System.out.println("Black Wins");
                            }
                        }
                        else{
                            System.out.println("Check");
                        }
                    }
                    whiteMove = !whiteMove;

                }

            }


        }







    }
}
