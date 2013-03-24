import scala.pickling._
import binary._

import reflect.runtime.universe._

class Base
case class Generic[T](x: T) extends Base {
  override def toString =
    s"Generic(${ x.toString })"
}
case class NonGeneric(s: String) extends Base
case class Person(age: Int)

object Test extends App {

  val g: Any = Generic(Person(42))
  val p = g.pickle // select runtime pickler generation since g has static type Any
  println(p.value.asInstanceOf[Array[Byte]].mkString("[", ",", "]"))

  val ug: Base = p.unpickle[Base]
  println("unpickled: " + ug)

}
