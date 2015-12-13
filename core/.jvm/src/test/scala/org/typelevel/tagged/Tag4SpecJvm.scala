package org.typelevel.tagged

import org.scalacheck.Prop._
import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag4._

class Tag4DisassembledAnyVal {
  val base: Int = 1
  val tagged: Int @@ SomeTag = tag(1)
  val untagged: Int = untag(tagged)
  val arrayWithTagged: Array[Int @@ SomeTag] = Array(tag(1), tag(2))
}

class Tag4DisassembledAnyRef {
  val base: String = "1"
  val tagged: String @@ SomeTag = tag("1")
  val untagged: String = untag(tagged)
  val arrayWithTagged: Array[String @@ SomeTag] = Array(tag("1"), tag("2"))
}

object Tag4SpecJvm extends Properties("tag4") {

  property("javap.AnyVal") = secure {
    val actual = javapOutput(new Tag4DisassembledAnyVal)
    val expected =
      """Compiled from "Tag4SpecJvm.scala"
        |public class org.typelevel.tagged.Tag4DisassembledAnyVal {
        |  public int base();
        |  public int tagged();
        |  public int untagged();
        |  public int[] arrayWithTagged();
        |  public org.typelevel.tagged.Tag4DisassembledAnyVal();
        |}
      """.stripMargin.trim
    actual ?= expected
  }

  property("javap.AnyRef") = secure {
    val actual = javapOutput(new Tag4DisassembledAnyRef)
    val expected =
      """Compiled from "Tag4SpecJvm.scala"
        |public class org.typelevel.tagged.Tag4DisassembledAnyRef {
        |  public java.lang.String base();
        |  public java.lang.String tagged();
        |  public java.lang.String untagged();
        |  public java.lang.String[] arrayWithTagged();
        |  public org.typelevel.tagged.Tag4DisassembledAnyRef();
        |}
      """.stripMargin.trim
    actual ?= expected
  }
}
