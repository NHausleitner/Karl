package engine.karl;

public class Board {

    public long whitePawns=0L,whiteKnights=0L,whiteBishops=0L,whiteRooks=0L,whiteQueens=0L,whiteKing=0L,blackPawns=0L,blackKnights=0L,blackBishops=0L,blackRooks=0L,blackQueens=0L,blackKing=0L;

    private boolean whiteTurns;
    private int castlingRights;
    private int enPassantSquares;

    public void initiateStandardChess() {
        String[][] chessboard ={
                {"r","n","b","q","k","b","n","r"},
                {"p","p","p","p","p","p","p","p"},
                {" "," "," "," "," "," "," "," "},
                {" "," "," "," "," "," "," "," "},
                {" "," "," "," "," "," "," "," "},
                {" "," "," "," "," "," "," "," "},
                {"P","P","P","P","P","P","P","P"},
                {"R","N","B","Q","K","B","N","R"}
        };
        arrayToBitboards(chessboard);
    }

    private void arrayToBitboards(String[][] chessboard){
        String binary;
        for (int i = 0; i < 64; i++){
            binary = "0000000000000000000000000000000000000000000000000000000000000000";
            binary = binary.substring(i+1)+"1"+binary.substring(0, i);
            switch (chessboard[i/8][i%8]){
                case "P": whitePawns+=convertStringToBitboard(binary); break;
                case "R": whiteRooks+=convertStringToBitboard(binary); break;
                case "N": whiteKnights+=convertStringToBitboard(binary); break;
                case "B": whiteBishops+=convertStringToBitboard(binary); break;
                case "Q": whiteQueens+=convertStringToBitboard(binary); break;
                case "K": whiteKing+=convertStringToBitboard(binary); break;
                case "p": blackPawns+=convertStringToBitboard(binary); break;
                case "r": blackRooks+=convertStringToBitboard(binary); break;
                case "n": blackKnights+=convertStringToBitboard(binary); break;
                case "b": blackBishops+=convertStringToBitboard(binary); break;
                case "q": blackQueens+=convertStringToBitboard(binary); break;
                case "k": blackKing+=convertStringToBitboard(binary); break;
            }
        }
    }

    private long convertStringToBitboard(String binary){
        if (binary.charAt(0) == '0'){
            return Long.parseLong(binary, 2);
        } else {
            return Long.parseLong("1"+binary.substring(2), 2)*2;
        }
    }

    public boolean whiteMated(){
        if (blackKingInCheck()){
            return !blackHasLegalMove();
        }
        return false;
    }

    public boolean blackMated(){
        if (whiteKingInCheck()){
            return !whiteHasLegalMove();
        }
        return false;
    }

    public boolean blackKingInCheck(){
        return (blackKing & getPossibleWhiteRookMoves() & getPossibleWhiteKnightMoves()
                & getPossibleWhiteBishopMoves() & getPossibleWhitePawnAttacks()
                & getPossibleWhiteQueenMoves()) == 1;
    }

    public boolean whiteKingInCheck(){
        return (whiteKing & getPossibleBlackRookMoves() & getPossibleBlackKnightMoves()
                & getPossibleBlackBishopMoves() & getPossibleBlackPawnAttacks()
                & getPossibleBlackQueenMoves()) == 1;
    }

    public boolean blackHasLegalMove(){

    }

    public boolean whiteHasLegalMove(){

    }

    public long getPossibleWhiteRookMoves(){

    }


}