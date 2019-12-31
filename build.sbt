
import Dependencies._
name := "grpc"

version := "0.1"

scalaVersion := "2.13.1"

//Seq(javaxAnnotation, grpcNetty, grpcProtobuf, grpcStub)
// Definition of modules that make up this project
lazy val grpcjava = BuildSettings.ProtoModule("grpcjava")
  .settings(libraryDependencies ++= GrpcSets.grpc)




