package MathsVis.interpreter;

/**
 * This class defines the tokens and how they interact.
 * The token's data type is defined by an assigned enum type.
 * If the token is a number it will be assigned a double value.
 * If the token is not a number (operator) its double value will be assigned to Null.
 */

public class  Token {

    public enum TokenTypes {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        INTEGER,
        REAL_NUMBER,
        VARIABLE,
        LEFT_PREFERENCE,
        RIGHT_PREFERENCE,
        POWER_OF,
        FRACTION,
        LOG,
        ROOT,
        LEFT_BRACE,
        RIGHT_BRACE,
        UNDERSCORE,
        SQUARE_LEFT,
        SQUARE_RIGHT,
        NEGATE,
        NULL,
        EQUALS,
        SEMICOLON,
        INDETERMINATE,
        COS,
        TAN,
        SIN,
        USER_FUNCTION,
        DELIMITER,
        GRAPH,
        PIE,
        EULER,
        NATURAL_LOG,
        INTEGRATION,
        DIFFERENTIATION,
        SUMMATION,
        HASH,
        LESS_THAN,
        GREATER_THAN,
        RANGE
    }

    private TokenTypes tokenType;
    private double value;
    private String var;

    public void setValue(double value) {
        this.value = value;
    }

    public Token (TokenTypes t) {
        this.tokenType = t;
    }

    public Token (TokenTypes t, String var) {
        this.tokenType = t;
        this.var = var;
    }

    public Token (TokenTypes t, Double value) {
        this.tokenType = t;
        this.value = value;
    }

    public TokenTypes getTokenType() {
        return tokenType;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", value='" + value + '\'' +
                ", var=" + var +
                '}';
    }

    public void setTokenType(TokenTypes tokenType) {
        this.tokenType = tokenType;
    }

    public String getVar() {
        return var;
    }
}