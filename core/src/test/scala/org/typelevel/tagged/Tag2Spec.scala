package org.typelevel.tagged

import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag2._
import shapeless.{<:!<, =:!=}

class Tag2Spec extends Properties("tag2") {

  property("(T @@ U) =:!= T") = wellTyped {
    implicitly[(Int @@ SomeTag) =:!= Int]
  }

  property("(T @@ U) <:!  T") = wellTyped {
    implicitly[(Int @@ SomeTag) <:!< Int]
  }

}
