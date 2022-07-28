package com.twago.paulo;

import com.twago.paulo.providers.TwagoCodingArgumentsProvider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TwagoCodingTest {
    @Nested
    class GetMaximumValue {
        @ParameterizedTest
        @ArgumentsSource(TwagoCodingArgumentsProvider.class)
        void given_shortInput_when_getMaxValue_then_valueShouldBeCorrect(String firstLine, List<String> operations, long expectedMaximumValue) {
            TwagoCoding twagoCoding = new TwagoCoding(firstLine);
            for(String operation: operations) {
                twagoCoding.executeOperation(operation);
            }

            long maximumValue = twagoCoding.getMaximumValue();

            assertEquals(expectedMaximumValue, maximumValue);
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
