public class Tokenizer {
    String program;

    public Tokenizer(String program){
        this.program = program;
    }

    public void launch(){
        int line = 1;
        for(String l : program.split("\n")) {
            if(l.startsWith("CHECK ")){
                System.out.println("Check statement");
            } else if(l.startsWith("SET ")){
                System.out.println("Set statement");
            } else if(l.isEmpty()){
                System.out.println("Empty Line");
            } else {
                if(!isExpression(l)){
                    System.out.println("Syntax Error on Line #" + line);
                    System.exit(1);
                }
            }
            line++;
        }
    }

    public boolean isExpression(String expr){
        if(expr.isEmpty()){
            System.out.println("Empty String");
            return true;
        }
        try { //you used a regular language tokenizer you dingis
            System.out.println("Expr: " + expr);
            System.out.println("Char: " + expr.toCharArray()[0]);
            switch(expr.toCharArray()[0]){
                case '(':
                case ')': //fix this
                    return isExpression(expr.substring(1));
                case '\'':
                    if(!expr.substring(2).contains("\'")){
                        return false;
                    } else {
                        return isExpression(expr.substring(expr.indexOf('\'', 1)));
                    }
                default:
                    if(expr.matches("^[A-Z]+ ")){
                        return isExpression(expr.substring(expr.indexOf(' ', 1)));
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
