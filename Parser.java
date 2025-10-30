package domain;
import globalexceptions.*;
/**
 * class for a parser which contains information, parse method, match method, and necessary recursive methods for recursive descent
 */
public class Parser {

    private LexicalAnalyzer lex;
    private Token current_token;
    /**
     * constructor for parser, takes in lexical analyzer
     * @param lex - lexical analyzer
     */
    public Parser(LexicalAnalyzer lex) throws InvalidParse{
        this.lex = lex;
        this.current_token = lex.get_token();
    }

    /**
     * method to start parsing a line
     * @print row is valid
     * @throws InvalidParse if row is invalid
     */
    public void parse() throws InvalidParse{
        expression();
        System.out.println("Row " + current_token.get_row() + " is valid.");
    }

    /**
     * method to match current token to expected token type
     * @throws InvalidParse if tokens do not match
     */
    public void match(TokenType expected) throws InvalidParse{
        //check if current token matches expected, if so get next token
        if(current_token.get_type() == expected){
            current_token = lex.get_token();
        }
        else{
            throw new InvalidParse("Invalid token \"" + current_token + "\" on row " + current_token.get_row() + ", col " + current_token.get_col() + " while parsing. Expected " + expected);
        }
    }

    /**
     * method for an expression definition
     * @throws InvalidParse
     */
    public void expression() throws InvalidParse{
        term();
        expression_prime();
    }

    /**
     * method for an expression prime definition, matches + or -
     * @throws InvalidParse
     */
    public void expression_prime() throws InvalidParse{
        if(current_token.get_type() == TokenType.ADDITION){
            match(TokenType.ADDITION);
            term();
            expression_prime();
        }
        else if(current_token.get_type() == TokenType.SUBTRACTION){
            match(TokenType.SUBTRACTION);
            term();
            expression_prime();
        }
    }

    /**
     * method for a term definition
     * @throws InvalidParse
     */
    public void term() throws InvalidParse{
        factor();
        term_prime();
    }

    /**
     * method for a term prime definition, matches * or /
     * @throws InvalidParse
     */
    public void term_prime() throws InvalidParse{
        if(current_token.get_type() == TokenType.MULTIPLICATION){
            match(TokenType.MULTIPLICATION);
            factor();
            term_prime();
        }
        else if(current_token.get_type() == TokenType.DIVISION){
            match(TokenType.DIVISION);
            factor();
            term_prime();
        }
    }

    /**
     * method for a factor definition, matches ( & ), or - (unary)
     * @throws InvalidParse
     */
    public void factor() throws InvalidParse{
        if(current_token.get_type() == TokenType.LEFT_PAREN){
            match(TokenType.LEFT_PAREN);
            expression();
            match(TokenType.RIGHT_PAREN);
        }
        else if(current_token.get_type() == TokenType.SUBTRACTION){
            match(TokenType.SUBTRACTION);
            expression();
        }
        else {
            number();
        }
    }

    /**
     * method for a number definition
     * @throws InvalidParse if token is unrecognized in grammar
     */
    public void number() throws InvalidParse{
        if(current_token.get_type() == TokenType.INT_LIT){
            match(TokenType.INT_LIT);
        }
        else{
            //skip empty rows
            if(current_token.get_type() == TokenType.EOS){
                return;
            }
            throw new InvalidParse("Invalid token \"" + current_token + "\" on row " + current_token.get_row() + ", col " + current_token.get_col() + " while parsing.");
        }
    }
}
