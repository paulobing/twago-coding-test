package com.twago.paulo;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class TwagoCoding {
    private final int linesSize;
    private final long[] numbers;
    private int currentLine = 1;
    private long currentMaximumValue = Long.MIN_VALUE;

    public TwagoCoding(String firstLine) {
        validateFirstLineInput(firstLine);

        List<Integer> firstLineInt = convertSpaceSeparatedStringToListLong(firstLine, Integer::parseInt);
        int listSize = firstLineInt.get(0); // X
        this.linesSize = firstLineInt.get(1); // Y

        numbers = new long[listSize];
    }

    public long getMaximumValue() {
        return currentMaximumValue;
    }

    public int getLinesSize() {
        return linesSize;
    }

    public void executeOperation(String line) {
        validateOperationLineInput(line);
        validateCurrentLineDoesntExceed();
        List<Long> lineLong = convertSpaceSeparatedStringToListLong(line, Long::parseLong);

        // add value k to elements in the range i..j both inclusive
        int i = lineLong.get(0).intValue();
        long j = lineLong.get(1);
        long k = lineLong.get(2);

        for(; i <= j; i++) {
            numbers[i - 1]+= k;
            currentMaximumValue = Math.max(currentMaximumValue, numbers[i - 1]);
        }

        currentLine++;
    }

    private void validateCurrentLineDoesntExceed() {
        if (currentLine > linesSize) {
            throw new IllegalArgumentException("The current line (" + currentLine + ") has exceeded the initially declared lines size (" + linesSize + ")");
        }
    }

    private void validateFirstLineInput(String firstLine) {
        if (firstLine == null || !firstLine.matches("\\d+ \\d+")) {
            throw new IllegalArgumentException("Invalid input:\n" + firstLine + "\nFirst line input should have the below format separated by a single space\nNUMBER NUMBER\ne.g.:\n5 3");
        }
    }

    private void validateOperationLineInput(String operationLine) {
        if (operationLine == null || !operationLine.matches("\\d+ \\d+ \\d+")) {
            throw new IllegalArgumentException("Invalid operation:\n" + operationLine + "\nSecond line input (operation) should have the below format separated by a single space\nNUMBER NUMBER NUMBER\ne.g.:\n1 2 100");
        }
    }

    private <T> List<T> convertSpaceSeparatedStringToListLong(String line, Function<String, T> mapperFunction) {
        return Stream.of(line.split(" "))
                .map(mapperFunction)
                .toList();
    }
}
