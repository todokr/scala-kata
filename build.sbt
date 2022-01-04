import Dependencies._

lazy val scalaKata = (project in file("scala-kata"))
  .settings(
    scalaVersion := "2.13.3",
    name := "Scala Kata"
  )
  .aggregate(scalaKata1, scalaKata2, scalaKata3)

lazy val scalaKata1 = (project in file("scala-kata-1"))
  .settings(
    scalaVersion := "2.13.3",
    name := "Scala Kata1",
    libraryDependencies ++= Seq(
      scalaTest
    )
  )

lazy val scalaKata2 = (project in file("scala-kata-2"))
  .settings(
    scalaVersion := "2.13.3",
    name := "Scala Kata2",
    libraryDependencies ++= Seq(
      scalaTest
    )
  )

lazy val scalaKata3 = (project in file("scala-kata-3"))
  .settings(
    scalaVersion := "2.13.3",
    name := "Scala Kata3",
    libraryDependencies ++= Seq(
      scalaTest
    )
  )
