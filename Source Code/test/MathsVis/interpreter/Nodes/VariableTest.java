package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("A Variable")
class VariableTest {

    Variable variable;

    @Test
    @DisplayName("is instantiated with new Variable(TreeNode)")
    void isInstantiatedWithNew() {
        new Variable(new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewRealNumber() {
            variable = new Variable(new RealNumber(1.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(variable.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(1.0, variable.eval());
        }
    }
}