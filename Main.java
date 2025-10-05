import java.util.*;
import java.io.*;
import domain.*;
import globalexceptions.*;
import domain.LexicalAnalyzer;
/**
 * program reads from input file
 * through lexical analyzer object, creates tokens and displays token information
 * catches any exceptions
 */
public class Main {
    public static void main(String[] args){
        //do not run program without a given input file
        if (args.length == 0){
            System.err.println("Usage: java Main <inputfile>");
            return;
        }

        try{
            //file object of input file and scanner to read file
            File source = new File(args[0]);
            Scanner sc = new Scanner(source);
            ArrayList<Token> tokens_list = new ArrayList<>();

            //loop through file lines
            while(sc.hasNextLine()){
                //lexical analyzer object starting on first line of file
                LexicalAnalyzer lex = new LexicalAnalyzer(sc.nextLine());
                //loop through file lines, create tokens, and display tokens
                //terminate loop when EOS
                Token token;
                do{
                    token = lex.get_token();
                    tokens_list.add(token);
                    System.out.println(token.toString());
                }while(token.get_type() != TokenType.EOS);
            }

            sc.close();

        //catch exceptions
        }
        catch (InvalidTokenException e){
            System.err.println(e.getMessage());
        }
        catch(InvalidArgumentException e){
            System.err.println(e.getMessage());
        }
        catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }
        catch(Exception e){
            System.err.println("Unexpected error... terminating");
        }
    }
}