public class Square {
    private final int row;
    private final int col;
    private Piece piece;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    public int getX() {
        return col;
    }

    public int getY() {
        return row;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void isAtLastSquare() { // Check last square for Pawns
        if (this.piece instanceof Pawn) {
            if (this.piece.isWhite()) {
                if(this.row == 7){
                    this.promoteToQueen();
                }
            } else {
                if(this.row == 0){
                    this.promoteToQueen();
                }
            }
        }
    }
    public void promoteToQueen() {
        if (this.piece instanceof Pawn) { // Change Pawn if it is on last square
            if (this.piece.isWhite()) {
                this.piece = new Queen(true);
            } else {
                this.piece = new Queen(false);
            }
        }
    }
}