package com.test.itr;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v251.message.ADT_A01;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.protocol.impl.AppRoutingDataImpl;
import java.io.IOException;

public class TestListener
{
  private static final int PORT = 8085;
  private static HapiContext hapiContext = new DefaultHapiContext();

  public static void main(String[] args) throws HL7Exception
  {
    try
    {
      MinLowerLayerProtocol mllp = new MinLowerLayerProtocol();
      mllp.setCharset("UTF-8");
      hapiContext.setLowerLayerProtocol(mllp);
      Connection connection = hapiContext.newLazyClient("localhost", PORT, false);
      Initiator initiator = connection.getInitiator();
      HL7Service hl7Service = hapiContext.newServer(PORT, false);

      AppRoutingDataImpl router = new AppRoutingDataImpl("ADT", "A0.", "*", "*");
      hl7Service.registerApplication(router, new CustomReceivingApplication());
      hl7Service.startAndWait();

      ADT_A01 admitMessage = (ADT_A01) AdtMessageFactory.createMessage("A01");
      Message messageResponse = initiator.sendAndReceive(admitMessage);

      Parser parser = hapiContext.getPipeParser();
      String responseString = parser.encode(messageResponse);
      System.out.println("Received a message:\n" + responseString);
      connection.close();
      hl7Service.stopAndWait();

    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (LLPException e)
    {
      e.printStackTrace();
    }

  }
}
