import java.util.ArrayList;

public class Parser {
    ArrayList<TokenType> tokenizer;

    public Parser(ArrayList<TokenType> tokenizer){
        this.tokenizer = tokenizer;
    }

    public void launch(){ //TODO
        tokenizer.forEach(System.out::println);
    }
}
