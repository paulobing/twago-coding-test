package com.twago.paulo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TwagoCodingTest {
    @Nested
    class GetMaximumValue {
        @Test
        void given_shortInput_when_getMaxValue_then_valueShouldBeCorrect() {
            TwagoCoding twagoCoding = new TwagoCoding("5 3");
            twagoCoding.executeOperation("1 2 100");
            twagoCoding.executeOperation("2 5 100");
            twagoCoding.executeOperation("3 4 100");

            long maximumValue = twagoCoding.getMaximumValue();

            assertEquals(200, maximumValue);
        }
    }

    @Nested
    class NewInstance {
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {
                " ",
                "1",
                "10",
                "15 ",
                " 30",
                "15  30",
                "a b"
        })
        void given_invalidInput_when_creatingNewInstance_then_throwsIllegalArgumentException(String firstLine) {
            IllegalArgumentException thrown = assertThrows(
                    IllegalArgumentException.class,
                    () -> new TwagoCoding(firstLine),
                    "IllegalArgumentException was expected");

            assertTrue(thrown.getMessage().contains("First line input should have the below format separated by a single space"));
        }
    }

    @Nested
    class ExecuteOperation {
        @ParameterizedTest
        @ValueSource(strings = {
                "",
                " ",
                "1",
                "1 2",
                "1 2  100",
                "a b c"
        })
        void given_invalidOperation_when_executingOperation_then_throwsIllegalArgumentException(String operationLine) {
            IllegalArgumentException thrown = assertThrows(
                    IllegalArgumentException.class,
                    () -> new TwagoCoding("5 3")
                            .executeOperation(operationLine),
                    "IllegalArgumentException was expected");

            assertTrue(thrown.getMessage().contains("Second line input (operation) should have the below format separated by a single space"));
        }

        @Test
        void given_tooManyOperationLines_when_executingOperation_then_throwsIllegalArgumentException() {
            TwagoCoding twagoCoding = new TwagoCoding("5 2");
            twagoCoding.executeOperation("1 2 100");
            twagoCoding.executeOperation("1 2 100");

            IllegalArgumentException thrown = assertThrows(
                    IllegalArgumentException.class,
                    () -> twagoCoding.executeOperation("1 2 100"),
                    "IllegalArgumentException was expected");

            assertTrue(thrown.getMessage().contains("The current line (3) has exceeded the initially declared lines size (2)"));
        }

    }
}
