import Dependencies.{grpcNetty, grpcProtobuf, grpcStub, javaxAnnotation}
import sbt._

object Dependencies {


  /**
   * Grpc related dependencies
   */

  val grpcNetty = "io.grpc" % "grpc-netty" % "1.2.0"
  val protobuf     = "com.google.protobuf" % "protobuf-java" % "3.2.0"
  val javaxAnnotation = "javax.annotation" % "javax.annotation-api" % "1.3.2"
  val grpcStub = "io.grpc" % "grpc-stub" % "1.2.0"
  val grpcProtobuf = "io.grpc" % "grpc-protobuf" % "1.2.0"


  /**
   * Test libraries
   */
  val scalaTestVersion = "3.0.5"
  val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion
  val typesafeConfig = "com.typesafe" % "config" % "1.3.3"

}


object GrpcSets {
  val grpc    = Seq(javaxAnnotation, grpcNetty, grpcProtobuf, grpcStub)
}