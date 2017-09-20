package com.techolution.algorithms;

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 08:03
 */
public class TestCase {

    private String inputFileName;
    private String status;
    private String expectedOutput;
    private String output;
    private String executionTime;

    public TestCase(String inputFileName, String output, String expectedOutput, String status, String executionTime) {
        this.inputFileName = inputFileName;
        this.output = output;
        this.expectedOutput = expectedOutput;
        this.status = status;
        this.executionTime = executionTime;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "inputFileName='" + inputFileName + '\'' +
                ", output='" + output + '\'' +
                ", expectedOutput='" + expectedOutput + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public enum Status {
        FAILED("Failed"), TIMED_OUT("Timed Out"), PASSED("Passed");

        String description;

        Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
