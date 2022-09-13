package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Tan")
class TanTest {

    Tan tan;

    @Test
    @DisplayName("is instantiated with new Tan(TreeNode)")
    void isInstantiatedWithNew() {
        new Tan(new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewTan() {
            tan = new Tan(new RealNumber(45.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(tan.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(Math.tan(Math.toRadians(45.0)), tan.eval());
        }
    }
}