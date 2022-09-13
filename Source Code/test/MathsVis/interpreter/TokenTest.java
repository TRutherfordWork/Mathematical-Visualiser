package MathsVis.interpreter;

import MathsVis.interpreter.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A token")
class TokenTest {

    Token token;

    @Test
    @DisplayName("is instantiated with new Token(TokenTypes)")
    void isInstantiatedWithNew() {
        new Token(Token.TokenTypes.SIN);
    }

    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewToken() {
//            token = new Token()
        }
    }
}