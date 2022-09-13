package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Subtract")
class SubtractTest {

    Subtract subtract;

    @Test
    @DisplayName("is instantiated with new Subtract(TreeNode, TreeNode)")
    void isInstantiatedWithNew() {
        new Subtract(new TreeNode(), new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewSubtract() {
            subtract = new Subtract(new RealNumber(4.0), new RealNumber(2.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(subtract.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(2.0, subtract.eval());
        }
    }
}