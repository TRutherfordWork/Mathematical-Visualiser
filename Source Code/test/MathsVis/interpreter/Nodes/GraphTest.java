package MathsVis.interpreter.Nodes;

import MathsVis.interpreter.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("A Graph")
class GraphTest {

    Graph graph;

    @Test
    @DisplayName("is instantiated with new Graph(ArrayList<Token>, ArrayList<Token>)")
    void isInstantiatedWithNew() {
        new Graph(new ArrayList<Token>(), new ArrayList<Token>());
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewGraph() {
            graph = new Graph(new ArrayList<Token>(), new ArrayList<Token>());
        }

        @Test
        @DisplayName("has toString")
        void hasToString() {
            assertNotNull(graph.toString());
        }
    }
}