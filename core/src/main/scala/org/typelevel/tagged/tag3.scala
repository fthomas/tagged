package org.typelevel.tagged

object tag3 {
  // refined-style tag
  // https://github.com/fthomas/refined/blob/master/core/jvm/src/main/scala-2.11/eu/timepit/refined/api/Refined.scala

  final case class @@[T, U](t: T) extends AnyVal

  def tag[U] = new Tagger[U]

  def untag[T, U](tu: T @@ U): T = tu.t

  class Tagger[U] {
    def apply[T](t: T): T @@ U = @@(t)
  }
}
