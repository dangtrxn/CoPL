package domain;

import globalexceptions.*;
import domain.node.*;

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
    public Parser(LexicalAnalyzer lex) throws InvalidParseException {
        this.lex = lex;
        this.current_token = lex.get_token();
    }

    /**
     * method to start parsing an expression
     * @throws InvalidParseException if row is invalid
     * @return expression node that is root of parse tree
     */
    public ExpressionNode parse() throws InvalidParseException {
        ExpressionNode expr = expression();

        //check for unexpected tokens at the end
        if(current_token.get_type() != TokenType.EOS){
            throw new InvalidTokenException("Unexpected token after expression: " + current_token);
        }

        return expr;
    }

    /**
     * method to match current token to expected token type
     * @param expected token type of expected token
     * @throws InvalidParseException if tokens do not match
     */
    public void match(TokenType expected) throws InvalidParseException {
        //check if current token matches expected, if so get next token
        if(current_token.get_type() == expected){
            current_token = lex.get_token();
        }
        else{
            throw new InvalidParseException("Invalid token \"" + current_token + "\"" + " while parsing. Expected " + expected);
        }
    }

    /**
     * method for an expression definition
     * @return expression node
     */
    //< Expression > ::= <Term> <Expression_Prime>
    public ExpressionNode expression() throws InvalidParseException {
        ExpressionNode termNode = term();
        return expression_prime(termNode);
    }

    /**
     * method for an expression prime definition, matches + or - operator
     * @param expr expression node from expression()
     * @return BinaryExpressionNode if operator is + or -, else return expr
     */
    /*
    < Expression_Prime > ::= “+” < Term > <Expression_Prime>
                            | “-” < Term > <Expression_Prime>
                            | null
     */
    public ExpressionNode expression_prime(ExpressionNode expr) throws InvalidParseException {
        if(current_token.get_type() == TokenType.ADDITION){
            match(TokenType.ADDITION);
            TermNode termNode = term();
            BinaryExpressionNode addExpr = new BinaryExpressionNode(expr,TokenType.ADDITION,termNode);
            return expression_prime(addExpr);
        }
        else if(current_token.get_type() == TokenType.SUBTRACTION){
            match(TokenType.SUBTRACTION);
            TermNode termNode = term();
            BinaryExpressionNode subExpr = new BinaryExpressionNode(expr,TokenType.SUBTRACTION,termNode);
            return expression_prime(subExpr);
        }

        return expr;
    }

    /**
     * method for a term definition
     * @return TermNode from term_prime()
     */
    //< Term > ::= <Factor> <Term_Prime>
    public TermNode term() throws InvalidParseException {
        FactorNode factorNode = factor();
        return term_prime(factorNode);
    }

    /**
     * method for a term prime definition, matches * or / operator
     * @param term FactorNode from term()
     * @return BinaryTermNode if operator is * or /, else return term
     */
    /*
    <Term_Prime> ::= “*” <Factor> <Term_Prime>
                    | “/” <Factor> <Term_Prime>
                    | null
     */
    public TermNode term_prime(TermNode term) throws InvalidParseException {
        if(current_token.get_type() == TokenType.MULTIPLICATION){
            match(TokenType.MULTIPLICATION);
            FactorNode factor = factor();
            BinaryTermNode multTerm = new BinaryTermNode(term,TokenType.MULTIPLICATION,factor);
            return term_prime(multTerm);
        }
        else if(current_token.get_type() == TokenType.DIVISION){
            match(TokenType.DIVISION);
            FactorNode factor = factor();
            BinaryTermNode divTerm = new BinaryTermNode(term,TokenType.DIVISION,factor);
            return term_prime(divTerm);
        }

        return term;
    }

    /**
     * method for a factor definition, matches ( expr ), or -(unary)
     * @return ParenFactorNode if token type is paren
     * @return NegFactorNode if token type is unary -
     * @return NumNode if token type is int lit
     */
    //<Factor> ::= “(“ <Expression> “)” | “-” <Expression> | <Number>
    public FactorNode factor() throws InvalidParseException {
        if(current_token.get_type() == TokenType.LEFT_PAREN){
            match(TokenType.LEFT_PAREN);
            ExpressionNode expr = expression();
            match(TokenType.RIGHT_PAREN);
            return new ParenFactorNode(expr);
        }
        else if(current_token.get_type() == TokenType.SUBTRACTION){
            match(TokenType.SUBTRACTION);
            FactorNode factorNode = factor();
            return new NegFactorNode(factorNode);
        }
        else{
            int intLit = number();
            return new NumNode(intLit);
        }
    }

    /**
     * method for a number definition
     * @throws InvalidParseException if token is unrecognized in grammar
     * @return integer form of token lexeme
     */
    //< Number > ::= Int-Lit
    public int number() throws InvalidParseException {
        String num = current_token.get_lexeme();
        match(TokenType.INT_LIT);
        return Integer.parseInt(num);
    }
}
