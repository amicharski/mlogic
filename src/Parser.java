import java.util.ArrayList;
import java.util.Map;

public class Parser {
    ArrayList<Pair<TokenType, String>> tokenizer;

    public Parser(ArrayList<Pair<TokenType, String>> tokenizer){
        this.tokenizer = tokenizer;
    }

    public void launch(){ //TODO
        for(Pair<TokenType, String> pair : tokenizer){
            System.out.println(pair);
        }
    }
}
