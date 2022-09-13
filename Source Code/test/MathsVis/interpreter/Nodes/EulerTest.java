package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Euler")
class EulerTest {

    Euler euler;

    @Test
    @DisplayName("is instantiated with new Euler()")
    void isInstantiatedWithNew() {
        new Euler();
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewEuler() {
            euler = new Euler();
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(euler.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(Math.E, euler.eval());
        }
    }
}