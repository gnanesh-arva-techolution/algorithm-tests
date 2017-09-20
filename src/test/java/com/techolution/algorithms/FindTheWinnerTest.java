package com.techolution.algorithms;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

import static com.techolution.algorithms.FindTheWinner.winner;
import static com.techolution.algorithms.TestCase.Status.*;
import static com.techolution.algorithms.Utils.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 17:00
 */
public class FindTheWinnerTest {

    @Test
    public void winner_Test() throws Exception {
        List<TestCase> testCases = new ArrayList<>();
        File folder = new File(TEST_DATA_PATH + "Find the Winner");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (File file : folder.listFiles()) {
            if (file.getAbsolutePath().contains("output")) {
                continue;
            }
            TestCase testCase;
            String filePath = file.getAbsolutePath();
            String inputFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
            Scanner outputScanner = new Scanner(new File(filePath.replaceFirst("input", "output")));
            Scanner scanner = new Scanner(file);
            int[] andrea = new int[scanner.nextInt()];
            for (int i = 0; i < andrea.length; i++) {
                andrea[i] = scanner.nextInt();
            }
            int[] maria = new int[scanner.nextInt()];
            for (int i = 0; i < maria.length; i++) {
                maria[i] = scanner.nextInt();
            }
            String gameType = scanner.next(); // Even or Odd.
            String expectedOutput = outputScanner.next();
            String output = "";
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return winner(andrea, maria, gameType);
                }
            });
            long executionTime = TIME_OUT * 1000;
            try {
                long startTime = System.currentTimeMillis();
                output = future.get(TIME_OUT, SECONDS);
                executionTime = System.currentTimeMillis() - startTime;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                testCase = new TestCase(inputFileName, "", expectedOutput, TIMED_OUT.getDescription(), String.valueOf(executionTime));
                testCases.add(testCase);
                continue;
            }
            if (output.equals(expectedOutput)) {
                testCase = new TestCase(inputFileName, output, expectedOutput, PASSED.getDescription(), String.valueOf(executionTime));
                testCases.add(testCase);
            } else {
                testCase = new TestCase(inputFileName, output, expectedOutput, FAILED.getDescription(), String.valueOf(executionTime));
                testCases.add(testCase);
            }
        }
        printTestCasesStatus(testCases, "Find the Winner");
        assert getTestStatus(testCases);
    }

}
