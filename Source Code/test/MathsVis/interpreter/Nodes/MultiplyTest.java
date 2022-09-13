package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Multiply")
class MultiplyTest {

    Multiply multiply;

    @Test
    @DisplayName("is instantiated with new Multiply(TreeNode, TreeNode)")
    void isInstantiatedWithNew() {
        new Multiply(new TreeNode(), new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewMultiply() {
            multiply = new Multiply(new RealNumber(4.0), new RealNumber(5.0));
        }

        @Test
        @DisplayName("evaluates correctly")
        void evaluatesSelf() {
            assertEquals(20.0, multiply.eval());
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(multiply.toString());
        }
    }
}