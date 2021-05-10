import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Mlogic {
    public static void main(String[] args) throws IOException {
        if(args.length == 1){
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            br.lines().forEach(l -> sb.append(l).append('\n'));
            Tokenizer tokenizer = new Tokenizer(sb.toString());
            tokenizer.launch();
        } else {
            System.out.println("Expected a file");
        }
    }
}
