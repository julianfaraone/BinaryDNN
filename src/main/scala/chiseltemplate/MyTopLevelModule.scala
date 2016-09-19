package chiseltemplate

import Chisel._
import chiselutils.utils.Serializer // import something from chisel-utils
import chiseltemplate.adder.MyAdderTree // import something from this package

class MyTopLevelModule extends Module {
  val io = new Bundle {
    val in = Decoupled( Vec.fill( 10 ) { UInt( width = 8 ) } ).flip
    val out = Valid(UInt( OUTPUT, 8 ))
  }
  val mySerializer = Module( new Serializer( UInt( width = 8 ), 10, 8 ) )
  mySerializer.io.dataIn <> io.in
  mySerializer.io.flush := Bool(false)
  val myAdderTree = Module( new MyAdderTree )
  myAdderTree.io.in := mySerializer.io.dataOut.bits
  io.out.bits := myAdderTree.io.out
  io.out.valid := ShiftRegister( mySerializer.io.dataOut.valid, 3 )
}
