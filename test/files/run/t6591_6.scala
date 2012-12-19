import scala.language.existentials
import scala.reflect.runtime.universe._
import scala.tools.reflect.ToolBox
import scala.tools.reflect.Eval
import scala.util.control.Exception._

class O { class I }

class A extends O {
  val x = new O
  val code = reify {
    val v: x.I = ???
    v
  }
  println(showRaw(code))
}

object Test extends App {
  type T = a.x.I forSome { val a: A }
  ignoring(classOf[java.lang.reflect.InvocationTargetException]) {
    //val v: a.x.I forSome { val a: A } = (new A).code.eval //type mismatch
    val v: T = (new A).code.eval //compiles fine
  }
}
