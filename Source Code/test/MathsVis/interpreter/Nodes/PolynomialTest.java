package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("A Polynomial")
class PolynomialTest {

    Polynomial polynomial;

    @Test
    @DisplayName("is instantiated with new Polynomial(TreeNode, TreeNode)")
    void isInstantiatedWithNew() {
        new Polynomial(new TreeNode(), new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewPolynomial() {
            polynomial = new Polynomial(new RealNumber(4.0), new RealNumber(4.0));
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(polynomial.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(16.0, polynomial.eval());
        }
    }
}