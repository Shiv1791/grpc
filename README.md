 This template describes how to generate the java code from the grpc Protocol buffers also called protobuf.
 
 1. What are protocol buffers?
   Protocol buffers are Google's language-neutral, platform-neutral, extensible mechanism for serializing structured data – think XML,
    but smaller, faster, and simpler.
   You define how you want your data to be structured once, then you can use special generated source code to easily write and 
    read your structured data to and from a variety of data streams and using a variety of languages. 
 
 
 How do they work?
 You specify how you want the information you're serializing to be structured by defining protocol buffer message types in .proto files. 
 Each protocol buffer message is a small logical record of information, containing a series of name-value pairs. 
 Here's a very basic example of a .proto file that defines a message containing information about a Greeting Service: 
 
 How to define the the protobuf. 
  message HelloRequest {
      string name = 1;
  }
  
  // The response message containing the greetings
  message HelloReply {
      string message = 1;
  }
  
  // The greeting service definition.
  service StreamingGreeter {
      // Streams a many greetings
      rpc SayHelloStreaming (stream HelloRequest) returns (stream HelloReply) {}
  }
  
 
 How to generate the java code?
 You just simply run the command on sbt 
 `compile` 
 and it will generate the all corresponding java models for each message and service define in the .proto file in the given directory i.e generated for our case.
 
 
 
 Quick Start
 
 The best way to learn how to use protobuf is to follow the tutorials in our developer guide:
 https://developers.google.com/protocol-buffers/docs/tutorials

 Documentation
 
 The complete documentation for Protocol Buffers is available via the web at:
 
 https://developers.google.com/protocol-buffers/