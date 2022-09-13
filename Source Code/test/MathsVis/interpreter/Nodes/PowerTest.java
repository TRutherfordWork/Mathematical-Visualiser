package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("A power")
class PowerTest {

    Power power;

    @Test
    @DisplayName("is instantiated with new Power(TreeNode, TreeNode)")
    void isInstantiatedWithNew() {
        new Power(new TreeNode(), new TreeNode());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewPower() {
            power = new Power(new RealNumber(4.0), new RealNumber(2.0));
        }

        @Test
        @DisplayName("evaluates correctly")
        void evaluateSelf() {
            assertEquals(16.0, power.eval());
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(power.toString());
        }
    }
}