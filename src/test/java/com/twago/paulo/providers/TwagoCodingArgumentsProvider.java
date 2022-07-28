package com.twago.paulo.providers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TwagoCodingArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("5 3", List.of("1 2 100", "2 5 100", "3 4 100"), 200),
                Arguments.of("1000 3", Collections.nCopies(3, "5 20 1000"), 3000),
                Arguments.of("1000 25", Collections.nCopies(25, "5 20 1000"), 25000),
                Arguments.of("10000 100", Collections.nCopies(100, "5 150 10000"), 1000000)
        );
    }
}
