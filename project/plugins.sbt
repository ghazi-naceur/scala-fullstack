addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.14.0")
//sbt plugin, 'sbt-crossproject', which provides a builder crossProject constructing two related sbt projects,
// one for the JVM, and one for JS.
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.0.0")

// Fat jar generation: https://github.com/sbt/sbt-assembly
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.15.0")

// Backend hot reload: https://github.com/spray/sbt-revolver)
addSbtPlugin("io.spray" % "sbt-revolver" % "0.10.0")

// Build server and CLI tool for the Scala
addSbtPlugin("ch.epfl.scala" % "sbt-bloop" % "1.5.11")
