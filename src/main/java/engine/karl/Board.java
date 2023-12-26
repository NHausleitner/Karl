package engine.karl;

import java.util.HashSet;
import java.util.Set;

public class Board {

    private long whitePawns;
    private long whiteKnights;
    private long whiteBishops;
    private long whiteRooks;
    private long whiteQueens;
    private long whiteKing;
    private long blackPawns;
    private long blackKnights;
    private long blackBishops;
    private long blackRooks;
    private long blackQueens;
    private long blackKing;

    private boolean whiteTurns;
    private int castlingRights;
    private int enPassantSquares;

    public long getAllPieces(){
        return whitePawns | whiteKnights | whiteBishops | whiteRooks | whiteQueens | whiteKing | blackPawns | blackKnights | blackBishops | blackRooks | blackQueens | blackKing;
    }

    public long getWhitePawns() {
        return whitePawns;
    }

    public long getWhiteKnights() {
        return whiteKnights;
    }

    public long getWhiteBishops() {
        return whiteBishops;
    }

    public long getWhiteRooks() {
        return whiteRooks;
    }

    public long getWhiteQueens() {
        return whiteQueens;
    }

    public long getWhiteKing() {
        return whiteKing;
    }

    public long getBlackPawns() {
        return blackPawns;
    }

    public long getBlackKnights() {
        return blackKnights;
    }

    public long getBlackBishops() {
        return blackBishops;
    }

    public long getBlackRooks() {
        return blackRooks;
    }

    public long getBlackQueens() {
        return blackQueens;
    }

    public long getBlackKing() {
        return blackKing;
    }

    public Board(){
        initialiseBoard();
    }


    private void initialiseBoard(){
        whitePawns = Long.parseLong("0000000000000000000000000000000000000000000000001111111100000000", 2);
        whiteKnights = Long.parseLong("0000000000000000000000000000000000000000000000000000000001000010", 2);
        whiteBishops = Long.parseLong("0000000000000000000000000000000000000000000000000000000000100100", 2);
        whiteRooks = Long.parseLong("0000000000000000000000000000000000000000000000000000000010000001", 2);
        whiteQueens = Long.parseLong("0000000000000000000000000000000000000000000000000000000000010000", 2);
        whiteKing = Long.parseLong("0000000000000000000000000000000000000000000000000000000000001000", 2);

        blackPawns = Long.parseLong("0000000011111111000000000000000000000000000000000000000000000000", 2);
        blackKnights = Long.parseLong("0100001000000000000000000000000000000000000000000000000000000000", 2);
        blackBishops = Long.parseLong("0010010000000000000000000000000000000000000000000000000000000000", 2);
        blackRooks = Long.parseLong("1000000100000000000000000000000000000000000000000000000000000000", 2);
        blackQueens = Long.parseLong("0001000000000000000000000000000000000000000000000000000000000000", 2);
        blackKing = Long.parseLong("0000100000000000000000000000000000000000000000000000000000000000", 2);
    }

    private long switchBit(long bits, int position){
        return bits ^ (1L << position);
    }

    private boolean checkIfBitIsSet(long bits, int position){
        return ((bits & (1L << position)) != 0);
    }

    private long invertAllBits(long bits){
        return ~bits;
    }

    private long deleteAllBitsExceptOne(long bits, int position){
        return bits & (1L << position);
    }

    private long shiftToLeft(long bits, int shifts){
        return bits << shifts;
    }

    private long shiftToRight(long bits, int shifts){
        return bits >> shifts;
    }

    private boolean whiteMated(){
        return false;
    }

    private boolean blackMated(){
        return false;
    }

    private boolean checkWhite(){
        return false;
    }

    private boolean checkBlack(){
        return false;
    }

    private Set<Long> getLegalWhitePawnMoves(long whitePawns){
        Set<Long> LegalWhitePawnMoves = new HashSet<>();

        long firstPawn = deleteAllBitsExceptOne(whitePawns, (int) (Math.log((double) Long.highestOneBit(whitePawns)) / Math.log(2)));
        long allPieces = getAllPieces();
        boolean startingPosition = false;
        if (firstPawn >= Math.pow(2, 8) | firstPawn <= Math.pow(2, 15)){
            //Doppelzug möglich
            startingPosition = true;
        }

        long oneMoveForward = shiftToLeft(firstPawn, 8);






        return LegalWhitePawnMoves;
    }

    private void GameRunner(){

    }





}
