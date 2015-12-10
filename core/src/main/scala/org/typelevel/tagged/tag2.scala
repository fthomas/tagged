package org.typelevel.tagged

object tag2 {

  // scalaz-style tag
  // https://github.com/scalaz/scalaz/blob/series/7.3.x/core/src/main/scala/scalaz/package.scala

  type @@[T, U] = { type Self = T; type Tag = U }

  def tag[U] = new Tagger[U]

  def untag[T, U](tu: T @@ U): T = tu.asInstanceOf[T]

  class Tagger[U] {
    def apply[T](t: T): T @@ U = t.asInstanceOf[T @@ U]
  }

}
