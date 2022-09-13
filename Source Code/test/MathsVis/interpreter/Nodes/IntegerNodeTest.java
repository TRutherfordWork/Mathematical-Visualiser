package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("An IntegerNode")
class IntegerNodeTest {

    IntegerNode integerNode;

    @Test
    @DisplayName("is instantiated with new IntegerNode(double)")
    void isInstantiatedWithNew() {
        new IntegerNode(1.0);
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewIntegerNode() {
            integerNode = new IntegerNode(1.0);
        }

        @Test
        @DisplayName("has value")
        void hasValue() {
            assertNotNull(integerNode.getValue());
        }

        @Test
        @DisplayName("evaluates correctly")
        void evaluatesSelf() {
            assertEquals(1.0, integerNode.eval());
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(integerNode.toString());
        }
    }
}