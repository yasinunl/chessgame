public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }
    @Override
    public String getSymbol() {
        return isWhite() ? "N" : "n";
    }

    @Override
    public boolean canMove(Square[][] board, Square start, Square end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return x * y == 2;
    }
}