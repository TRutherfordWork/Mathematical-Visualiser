package MathsVis.interpreter.Nodes;

import MathsVis.interpreter.Token;
import java.util.ArrayList;
import java.util.Objects;

public class Graph extends TreeNode {

    private final ArrayList<Token> values;

    private final ArrayList<Token> expression;

    private ArrayList<Token> yPointsTokens;

    private ArrayList<Double> x = new ArrayList<Double>();

    private ArrayList<Double> y = new ArrayList<Double>();

    public Graph(ArrayList<Token> values, ArrayList<Token> expression) {
        this.values = values;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public ArrayList<Token> getyPoints() {
        return yPointsTokens;
    }

    public boolean containsOtherThanX() {
        //iterates though the tokens in expression variable saved to the object.
        for (Token token:
                expression) {
            //Checks to see if the token is a variable with no value
            if (token.getTokenType().equals(Token.TokenTypes.INDETERMINATE)) {
                // if the token is x do nothing.
                if(Objects.equals(token.getVar(), "x")) {
                    System.out.println("X");
                } else {
                    // if the token is not x (e.g. y, m, c, a) return false.
                    return true;
                }
            }
        }
        // returns false at the end.
        return false;
    }

    public void insertArgs() {

        ArrayList<Token> output = new ArrayList<Token>();

        for (Token t :
                values) {
            x.add(t.getValue());
                for (Token e : expression) {
                    if (e.getTokenType().equals(Token.TokenTypes.INDETERMINATE)) {
                        output.add(t);
                    } else {
                        output.add(e);
                    }
                }
            output.add(new Token(Token.TokenTypes.SEMICOLON));
            }
        output.add(new Token(Token.TokenTypes.NULL));
        this.yPointsTokens = output;
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public ArrayList<Double> getY() {
        return y;
    }

    public double eval() {
        return 0;
    }
}

