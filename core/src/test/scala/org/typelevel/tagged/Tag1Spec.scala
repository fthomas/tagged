package org.typelevel.tagged

import org.scalacheck.Prop._
import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag1._
import shapeless.=:!=
import shapeless.test.illTyped

object Tag1Spec extends Properties("tag1") {

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

  property("type alias unfriendly") = wellTyped {
    type SomeInt = Int @@ SomeTag
    def foo(i: SomeInt) = i
    illTyped("foo(tag(1))", "(?s)type mismatch.*")
    // see https://issues.scala-lang.org/browse/SI-8740
  }
}
