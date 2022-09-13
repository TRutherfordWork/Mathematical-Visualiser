package MathsVis.interpreter.Nodes;
import MathsVis.interpreter.Token;
import java.util.ArrayList;
import java.util.Objects;

public class Summation {

    int startOfSummation;
    int lastValueOfSummation;
    ArrayList<Token> formula = new ArrayList<Token>();
    private ArrayList<Token> allSums = new ArrayList<Token>();

    public Summation(int startOfSummation, int lastValueOfSummation, ArrayList<Token> formula) {
        this.startOfSummation = startOfSummation;
        this.lastValueOfSummation = lastValueOfSummation;
        this.formula = formula;
    }

    public boolean containsOtherThanX() {
        for (Token token:
                formula) {
            if (token.getTokenType().equals(Token.TokenTypes.INDETERMINATE)) {
                if(Objects.equals(token.getVar(), "x")) {

                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public void insertArgs() {

        ArrayList<Double> values = new ArrayList<Double>();

        for(int i = startOfSummation; i <= lastValueOfSummation; i++) {
            values.add((double) i);
        }

        for (Double t :
                values) {
            for (Token e : formula) {
                if (e.getTokenType().equals(Token.TokenTypes.INDETERMINATE)) {
                    allSums.add(new Token(Token.TokenTypes.INTEGER, t));
                } else {
                    allSums.add(e);
                }
            }
            allSums.add(new Token(Token.TokenTypes.SEMICOLON));
        }
        allSums.add(new Token(Token.TokenTypes.NULL));
    }

    public ArrayList<Token> getAllSums() {
        return allSums;
    }
}
