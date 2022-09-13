package MathsVis.interpreter.controllers;

import MathsVis.controllers.graphVisualisation_Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("graphVisualisation_Controller")
class graphVisualisation_ControllerTest {

    MathsVis.controllers.graphVisualisation_Controller graphVisualisation_Controller;

    @Test
    @DisplayName("is instantiated with new graphVisualisation_Controller()")
    void isInstantiatedWithNew() {
        new graphVisualisation_Controller();
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewGraphVisualisation_Controller() {
            graphVisualisation_Controller = new graphVisualisation_Controller();
        }
    }
}