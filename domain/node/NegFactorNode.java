package domain.node;

/**
 * class for unary - operation, inherits FactorNode, implements evaluate for unary negation operation
 */
//(-factor)
public class NegFactorNode extends FactorNode{
    private ExpressionNode expr;

    /**
     * constructor for NegFactorNode, takes in expression node
     */
    public NegFactorNode(ExpressionNode expr){
        this.expr = expr;
    }

    /**
     * method to evaluate unary negation operation
     * @return negation operation on expression
     */
    @Override
    public int evaluate(){
        return -expr.evaluate();
    }
}
