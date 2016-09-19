// These imports are needed for scala test
import org.junit.Assert._
import org.junit.Test
import org.junit.Ignore

// These are other scala imports
import scala.util.Random
import scala.collection.mutable.ArrayBuffer

// Chisel imports
import Chisel._
import chiseltemplate.MyTopLevelModule

/** TestSuite should test only one module
  * It should be named [MyModuleName]Suite
  */
class MyTopLevelModuleSuite extends TestSuite {

  // You can have multiple tests in a test suite
  @Test def topModTest {
    // Put test code here ...
    class MyTopLevelModuleTests( c : MyTopLevelModule ) extends Tester(c) {
      val cycles = 30
      val myRand = new Random
      val inputs = ArrayBuffer.fill( cycles*10 ) { myRand.nextInt(8) }
      var idxInput = 0
      var idxOutput = 0
      poke( c.io.in.valid, true )
      for ( cyc <- 0 until cycles ) {
        poke( c.io.in.bits, inputs.slice(idxInput, idxInput + 10).map(BigInt(_)).toArray )
        if ( peek( c.io.in.ready ) == 1 )
          idxInput = idxInput + 10
        step(1)
        if ( peek( c.io.out.valid ) == 1 ) {
          expect( c.io.out.bits, inputs.slice(idxOutput, idxOutput + 8).reduce(_+_) )
          idxOutput = idxOutput + 8
        }
      }

    }
    chiselMainTest(Array("--genHarness", "--compile", "--test", "--backend", "c"), () => {
      Module( new MyTopLevelModule ) }) { c => new MyTopLevelModuleTests( c ) }
  }
}
