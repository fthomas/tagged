package org.typelevel.tagged

import org.scalacheck.Prop._
import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag2._

class Tag2DisassembledAnyVal {
  val base: Int = 1
  val tagged: Int @@ SomeTag = tag(1)
  val untagged: Int = untag(tagged)
  val arrayWithTagged: Array[Int @@ SomeTag] = Array(tag(1), tag(2))
  val taggedTwice: (Int @@ SomeTag) @@ SomeTag = tag(tag(1))
}

class Tag2DisassembledAnyRef {
  val base: String = "1"
  val tagged: String @@ SomeTag = tag("1")
  val untagged: String = untag(tagged)
  val arrayWithTagged: Array[String @@ SomeTag] = Array(tag("1"), tag("2"))
  val taggedTwice: (String @@ SomeTag) @@ SomeTag = tag(tag("1"))
}

object Tag2SpecJvm extends Properties("tag2") {

  property("javap.AnyVal") = secure {
    val actual = javapOutput(new Tag2DisassembledAnyVal)
    val expected =
      """Compiled from "Tag2SpecJvm.scala"
        |public class org.typelevel.tagged.Tag2DisassembledAnyVal {
        |  public int base();
        |  public java.lang.Object tagged();
        |  public int untagged();
        |  public java.lang.Object[] arrayWithTagged();
        |  public java.lang.Object taggedTwice();
        |  public org.typelevel.tagged.Tag2DisassembledAnyVal();
        |}
      """.stripMargin.trim
    actual ?= expected
  }

  property("javap.AnyRef") = secure {
    val actual = javapOutput(new Tag2DisassembledAnyRef)
    val expected =
      """Compiled from "Tag2SpecJvm.scala"
        |public class org.typelevel.tagged.Tag2DisassembledAnyRef {
        |  public java.lang.String base();
        |  public java.lang.Object tagged();
        |  public java.lang.String untagged();
        |  public java.lang.Object[] arrayWithTagged();
        |  public java.lang.Object taggedTwice();
        |  public org.typelevel.tagged.Tag2DisassembledAnyRef();
        |}
      """.stripMargin.trim
    actual ?= expected
  }
}
