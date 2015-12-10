package org.typelevel.tagged

import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag1._
import shapeless.=:!=

class Tag1Spec extends Properties("tag1") {

  property("(T @@ U) =:!= T") = wellTyped {
    implicitly[(Int @@ SomeTag) =:!= Int]
  }

  property("(T @@ U) <:   T") = wellTyped {
    implicitly[(Int @@ SomeTag) <:< Int]
  }

}
