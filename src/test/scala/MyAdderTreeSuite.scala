import org.junit.Assert._
import org.junit.Test
import org.junit.Ignore

import Chisel._
import chiseltemplate.adder.MyAdderTree

class MyAdderTreeSuite extends TestSuite {

  @Test def addTreeTest {
    class MyAdderTreeTests( c : MyAdderTree ) extends Tester(c) {
      poke( c.io.in, (0 until 8).map(BigInt(_)).toArray )
      step(3)
      expect( c.io.out, (0 until 8).reduce(_+_) )
    }
    chiselMainTest(Array("--genHarness", "--compile", "--test", "--backend", "c"), () => {
      Module( new MyAdderTree ) }) { c => new MyAdderTreeTests( c ) }
  }
}
