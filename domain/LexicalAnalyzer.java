package domain;
import globalexceptions.InvalidTokenException;
/**
 * class for a lexical analyzer which contains information, get_token method, and additional helper methods
 */
public class LexicalAnalyzer {
    private String source;
    private static int row_index = 0;
    private int col_index;
    /**
     * constructor for lexical analyzer, takes in source string to analyze
     */
    public LexicalAnalyzer(String source){
        this.source = source;
        this.col_index = 0;
    }

    /**
     * method to retrieve token based on the token type, skips all whitespace
     * @throws InvalidTokenException if an undefined token is being analyzed
     * @return current char as a token
     */
    public Token get_token() throws InvalidTokenException {
        //skip all whitespace
        while (col_index < source.length() && Character.isWhitespace(source.charAt(col_index))) {
            col_index++;
        }

        //if all characters consumed, return EOS
        if (col_index >= source.length()) {
            return new Token(TokenType.EOS, "", row_index++, col_index);
        }

        //get current char
        char current = source.charAt(col_index);


        //identify parenthesis
        if (isParenthesis(current)){
            //return left paren token
            if (current == '('){
                Token t = new Token(TokenType.LEFT_PAREN, "(", row_index, col_index);
                col_index++;
                return t;
            }
            else{
                //return right paren token
                Token t = new Token(TokenType.RIGHT_PAREN, ")", row_index, col_index);
                col_index++;
                return t;
            }
        }

        //identify operators
        if (isOperator(current)){
            switch (current){
                case '+':
                    //return + token
                    Token add = new Token(TokenType.ADDITION, "+", row_index, col_index);
                    col_index++;
                    return add;
                case '-':
                    //return - token
                    Token sub = new Token(TokenType.SUBTRACTION, "-", row_index, col_index);
                    col_index++;
                    return sub;
                case '*':
                    //return * token
                    Token mult = new Token(TokenType.MULTIPLICATION, "*", row_index, col_index);
                    col_index++;
                    return mult;
                case '/':
                    //return / token
                    Token div = new Token(TokenType.DIVISION, "/", row_index, col_index);
                    col_index++;
                    return div;
            }
        }

        //identify digits
        if (Character.isDigit(current)) {
            int start_index = col_index;
            while (col_index < source.length() && Character.isDigit(source.charAt(col_index))) {
                col_index++;
            }
            String lexeme = source.substring(start_index, col_index);
            return new Token(TokenType.INT_LIT, lexeme, row_index, start_index);
        }

        //INVALID TOKEN THROW ERROR
        throw new InvalidTokenException("Invalid token type in get_token()");
    }

    /**
     * method to identify parenthesis
     * @return true if paren, false otherwise
     */
    public boolean isParenthesis(char c){
        return c == '(' || c ==')';
    }
    /**
     * method to identify operator
     * @return true if paren, false otherwise
     */
    public boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
