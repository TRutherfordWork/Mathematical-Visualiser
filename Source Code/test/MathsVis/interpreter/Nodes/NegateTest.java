package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Negate")
class NegateTest {

    Negate negate;

    @Test
    @DisplayName("is instantiated with new Negate(TreeNode)")
    void isInstantiatedWithNew() {
        new Negate(new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewNegate() {
            negate = new Negate(new RealNumber(1.0));
        }

        @Test
        @DisplayName("evaluates correctly")
        void evaluatesSelf() {
            assertEquals(-1.0, negate.eval());
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(negate.toString());
        }
    }
}