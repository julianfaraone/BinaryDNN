# chisel-template
This is a blank chisel project to be used as a template in large designs

It contains the following files:
 * LICENSE
 * Makefile
 * README.md
 * build.sbt
 * src/main/c/myCCode.c
 * src/main/scala/chiseltemplate/MyTopLevelModule.scala
 * src/main/scala/chiseltemplate/adder/MyAdderTree.scala
 * src/main/scala/top.scala
 * src/main/verilog/my_verilog_file.v
 * src/test/scala/MyAdderTreeSuite.scala
 * src/test/scala/MyTopLevelModuleSuite.scala
 * src/test/scala/TestSuite.scala
 * src/test/scala/TextComparator.scala

The files TestSuite.scala and TestComparator.scala are copied from the Chisel repo


There are two test suites:
 * MyAdderTreeSuite
 * MyTopLevelModuleSuite

They can be run using:
```
make test
```

To run one test individually use:
```
sbt "testOnly *MyAdderTreeSuite"
```
# Misc
 * src/main should contain all your source code
 * src/test should contain all your test code
 * Scala package convention is lower case one word eg) "chiseltemplate"
 * The package should be contained in src/main/scala/chiseltemplate
 * If necessary, sub-packages should have their own folder named appropriately eg) chiseltemplate.adder => src/main/scala/chiseltemplate/adder
 * The file top.scala ( and other files in src/main/scala ) are anything else you need which aren't part of the package.
In this case top.scala is used for ``` make verilog ```
 * The files have explanations in comments, look at them for further details
