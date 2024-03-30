public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
    }
    @Override
    public String getSymbol() {
        return isWhite() ? "P" : "p";
    }
    @Override
    public boolean canMove(Square[][] board, Square start, Square end) {
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();
        //if they are on same square
        if (startX == endX && startY == endY) {
            return false;
        }/* Check cross opponent Pawn
        |   | p |   |
        | P |   | P |
        */
        if (startX == endX + 1 || startX == endX - 1) {
            // When white move
            if (startY + 1== endY && isWhite() && end.getPiece() != null &&
                    !end.getPiece().isWhite()) {
                return true;
            }
            //When black move
            return (startY == endY + 1 && !isWhite() && end.getPiece() != null &&
                    end.getPiece().isWhite());
        }
        // First move of white Pawn
        if (startY == 1 && endY == 3 && isWhite() && board[endY - 1][endX].getPiece() == null) {
            return true;
        }
        //Move white pawn
        if (startY == endY - 1 && isWhite()) {
            return true;
        }
        // First move of black Pawn
        if (startY == 6 && endY == 4 && !isWhite()) {
            return true;
        }
        //Move black pawn
        return (startY == endY + 1 && !isWhite());
    }
}