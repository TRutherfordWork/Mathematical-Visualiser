package MathsVis.interpreter.Nodes;

import MathsVis.interpreter.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@DisplayName("A Summation")
class SummationTest {

    Summation summation;

    @Test
    @DisplayName("is instantiated with new Summation(int, int, ArrayList<Token>)")
    void isInstantiatedWithNew() {
        new Summation(1, 2, new ArrayList<Token>());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewSummation() {
            new Summation(0, 3, new ArrayList<Token>());
        }
    }
}