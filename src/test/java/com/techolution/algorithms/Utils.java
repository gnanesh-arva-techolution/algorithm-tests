package com.techolution.algorithms;

import java.util.List;

import static com.techolution.algorithms.TestCase.Status.FAILED;
import static com.techolution.algorithms.TestCase.Status.TIMED_OUT;

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 07:47
 */
public class Utils {

    // Strings.
    public static final String TEST_DATA_PATH = "src/test/resources/data/";

    // Numbers
    public static final Integer TIME_OUT = 5; // 5 seconds.

    public static void printTestCasesStatus(List<TestCase> testCases, String puzzleName) {
        System.out.println(puzzleName);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("%-18.18s  %-18.18s  %-18.18s  %-18.18s %-18.18s%n", "Input File", "  Status", "Expected Output", "       Output    ", "Exe Time(millis)");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for (TestCase testCase : testCases) {
            String inputFileName = testCase.getInputFileName();
            String status = testCase.getStatus();
            String expectedOutput = testCase.getExpectedOutput().length() > 10 ? "It's Huge" : testCase.getExpectedOutput();
            String output = testCase.getOutput().length() > 10 ? "It's Huge" : testCase.getOutput();
            String executionTime = testCase.getExecutionTime();
            System.out.printf("%-20.20s  %-20.20s  %-20.20s  %-20.20s %-20.20s%n", inputFileName, status, expectedOutput, output, executionTime);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static boolean getTestStatus(List<TestCase> testCases) {
        boolean testStatus = true;
        for (TestCase testCase : testCases) {
            if (TIMED_OUT.getDescription().equals(testCase.getStatus()) || FAILED.getDescription().equals(testCase.getStatus())) {
                testStatus = false;
                break;
            }
        }
        return testStatus;
    }

}
