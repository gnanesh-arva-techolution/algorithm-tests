Approach we followed in the test cases:

Read the respective input data folder of algorithm.

Read EACH input file one by one.

Prepare input data using Scanner.

Pass the input to candidate's algorithm and get his output.

Prepare expected output data using Scanner.

Compare both the candidate's output and expected output. If both are equal then the test case is marked "PASSED" else "FAILED".

If the candidate's algorithm takes more time than the timeout set, it's marked "TIMED OUT".


Issues so far:

For consecutive sum 000-008 inputs are working fine (008 timing out). 009-012 are taking much time to execute.

For largest subset sum 000-010 inputs are working fine. 011-012 are taking much time to execute.

For two circles puzzle some inputs are getting failed.