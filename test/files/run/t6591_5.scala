import scala.reflect.runtime.universe._
import scala.tools.reflect.ToolBox
import scala.tools.reflect.Eval
import scala.util.control.Exception._

class O { class I }

object A extends O {
  val x = new O
  val code = reify {
    val v: x.I = ???
    v
  }
  println(showRaw(code))
}

object Test extends App {
  ignoring(classOf[java.lang.reflect.InvocationTargetException]) {
    val v: A.x.I = A.code.eval
  }
}
