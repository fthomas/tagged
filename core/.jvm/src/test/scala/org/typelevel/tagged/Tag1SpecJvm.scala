package org.typelevel.tagged

import org.scalacheck.Prop._
import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag1._

class Tag1DisassembledAnyVal {
  val base: Int = 1
  val tagged: Int @@ SomeTag = tag(1)
  val untagged: Int = untag(tagged)
  val arrayWithTagged: Array[Int @@ SomeTag] = Array(tag(1), tag(2))
}

class Tag1DisassembledAnyRef {
  val base: String = "1"
  val tagged: String @@ SomeTag = tag("1")
  val untagged: String = untag(tagged)
  val arrayWithTagged: Array[String @@ SomeTag] = Array(tag("1"), tag("2"))
}

object Tag1SpecJvm extends Properties("tag1") {

  property("javap.AnyVal") = secure {
    val actual = javapOutput(new Tag1DisassembledAnyVal)
    val expected =
      """Compiled from "Tag1SpecJvm.scala"
        |public class org.typelevel.tagged.Tag1DisassembledAnyVal {
        |  public int base();
        |  public java.lang.Object tagged();
        |  public int untagged();
        |  public java.lang.Object[] arrayWithTagged();
        |  public org.typelevel.tagged.Tag1DisassembledAnyVal();
        |}
      """.stripMargin.trim

    actual ?= expected
  }

  property("javap.AnyRef") = secure {
    val actual = javapOutput(new Tag1DisassembledAnyRef)
    val expected =
      """Compiled from "Tag1SpecJvm.scala"
        |public class org.typelevel.tagged.Tag1DisassembledAnyRef {
        |  public java.lang.String base();
        |  public java.lang.String tagged();
        |  public java.lang.String untagged();
        |  public java.lang.String[] arrayWithTagged();
        |  public org.typelevel.tagged.Tag1DisassembledAnyRef();
        |}
      """.stripMargin.trim
    actual ?= expected
  }
}
