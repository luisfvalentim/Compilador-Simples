package src;

import java.util.Objects;
import java.util.List;

public class Parser {
    private Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    private void eat(TokenType type) {
        if (currentToken.getType() == type) {
            currentToken = lexer.getNextToken();
        } else {
            throw new RuntimeException("Erro de sintaxe: esperado token " + type);
        }
    }

    private AST factor() {
        Token token = currentToken;
        if (token.getType() == TokenType.TOKEN_INTEGER) {
            eat(TokenType.TOKEN_INTEGER);
            return new AST(token);
        } else if (token.getType() == TokenType.TOKEN_LPAREN) {
            eat(TokenType.TOKEN_LPAREN);
            AST node = expr();
            eat(TokenType.TOKEN_RPAREN);
            return node;
        } else {
            throw new RuntimeException("Erro de sintaxe: token inesperado");
        }
    }

    private AST term() {
        AST node = factor();
        while (currentToken.getType() == TokenType.TOKEN_MUL || currentToken.getType() == TokenType.TOKEN_DIV) {
            Token token = currentToken;
            if (token.getType() == TokenType.TOKEN_MUL) {
                eat(TokenType.TOKEN_MUL);
            } else if (token.getType() == TokenType.TOKEN_DIV) {
                eat(TokenType.TOKEN_DIV);
            }
            AST newNode = new AST(token);
            newNode.setLeft(node);
            newNode.setRight(factor());
            node = newNode;
        }
        return node;
    }

    private AST expr() {
        AST node = term();
        while (currentToken.getType() == TokenType.TOKEN_PLUS || currentToken.getType() == TokenType.TOKEN_MINUS) {
            Token token = currentToken;
            if (token.getType() == TokenType.TOKEN_PLUS) {
                eat(TokenType.TOKEN_PLUS);
            } else if (token.getType() == TokenType.TOKEN_MINUS) {
                eat(TokenType.TOKEN_MINUS);
            }
            AST newNode = new AST(token);
            newNode.setLeft(node);
            newNode.setRight(term());
            node = newNode;
        }
        return node;
    }

    public AST parse() {
        return expr();
    }
}

