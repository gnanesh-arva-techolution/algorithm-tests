package com.techolution.algorithms;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

import static com.techolution.algorithms.PsychometricTesting.jobOffers;
import static com.techolution.algorithms.TestCase.Status.*;
import static com.techolution.algorithms.Utils.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Gnanesh Arva
 * @since 19 Sep 2017 at 22:07
 */
public class PsychometricTestingTest {

    @Test
    public void jobOffers_Test() throws Exception {
        List<TestCase> testCases = new ArrayList<>();
        File folder = new File(TEST_DATA_PATH + "Psychometric Testing");
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
            int[] scores = new int[scanner.nextInt()];
            for (int i = 0; i < scores.length; i++) {
                scores[i] = scanner.nextInt();
            }
            int[] lowerLimits = new int[scanner.nextInt()];
            for (int i = 0; i < lowerLimits.length; i++) {
                lowerLimits[i] = scanner.nextInt();
            }
            int[] upperLimits = new int[scanner.nextInt()];
            for (int i = 0; i < upperLimits.length; i++) {
                upperLimits[i] = scanner.nextInt();
            }
            int[] expectedOutput = new int[lowerLimits.length];
            for (int i = 0; i < expectedOutput.length; i++) {
                expectedOutput[i] = outputScanner.nextInt();
            }


            int[] output = jobOffers(scores, lowerLimits, upperLimits);
            Future<int[]> future = executorService.submit(new Callable<int[]>() {
                @Override
                public int[] call() throws Exception {
                    return jobOffers(scores, lowerLimits, upperLimits);
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
                testCase = new TestCase(inputFileName, "", Arrays.toString(expectedOutput), TIMED_OUT.getDescription(), String.valueOf(executionTime));
                testCases.add(testCase);
                continue;
            }
            if (Arrays.equals(output, expectedOutput)) {
                testCase = new TestCase(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), PASSED.getDescription(), String.valueOf(executionTime));
                testCases.add(testCase);
            } else {
                testCase = new TestCase(inputFileName, Arrays.toString(output), Arrays.toString(expectedOutput), FAILED.getDescription(), String.valueOf(executionTime));
                testCases.add(testCase);
            }
        }
        printTestCasesStatus(testCases, "Psychometric Testing");
        assert getTestStatus(testCases);
    }

}
