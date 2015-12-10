package org.typelevel.tagged

object tag1 {

  // shapeless-style tag
  // https://github.com/milessabin/shapeless/blob/master/core/src/main/scala/shapeless/typeoperators.scala

  trait Tagged[U]

  type @@[T, U] = T with Tagged[U]

  def apply[U] = new Tagger[U]

  class Tagger[U] {
    def apply[T](t: T): T @@ U = t.asInstanceOf[T @@ U]
  }

}
