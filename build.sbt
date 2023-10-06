ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

val laminarVersion = "16.0.0"
val webComponentsVersion = "1.17.0"
val http4sVersion = "0.23.9"
val logbackVersion = "1.4.7"

lazy val root = (project in file("."))
  .settings(
    name := "scala-fullstack"
  )

lazy val frontend = project
  .in(file("./frontend"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "com.raquo" %%% "laminar" % laminarVersion,
      "be.doeraene" %%% "web-components-ui5" % webComponentsVersion
    ),
    scalaJSUseMainModuleInitializer := true
  )
  .dependsOn(domain.js)

lazy val backend = project
  .in(file("./backend"))
  .settings(
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-client" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "ch.qos.logback" % "logback-classic" % logbackVersion
    )
  )
  .dependsOn(domain.jvm)

lazy val domain = crossProject(JSPlatform, JVMPlatform)
  .in(file("./domain"))
  .settings(
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-circe" % http4sVersion,
      // Optional for auto-derivation of JSON codecs
      "io.circe" %% "circe-generic" % "0.14.5",
      "io.circe" %% "circe-parser" % "0.14.5",
      // Optional for string interpolation to JSON model
      "io.circe" %% "circe-literal" % "0.14.5"
    )
  )
