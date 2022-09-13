package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FractionTest {

    Fraction fraction;

    @Test
    @DisplayName("is instantiated with new Cos(TreeNode)")
    void isInstantiatedWithNew() {
        new Cos(new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewFraction() {
            fraction = new Fraction(new RealNumber(1.0), new RealNumber(2.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(fraction.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(0.5, fraction.eval());
        }
    }
}