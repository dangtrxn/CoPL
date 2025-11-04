package domain.node;

/**
 * class for paren factor node, extends FactorNode, implements evaluate for parenthesized expressions
 */
//(<expr>)
public class ParenFactorNode extends FactorNode{
    private ExpressionNode expr;

    /**
     * constructor for ParenFactorNode, takes in expression node
     */
    public ParenFactorNode(ExpressionNode expr){
        this.expr = expr;
    }

    /**
     * method to evaluate paren factor node
     * @return expression evaluation
     */
    @Override
    public int evaluate(){
        return expr.evaluate();
    }
}
