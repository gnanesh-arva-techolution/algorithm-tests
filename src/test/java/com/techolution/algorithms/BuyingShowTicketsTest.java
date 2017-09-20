package com.techolution.algorithms;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

import static com.techolution.algorithms.BuyingShowTickets.waitingTime;
import static com.techolution.algorithms.TestCase.Status.*;
import static com.techolution.algorithms.Utils.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 20:31
 */
public class BuyingShowTicketsTest {

    @Test
    public void waitingTime_Test() throws Exception {
        List<TestCase> testCases = new ArrayList<>();
        File folder = new File(TEST_DATA_PATH + "Buying Show Tickets");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (File file : folder.listFiles()) {
            if (file.getAbsolutePath().contains("output")) {
                continue;
            }
            TestCase testCase;
            String filePath = file.getAbsolutePath();
            String inputFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
            Scanner outputScanner = new Scanner(new File(filePath.replaceFirst("input", "output")));
            long expectedOutput = outputScanner.nextLong();
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            int[] tickets = new int[n];
            for (int i = 0; i < n; i++) {
                tickets[i] = scanner.nextInt();
            }
            int p = scanner.nextInt();
            long output = 0;
            Future<Long> future = executorService.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    return waitingTime(tickets, p);
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
        printTestCasesStatus(testCases, "Buying Show Tickets");
        assert getTestStatus(testCases);
    }

}
