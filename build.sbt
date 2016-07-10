lazy val root = (project in file(".")).
  settings(
    organization := "net.nornagon",
    name := "scala-llvm",
    version := "1.0",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "net.java.dev.jna" % "jna" % "4.2.1",
      "com.nativelibs4java" % "bridj" % "0.7.0"
    )
  ).
  settings(Jnaerator.settings).
  settings(
    jnaeratorTargets := Seq(Jnaerator.Target(
      packageName = "llvm.binding",
      libraryName = "LLVM",
      headerFiles = (
        file("/usr/local/opt/llvm/include/llvm-c/Support.h").get ++
        (file("/usr/local/opt/llvm/include/llvm-c") ** "*.h").get
      ).distinct.filterNot(_.getPath.endsWith("/lto.h")),
      extraArgs = Seq("-emptyStructsAsForwardDecls")
    ))
  )
