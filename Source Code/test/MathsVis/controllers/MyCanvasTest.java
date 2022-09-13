package MathsVis.interpreter.controllers;

import MathsVis.controllers.MyCanvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MyCanvas")
class MyCanvasTest {

    MyCanvas myCanvas;

    @Test
    @DisplayName("is instantiated with new MyCanvas(String, boolean)")
    void isInstantiatedWithNew() {
        new MyCanvas("", false);
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewMyCanvas() {
            myCanvas = new MyCanvas("3+\\frac{4}{3}", false);
        }

        @Test
        @DisplayName("is resizable")
        void isResizable() {
            assertTrue(myCanvas.isResizable());
        }

        @Test
        @DisplayName("correctly produces equation from LaTeX")
        void correctEquation() {
            assertEquals("3+\\frac{3}{4}", myCanvas.defineVariables("3+\\frac{3}{4}"));
        }
    }
}