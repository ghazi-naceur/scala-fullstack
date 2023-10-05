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
