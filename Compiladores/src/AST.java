package src;

public class AST {
    private Token token;
    private AST left;
    private AST right;

    public AST(Token token) {
        this.token = token;
        this.left = null;
        this.right = null;
    }

    public Token getToken() {
        return token;
    }

    public AST getLeft() {
        return left;
    }

    public void setLeft(AST left) {
        this.left = left;
    }

    public AST getRight() {
        return right;
    }

    public void setRight(AST right) {
        this.right = right;
    }
}

