package com.suslov.jetbrains.models;

import com.suslov.jetbrains.exceptions.NumException;
import com.suslov.jetbrains.services.InputParser;

import java.util.Scanner;

/**
 * @author Mikhail Suslov
 */
public class AmazingNumbers {
    public static final String WELCOME = "Welcome to Amazing Numbers!\n";
    public static final String GOODBYE = "\nGoodbye!";
    public static final String REQUEST_INFO = "Supported requests:\n" +
            "- enter a natural number to know its properties;\n" +
            "- enter two natural numbers to obtain the properties of the list:\n" +
            "  * the first parameter represents a starting number;\n" +
            "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
            "- two natural numbers and properties to search for;\n" +
            "- a property preceded by minus must not be present in numbers;\n" +
            "- separate the parameters with one space;\n" +
            "- enter 0 to exit.";
    public static final String ENTER_MESSAGE = "\nEnter a request: ";

    private final Scanner scanner;
    boolean isEnd;

    public AmazingNumbers() {
        this(new Scanner(System.in));
    }

    public AmazingNumbers(Scanner scanner) {
        this.scanner = scanner;
    }

    public void launch() {
        System.out.println(WELCOME);
        System.out.println(REQUEST_INFO);

        do {
            try {
                System.out.print(ENTER_MESSAGE);
                isEnd = InputParser.parseInputLineAndFinish(scanner.nextLine());
            } catch (NumException exp) {
                System.out.println(exp.getMessage());
            }
        } while (!isEnd);

        close();
    }

    void close() {
        System.out.println(GOODBYE);
        scanner.close();
    }
}