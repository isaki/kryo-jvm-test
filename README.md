# Overview

This project exists to exercise the conditions which can cause JVM crashes when working with Kryo.

# Instructions

Comment out and in the various types to create 3 distinct jars. Then, serialize for all three. Finally, run the deserialize for all three jars for all output files (for a total of 9 executions).

# Credits

This project is based on Kryo issue 663 and the test provided by HiwayChe in their [kryotest project](https://github.com/HiwayChe/kryotest).
