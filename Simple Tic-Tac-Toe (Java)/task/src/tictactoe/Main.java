package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

class Field {
    public static char[][] field = {
        {'X', 'O', 'X'},
        {'O', 'X', 'O'},
        {'X', 'X', 'O'},
    };
    public static int countX = 0;
    public static int countO = 0;
    public static boolean end = false;
    public static char turn = 'X';

    protected Field() {
        resetField();
    }

    public static void resetField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '_';
            }
        }
        printField();
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }
    public static void setField(String fieldString) {
        assert (fieldString != null) : "Must not be null!";
        assert (fieldString.length() == 9 || fieldString.length() == 2) : "Must be either 9 or 2 input";
        countX = 0;
        countO = 0;
        if (fieldString.length() == 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    field[i][j] = fieldString.charAt(j + 3 * i);
                    if (fieldString.charAt(j + 3 * i) == 'X') {
                        countX += 1;
                    } else if (fieldString.charAt(j + 3 * i) == 'O') {
                        countO += 1;
                    }
                }
            }
        } else if (fieldString.length() == 2){
            int[] coord = { (int) fieldString.charAt(0), (int) fieldString.charAt(1)};
            setField(coord);
        }
    }
    public static void setField(int[] input){
        if (input[0] > 3 || input[0] < 1 || input[1] > 3 || input[1] < 1){
            System.out.println("Coordinates should be from 1 to 3!");
        } else {
            field[input[0]-1][input[1]-1] = turn;
            printField();
        }
    }
    public static void printField() {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }
    public static void userInput() {
        Scanner sc = new Scanner(System.in);
        boolean tmp = true;
        int i = 0;
        int j = 0;
        while (!end) {
            if (i<10){
                System.out.println("i = " + ++i);
            }
            tmp = true;
            while (tmp) {
                if (j<10){
                System.out.println("j = " + ++j);
                }
                String move = sc.nextLine();
                if (move.matches("[a-zA-Z]+")) {
                    System.out.println("You should enter numbers!");
                } else if (move.length() == 3) {
                    System.out.println("move.length() == 3");
                    String[] parts = move.split(" ");
                    int[] coord = Arrays.stream(parts)
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    if (coord[0] > 0 && coord[0] < 4 && coord[1] > 0 && coord[1] < 4) {
                        char fieldChar = field[coord[0] - 1][coord[1] - 1];
                        if (fieldChar != 'X' && fieldChar != 'O') {
                            setField(coord);
                            tmp = false;
                            if (turn == 'X') {
                                turn = 'O';
                            } else {
                                turn = 'X';
                            }
                            gameState();
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                } else {
                    System.out.println("Invalid input!");
                }
            }
        }
    }
    public static void gameState() {
        int foul = Math.abs(countX - countO);
        int finished = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == '_') {
                    finished *= 0;
                }
            }
        }
        char win = '_';
        if ((field[0][0] != '_' && field[1][1] != '_' && field[2][2] != '_') && ((field[0][0] == field[1][1] && field[0][0] == field[2][2]) || (field[0][2] == field[1][1] && field[0][2] == field[2][0]))) {
            if (win != 'X' && win != 'O') {
                win = field[1][1];
                System.out.println("W1");
            } else {
                System.out.println("Z1");
                win = 'Z';
            }
        } else if (true) {
            for (int i = 0; i < 3; i++) {
                if ((field[i][0] != '_' && field[i][1] != '_' && field[i][2] != '_') && (field[i][0] == field[i][1] && field[i][0] == field[i][2])) {
                    if (win != 'X' && win != 'O') {
                        win = field[i][0];
                        System.out.println("W2");
                    } else {
                        System.out.println("Z2");
                        win = 'Z';
                    }
                }
            }
        } else if (true) {
            for (int i = 0; i < 3; i++) {
                if ((field[0][i] != '_' && field[1][i] != '_' && field[2][i] != '_') && (field[0][i] == field[1][i] && field[0][i] == field[2][i])) {
                    if (win != 'X' && win != 'O') {
                        win = field[0][i];
                        System.out.println("W3");
                    } else {
                        System.out.println("Z3");
                        win = 'Z';
                    }
                }
            }
        }
        if (foul > 1){
            System.out.println("foul");
            end = true;
        } else if (win == '_' && finished == 0) {
            System.out.println("Game not finished");
        } else if (win == '_' && finished == 1) {
            System.out.println("Draw");
            end = true;
        } else if (win != '_' && win != 'Z') {
            System.out.println(win + " wins");
            end = true;
        } else if (win == 'Z') {
            System.out.println("Impossible");
            end = true;
        }

    }
}

public class Main {
    public static void main(String[] args) {
        Field.resetField();
        Field.userInput();

    }
}
