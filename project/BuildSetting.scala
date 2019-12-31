import org.apache.commons.io.FileUtils
import sbt.Keys._
import sbt.{Def, File, taskKey, _}

object BuildSettings {

  private lazy val compileProtoFile   = taskKey[Unit]("Generates java code from proto file")

  private lazy val cleanGeneratedClasses = taskKey[Unit]("Deletes generated Classes")

  private val generatedSrcCodeJavaDir = "src/generated/java"

  private def protoGenDir(module: String): String = {
    s"${module}/${generatedSrcCodeJavaDir}"
  }

  private def grpcCompileTask(moduleName: String) = {
    def generateProto(outputDir: String): Unit = {
      file(outputDir).mkdirs()
      import scala.sys.process._
      val generateGrpcJava =
        s"downloads/protoc-3.2.0-linux-x86_64/bin/protoc --plugin=protoc-gen-grpc-java=downloads/protoc-gen-grpc-java-1.2.0-linux-x86_64.exe --grpc-java_out=grpcjava/src/generated/java --java_out=grpcjava/src/generated/java -Igrpcjava/src/main/proto grpcjava/src/main/proto/example.proto"
      println(s"Running: $generateGrpcJava")
      generateGrpcJava.!
    }

    val outdir   = protoGenDir(moduleName)
    file(outdir).mkdirs()
    generateProto(outdir)

  }

  private def cleanGenerated(moduleName: String) = {
    val generatedDirectory = file(protoGenDir(moduleName))
    if (generatedDirectory.exists()) {
      FileUtils.deleteDirectory(generatedDirectory)
    }
  }

  val projectSettings: Seq[Def.Setting[_]] = Defaults.coreDefaultSettings ++
    Seq(
      organization in ThisBuild := "com.knoldus",
      scalaVersion in ThisBuild := "2.12.6",
      version := "0.1-SNAPSHOT",
      resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/",
      resolvers += "bintray algd" at "http://dl.bintray.com/content/algd/maven"
    )
  //scalastyle:off
  def BaseProject(name: String): Project =
    Project(name, file(name))
  //scalastyle:on

  def ProtoModule(name: String) = {
   BaseProject(name)
      .settings(
        Seq(
          javaSource in Compile := baseDirectory.value / generatedSrcCodeJavaDir,
          compileProtoFile := grpcCompileTask(name),
          (compile in Compile) := (compile in Compile).dependsOn(compileProtoFile).value,
          cleanGeneratedClasses := cleanGenerated(name),
          clean := clean.dependsOn(cleanGeneratedClasses).value,
          libraryDependencies ++= GrpcSets.grpc ++ List(Dependencies.protobuf),
          watchSources += baseDirectory.value / "src" / "main" / "proto"
        ) ++ projectSettings: _* )
  }
}
