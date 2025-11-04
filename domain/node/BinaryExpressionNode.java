package domain.node;

import domain.*;
import globalexceptions.InvalidParseException;

/**
 * class for a binary expression node, inherits ExpressionNode, implements evaluate for addition and subtraction
 */
/*
(expr + term)
(expr - term)
 */
public class BinaryExpressionNode extends ExpressionNode{
    private ExpressionNode left;
    private TokenType operator;
    private TermNode right;

    /**
     * constructor for BinaryExpressionNode, takes in expression node for left child, operator, and term node for right child
     */
    public BinaryExpressionNode(ExpressionNode left, TokenType operator, TermNode right){
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    /**
     * method to evaluate binary expression, evaluate left child then right child, operate on both children
     * @throws InvalidParseException if invalid operator found
     * @return operation value on both children
     */
    @Override
    public int evaluate(){
        int leftVal = left.evaluate();
        int rightVal = right.evaluate();

        switch(operator){
            case ADDITION:
                return leftVal + rightVal;
            case SUBTRACTION:
                return leftVal - rightVal;
            default:
                throw new InvalidParseException("Invalid operator in ExpressionNodeBinary: " + operator);
        }
    }
}
