package src;

public class Lexer {
    private String text;
    private int pos;
    private char currentChar;

    public Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.currentChar = text.charAt(pos);
    }

    private void advance() {
        pos++;
        if (pos > text.length() - 1) {
            currentChar = '\0';
        } else {
            currentChar = text.charAt(pos);
        }
    }

    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    private int integer() {
        int result = 0;
        while (currentChar != '\0' && Character.isDigit(currentChar)) {
            result = result * 10 + (currentChar - '0');
            advance();
        }
        return result;
    }

    public Token getNextToken() {
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }
            if (Character.isDigit(currentChar)) {
                return new Token(TokenType.TOKEN_INTEGER, integer());
            }
            if (currentChar == '+') {
                advance();
                return new Token(TokenType.TOKEN_PLUS, 0);
            }
            if (currentChar == '-') {
                advance();
                return new Token(TokenType.TOKEN_MINUS, 0);
            }
            if (currentChar == '*') {
                advance();
                return new Token(TokenType.TOKEN_MUL, 0);
            }
            if (currentChar == '/') {
                advance();
                return new Token(TokenType.TOKEN_DIV, 0);
            }
            if (currentChar == '(') {
                advance();
                return new Token(TokenType.TOKEN_LPAREN, 0);
            }
            if (currentChar == ')') {
                advance();
                return new Token(TokenType.TOKEN_RPAREN, 0);
            }
            return new Token(TokenType.TOKEN_UNKNOWN, 0);
        }
        return new Token(TokenType.TOKEN_EOF, 0);
    }
}

