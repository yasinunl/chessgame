public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }
    @Override
    public String getSymbol() {
        return isWhite() ? "B" : "b";
    }

    @Override
    public boolean canMove(Square[][] board, Square start, Square end) {
        // Check if end square is diagonal to start square
        if (Math.abs(start.getX() - end.getX()) != Math.abs(start.getY() - end.getY())) {
            return false;
        }

        // Check if there are any pieces in the way
        int xDir = start.getX() < end.getX() ? 1 : -1;
        int yDir = start.getY() < end.getY() ? 1 : -1;
        int x = start.getX() + xDir;
        int y = start.getY() + yDir;
        while (x != end.getX() && y != end.getY()) {
            if (board[y][x].getPiece() != null) {
                return false;
            }
            x += xDir;
            y += yDir;
        }

        return true;
    }
}
