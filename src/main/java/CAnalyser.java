import gen.CLexer;
import gen.CParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CAnalyser {
    public void analyse(File file) throws IOException {
        String content = new String(Files.readAllBytes(file.toPath()));
        String modifiedContent = content.replaceAll("#define .*", "");
        CharStream in = CharStreams.fromString(modifiedContent);
        CLexer lexer = new CLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CParser parser = new CParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.compilationUnit();

        int errors = parser.getNumberOfSyntaxErrors();
        TreeEvaluationVisitor visitor = new TreeEvaluationVisitor(parser);
        if (errors == 0) {
            System.out.println("Analiza programu: " + file.getName());
            visitor.visit(tree);
            visitor.counter.printStatistics();
        } else {
            System.out.println("Ciąg wejściowy jest niepoprawny");
            System.out.println("Liczba błędów syntaktycznych: " + errors);

        }
    }
}
