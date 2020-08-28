package com.test.itr;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;
import java.io.IOException;
import java.util.Map;

public class CustomReceivingApplication implements ReceivingApplication
{
  private static HapiContext context = new DefaultHapiContext();

  public Message processMessage(Message receivedMessage, Map map) throws ReceivingApplicationException, HL7Exception
  {
    String receivedEncodedMessage = context.getPipeParser().encode(receivedMessage);
    System.out.println("Incoming message:\n" + receivedEncodedMessage + "\n");

    try {
      return receivedMessage.generateACK();
    } catch (IOException e) {
      throw new HL7Exception(e);
    }
  }

  public boolean canProcess(Message message)
  {
    return true;
  }
}
