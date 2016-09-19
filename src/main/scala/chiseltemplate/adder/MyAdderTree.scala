package chiseltemplate.adder

import Chisel._
import scala.collection.mutable.ArrayBuffer

class MyAdderTree extends Module {
  val io = new Bundle {
    val in = Vec.fill( 8 ) { UInt( INPUT, 8 ) }
    val out = UInt( OUTPUT, 8 )
  }
  val lvls = ArrayBuffer(io.in.toArray)
  while( lvls.last.length > 1 ) {
    lvls += lvls.last.grouped(2).map( x => {
      if ( x.length > 1 ) RegNext(x(0) + x(1))
      else RegNext( x(0) )
    }).toArray
  }
  io.out := lvls.last(0)
}
