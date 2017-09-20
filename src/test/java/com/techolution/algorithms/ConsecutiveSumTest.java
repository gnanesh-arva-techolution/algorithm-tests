package com.techolution.algorithms;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

import static com.techolution.algorithms.ConsecutiveSum.consecutive;
import static com.techolution.algorithms.TestCase.Status.*;
import static com.techolution.algorithms.Utils.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 21:21
 */
public class ConsecutiveSumTest {

    @Test
    public void consecutive_Test() throws Exception {
        List<TestCase> testCases = new ArrayList<>();
        File folder = new File(TEST_DATA_PATH + "Consecutive Sum");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (File file : folder.listFiles()) {
            if (file.getAbsolutePath().contains("output")) {
                continue;
            }
            TestCase testCase;
            String filePath = file.getAbsolutePath();
            String inputFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
            Scanner outputScanner = new Scanner(new File(filePath.replaceFirst("input", "output")));
            int expectedOutput = outputScanner.nextInt();
            Scanner scanner = new Scanner(file);
            long num = scanner.nextLong();
            Future<Integer> future = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return consecutive(num);
                }
            });
            int output = 0;
            long executionTime = TIME_OUT * 1000;
            try {
                long startTime = System.currentTimeMillis();
                output = future.get(TIME_OUT, SECONDS);
                executionTime = System.currentTimeMillis() - startTime;
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                testCase = new TestCase(inputFileName, "", String.valueOf(expectedOutput), TIMED_OUT.getDescription(), String.valueOf(executionTime));
                testCases.add(testCase);
                continue;
            }
            if (output == expectedOutput) {
                testCase = new TestCase(inputFileName, String.valueOf(output), String.valueOf(expectedOutput), PASSED.getDescription(), String.valueOf(executionTime));
                testCases.add(testCase);
            } else {
                testCase = new TestCase(inputFileName, String.valueOf(output), String.valueOf(expectedOutput), FAILED.getDescription(), String.valueOf(executionTime));
                testCases.add(testCase);
            }
        }
        printTestCasesStatus(testCases, "Consecutive Sum");
        assert getTestStatus(testCases);
    }

}
