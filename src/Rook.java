public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }
    @Override
    public String getSymbol() {
        return isWhite() ? "R" : "r";
    }

    @Override
    public boolean canMove(Square[][] board, Square start, Square end) {
        // Check if end square is on same row or column as start square
        if (start.getX() != end.getX() && start.getY() != end.getY()) {
            return false;
        }

        // Check if there are any pieces in the way
        int xDir = Integer.compare(end.getX(), start.getX());
        int yDir = Integer.compare(end.getY(), start.getY());
        int x = start.getX() + xDir;
        int y = start.getY() + yDir;
        if(xDir == 0) while (y != end.getY()) {
            if (board[y][x].getPiece() != null) {
                return false;
            }
            y += yDir;
        }else while (x != end.getX()) {
                if (board[y][x].getPiece() != null) {
                    return false;
                }
                x += xDir;
            }


        return true;
    }
}