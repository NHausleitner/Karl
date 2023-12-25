package engine.karl;

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




}
