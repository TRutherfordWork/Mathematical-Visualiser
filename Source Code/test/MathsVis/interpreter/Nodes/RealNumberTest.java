package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("A real number")
class RealNumberTest {

    RealNumber realNumber;

    @Test
    @DisplayName("is instantiated with new RealNumber(Double)")
    void isInstantiatedWithNew() {
        new RealNumber(1.0);
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewRealNumber() {
            realNumber = new RealNumber(1.0);
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(realNumber.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(1.0, realNumber.eval());
        }
    }
}