package org.typelevel.tagged

import org.scalacheck.Prop._
import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag4._
import shapeless.=:!=
import shapeless.test.illTyped

object Tag4Spec extends Properties("tag4") {

  property("untag . tag = id") = forAll { (i: Int) =>
    untag(tag[SomeTag](i)) == i
  }

  property("tag(x) == x") = secure {
    tag[SomeTag](1) == 1
  }

  property("(T @@ U) =:!= T") = wellTyped {
    implicitly[(Int @@ SomeTag) =:!= Int]
  }

  property("(T @@ U) <: T") = wellTyped {
    implicitly[(Int @@ SomeTag) <:< Int]
  }

  property("(Int @@ U) <: AnyVal") = wellTyped {
    implicitly[(Int @@ SomeTag) <:< AnyVal]
  }

  property("(Int @@ U) <: AnyRef") = wellTyped {
    implicitly[(Int @@ SomeTag) <:< AnyRef]
  }

  property("type alias") = wellTyped {
    type SomeInt = Int @@ SomeTag
    def foo(i: SomeInt) = i
    illTyped("foo(tag(1))", "(?s)type mismatch.*")
  }

  property("nested tags") = wellTyped {
    illTyped("val i: (Int @@ SomeTag) @@ SomeTag = tag[SomeTag](tag[SomeTag](1))",
      "cyclic aliasing or subtyping involving type @@")
  }
}
