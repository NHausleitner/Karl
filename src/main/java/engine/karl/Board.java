package engine.karl;

import java.util.*;

public class Board {

    public long whitePawns=0L,whiteKnights=0L,whiteBishops=0L,whiteRooks=0L,whiteQueens=0L,whiteKing=0L,blackPawns=0L,blackKnights=0L,blackBishops=0L,blackRooks=0L,blackQueens=0L,blackKing=0L;
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

    public long getAllPieces(){
        return whitePawns|whiteRooks|whiteKnights|whiteBishops|whiteQueens|whiteKing|
                blackPawns|blackRooks|blackKnights|blackBishops|blackQueens|blackKing;
    }

    public long getAllBlackPieces(){
        return blackPawns|blackRooks|blackKnights|blackBishops|blackQueens|blackKing;
    }

    public long getAllWhitePieces(){
        return whitePawns|whiteRooks|whiteKnights|whiteBishops|whiteQueens|whiteKing;
    }

    public long getPossibleWhiteRookMoves(){

        // Beispiel: "00000000 00000000 00000000 00000000 01000000 00000000 00000000 00000000" wird zu
        //           "01000000 01000000 01000000 01000000 10111111 01000000 01000000 01000000" (theoretisch bei freiem Board)
        // ABER
        // 1. Steht eine weiße Figur im Weg?
        // 2. Steht eine schwarze Figur im Weg?

        // Idee: Alle Türme in einzelne longs zerlegen und einzeln betrachten.
        long whiteRooksCopy = whiteRooks;
        List<Long> whiteRooksSplittet = splitLong(whiteRooksCopy);

        List<Long> legalMoves = new ArrayList<>();
        long setPieces = getAllPieces();

        for (int a = 0; a < whiteRooksSplittet.size(); a++){

            long rook = whiteRooksSplittet.get(a);
            long leadingZeros = Long.numberOfLeadingZeros(rook);
            long startingPosition = 63 - leadingZeros;
            long rowOfStartingPosition = startingPosition / 8;
            long columnOfStartingPosition = startingPosition % 8;

            // vertikal nach oben
            for (int i = 1; i <= 7 - rowOfStartingPosition; i++){
                long target = startingPosition + (8 * i);
                long rowOfTarget = target / 8;
                long columnOfTarget = target % 8;
                if ((target & setPieces) == 1){
                    if ((target & blackKing) == 1){
                        legalMoves.add(target);
                    } else { // !!!!
                        break;
                    }
                } else if ((target & setPieces) == 0) {
                    legalMoves.add(target);
                }
            }

            // vertikal nach unten
            for (int i = 1; i <=rowOfStartingPosition; i++){
                long target = startingPosition - (8 * i);
                long rowOfTarget = target / 8;
                long columnOfTarget = target % 8;
                if ((target & setPieces) == 1){
                    if ((target & blackKing) == 1){
                        legalMoves.add(target);
                    } else { // !!!!
                        break;
                    }
                } else if ((target & setPieces) == 0) {
                    legalMoves.add(target);
                }
            }

        }




        long sum = 0L;
        for (Long l: legalMoves){
            sum = sum | l;
        }

        return sum;
    }

    public static List<Long> splitLong(long input) {
        List<Long> result = new ArrayList<>();
        long rest = input;
        while (rest > 0){
            long a = 1;
            while (a*2 <= rest){
                a=a*2;
            }
            rest = rest - a;
            result.add(a);
        }
        return result;
    }





}