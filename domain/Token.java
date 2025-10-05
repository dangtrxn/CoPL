package domain;
/**
 * class for a lexical analyzer which contains information, get_token method, and additional helper methods
 */
public class Token {
    private TokenType type;
    private String lexeme;
    private int row;
    private int col;

    /**
     * constructor for lexical analyzer, takes in source string to analyze
     * @param type - TokenType
     * @param lexeme - subject of token
     * @param row - token row
     * @param col - token col
     */
    public Token(TokenType type, String lexeme, int row, int col){
        this.type = type;
        this.lexeme = lexeme;
        this.row = row;
        this.col = col;
    }

    /**
     * method to get token type
     * @return token type
     */
    public TokenType get_type(){
        return type;
    }
    /**
     * method to get token lexeme
     * @return token lexeme
     */
    public String get_lexeme(){
        return lexeme;
    }
    /**
     * method to get token row
     * @return token row
     */
    public int get_row(){
        return row;
    }
    /**
     * method to get token col
     * @return token col
     */
    public int get_col(){
        return col;
    }
    /**
     * method to print token information
     * @return token information in string form
     */
    @Override
    public String toString(){
        return "<Token: " + get_lexeme() + " Type: " + get_type() + " Row: " + get_row() + " Col: " + get_col() + ">";
    }
}
