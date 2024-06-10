package src;

public class Interpreter {
    private Parser parser;

    public Interpreter(Parser parser) {
        this.parser = parser;
    }

    private int visit(AST node) {
        switch (node.getToken().getType()) {
            case TOKEN_INTEGER:
                return node.getToken().getValue();
            case TOKEN_PLUS:
                return visit(node.getLeft()) + visit(node.getRight());
            case TOKEN_MINUS:
                return visit(node.getLeft()) - visit(node.getRight());
            case TOKEN_MUL:
                return visit(node.getLeft()) * visit(node.getRight());
            case TOKEN_DIV:
                return visit(node.getLeft()) / visit(node.getRight());
            default:
                throw new RuntimeException("Erro de interpretação: token inesperado");
        }
    }

    public int interpret() {
        AST tree = parser.parse();
        return visit(tree);
    }
}

