package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Cos")
class CosTest {

    Cos cos;

    @Test
    @DisplayName("is instantiated with new Cos(TreeNode)")
    void isInstantiatedWithNew() {
        new Cos(new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewCos() {
            cos = new Cos(new RealNumber(0.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(cos.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(1.0, cos.eval());
        }
    }
}