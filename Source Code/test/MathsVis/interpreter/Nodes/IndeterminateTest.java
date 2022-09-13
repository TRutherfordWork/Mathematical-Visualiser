package MathsVis.interpreter.Nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("An Indeterminate")
class IndeterminateTest {

    Indeterminate indeterminate;

    @Test
    @DisplayName("is instantiated with new Indeterminate(String)")
    void isInstantiatedWithNew() {
        new Indeterminate("");
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewIndeterminate() {
            indeterminate = new Indeterminate("");
        }

        @Test
        @DisplayName("has key")
        void hasKey() {
            assertNotNull(indeterminate.getKey());
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(indeterminate.toString());
        }
    }
}