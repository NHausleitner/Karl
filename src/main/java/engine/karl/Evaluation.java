package engine.karl;

public class Evaluation {

    public static double evaluate(Board board) {
        if (board.whiteMated()) return 1000; // white won
        if (board.blackMated()) return -1000; // black won

    }


}
