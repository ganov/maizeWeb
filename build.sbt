name := """maizeWeb"""

version := "2.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  //WebJar Tools
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "bootstrap" % "3.3.6",
  "org.webjars" % "jquery" % "2.2.4",
  "org.webjars" % "font-awesome" % "4.6.3",
  "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3",
  "be.objectify" %% "deadbolt-java" % "2.5.0",
  "org.webjars" % "datatables" % "1.10.12"
)

includeFilter in (Assets, LessKeys.less) := "fontFamily.less" | "main.less" | "mainAdmin.less"