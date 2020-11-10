import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Analyser {

    public static void main(String[] args) throws IOException {
        LinkedList<Character> chars = new LinkedList<Character>();
        char ch;
        boolean end = false;
        int[][] priority = new int[][]{
                // + * i ( ) #
                {1, -1, -1, -1, 1, 1}, // +
                {1, 1, -1, -1, 1, 1}, // *
                {1, 1, 2, 2, 1, 1}, // i
                {-1, -1, -1, -1, 0, 2}, // (
                {1, 1, 2, 2, 1, 1}, // )
                {-1, -1, -1, -1, 2, 2}, // #
                {-1, -1, 2, 2, 0, 0}, // N
        };
        FileReader reader = new FileReader(args[0]);
        chars.add('#');
        ch = (char)reader.read();
        if (ch == '\r') {
            ch = '#';
            end = true;
        }
        while (true) {
            char top = chars.getLast();
            if (priority[c2d(top)][c2d(ch)] == -1 || priority[c2d(top)][c2d(ch)] == 0) {
                chars.add(ch);
                System.out.println("I" + ch);
                ch = (char)reader.read();
                if (ch == '\r') {
                    ch = '#';
                    end = true;
                }
            } else if (priority[c2d(top)][c2d(ch)] == 1) {
                if (top == 'i') {
                    chars.removeLast();
                    chars.add('N');
                    System.out.println("R");
                } else if (top == 'N') {
                    chars.removeLast();
                    chars.removeLast();
                    System.out.println("R");
                } else if (top == ')') {
                    chars.removeLast();
                    chars.removeLast();
                    chars.removeLast();
                    chars.add('N');
                    System.out.println("R");
                } else {
                    System.out.println("RE");
                    break;
                }
            } else {
                System.out.println("E");
                break;
            }
            if (end && chars.size() == 2 && chars.getLast() == 'N') break;
        }
    }

    private static int c2d(char ch) {
        switch (ch) {
            case '+': return 0;
            case '*': return 1;
            case 'i': return 2;
            case '(': return 3;
            case ')': return 4;
            case 'N': return 6;
            default: return 5;
        }
    }
}
