package com.twago.paulo;

import com.twago.paulo.providers.TwagoCodingCommandLineProcessArgumentsProvider;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TwagoCodingCommandLineProcessTest {
    @ParameterizedTest
    @ArgumentsSource(TwagoCodingCommandLineProcessArgumentsProvider.class)
    void should(String filenameInput, String expectedMaxValue) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            System.setIn(getClass().getClassLoader().getResourceAsStream(filenameInput));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(byteArrayOutputStream));
            TwagoCodingCommandLineProcess.main();
            assertEquals(expectedMaxValue, byteArrayOutputStream.toString().replaceAll("[\n|\r]", ""));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
