public class King extends Piece {

    public King(boolean white) {
        super(white);
    }
    @Override
    public String getSymbol() {
        return isWhite() ? "K" : "k";
    }
    @Override
    public boolean canMove(Square[][] board, Square start, Square end) {
        // Check if end square is adjacent to start square
        if (Math.abs(start.getX() - end.getX()) > 1 || Math.abs(start.getY() - end.getY()) > 1) {
            return false;
        }

        // Check if end square is occupied by a piece of the same color
        return !(end.getPiece() != null && end.getPiece().isWhite() == isWhite());

    }
}
