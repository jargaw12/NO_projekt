import gen.CLexer;
import gen.CParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class Test {
    private final static String FILE_NAME = "Program2.c";

    public void test() throws IOException, URISyntaxException {
        File file = new File(getClass().getResource(FILE_NAME).getFile());
        InputStream inputStream = new FileInputStream(file);
        CharStream in = CharStreams.fromStream(inputStream);
        CLexer lexer = new CLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CParser parser = new CParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.compilationUnit();
        System.out.println(tree.toStringTree(parser));

        int errors = parser.getNumberOfSyntaxErrors();
        TreeEvaluationVisitor visitor = new TreeEvaluationVisitor(parser);
        if (errors == 0) {
            visitor.visit(tree);
            visitor.counter.printStatistics();
        } else {
            System.out.println("Ciąg wejściowy jest niepoprawny");
            System.out.println("Liczba błędów syntaktycznych: " + errors);

        }
//        System.out.println(tree.toStringTree(parser));
    }
}
