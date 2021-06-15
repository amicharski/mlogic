import java.util.*;

public class Tokenizer {
    String program;
    Stack<Character> pushdownAutomata;
    ArrayList<Pair<TokenType, String>> tokens;

    public Tokenizer(String program){
        this.program = program;
        tokens = new ArrayList<>();
    }

    public void launch(){
        int line = 1;
        for(String l : program.split("\n")) {
            pushdownAutomata = new Stack<>();
            if(l.startsWith("CHECK ")){
                tokens.add(new Pair<>(TokenType.CHECK, "CHECK"));
                String lend = l.substring(6);
                if(lend.matches("^'[a-z]+'")){
                    tokens.add(new Pair<>(TokenType.PRODUCTION, lend.substring(1, lend.indexOf("'", 2))));
                } else {
                    System.out.println("Syntax Error on Line #" + line);
                    System.exit(1);
                }
            } else if(l.startsWith("SET ")){
                tokens.add(new Pair<>(TokenType.SET, "SET"));
                String lend = l.substring(4);
                if(lend.matches("^'[a-z]+' .*")){
                    int exprStart = lend.indexOf("'", 2);
                    tokens.add(new Pair<>(TokenType.PRODUCTION, lend.substring(1, exprStart)));
                    if(!isExpression(lend.substring(exprStart+1))){
                        System.out.println("Syntax Error on Line #" + line);
                        System.exit(1);
                    }
                } else {
                    System.out.println("Syntax Error on Line #" + line);
                    System.exit(1);
                }
            } else if(l.isEmpty()){
            } else {
                if(!isExpression(l)){
                    System.out.println("Syntax Error on Line #" + line);
                    System.exit(1);
                } else {
                }
            }
            tokens.add(new Pair<>(TokenType.NEW_LINE, "\n"));
            line++;
        }
        Parser parser = new Parser(tokens);
        parser.launch();
    }

    public boolean isExpression(String expr){
        if(expr.isEmpty()){
            return pushdownAutomata.isEmpty();
        }
        try {
            switch(expr.toCharArray()[0]){
                case '#':
                    return true;
                case ' ':
                    return isExpression(expr.substring(1));
                case '(':
                    pushdownAutomata.push('P');
                    tokens.add(new Pair<>(TokenType.LEFT_PAREN, "("));
                    return isExpression(expr.substring(1));
                case ')': //fix this
                    if(pushdownAutomata.peek() == 'P'){
                        pushdownAutomata.pop();
                        tokens.add(new Pair<>(TokenType.RIGHT_PAREN, ")"));
                        return isExpression(expr.substring(1));
                    } else {
                        return false;
                    }
                case '\'':
                    if (pushdownAutomata.isEmpty() || pushdownAutomata.peek() != 'S'){
                        final boolean match = expr.substring(1).matches("^[a-z]+'") ||
                                expr.substring(1).matches("^[a-z]+' [A-Z]+ .*") ||
                                expr.substring(1).matches("^[a-z]+'\\).*");
                        if(match){
                            pushdownAutomata.push('S');
                            tokens.add(new Pair<>(TokenType.PRODUCTION, expr.substring(1, expr.indexOf("'", 2))));
                            return isExpression(expr.substring(expr.indexOf('\'', 1)));
                        } else {
                            return false;
                        }
                    } else {
                        pushdownAutomata.pop();
                        return isExpression(expr.substring(1));
                    }
                default:
                    if(expr.matches("^[ ]?FALSE$") || expr.matches("^[ ]?TRUE$")){
                        tokens.add(new Pair<>(TokenType.IDENTIFIER, expr));
                        return true;
                    }
                    if(expr.matches("^[A-Z]+ .*")){
                        tokens.add(new Pair<>(TokenType.IDENTIFIER, expr.substring(0, expr.indexOf(" ", 1))));
                        return isExpression(expr.substring(expr.indexOf(" ", 1)));
                    } else {
                        return false;
                    }
            }
        } catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
            return false;
        }
        //you shouldn't get here
    }
}
