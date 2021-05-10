import java.util.ArrayList;
import java.util.Stack;

public class Tokenizer {
    String program;
    Stack<Character> pushdownAutomata;
    ArrayList<TokenType> tokens;

    public Tokenizer(String program){
        this.program = program;
    }

    public void launch(){
        int line = 1;
        for(String l : program.split("\n")) {
            pushdownAutomata = new Stack<>();
            if(l.startsWith("CHECK ")){
                System.out.println("Check statement");
                tokens.add(TokenType.CHECK);
            } else if(l.startsWith("SET ")){
                System.out.println("Set statement");
                tokens.add(TokenType.SET);
            } else if(l.isEmpty()){
                System.out.println("Empty Line");
            } else {
                if(!isExpression(l)){
                    System.out.println("Syntax Error on Line #" + line);
                    System.exit(1);
                } else {
                    System.out.println("Expression statement");
                }
            }
            line++;
        }
    }

    public boolean isExpression(String expr){
        if(expr.isEmpty()){
            //System.out.println("Empty String");
            return pushdownAutomata.isEmpty();
        }
        try {
            //System.out.println("Expr: " + expr);
            //System.out.println("Char: " + expr.toCharArray()[0]);
            switch(expr.toCharArray()[0]){
                case ' ':
                    return isExpression(expr.substring(1));
                case '(':
                    pushdownAutomata.push('P');
                    tokens.add(TokenType.LEFT_PAREN);
                    return isExpression(expr.substring(1));
                case ')': //fix this
                    //System.out.println(") detected");
                    if(pushdownAutomata.peek() == 'P'){
                        pushdownAutomata.pop();
                        tokens.add(TokenType.RIGHT_PAREN);
                        return isExpression(expr.substring(1));
                    } else {
                        return false;
                    }
                case '\'':
                    if (pushdownAutomata.isEmpty() || pushdownAutomata.peek() != 'S'){
                        //System.out.println("substring 1: " + expr.substring(1));
                        final boolean match = expr.substring(1).matches("^[a-z]+'") ||
                                expr.substring(1).matches("^[a-z]+' [A-Z]+ .*") ||
                                expr.substring(1).matches("^[a-z]+'\\).*");
                        //System.out.println("matches: " + match);
                        if(match){
                            pushdownAutomata.push('S');
                            tokens.add(TokenType.PRODUCTION);
                            return isExpression(expr.substring(expr.indexOf('\'', 1)));
                        } else {
                            return false;
                        }
                    } else {
                        pushdownAutomata.pop();
                        return isExpression(expr.substring(1));
                    }
                default:
                    if(expr.matches("^[A-Z]+ .*")){
                        tokens.add(TokenType.IDENTIFIER);
                        return isExpression(expr.substring(expr.indexOf(' ', 1)));
                    } else {
                        return false;
                    }
            }
        } catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
            //System.out.println("pushdownAutomata.empty: " + pushdownAutomata.isEmpty());
            return false;
        }
        //you shouldn't get here
    }
}
