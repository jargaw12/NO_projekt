import gen.CBaseListener;
import gen.CParser;

public class TreeEvaluationListener extends CBaseListener {
    Counter counter = new Counter();
    CParser parser;

    public TreeEvaluationListener(CParser parser) {
        this.parser = parser;
    }

    @Override
    public void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        super.enterCompoundStatement(ctx.compoundStatement());
    }

    @Override
    public void exitRelationalExpression(CParser.RelationalExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
//            counter.addOperand(ctx.getChild(0).getText());
//            counter.addOperator(ctx.getChild(1).getText());
//            counter.addOperand(ctx.getChild(2).getText());
        }
        super.exitRelationalExpression(ctx);
    }

    @Override
    public void exitAdditiveExpression(CParser.AdditiveExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
//            counter.addOperand(ctx.getChild(0).getText());
//            counter.addOperator(ctx.getChild(1).getText());
//            counter.addOperand(ctx.getChild(2).getText());
        }
        super.exitAdditiveExpression(ctx);
    }

    @Override
    public void enterMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
//            enterMultiplicativeExpression((CParser.MultiplicativeExpressionContext) ctx.getChild(0));
//            counter.addOperand(ctx.getChild(0).getText());
            counter.addOperator(ctx.getChild(1).getText());
//            counter.addOperand(ctx.getChild(2).getText());
//            enterMultiplicativeExpression((CParser.MultiplicativeExpressionContext) ctx.getChild(2));
        }
//        super.enterMultiplicativeExpression(ctx);
    }

    @Override
    public void enterPrimaryExpression(CParser.PrimaryExpressionContext ctx) {
        counter.addOperand(ctx.getText());
        super.enterPrimaryExpression(ctx);
    }

    @Override
    public void exitMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
//            counter.addOperand(ctx.getChild(0).getText());
//            counter.addOperator(ctx.getChild(1).getText());
//            counter.addOperand(ctx.getChild(2).getText());
        }
        super.exitMultiplicativeExpression(ctx);
    }

    @Override
    public void exitEqualityExpression(CParser.EqualityExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
//            counter.addOperand(ctx.getChild(0).getText());
//            counter.addOperator(ctx.getChild(1).getText());
//            counter.addOperand(ctx.getChild(2).getText());
        }
        super.exitEqualityExpression(ctx);
    }

    @Override
    public void exitAssignmentExpression(CParser.AssignmentExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
//            counter.addOperand(ctx.getChild(0).getText());
//            counter.addOperator(ctx.getChild(1).getText());
//            counter.addOperand(ctx.getChild(2).getText());
        }
        super.exitAssignmentExpression(ctx);
    }

    @Override
    public void enterPostfixExpression(CParser.PostfixExpressionContext ctx) {
        super.enterPostfixExpression(null);
    }

    @Override
    public void exitCompilationUnit(CParser.CompilationUnitContext ctx) {
        System.out.println("Tokens:" + parser.getTokenTypeMap());
        super.exitCompilationUnit(ctx);
    }
}
