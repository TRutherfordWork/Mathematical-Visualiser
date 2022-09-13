package MathsVis.interpreter;

import MathsVis.interpreter.ErrorLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("An error log")
class ErrorLogTest {

    ErrorLog errorLog;

    @Test
    @DisplayName("is instantiated with new ErrorLog()")
    void isInstantiatedWithNew() {
        new ErrorLog();
    }

    @Nested
    @DisplayName("when new")
    class WhenNewTest {
        @BeforeEach
        void createNewErrorLog() {
            errorLog = new ErrorLog();
        }

        @Test
        @DisplayName("has no error log")
        void hasNoErrorLog() {
            assertTrue(errorLog.getMessage().isEmpty());
        }

        @Test
        @DisplayName("formats a string")
        void formatString() {
            errorLog.append("A string");
            assertEquals("A string\n", errorLog.getMessage());
        }

        @Test
        @DisplayName("formats an integer")
        void formatInteger() {
            errorLog.append(1);
            assertEquals("1\n", errorLog.getMessage());
        }

        @Test
        @DisplayName("formats a string and 2 integers")
        void formatStringIndexExpression() {
            errorLog.appendParser("A string", 1, 2);
            assertEquals("A string in column 1 in expression 3\n", errorLog.getMessage());
        }

        @Nested
        @DisplayName("after formatting")
        class AfterFormatTest {
            @BeforeEach
            void formatNewString() {
                errorLog.append("A string");
            }

            @Test
            @DisplayName("has error log")
            void hasErrorLog() {
                assertFalse(errorLog.getMessage().isEmpty());
            }
        }
    }
}