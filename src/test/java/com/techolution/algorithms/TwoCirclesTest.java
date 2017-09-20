package com.techolution.algorithms;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

import static com.techolution.algorithms.TestCase.Status.*;
import static com.techolution.algorithms.TwoCircles.circles;
import static com.techolution.algorithms.Utils.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 18:13
 */
public class TwoCirclesTest {

    @Test
    public void circles_Test() throws Exception {
        List<TestCase> testCases = new ArrayList<>();
        File folder = new File(TEST_DATA_PATH + "Two Circles");
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
            String[] info = new String[scanner.nextInt()];
            scanner.nextLine();
            for (int i = 0; i < info.length; i++) {
                info[i] = scanner.nextLine();
            }
            String[] expectedOutput = new String[info.length];
            for (int i = 0; i < expectedOutput.length; i++) {
                expectedOutput[i] = outputScanner.nextLine();
            }
            String[] output = null;
            Future<String[]> future = executorService.submit(new Callable<String[]>() {
                @Override
                public String[] call() throws Exception {
                    return circles(info);
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
        printTestCasesStatus(testCases, "Two Circles");
        assert getTestStatus(testCases);
    }

}
