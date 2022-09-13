package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("A Logarithm")
class LogarithmTest {

    Logarithm logarithm;

    @Test
    @DisplayName("is instantiated with new Logarithm(TreeNode, TreeNode)")
    void isInstantiatedWithNew() {
        new Logarithm(new TreeNode(), new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewLogarithm() {
            logarithm = new Logarithm(new RealNumber(10.0), new RealNumber(2.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(logarithm.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(Math.log(2) / Math.log(10), logarithm.eval());
        }
    }
}