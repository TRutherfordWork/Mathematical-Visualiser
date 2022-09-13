package MathsVis.interpreter;

import MathsVis.interpreter.Nodes.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class Parser {

    private boolean localVarFlag = false;

    private int index = 0;

    private final ArrayList<Token> tokenList;

    private Token nextToken ;

    private int expressionCount = 0;

    private ErrorLog errorLog;

    private final ArrayList<TreeNode> resultTrees = new ArrayList<TreeNode>();

    private final ArrayList<TreeNode> graphs = new ArrayList<TreeNode>();

    private boolean endStatment = false;

    private HashMap<String, TreeNode> variables = new HashMap();

    private HashMap<String, TreeNode> localvariables = new HashMap();

    public ErrorLog getErrorLog() {
        return errorLog;
    }

    TreeMap<String, UserFunction> knownFunctions = new TreeMap<>();

    public ArrayList<TreeNode> getGraphs() {
        return graphs;
    }

    private void scanToken() {
        index ++;
        this.nextToken = tokenList.get(index);
    }

    //Sub parsing functions to handle processing at factor level.

    private TreeNode parseCos() {
        scanToken();
        if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
            scanToken();
            TreeNode x = parseE();
            if (x == null) {
                return null;
            }
            if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                return new Cos(x);
            } else {
                errorLog.appendParser("Cos function requires closing }", index, expressionCount);
                return null;
            }
        } else {
            errorLog.appendParser("Cos function requires opening {", index, expressionCount);
            return null;
        }
    }

    private TreeNode parseSin() {
        scanToken();
        if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
            scanToken();
            TreeNode x = parseE();
            if (x == null) {
                return null;
            }
            if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                return new Sin(x);
            } else {
                errorLog.appendParser("Sin function requires closing }", index, expressionCount);
                return null;
            }
        } else {
            errorLog.appendParser("Sin function requires opening {", index, expressionCount);
            return null;
        }
    }

    private TreeNode parseTan() {
        scanToken();
        if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
            scanToken();
            TreeNode x = parseE();
            if (x == null) {
                return null;
            }
            if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                return new Tan(x);
            } else {
                errorLog.appendParser("Tan function requires closing }", index, expressionCount);
                return null;
            }
        } else {
            errorLog.appendParser("Tan function requires opening {", index, expressionCount);
            return null;
        }
    }

    private TreeNode parseVar() {

        //used to set values of variables that have been declared inline

        String key = nextToken.getVar();
        scanToken();
        if (this.nextToken.getTokenType() == Token.TokenTypes.EQUALS) {
            scanToken();
            TreeNode x = parseE();
            if (x == null) {
                return null;
            }
            if (this.nextToken.getTokenType() == Token.TokenTypes.SEMICOLON) {
                scanToken();
                //entering the value of the variables into the key,value pairs (variable:value)
                if(localVarFlag) {
                    localvariables.put(key, new Variable(x));
                }
                variables.put(key, new Variable(x));
                return null;
            } else {
                errorLog.appendParser("Grammar violation var must end with a semicolon", index, expressionCount);
                return null;
            }
        } else {
            if (variables.get(key) != null)
                return variables.get(key);

            errorLog.appendParser("Undeclared VAR", index, expressionCount);
            return null;
        }
    }

    private TreeNode parseLog() {
        if(this.nextToken.getTokenType() == Token.TokenTypes.NATURAL_LOG) {
            scanToken();
            if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
                scanToken();
                TreeNode y = parseE();
                if (y == null) {
                    return null;
                }
                if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                    scanToken();
                    System.out.println("hello");
                    return new Logarithm(new Euler(), y);
                } else {
                    errorLog.appendParser("Grammar violation in natual log", index, expressionCount);
                    scanToken();
                    return null;
                }
            } else {
                errorLog.appendParser("Grammar violation in natual log, no {", index, expressionCount);
                scanToken();
                return null;
            }
        }
        scanToken();
        if (this.nextToken.getTokenType() == Token.TokenTypes.UNDERSCORE) {
            scanToken();
            TreeNode x = parseE();
            if (x == null) {
                return null;
            }
            if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
                scanToken();
                TreeNode y = parseE();
                if (y == null) {
                    return null;
                }
                if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                    scanToken();
                    return new Logarithm(x, y);
                } else {
                    errorLog.appendParser("Grammar violation invalid log", index, expressionCount);
                    scanToken();
                    return null;
                }
            } else {
                errorLog.appendParser("Grammar violation invalid log", index, expressionCount);
                scanToken();
                return null;
            }
        }
        else {
            errorLog.appendParser("Grammar violation invalid log", index, expressionCount);
            scanToken();
            return null;
        }
    }

    private TreeNode parseRoot() {
        scanToken();
        if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
            scanToken();
            TreeNode x = parseE();
            if (x == null) {
                return null;
            }
            if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                scanToken();
                return new Root(new IntegerNode(2), x);
            } else {
                errorLog.appendParser("Grammar violation invalid root", index, expressionCount);
                scanToken();
                return null;
            }
        } else if (this.nextToken.getTokenType() == Token.TokenTypes.SQUARE_LEFT) {
            scanToken();
            TreeNode x = parseE();
            if (x == null) {
                return null;
            }
            if (this.nextToken.getTokenType() == Token.TokenTypes.SQUARE_RIGHT) {
                scanToken();
                if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
                    scanToken();
                    TreeNode y = parseE();
                    if (y == null) {
                        return null;
                    }
                    if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                        scanToken();
                        return new Root(x, y);
                    } else {
                        errorLog.appendParser("Grammar violation invalid root", index, expressionCount);
                        scanToken();
                        return null;
                    }
                } else {
                    errorLog.appendParser("Grammar violation invalid root", index, expressionCount);
                    scanToken();
                    return null;
                }
            } else {
                errorLog.appendParser("Grammar violation invalid root", index, expressionCount);
                scanToken();
                return null;
            }
        } else {
            errorLog.appendParser("Grammar violation invalid root", index, expressionCount);
            scanToken();
            return null;
        }
    }

    private TreeNode parseFraction() {
        scanToken();
        //the grammar check
        if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
            //scanning the next token along
            scanToken();
            //getting rid of left brace, no longer needed. Parsing what is needed. There could be an expression, therefore recursion
            TreeNode x = parseE();
            if (x == null) {
                return null;
            }
            //another grammar check for next expected brace
            if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                scanToken();
                if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
                    scanToken();
                    TreeNode y = parseE();

                    if (y == null) {
                        return null;
                    }
                    if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                        scanToken();
                        return new Fraction(x,y);
                    } else {
                        errorLog.appendParser("Grammar violation invalid fraction", index, expressionCount);
                        scanToken();
                        return null;
                    }
                } else {
                    errorLog.appendParser("Grammar violation invalid fraction", index, expressionCount);
                    scanToken();
                    return null;
                }
            } else {
                errorLog.appendParser("Grammar violation invalid fraction", index, expressionCount);
                scanToken();
                return null;
            }
        } else {
            errorLog.appendParser("Grammar violation invalid fraction", index, expressionCount);
            scanToken();
            return null;
        }
    }

    private TreeNode parseFunction() {

        String functionName = nextToken.getVar();

        scanToken();

        if (this.nextToken.getTokenType() == Token.TokenTypes.LESS_THAN) {

            if(knownFunctions.containsKey(functionName)) {

                scanToken();

                ArrayList<ArrayList<Token>> functionInput = new ArrayList<ArrayList<Token>>();

                if (this.nextToken.getTokenType() != Token.TokenTypes.GREATER_THAN) {

                    while (this.nextToken.getTokenType() != Token.TokenTypes.GREATER_THAN) {

                        ArrayList<Token> arg = new ArrayList<Token>();

                        while (this.nextToken.getTokenType() != Token.TokenTypes.DELIMITER) {

                            if(this.nextToken.getTokenType() == Token.TokenTypes.GREATER_THAN) {
                                break;
                            }

                            arg.add(this.nextToken);
                            scanToken();
                        }
                        if (this.nextToken.getTokenType() == Token.TokenTypes.DELIMITER) {
                            scanToken();
                        }

                        functionInput.add(arg);
                    }
                } else {
                    scanToken();
                }

                System.out.println("JORDAN DEBUG: " + functionInput);

                scanToken();

                UserFunction activeFunction = knownFunctions.get(functionName);

                activeFunction.insertArgs(functionInput);

                Parser test = new Parser(activeFunction.getFunctionBodyPopulated(), new ErrorLog());

                for (TreeNode node :
                        test.getResultsTrees()) {
                    node.eval();
                }

                int size = test.getResultsTrees().size() -1;

                return new RealNumber(test.getResultsTrees().get(size).eval());
            }

            scanToken();

            ArrayList<String> argKeys = new ArrayList<String>();

            while (this.nextToken.getTokenType() != Token.TokenTypes.GREATER_THAN) {

                if(this.nextToken.getTokenType() == Token.TokenTypes.INDETERMINATE) {

                    argKeys.add(this.nextToken.getVar());

                    scanToken();

                    if (this.nextToken.getTokenType() == Token.TokenTypes.DELIMITER) {
                        scanToken();
                    } else if (Token.TokenTypes.GREATER_THAN == nextToken.getTokenType()) {

                    } else {
                        errorLog.appendParser("params require a ,", index, expressionCount);
                        return null;
                    }

                } else {
                    errorLog.appendParser("Function contains invalid params", index, expressionCount);
                    return null;
                }

                if(this.nextToken.getTokenType() == Token.TokenTypes.NULL) {
                    errorLog.appendParser("User function requires closing >", index, expressionCount);
                    return null;
                }
            }

            scanToken();

            if(this.nextToken.getTokenType() == Token.TokenTypes.HASH) {
                scanToken();

                ArrayList<Token> tokens = new ArrayList<Token>();

                while(nextToken.getTokenType() != Token.TokenTypes.HASH) {

                    if (nextToken.getTokenType() == Token.TokenTypes.NULL) {
                        errorLog.appendParser("User function requires closing #", index, expressionCount);
                        return null;
                    }

                    tokens.add(nextToken);
                    scanToken();
                }

                UserFunction userFunction = new UserFunction(tokens, argKeys, functionName);

                knownFunctions.put(functionName, userFunction);

            } else {
                errorLog.appendParser("User function requires opening #", index, expressionCount);
                return null;
            }

        } else {
            errorLog.appendParser("User function requires closing #", index, expressionCount);
            return null;
        }
        return null;
    }

    private TreeNode parseGraph() {

        ArrayList<Token> xValues = new ArrayList<Token>();

        ArrayList<Token> expression = new ArrayList<Token>();

        boolean range = false;

        double rangeStart = 0;

        double rangeEnd = 0;

        double rangeInterval = 0;

        scanToken();

        if (this.nextToken.getTokenType() == Token.TokenTypes.SQUARE_LEFT) {
            scanToken();
            if (this.nextToken.getTokenType() != Token.TokenTypes.SQUARE_RIGHT) {

                if (this.nextToken.getTokenType() == Token.TokenTypes.RANGE) {
                    scanToken();
                    if (this.nextToken.getTokenType() == Token.TokenTypes.MINUS) {
                        scanToken();
                        if(this.nextToken.getTokenType() != Token.TokenTypes.REAL_NUMBER
                                && this.nextToken.getTokenType() != Token.TokenTypes.INTEGER) {
                            errorLog.appendParser("Graph range starts requires real or int value", index, expressionCount);
                            return null;
                        }
                        rangeStart = this.nextToken.getValue() * -1;
                    } else {
                        rangeStart = this.nextToken.getValue();
                    }
                    scanToken();
                    if (this.nextToken.getTokenType() == Token.TokenTypes.RANGE) {
                        scanToken();
                        if(this.nextToken.getTokenType() != Token.TokenTypes.REAL_NUMBER
                                && this.nextToken.getTokenType() != Token.TokenTypes.INTEGER) {
                            errorLog.appendParser("Graph range end requires a pos real or int value", index, expressionCount);
                            return null;
                        }
                        rangeEnd = this.nextToken.getValue();
                        scanToken();
                        if (this.nextToken.getTokenType() == Token.TokenTypes.RANGE) {
                            scanToken();
                            if(this.nextToken.getTokenType() != Token.TokenTypes.REAL_NUMBER
                                    && this.nextToken.getTokenType() != Token.TokenTypes.INTEGER) {
                                errorLog.appendParser("Graph range interval requires a pos real or int value", index, expressionCount);
                                return null;
                            }
                            rangeInterval = this.nextToken.getValue();
                            scanToken();
                            range = true;
                        } else {
                            errorLog.appendParser("Graph range missing ~ before range interval value", index, expressionCount);
                            return null;
                        }
                    } else {
                        errorLog.appendParser("Graph range missing ~ before range end", index, expressionCount);
                        return null;
                    }
                }

                //Logic to decide if range is used

                if(range) {
                    for (; rangeStart <= rangeEnd; rangeStart+=rangeInterval) {
                        xValues.add(new Token(Token.TokenTypes.REAL_NUMBER, rangeStart));
                    }
                }

                if(!range) {
                    if (this.nextToken.getTokenType() == Token.TokenTypes.MINUS) {
                        scanToken();
                        Token temp = this.nextToken;
                        temp.setValue(temp.getValue() * -1);
                        xValues.add(temp);
                    } else {
                        xValues.add(nextToken);
                    }
                    scanToken();
                }

                while (true) {

                    if (this.nextToken.getTokenType() == Token.TokenTypes.DELIMITER) {
                        scanToken();
                        if (this.nextToken.getTokenType() == Token.TokenTypes.MINUS) {
                            scanToken();
                            Token temp = this.nextToken;
                            temp.setValue(temp.getValue() * -1);
                            xValues.add(temp);
                        } else {
                            xValues.add(nextToken);
                        }
                        scanToken();
                    } else if (this.nextToken.getTokenType() == Token.TokenTypes.SQUARE_RIGHT) {
                        scanToken();
                        break;
                    } else {
                        errorLog.appendParser("Graph params invalid format, please use the form -1,0,-1,2,3 ... ", index, expressionCount);
                        return null;
                    }
                }
            } else {
                scanToken();
            }
        }

        if(xValues.isEmpty()) {
            for (int i = -10; i <= 10; i++) {
                xValues.add(new Token(Token.TokenTypes.REAL_NUMBER, (double)i));
                System.out.println(i);
            }
        }

        if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_PREFERENCE) {
            //this is the expression for y
            scanToken();
            while (this.nextToken.getTokenType() != Token.TokenTypes.RIGHT_PREFERENCE) {

                if (this.nextToken.getTokenType() == Token.TokenTypes.NULL) {
                    errorLog.appendParser("Graph function is missing closing preference", index, expressionCount);
                    return null;
                }

                expression.add(nextToken);

                scanToken();
            }
        }

        if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_PREFERENCE) {
            scanToken();
            Graph graph = new Graph(xValues, expression);
            if(graph.containsOtherThanX()) {
                errorLog.appendParser("Graph expression must only us x as a var", index, expressionCount);
                return null;
            }
            graph.insertArgs();
            Parser graphParser = new Parser(graph.getyPoints(), new ErrorLog());
                for (TreeNode node :
                        graphParser.getResultsTrees()) {
                    graph.getY().add(node.eval());
                }
            graphs.add(graph);
        } else {
            errorLog.appendParser("Graph function is missing closing preference", index, expressionCount);
            return null;
        }
        return null;
    }

    private TreeNode parseDifferentiation() {
        return null;
    }

    private TreeNode parseIntegration() {
        return null;
    }

    private TreeNode parseSummation() {

        int startOfSummation;
        int lastValueOfSummation;
        ArrayList<Token> formula = new ArrayList<Token>();

        scanToken();

        if (this.nextToken.getTokenType() == Token.TokenTypes.UNDERSCORE) {
            scanToken();
            if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
                scanToken();
                if (this.nextToken.getTokenType() == Token.TokenTypes.INTEGER) {
                    startOfSummation = (int) this.nextToken.getValue();
                    scanToken();

                    if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                        scanToken();
                        if (this.nextToken.getTokenType() == Token.TokenTypes.POWER_OF) {
                            scanToken();
                            if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
                                scanToken();
                                if (this.nextToken.getTokenType() == Token.TokenTypes.INTEGER) {
                                    lastValueOfSummation = (int) this.nextToken.getValue();
                                    scanToken();
                                    if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                                        scanToken();
                                        while (this.nextToken.getTokenType() != Token.TokenTypes.SEMICOLON) {

                                            if (this.nextToken.getTokenType() == Token.TokenTypes.NULL) {
                                                errorLog.appendParser("Summation function is missing closing semicolon", index, expressionCount);
                                                return null;
                                            }
                                            formula.add(nextToken);
                                            scanToken();
                                        }
                                        Summation summation = new Summation(startOfSummation, lastValueOfSummation, formula);
                                        if (summation.containsOtherThanX()) {
                                            errorLog.appendParser("Summation expression must only us x as a var", index, expressionCount);
                                            return null;
                                        }
                                        summation.insertArgs();
                                        Parser summationParser = new Parser(summation.getAllSums(), new ErrorLog());
                                        double total = 0;
                                        for (TreeNode node :
                                                summationParser.getResultsTrees()) {
                                            total += node.eval();
                                        }
                                        return new RealNumber(total);
                                    } else {
                                        errorLog.appendParser("Summation expression missing closing } at end of summation value", index, expressionCount);
                                        return null;
                                    }
                                } else {
                                    errorLog.appendParser("Summation expression accepts only integers for end of summation value", index, expressionCount);
                                    return null;
                                }
                            } else {
                                errorLog.appendParser("Summation expression missing opening { at start of summation end value", index, expressionCount);
                                return null;
                            }
                        } else {
                            errorLog.appendParser("Summation expression accepts only ^ between start and end values", index, expressionCount);
                            return null;
                        }
                    } else {
                        errorLog.appendParser("Summation expression missing closing } after start of summation value", index, expressionCount);
                        return null;
                    }
                } else {
                    errorLog.appendParser("Summation expression accepts only integers at start of summation value", index, expressionCount);
                    return null;
                }
            } else {
                errorLog.appendParser("Summation expression missing open { before start of summation value", index, expressionCount);
                return null;
            }
        } else {
            errorLog.appendParser("Summation command 'sum' must be followed by '_'", index, expressionCount);
            return null;
        }
    }

    //parse factor

    //used to parse factor (factor is anything comprising an expression)
    private TreeNode parseF() {
        if (this.nextToken.getTokenType() == Token.TokenTypes.SEMICOLON) {
            endStatment = true;
            scanToken();
            return null;
        } else if (this.nextToken.getTokenType() == Token.TokenTypes.VARIABLE) {
            return parseVar();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.PIE) {
            scanToken();
            return new Pie();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.EULER) {
            scanToken();
            return new Euler();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.INTEGRATION) {
            return parseIntegration();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.DIFFERENTIATION) {
            return parseDifferentiation();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.GRAPH) {
            return parseGraph();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.INDETERMINATE) {
            Indeterminate temp = new Indeterminate(this.nextToken.getVar());
            scanToken();
            return temp;
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.MINUS) {
            scanToken();
            TreeNode x = parseF();
            if(x == null) {
                return null;
            }
            return new Negate(x);
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.USER_FUNCTION) {
            return parseFunction();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.INTEGER) {
            IntegerNode temp = new IntegerNode(this.nextToken.getValue());
            scanToken();
            return temp;
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.REAL_NUMBER) {
            RealNumber temp = new RealNumber(this.nextToken.getValue());
            scanToken();
            return temp;
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.FRACTION) {
            return parseFraction();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.ROOT) {
            return parseRoot();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.LOG
                || this.nextToken.getTokenType() == Token.TokenTypes.NATURAL_LOG) {
            return parseLog();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.COS) {
            return parseCos();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.SIN) {
            return parseSin();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.TAN) {
            return parseTan();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.SUMMATION) {
            return parseSummation();
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_PREFERENCE) {
            scanToken();
            TreeNode x = parseE();
            if(x == null) {
                return null;
            }
            if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_PREFERENCE) {
                scanToken();
                return x;
            }
            else {
                errorLog.append("Grammar violation ( <expr> ) at position");
                errorLog.append(index);
                return null;
            }
        }
        else if (this.nextToken.getTokenType() == Token.TokenTypes.NEGATE) {
            return new Negate(parseE());
        }
        else {
            scanToken();
            //errorLog.append("Grammar violation <factor>", index, expressionCount);
            return null;
        }
    }

    //parse term

    private TreeNode parseT() {
        TreeNode x = parseF();
        if (endStatment) {
            return x;
        }
        while (true) {
//            if (this.nextToken.getTokenType() == Token.TokenTypes.INDETERMINATE) {
//                TreeNode y = new Indeterminate(this.nextToken.getVar());
//                scanToken();
//                x = new Multiply(y, x);
            if (this.nextToken.getTokenType() == Token.TokenTypes.MULTIPLY) {
                scanToken();
                if (x == null) {
                    return null;
                }
                TreeNode y = parseT();
                if (y == null) {
                    errorLog.appendParser("Grammar violation <term> * <factor> at position", index, expressionCount);
                    return null;
                }
                x = new Multiply(x, y);
            } else if (this.nextToken.getTokenType() == Token.TokenTypes.DIVIDE) {
                scanToken();
//                }
                TreeNode y = parseT();
                if (y == null) {
                    errorLog.appendParser("Grammar violation <term> / <factor> at position", index, expressionCount);
                    return null;
                }
                x = new Divide(x, y);
            } else if (this.nextToken.getTokenType() == Token.TokenTypes.POWER_OF) {
                scanToken();
                if (this.nextToken.getTokenType() == Token.TokenTypes.LEFT_BRACE) {
                    scanToken();
                    TreeNode y = parseT();
                    if (y == null) {
                        errorLog.appendParser("Grammar violation <term> ^ <factor> at position", index, expressionCount);
                        return null;
                    }
                    System.out.print(this.nextToken.getTokenType());
                    if (this.nextToken.getTokenType() == Token.TokenTypes.RIGHT_BRACE) {
                        x = new Power(x, y);
                        scanToken();
                    } else {
                        errorLog.appendParser("Grammar violation, integer value must end with '}'", index, expressionCount);
                        return null;
                    }
                } else{
                    errorLog.appendParser("Grammar violation, POWER_OF token must be followed with '{int}'", index, expressionCount);
                    return null;
                }
            }
            else {
                return x;
            }
        }
    }

    //parse Expression

    private TreeNode parseE() {

        TreeNode x = parseT();

        if(endStatment) {
            return x;
        }

        while (true) {
            if (this.nextToken.getTokenType() == Token.TokenTypes.PLUS) {
                scanToken();
                TreeNode y = parseT();
                if (y == null) {
                    errorLog.appendParser("Grammar violation <expr> + <term> at position", index, expressionCount);
                    return null;
                }
                x = new Add(x, y);
            } else if (this.nextToken.getTokenType() == Token.TokenTypes.MINUS) {
                scanToken();
                TreeNode y = parseT();
                if (y == null) {
                    errorLog.appendParser("Grammar violation <expr> - <term> at position", index, expressionCount);
                    return null;
                }
                x = new Subtract(x, y);
//            }
//            else if (this.nextToken.getTokenType() == Token.TokenTypes.INDETERMINATE) {
//                scanToken();
//                TreeNode y = parseP();
//                if (y == null) {
////                    errorLog.append("Grammar violation <expr> - <term> at position");
////                    errorLog.append(index);
//                    return null;
//                }
//                x = new Subtract(x, y);
            } else {
                return x;
            }
        }
    }

    public Parser (ArrayList<Token> tokenList, ErrorLog errorLog) {

        this.tokenList = tokenList;
        this.nextToken = tokenList.get(index);
        this.errorLog = errorLog;

        if(nextToken == null) {

        }

        while(nextToken.getTokenType() != Token.TokenTypes.NULL) {
            TreeNode x = parseE();
            if(x != null) {
                expressionCount ++;
                resultTrees.add(x);
            }
            endStatment = false;
        }
        //System.out.println(this.graphs);
    }

    public ArrayList<TreeNode> getResultsTrees() {
        return resultTrees;
    }
}
