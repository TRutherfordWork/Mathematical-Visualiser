package MathsVis.interpreter.Nodes;

import MathsVis.interpreter.Token;

import java.util.ArrayList;

public class UserFunction {

    private ArrayList<Token> functionBody;
    private ArrayList<Token> functionBodyPopulated;
    private final ArrayList<String> argKeys;
    private final String functionName;
    private ArrayList<Token> OriginalFunctionBody;

    public ArrayList<Token> getFunctionBody() {
        return functionBody;
    }

    public ArrayList<String> getArgKeys() {
        return argKeys;
    }

    public String getFunctionName() {
        return functionName;
    }

    public ArrayList<Token> getFunctionBodyPopulated() {
        return functionBodyPopulated;
    }

    public UserFunction(ArrayList<Token> functionBody, ArrayList<String> argKeys, String functionName) {
        this.OriginalFunctionBody = functionBody;
        this.functionBody = functionBody;
        this.argKeys = argKeys;
        this.functionName = functionName;
    }

    @Override
    public String toString() {
        return "UserFunction{" +
                "functionBody=" + functionBody +
                ", argKeys=" + argKeys +
                ", functionName='" + functionName + '\'' +
                '}';
    }

    public void insertArgs(ArrayList<ArrayList<Token>> newArgs) {
        //this.functionBody = OriginalFunctionBody;
        ArrayList<Token> output = new ArrayList<Token>();

            if(argKeys.size() == newArgs.size()) {


                int index = 0;

                for (Token t :
                        functionBody) {
                for (String s : argKeys) {
                        if (t.getTokenType().equals(Token.TokenTypes.INDETERMINATE)) {
                            if (t.getVar().equals(s)) {
                                output.addAll(newArgs.get(index));
                            }
                        }

                    index++;

                    }
                if (!t.getTokenType().equals(Token.TokenTypes.INDETERMINATE)) {
                    output.add(t);
                    }

                index = 0;

                }
            } else {
                System.out.println("Size is not equal");
            }
            output.add(new Token(Token.TokenTypes.NULL));
            this.functionBodyPopulated = output;
        }
}
