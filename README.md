# InfinityHL7MessageReceiver for HL7 Message Processing

This repository contains Java classes for processing HL7 messages. HL7 (Health Level Seven) is a set of international standards for the exchange, integration, sharing, and retrieval of electronic health information. The classes in this repository demonstrate how to send and receive HL7 messages using the HAPI (HL7 API) library.

## Classes

### CustomReceivingApplication.java

This class implements the `ReceivingApplication` interface provided by HAPI. It is responsible for processing incoming HL7 messages and generating an acknowledgment message in response. The `processMessage` method takes the received HL7 message, encodes it, prints it to the console, and then generates an ACK message as a response. The `canProcess` method always returns `true`, indicating that this application can process any type of HL7 message.

### TestListener.java

This class demonstrates how to set up an HL7 listener using HAPI. It creates a new server instance on the specified port (default: 8085) and registers a custom receiving application (`CustomReceivingApplication`) to process incoming HL7 messages. The class uses the `AppRoutingDataImpl` to define which messages this application should process. In this case, it processes all "ADT" messages with trigger event "A01".

When a connection is established, the listener waits for incoming messages, and upon receiving an ADT_A01 message, it sends back an acknowledgment message. The message exchange is printed to the console.

## Prerequisites

1. Java Development Kit (JDK) installed on your system.
2. HAPI (HL7 API) library included in your project. You can download it from the [HAPI website](https://hapifhir.io/hapi-hl7v2/download.html) or use a build tool like Maven or Gradle to manage dependencies.

## How to Use

1. Download or clone this repository to your local machine.

2. Make sure you have the HAPI library included in your project classpath.

3. Update the code to adapt it to your specific use case, if needed. For example, you can modify the port number or the conditions for processing messages.

4. Compile and run the `TestListener` class. This will start the HL7 listener, waiting for incoming ADT_A01 messages.

5. Send an ADT_A01 message to the listener. You can use your preferred HL7 message sender or testing tool to send the message to the listener's address (localhost:8085 by default).

6. The listener will process the received message, generate an acknowledgment message, and print the response to the console.

## Note

Ensure that the custom receiving application (`CustomReceivingApplication`) is appropriately modified to handle different types of HL7 messages and responses as per your requirements.

For more information on HL7, HAPI, and how to customize and extend the functionality, refer to the [HAPI documentation](https://hapifhir.io/hapi-hl7v2/).
