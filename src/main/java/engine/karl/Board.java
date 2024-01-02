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
        List<Long> legalMovesOfAllWhiteRooks = new ArrayList<>();
        long whiteRooksCopy = whiteRooks;
        List<Long> whiteRooksSplit = splitLong(whiteRooksCopy);
        for (Long l : whiteRooksSplit) {
            legalMovesOfAllWhiteRooks.add(determinePossibleAndLegalMoves(-1, 0, l, true)); // vertical up
            legalMovesOfAllWhiteRooks.add(determinePossibleAndLegalMoves(1, 0, l, true)); // vertical down
            legalMovesOfAllWhiteRooks.add(determinePossibleAndLegalMoves(0, -1, l, true)); // horizontal left
            legalMovesOfAllWhiteRooks.add(determinePossibleAndLegalMoves(0, 1, l, true)); // horizontal right
        }
        long sum = 0L;
        for (Long l: legalMovesOfAllWhiteRooks){
            sum = sum | l;
        }
        return sum;
    }
    
    public long getPossibleBlackRookMoves(){
        List<Long> legalMovesOfAllBlackRooks = new ArrayList<>();
        long blackRooksCopy = blackRooks;
        List<Long> blackRooksSplit = splitLong(blackRooksCopy);
        for (Long l : blackRooksSplit) {
            legalMovesOfAllBlackRooks.add(determinePossibleAndLegalMoves(-1, 0, l, true)); // vertical up
            legalMovesOfAllBlackRooks.add(determinePossibleAndLegalMoves(1, 0, l, true)); // vertical down
            legalMovesOfAllBlackRooks.add(determinePossibleAndLegalMoves(0, -1, l, true)); // horizontal left
            legalMovesOfAllBlackRooks.add(determinePossibleAndLegalMoves(0, 1, l, true)); // horizontal right
        }
        long sum = 0L;
        for (Long l: legalMovesOfAllBlackRooks){
            sum = sum | l;
        }
        return sum;
    }

    public long getPossibleWhiteBishopMoves(){
        List<Long> legalMovesOfAllWhiteBishops = new ArrayList<>();
        long whiteBishopsCopy = whiteBishops;
        List<Long> whiteBishopsSplit = splitLong(whiteBishopsCopy);
        for (Long l : whiteBishopsSplit) {
            legalMovesOfAllWhiteBishops.add(determinePossibleAndLegalMoves(-1, -1, l, true)); // diagonal left up
            legalMovesOfAllWhiteBishops.add(determinePossibleAndLegalMoves(1, -1, l, true)); // diagonal left down
            legalMovesOfAllWhiteBishops.add(determinePossibleAndLegalMoves(-1, 1, l, true)); // diagonal right up
            legalMovesOfAllWhiteBishops.add(determinePossibleAndLegalMoves(1, 1, l, true)); // diagonal right down
        }
        long sum = 0L;
        for (Long l: legalMovesOfAllWhiteBishops){
            sum = sum | l;
        }
        return sum;
    }

    public long getPossibleBlackBishopMoves(){
        List<Long> legalMovesOfAllBlackBishops = new ArrayList<>();
        long blackBishopsCopy = blackBishops;
        List<Long> blackBishopsSplit = splitLong(blackBishopsCopy);
        for (Long l : blackBishopsSplit) {
            legalMovesOfAllBlackBishops.add(determinePossibleAndLegalMoves(-1, -1, l, true)); // diagonal left up
            legalMovesOfAllBlackBishops.add(determinePossibleAndLegalMoves(1, -1, l, true)); // diagonal left down
            legalMovesOfAllBlackBishops.add(determinePossibleAndLegalMoves(-1, 1, l, true)); // diagonal right up
            legalMovesOfAllBlackBishops.add(determinePossibleAndLegalMoves(1, 1, l, true)); // diagonal right down
        }
        long sum = 0L;
        for (Long l: legalMovesOfAllBlackBishops){
            sum = sum | l;
        }
        return sum;
    }

    public long getPossibleWhiteQueenMoves(){
        List<Long> legalMovesOfAllWhiteQueens = new ArrayList<>();
        long whiteQueensCopy = whiteQueens;
        List<Long> whiteQueensSplit = splitLong(whiteQueensCopy);
        for (Long l : whiteQueensSplit) {
            legalMovesOfAllWhiteQueens.add(determinePossibleAndLegalMoves(-1, -1, l, true)); // diagonal left up
            legalMovesOfAllWhiteQueens.add(determinePossibleAndLegalMoves(1, -1, l, true)); // diagonal left down
            legalMovesOfAllWhiteQueens.add(determinePossibleAndLegalMoves(-1, 1, l, true)); // diagonal right up
            legalMovesOfAllWhiteQueens.add(determinePossibleAndLegalMoves(1, 1, l, true)); // diagonal right down
            legalMovesOfAllWhiteQueens.add(determinePossibleAndLegalMoves(-1, 0, l, true)); // vertical up
            legalMovesOfAllWhiteQueens.add(determinePossibleAndLegalMoves(1, 0, l, true)); // vertical down
            legalMovesOfAllWhiteQueens.add(determinePossibleAndLegalMoves(0, -1, l, true)); // horizontal left
            legalMovesOfAllWhiteQueens.add(determinePossibleAndLegalMoves(0, 1, l, true)); // horizontal right
        }
        long sum = 0L;
        for (Long l: legalMovesOfAllWhiteQueens){
            sum = sum | l;
        }
        return sum;
    }

    public long getPossibleBlackQueenMoves(){
        List<Long> legalMovesOfAllBlackQueens = new ArrayList<>();
        long blackQueensCopy = blackQueens;
        List<Long> blackQueensSplit = splitLong(blackQueensCopy);
        for (Long l : blackQueensSplit) {
            legalMovesOfAllBlackQueens.add(determinePossibleAndLegalMoves(-1, -1, l, true)); // diagonal left up
            legalMovesOfAllBlackQueens.add(determinePossibleAndLegalMoves(1, -1, l, true)); // diagonal left down
            legalMovesOfAllBlackQueens.add(determinePossibleAndLegalMoves(-1, 1, l, true)); // diagonal right up
            legalMovesOfAllBlackQueens.add(determinePossibleAndLegalMoves(1, 1, l, true)); // diagonal right down
            legalMovesOfAllBlackQueens.add(determinePossibleAndLegalMoves(-1, 0, l, true)); // vertical up
            legalMovesOfAllBlackQueens.add(determinePossibleAndLegalMoves(1, 0, l, true)); // vertical down
            legalMovesOfAllBlackQueens.add(determinePossibleAndLegalMoves(0, -1, l, true)); // horizontal left
            legalMovesOfAllBlackQueens.add(determinePossibleAndLegalMoves(0, 1, l, true)); // horizontal right
        }
        long sum = 0L;
        for (Long l: legalMovesOfAllBlackQueens){
            sum = sum | l;
        }
        return sum;
    }

    public long getPossibleWhiteKnightMoves(){

    }

    public long getPossibleBlackKnightMoves(){

    }

    public long getPossibleWhitePawnAttacks(){

    }

    public long getPossibleBlackPawnAttacks(){

    }

    private long determinePossibleAndLegalMoves(long rowOffset, long columnOffset, long bitboard, boolean possibleNotLegal) {
        List<Long> legalMovesForOnePiece = new ArrayList<>();
        long setPieces = getAllPieces();
        long leadingZeros = Long.numberOfLeadingZeros(bitboard);
        long startingPosition = 63 - leadingZeros;
        long rowOfStartingPosition = startingPosition / 8;
        long columnOfStartingPosition = startingPosition % 8;
        for (int i = 1; rowOfStartingPosition + i * rowOffset >= 0 && rowOfStartingPosition + i * rowOffset < 8 && columnOfStartingPosition + i * columnOffset >= 0 && columnOfStartingPosition + i * columnOffset < 8; i++) {
            long targetDecimal = (rowOfStartingPosition + i * rowOffset) * 8 + (columnOfStartingPosition + i * columnOffset);
            long targetBinary = 1L << targetDecimal;
            if ((targetBinary & setPieces) == 1){
                if ((targetBinary & blackKing) == 1){
                    legalMovesForOnePiece.add(targetBinary);
                } else { // !!!!
                    break;
                }
            } else if ((targetBinary & setPieces) == 0) {
                legalMovesForOnePiece.add(targetBinary);
            }
        }
        long sum = 0L;
        for (Long l: legalMovesForOnePiece){
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