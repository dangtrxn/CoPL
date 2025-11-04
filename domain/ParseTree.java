package domain;
import domain.node.ExpressionNode;
import globalexceptions.InvalidParseException;

/**
 * class for parse tree, evaluates value of expression
 */
public class ParseTree {
    //root node
    private ExpressionNode expr;

    /**
     * constructor for parse tree, takes in expr node as root of tree
     */
    public ParseTree(ExpressionNode expr){
        this.expr = expr;
    }

    /**
     * method to evaluate value of expression tree
     * @return value of tree in postorder traversal
     */
    public int evaluate(){
        return expr.evaluate();
    }
}
