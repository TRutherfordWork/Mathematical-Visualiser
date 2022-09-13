package MathsVis.interpreter;

import java.util.ArrayList;


/**
 * Takes the user's input from the GUI entry box
 * Breaks up the input into tokens
 * Tokens defined in class: Token.java
 */

import java.util.*;

public class Lexer {

    private final ArrayList<Token> tokenList;
    private ErrorLog errorLog;
    private int expressionCount = 1;

    private static final String[] DIGITS = new String[] {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static final String[] LETTERS = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "o", "m", "n" , "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "O", "M", "N" , "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static final Set<String> DIGITS_SET = new HashSet<>(Arrays.asList(DIGITS));

    public static final Set<String> LETTERS_SET = new HashSet<>(Arrays.asList(LETTERS));

    public static final Set<String> VARS_SET = new HashSet<>();

    private TreeMap<String, Token> latexFunctions = new TreeMap<>();

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    public ErrorLog getErrorLog() {
        return errorLog;
    }

    public String toString() {
        return "Lexer{" +
                "tokenList=" + tokenList +
                '}';
    }

    public Lexer(String s) {

        //Tree to store latex function tokens

        latexFunctions.put("frac", new Token(Token.TokenTypes.FRACTION));

        latexFunctions.put("sqrt", new Token(Token.TokenTypes.ROOT));

        latexFunctions.put("log", new Token(Token.TokenTypes.LOG));

        latexFunctions.put("cos", new Token(Token.TokenTypes.COS));

        latexFunctions.put("sin", new Token(Token.TokenTypes.SIN));

        latexFunctions.put("graph", new Token(Token.TokenTypes.GRAPH));

        latexFunctions.put("tan", new Token(Token.TokenTypes.TAN));

        latexFunctions.put("pi", new Token(Token.TokenTypes.PIE));

        latexFunctions.put("varepsilon", new Token(Token.TokenTypes.EULER));

        latexFunctions.put("ln", new Token(Token.TokenTypes.NATURAL_LOG));

        latexFunctions.put("diff", new Token(Token.TokenTypes.DIFFERENTIATION));

        latexFunctions.put("int", new Token(Token.TokenTypes.INTEGRATION));

        latexFunctions.put("sum", new Token(Token.TokenTypes.SUMMATION));

        tokenList = new ArrayList<>();

        errorLog = new ErrorLog();

        String[] charList =  s.replaceAll("\\s+","").split("(?!^)");

        int listLength = charList.length - 1;
        int errorFlag = 0;

        for(int i = 0; i <= listLength; i++) {

            if("%".equals(charList[i])){
                i++;
                while(!"%".equals(charList[i])) {
                    i++;
                }
                if (i == listLength) {
                    break;
                }
            }

            switch(charList[i]) {

                case "$":

                    StringBuilder temp = new StringBuilder();

                    for(int j = i + 1; j <= listLength; j++) {
                        if(LETTERS_SET.contains(charList[j])) {
                            temp.append(charList[j]);
                            i++;
                        } else {
                            tokenList.add(new Token(Token.TokenTypes.USER_FUNCTION, temp.toString()));
                            //i++;
                            break;
                        }
                    }
//
//                    if((charList[i]).equals("(")) {
//                        i++;
//
//                        temp = new StringBuilder();
//
//                        tokenList.add(new Token(Token.TokenTypes.LEFT_PREFERENCE));
//
//                        while (!((charList[i]).equals(")"))) {
//
//
//                            if (LETTERS_SET.contains(charList[i])) {
//                                temp.append(charList[i]);
//                                i++;
//                            } else if (charList[i].equals(",")) {
//                                tokenList.add(new Token(Token.TokenTypes.INDETERMINATE, temp.toString()));
//                                tokenList.add(new Token(Token.TokenTypes.DELIMITER));
//                                temp = new StringBuilder();
//                                i++;
//                            } else {
//                                break;
//                            }
//                        }
//
//                        if ((charList[i]).equals(")")) {
//
//                            if(!temp.toString().equals("")) {
//                                tokenList.add(new Token(Token.TokenTypes.INDETERMINATE, temp.toString()));
//                            }
//                            tokenList.add(new Token(Token.TokenTypes.RIGHT_PREFERENCE));
//
//                            continue;
//
//                        }
//
//                        break;
//                    }
                    continue;
                case "\\":
                    temp = new StringBuilder();
                    int startOfString = i;
                    for(int j = i + 1; j <= listLength; j++) {
                        if(LETTERS_SET.contains(charList[j])) {
                            temp.append(charList[j]);
                            i++;
                        } else {
                            break;
                        }
                    }
                    if(latexFunctions.containsKey(temp.toString())) {
                        tokenList.add(latexFunctions.get(temp.toString()));
                    } else {
                        errorLog.appendParser("Invalid function name starting in column", startOfString, expressionCount);
                        String context = "Could you have meant " + latexFunctions.descendingKeySet();
                        errorLog.append(context);
                        errorFlag++;
                    }
                    continue;
                case "#":
                    tokenList.add(new Token(Token.TokenTypes.HASH));
                    break;
                case "<":
                    tokenList.add(new Token(Token.TokenTypes.LESS_THAN));
                    break;
                case "~":
                    tokenList.add(new Token(Token.TokenTypes.RANGE));
                    break;
                case ">":
                    tokenList.add(new Token(Token.TokenTypes.GREATER_THAN));
                    break;
                case "*":
                    tokenList.add(new Token(Token.TokenTypes.MULTIPLY));
                    break;
                case "/":
                    tokenList.add(new Token(Token.TokenTypes.DIVIDE));
                    break;
                case "+":
                    tokenList.add(new Token(Token.TokenTypes.PLUS));
                    break;
                case "-":
                    tokenList.add(new Token(Token.TokenTypes.MINUS));
                    break;
                case "(":
                    tokenList.add(new Token(Token.TokenTypes.LEFT_PREFERENCE));
                    break;
                case ")":
                    tokenList.add(new Token(Token.TokenTypes.RIGHT_PREFERENCE));
                    break;
                case "^":
                    tokenList.add(new Token(Token.TokenTypes.POWER_OF));
                    break;
                case "{":
                    tokenList.add(new Token(Token.TokenTypes.LEFT_BRACE));
                    break;
                case "}":
                    tokenList.add(new Token(Token.TokenTypes.RIGHT_BRACE));
                    break;
                case "_":
                    tokenList.add(new Token(Token.TokenTypes.UNDERSCORE));
                    break;
                case "[":
                    tokenList.add(new Token(Token.TokenTypes.SQUARE_LEFT));
                    break;
                case "]":
                    tokenList.add(new Token(Token.TokenTypes.SQUARE_RIGHT));
                    break;
                case "=":
                    tokenList.add(new Token(Token.TokenTypes.EQUALS));
                    break;
                case ",":
                    tokenList.add(new Token(Token.TokenTypes.DELIMITER));
                    break;
                case ";":
                    expressionCount++;
                    tokenList.add(new Token(Token.TokenTypes.SEMICOLON));
                    break;
                case ".":
                    errorLog.appendParser("Real numbers cannot be lead by decimal", i, expressionCount);
                    errorFlag++;
                    break;
            }

            if(DIGITS_SET.contains(charList[i])) {

                Token number = new Token(Token.TokenTypes.INTEGER);

                StringBuilder temp = new StringBuilder();

                temp.append(charList[i]);

                int multiDecimals = 0;

                if(i == listLength) {
                    number.setValue(Double.parseDouble(temp.toString()));
                    tokenList.add(number);
                    break;
                }

                for(int j = i + 1; j <= listLength; j++) {

                    if(multiDecimals == 2) {
                        errorLog.appendParser("Multiple Decimals", j, expressionCount);
                        errorFlag++;
                    }

                    if(charList[j].equals(".")) {
                        number.setTokenType(Token.TokenTypes.REAL_NUMBER);
                        multiDecimals++;
                        temp.append(charList[j]);
                    }
                    else if(DIGITS_SET.contains(charList[j])) {
                        temp.append(charList[j]);
                        if(j == listLength) {
                            number.setValue(Double.parseDouble(temp.toString()));

                            tokenList.add(number);
                        }
                    }
                    else {
                        number.setValue(Double.parseDouble(temp.toString()));
                        tokenList.add(number);
                        break;
                    }
                    i ++;
                }
            }

            else if(LETTERS_SET.contains(charList[i])) {

                StringBuilder temp = new StringBuilder();

                temp.append(charList[i]);

                for(int j = i + 1; j <= listLength; j++) {

                    if(LETTERS_SET.contains(charList[j])) {
                        temp.append(charList[j]);
                        i++;
                        if (j == listLength) {
                            errorLog.appendParser("Invalid variable declaration", j, expressionCount);
                            errorFlag++;
                            break;
                        }
                    }
                    else if (charList[j].equals("=")) {
                        VARS_SET.add(temp.toString());
                        tokenList.add(new Token(Token.TokenTypes.VARIABLE, temp.toString()));
                        break;
                    }
                    else if (VARS_SET.contains(temp.toString())){
                        tokenList.add(new Token(Token.TokenTypes.VARIABLE, temp.toString()));
                        break;
                    } else {
                        tokenList.add(new Token(Token.TokenTypes.INDETERMINATE, temp.toString()));
                        break;
                    }
                }
            }

        }

        if(errorFlag > 0) {
            errorLog.append("Input Contains Errors, please amend");
            this.tokenList.clear();
        }

        this.tokenList.add(new Token(Token.TokenTypes.NULL));
        System.out.println(this.tokenList);
        VARS_SET.clear();
    }
}


