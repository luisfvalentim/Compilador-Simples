package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a operação: ");
        String text = scanner.nextLine();

        Lexer lexer = new Lexer(text);
        Parser parser = new Parser(lexer);
        Interpreter interpreter = new Interpreter(parser);

        try {
            int result = interpreter.interpret();
            System.out.println("Resultado: " + result);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        scanner.close();
    }
}
