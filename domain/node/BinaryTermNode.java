package domain.node;

import domain.TokenType;
import globalexceptions.InvalidParseException;

/**
 * class for binary term node, extends TermNode, implements evaluate for multiplication and division
 */
/*
(term * factor)
(term / factor)
 */
public class BinaryTermNode extends TermNode{
    private TermNode left;
    private TokenType operator;
    private FactorNode right;

    /**
     * constructor for BinaryTermNode, takes in term node for left child, operator, and factor node for right child
     */
    public BinaryTermNode(TermNode left, TokenType operator, FactorNode right){
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    /**
     * method to evaluate binary term, evaluate left child then right child, operate on both children
     * @throws InvalidParseException if invalid operator found
     * @return operation value on both children
     */
    @Override
    public int evaluate(){
        int leftVal = left.evaluate();
        int rightVal = right.evaluate();

        switch(operator){
            case MULTIPLICATION:
                return leftVal * rightVal;
            case DIVISION:
                return leftVal / rightVal;
            default:
                throw new InvalidParseException("Invalid operator in TermNodeBinary: " + operator);
        }
    }
}
