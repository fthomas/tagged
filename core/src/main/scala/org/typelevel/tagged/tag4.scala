package org.typelevel.tagged

object tag4 {
  // @milessabin's original "unboxed newtype"
  // https://gist.github.com/milessabin/89c9b47a91017973a35f

  type Tagged[U] = { type Tag = U }

  type @@[T, U] = T with Tagged[U]

  def tag[U] = new Tagger[U]

  def untag[T, U](tu: T @@ U): T = tu

  class Tagger[U] {
    def apply[T](t: T): T @@ U = t.asInstanceOf[T @@ U]
  }
}
