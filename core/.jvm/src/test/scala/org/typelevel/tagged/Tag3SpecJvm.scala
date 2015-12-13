package org.typelevel.tagged

import org.scalacheck.Prop._
import org.scalacheck.Properties
import org.typelevel.tagged.TestUtils._
import org.typelevel.tagged.tag3._

class Tag3DisassembledAnyVal {
  val base: Int = 1
  val tagged: Int @@ SomeTag = tag(1)
  val untagged: Int = untag(tagged)
  val arrayWithTagged: Array[Int @@ SomeTag] = Array(tag(1), tag(2))
  val taggedTwice: (Int @@ SomeTag) @@ SomeTag = tag(tag(1))
}

class Tag3DisassembledAnyRef {
  val base: String = "1"
  val tagged: String @@ SomeTag = tag("1")
  val untagged: String = untag(tagged)
  val arrayWithTagged: Array[String @@ SomeTag] = Array(tag("1"), tag("2"))
  val taggedTwice: (String @@ SomeTag) @@ SomeTag = tag(tag("1"))
}

object Tag3SpecJvm extends Properties("tag3") {

  property("javap.AnyVal") = secure {
    val actual = javapOutput(new Tag3DisassembledAnyVal)
    val expected =
      """Compiled from "Tag3SpecJvm.scala"
        |public class org.typelevel.tagged.Tag3DisassembledAnyVal {
        |  public int base();
        |  public int tagged();
        |  public int untagged();
        |  public int[] arrayWithTagged();
        |  public int taggedTwice();
        |  public org.typelevel.tagged.Tag3DisassembledAnyVal();
        |}
      """.stripMargin.trim
    actual ?= expected
  }

  property("javap.AnyRef") = secure {
    val actual = javapOutput(new Tag3DisassembledAnyRef)
    val expected =
      """Compiled from "Tag3SpecJvm.scala"
        |public class org.typelevel.tagged.Tag3DisassembledAnyRef {
        |  public java.lang.String base();
        |  public java.lang.String tagged();
        |  public java.lang.String untagged();
        |  public java.lang.String[] arrayWithTagged();
        |  public java.lang.String taggedTwice();
        |  public org.typelevel.tagged.Tag3DisassembledAnyRef();
        |}
      """.stripMargin.trim
    actual ?= expected
  }
}
