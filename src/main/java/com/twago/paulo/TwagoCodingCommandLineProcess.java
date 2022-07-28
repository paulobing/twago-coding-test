package com.twago.paulo;

import java.util.Scanner;

public class TwagoCodingCommandLineProcess {
    private final Scanner scanner;

    public static void main(String... args) {
        new TwagoCodingCommandLineProcess().runTwagoCodingFromStandardIn();
    }
    
    private TwagoCodingCommandLineProcess() {
        this.scanner = new Scanner(System.in);
    }

    private void runTwagoCodingFromStandardIn() {
        String firstLine = getNextLine();
        TwagoCoding twagoCoding = new TwagoCoding(firstLine);
        for(int i = 0; i < twagoCoding.getLinesSize(); i++) {
            twagoCoding.executeOperation(getNextLine());
        }
        System.out.println(twagoCoding.getMaximumValue());
    }

    private String getNextLine() {
        return scanner.nextLine();
    }
}
