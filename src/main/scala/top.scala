import Chisel._
import chiseltemplate._

/** This file is used to generate verilog but can be used for anything you want
  */
object top {
  def main(args: Array[String]): Unit = {
    val chiselArgs = args.drop(1)
    chiselMain(chiselArgs, () => Module( new MyTopLevelModule ) )
  }
}
