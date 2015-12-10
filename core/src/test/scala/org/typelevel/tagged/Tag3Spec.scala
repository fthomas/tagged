package org.typelevel.tagged

import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag3._
import shapeless.{<:!<, =:!=}

class Tag3Spec extends Properties("tag3") {

  property("(T @@ U) =:!= T") = wellTyped {
    implicitly[(Int @@ SomeTag) =:!= Int]
  }

  property("(T @@ U) <:!  T") = wellTyped {
    implicitly[(Int @@ SomeTag) <:!< Int]
  }

}
