package MathsVis.interpreter.Nodes;

import MathsVis.interpreter.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("A UserFunction")
class UserFunctionTest {

    UserFunction userFunction;

    @Test
    @DisplayName("is instantiated with new UserFunction(ArrayList<token>, ArrayList<String>, String")
    void isInstantiatedWithNew() {
        new UserFunction(new ArrayList<>(), new ArrayList<>(), "");
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewRealNumberUserFunction() {
            ArrayList<Token> bodyList = new ArrayList<>();
            ArrayList<String> argList = new ArrayList<>();

            userFunction = new UserFunction(bodyList, argList, "myFunc");
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(userFunction.toString());
        }
    }
}