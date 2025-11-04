package domain.node;

/**
 * class for num node, extends FactorNode, implements evaluate for integer literal
 */
//(int_lit)
public class NumNode extends FactorNode{
    private int num;

    /**
     * constructor for NumNode, takes in integer num
     */
    public NumNode(int num){
        this.num = num;
    }

    /**
     * method to evaluate num node
     * @return integer
     */
    @Override
    public int evaluate(){
        return num;
    }
}
