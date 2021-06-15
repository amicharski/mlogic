import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    static final Set<TokenType> expression = new HashSet<>();
    static {
        expression.add(TokenType.LEFT_PAREN);
        expression.add(TokenType.RIGHT_PAREN);
        expression.add(TokenType.PRODUCTION);
        expression.add(TokenType.IDENTIFIER);
    }
    ArrayList<Pair<TokenType, String>> tokenizer;
    ArrayList<TokenType> tokens;


    public Parser(ArrayList<Pair<TokenType, String>> tokenizer){
        this.tokenizer = tokenizer;
        this.tokens = new ArrayList<>();
        //instantiate tree
    }

    public void launch(){ //TODO
        for(Pair<TokenType, String> pair : tokenizer){
            System.out.println(pair);
            tokens.add(pair.getKey());
        }
    }

//    private class AST {
//        Collection<Stmt> stmts;
//    }
//
//    private class Stmt {
//        Iden identifier;
//        Collection<Expr> exprs;
//    }
//
//    private class Iden {
//        String name;
//    }
//
//    private class Prod {
//        String prod;
//    }
//
//    private class Expr {
//        Collection<Expr> exprs;
//    }
}
