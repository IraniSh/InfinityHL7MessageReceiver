package com.test.itr;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import java.io.IOException;

public class AdtMessageFactory
{
  public static Message createMessage(String messageType) throws HL7Exception, IOException
  {

    if ( messageType.equals("A01") )
    {
      return new AdtA01MessageBuilder().Build();
    }

    throw new RuntimeException(String.format("%s message type is not supported yet.", messageType));

  }
}
