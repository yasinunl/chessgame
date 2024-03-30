public class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }
    @Override
    public String getSymbol() {
        return isWhite() ? "Q" : "q";
    }

    @Override
    public boolean canMove(Square[][] board, Square start, Square end) {
        // Check if end square is on same row or column as start square
        if (start.getX() != end.getX() && start.getY() != end.getY()) {
            // Check if end square is on same diagonal as start square
            if (Math.abs(start.getX() - end.getX()) != Math.abs(start.getY() - end.getY())) {
                return false;
            }
        }

        // Check if there are any pieces in the way
        int xDir = Integer.compare(end.getX(), start.getX());
        int yDir = Integer.compare(end.getY(), start.getY());
        int x = start.getX() + xDir;
        int y = start.getY() + yDir;
        if(xDir != 0 && yDir != 0) while (x != end.getX() && y != end.getY()) {
            if (board[y][x].getPiece() != null) {
                return false;
            }
            x += xDir;
            y += yDir;
        }
        else if (xDir != 0) while (x != end.getX()) {
                if (board[y][x].getPiece() != null) {
                    return false;
                }
                x += xDir;
            }
         else while (y != end.getY()) {
                if (board[y][x].getPiece() != null) {
                    return false;
                }
                y += yDir;
            }


        return true;
    }
}
