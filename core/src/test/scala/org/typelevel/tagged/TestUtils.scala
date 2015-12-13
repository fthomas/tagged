package org.typelevel.tagged

import java.net.URL

import org.scalacheck.Prop

import scala.sys.process._

object TestUtils {

  trait SomeTag

  def wellTyped(body: => Unit): Prop = Prop.secure {
    body
    true
  }

  def getClassFile[C](c: C): String =
    c.getClass.getCanonicalName.replace('.', '/') + ".class"

  def getClassFilePath[C](c: C): URL =
    getClass.getClassLoader.getResource(getClassFile(c))

  def javapOutput[C](c: C): String =
    s"javap ${getClassFilePath(c)}".!!.trim
}
