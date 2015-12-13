package org.typelevel.tagged

import org.scalacheck.Prop._
import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag2._
import shapeless.{<:!<, =:!=}

object Tag2Spec extends Properties("tag2") {

  property("untag . tag = id") = forAll { (i: Int) =>
    untag(tag[SomeTag](i)) == i
  }

  property("tag(x) == x") = secure {
    tag[SomeTag](1) == 1
  }

  property("(T @@ U) =:!= T") = wellTyped {
    implicitly[(Int @@ SomeTag) =:!= Int]
  }

  property("(T @@ U) <:! T") = wellTyped {
    implicitly[(Int @@ SomeTag) <:!< Int]
  }

  property("(Int @@ U) <:! AnyVal") = wellTyped {
    implicitly[(Int @@ SomeTag) <:!< AnyVal]
  }

  property("(Int @@ U) <: AnyRef") = wellTyped {
    implicitly[(Int @@ SomeTag) <:< AnyRef]
  }

  property("type alias friendly") = secure {
    type SomeInt = Int @@ SomeTag
    def foo(i: SomeInt) = i
    foo(tag(1)) == tag[SomeTag](1)
  }
}
