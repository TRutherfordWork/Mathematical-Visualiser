package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Pie")
class PieTest {

    Pie pie;

    @Test
    @DisplayName("is instantiated with new Pie()")
    void isInstantiatedWithNew() {
        new Pie();
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewPie() {
            pie = new Pie();
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(pie.toString());
        }

        @Test
        @DisplayName("evaluates correctly")
        void eval() {
            assertEquals(Math.PI, pie.eval());
        }
    }
}