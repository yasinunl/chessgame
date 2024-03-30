public abstract class Piece {
    private final boolean white;
    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return this.white;
    }
    public abstract boolean canMove(Square[][] board, Square start, Square end);
    public abstract String getSymbol();
}