import gen.CBaseVisitor;
import gen.CParser;
import org.antlr.v4.runtime.tree.TerminalNode;

public class TreeEvaluationVisitor extends CBaseVisitor {

    public Counter counter = new Counter();
    CParser parser;

    public TreeEvaluationVisitor(CParser parser) {
        this.parser = parser;
    }

    @Override
    public Object visitFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        visitCompoundStatement(ctx.compoundStatement());
        return null;
    }

    @Override
    public Object visitDeclaration(CParser.DeclarationContext ctx) {
        if (ctx.initDeclaratorList() != null && ctx.initDeclaratorList().initDeclarator().getChildCount() == 3) {
            counter.addOperator(ctx.initDeclaratorList().initDeclarator().getChild(1).getText());
        } else return null;
        return super.visitDeclaration(ctx);
    }

    @Override
    public Object visitTable(CParser.TableContext ctx) {
        counter.addOperand(ctx.getText());
        return null;
    }

    @Override
    public Object visitInitDeclaratorList(CParser.InitDeclaratorListContext ctx) {
        if (ctx.getChildCount() > 1 && ctx.Comma() != null) return null;
        return super.visitInitDeclaratorList(ctx);
    }

    @Override
    public Object visitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
        if (ctx.getChildCount() > 1) return null;
        if (ctx.getChildCount() == 1) {
            counter.addOperand(ctx.getText());
        }
        return super.visitDirectDeclarator(ctx);
    }

    @Override
    public Object visitJumpStatement(CParser.JumpStatementContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimaryExpression(CParser.PrimaryExpressionContext ctx) {
        if (ctx.getChildCount() == 1 && ctx.StringLiteral().size() == 0) {
            counter.addOperand(ctx.getText());
        }
        return super.visitPrimaryExpression(ctx);
    }

    @Override
    public Object visitMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            counter.addOperator(ctx.getChild(1).getText());
        }
        return super.visitMultiplicativeExpression(ctx);
    }


    @Override
    public Object visitPostfixExpression(CParser.PostfixExpressionContext ctx) {
        if (ctx.getChildCount() > 2) {
            if (ctx.Arrow() != null) {
                counter.addOperand(ctx.getText());
                return null;
            } else return null;
        } else if (ctx.getChildCount() == 2) {
            counter.addOperator(ctx.getChild(1).getText());
        }
        return super.visitPostfixExpression(ctx);
    }

    @Override
    public Object visitFunctionCall(CParser.FunctionCallContext ctx) {
        return super.visitFunctionCall(ctx);
    }

    @Override
    public Object visitArgumentExpressionList(CParser.ArgumentExpressionListContext ctx) {
        String argument = ctx.assignmentExpression().getText();
        if (!argument.contains("+") && !argument.contains("*") && !argument.contains("/") && !argument.contains("="))
            return null;
        return super.visitArgumentExpressionList(ctx);
    }

    @Override
    public Object visitCastExpression(CParser.CastExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            counter.addOperator(ctx.getChild(1).getText());
        }
        return super.visitCastExpression(ctx);
    }

    @Override
    public Object visitAdditiveExpression(CParser.AdditiveExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            counter.addOperator(ctx.getChild(1).getText());
        }
        return super.visitAdditiveExpression(ctx);
    }

    @Override
    public Object visitShiftExpression(CParser.ShiftExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            if (ctx.getChild(0).getText().equals("cout")) return null;
            else {
                counter.addOperator(ctx.getChild(1).getText());
            }
        }
        return super.visitShiftExpression(ctx);
    }

    @Override
    public Object visitRelationalExpression(CParser.RelationalExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            counter.addOperator(ctx.getChild(1).getText());
        }
        return super.visitRelationalExpression(ctx);
    }

    @Override
    public Object visitEqualityExpression(CParser.EqualityExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            counter.addOperator(ctx.getChild(1).getText());
        }
        return super.visitEqualityExpression(ctx);
    }

    @Override
    public Object visitAssignmentExpression(CParser.AssignmentExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            counter.addOperator(ctx.getChild(1).getText());
        }
        return super.visitAssignmentExpression(ctx);
    }

    @Override
    public Object visitLogicalAndExpression(CParser.LogicalAndExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            counter.addOperator(ctx.getChild(1).getText());
        }
        return super.visitLogicalAndExpression(ctx);
    }

    @Override
    public Object visitLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            counter.addOperator(ctx.getChild(1).getText());
        }
        return super.visitLogicalOrExpression(ctx);
    }

    @Override
    public Object visitLabeledStatement(CParser.LabeledStatementContext ctx) {
        if (((TerminalNode) ctx.getChild(0)).getSymbol().getType() == CParser.Case) {
            return super.visitStatement(ctx.statement());
        }
        return super.visitLabeledStatement(ctx);
    }

    @Override
    public Object visitSelectionStatement(CParser.SelectionStatementContext ctx) {
        if (((TerminalNode) ctx.getChild(0)).getSymbol().getType() == CParser.Switch) {
            return super.visitStatement(ctx.statement(0));
        }
        return super.visitSelectionStatement(ctx);
    }

    @Override
    public Object visitUnaryExpression(CParser.UnaryExpressionContext ctx) {
        if (ctx.PlusPlus() != null || ctx.MinusMinus() != null) {
            counter.addOperator(ctx.getChild(0).getText());
        }
        if (ctx.unaryOperator() != null && ctx.unaryOperator().Not() != null) {
            counter.addOperator(ctx.unaryOperator().getChild(0).getText());
        }
        return super.visitUnaryExpression(ctx);
    }
}
