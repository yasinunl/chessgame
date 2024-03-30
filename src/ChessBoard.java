import java.util.Scanner;

public class ChessBoard {
    private final Square[][] board;
    public ChessBoard() {
        board = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Square(row, col);
            }
        }
    }
    public void initialize() {
        // Initialize pieces on board
        board[0][0].setPiece(new Rook(true));
        board[0][1].setPiece(new Knight(true));
        board[0][2].setPiece(new Bishop(true));
        board[0][3].setPiece(new Queen(true));
        board[0][4].setPiece(new King(true));
        board[0][5].setPiece(new Bishop(true));
        board[0][6].setPiece(new Knight(true));
        board[0][7].setPiece(new Rook(true));

        for (int col = 0; col < 8; col++) {
            board[1][col].setPiece(new Pawn(true));
        }

        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col].setPiece(null);
            }
        }

        for (int col = 0; col < 8; col++) {
            board[6][col].setPiece(new Pawn(false));
        }

        board[7][0].setPiece(new Rook(false));
        board[7][1].setPiece(new Knight(false));
        board[7][2].setPiece(new Bishop(false));
        board[7][3].setPiece(new Queen(false));
        board[7][4].setPiece(new King(false));
        board[7][5].setPiece(new Bishop(false));
        board[7][6].setPiece(new Knight(false));
        board[7][7].setPiece(new Rook(false));
    }

    public void printBoard() {
        System.out.println("    A   B   C   D   E   F   G   H");
        System.out.println("  ---------------------------------");

        for (int row = 7; row >= 0; row--) {
            System.out.print((row + 1) + " |");
            for (int col = 0; col < 8; col++) {
                if (board[row][col].getPiece() == null) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + board[row][col].getPiece().getSymbol() + " ");
                }
                System.out.print("|");
            }
            System.out.println(" " + (row + 1));
            System.out.println("  ---------------------------------");
        }

        System.out.println("    A   B   C   D   E   F   G   H");
    }
    public boolean isGameOver() {
        // Check if either king is in checkmate
        boolean whiteKingInCheckmate = true;
        boolean blackKingInCheckmate = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col].getPiece();
                if (piece instanceof King) {
                    if (piece.isWhite()) {
                        whiteKingInCheckmate = false;
                    } else {
                        blackKingInCheckmate = false;
                    }
                }
            }
        }
        return whiteKingInCheckmate || blackKingInCheckmate;
    }
    private boolean currentPlayer = true;
    public boolean getCurrentPlayer() {
        // Count pieces of each color to determine whose turn it is
        return currentPlayer;
    }
    public  void  setCurrentPlayer(){
        this.currentPlayer = !currentPlayer;
    }
    public boolean isEmpty(int row, int col, boolean color) {
        Piece piece = board[row][col].getPiece();
        return piece == null || piece.isWhite() != color;
    }
    public boolean answerCheck(String answer){
        // Check of correct version 'd2'
        String strToNumber = "abcdefgh";
        return !(answer.length() == 2 && strToNumber.indexOf(answer.charAt(0)) != -1 &&
                Character.getNumericValue(answer.charAt(1)) <= 8);
    }
    public void play() {
        String strToNumber = "abcdefgh";
        String endMove;
        String startMove;
        int newRow;
        int newCol;
        int oldRow;
        int oldCol;
        Scanner scanner = new Scanner(System.in);
        while (!isGameOver()) {
            System.out.println("It is " +(getCurrentPlayer() ? "White" : "Black") + "'s turn");
            while(true){
                System.out.print("Enter the location of the piece: ");
                startMove = scanner.nextLine();
                    if (answerCheck(startMove) ||//Check correct word entered
                            isEmpty(Character.getNumericValue(startMove.charAt(1))
                                    - 1,strToNumber.indexOf(startMove.charAt(0)), getCurrentPlayer())) {//If the column is empty
                        continue;
                    }
                    oldCol = strToNumber.indexOf(startMove.charAt(0));
                    oldRow = Character.getNumericValue(startMove.charAt(1)) - 1;

            while (true){
            System.out.print("Enter the new location of the piece: ");
            endMove = scanner.nextLine();
                if (answerCheck(endMove)) {//Check correct word entered
                    continue;
                }
            newCol = strToNumber.indexOf(endMove.charAt(0));
            newRow = Character.getNumericValue(endMove.charAt(1)) - 1;

            Square start = board[oldRow][oldCol];// For locations where it is starting to move
            Square end = board[newRow][newCol];// For locations where it is ending to move
            if(start.getPiece().canMove(board, start, end)){
                if(end.getPiece() != null && // If is there same color piece, it will be back
                start.getPiece().isWhite() == end.getPiece().isWhite()){
                    break;
                }
                end.setPiece(board[oldRow][oldCol].getPiece());
                end.isAtLastSquare();//Check pawn on last squares
                start.setPiece(null);
                setCurrentPlayer();// Change player's color
            }else{
                break;
            }
            break;}
            if(board[oldRow][oldCol].getPiece() == null){//After the move, if everything is ok, finish the question
            break;
            }
            }
            printBoard();
        }
        scanner.close();
        if (!getCurrentPlayer()) {
            System.out.println("White won the game");
        } else {
            System.out.println("Black won the game");
        }
    }
}