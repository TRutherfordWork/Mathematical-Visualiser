package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Sin")
class SinTest {

    Sin sin;

    @Test
    @DisplayName("is instantiated with new Sin(TreeNode)")
    void isInstantiatedWithNew() {
        new Sin(new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewSin() {
            sin = new Sin(new RealNumber(90.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(sin.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(1.0, sin.eval());
        }
    }
}