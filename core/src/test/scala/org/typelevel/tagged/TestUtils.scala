package org.typelevel.tagged

import org.scalacheck.Prop

object TestUtils {

  trait SomeTag

  def wellTyped(body: => Unit): Prop = Prop.secure {
    body
    true
  }

}
