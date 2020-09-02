package com.test.itr;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v251.datatype.PL;
import ca.uhn.hl7v2.model.v251.datatype.XAD;
import ca.uhn.hl7v2.model.v251.datatype.XCN;
import ca.uhn.hl7v2.model.v251.datatype.XPN;
import ca.uhn.hl7v2.model.v251.message.ADT_A01;
import ca.uhn.hl7v2.model.v251.segment.EVN;
import ca.uhn.hl7v2.model.v251.segment.MSH;
import ca.uhn.hl7v2.model.v251.segment.PID;
import ca.uhn.hl7v2.model.v251.segment.PV1;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdtA01MessageBuilder
{
  private ADT_A01 adtMessage;

  public ADT_A01 Build() throws HL7Exception, IOException
  {
    adtMessage = new ADT_A01();
    adtMessage.initQuickstart("ADT", "A01", "P");
    createMshSegment();
    createEvnSegment();
    createPidSegment();
    createPv1Segment();
    return adtMessage;
  }

  private void createMshSegment() throws DataTypeException
  {
    MSH mshSegment = adtMessage.getMSH();
    mshSegment.getFieldSeparator().setValue("|");
    mshSegment.getEncodingCharacters().setValue("^~\\&");
    mshSegment.getSendingApplication().getNamespaceID().setValue("System");
    mshSegment.getSendingFacility().getNamespaceID().setValue("Facility");
    mshSegment.getReceivingApplication().getNamespaceID().setValue("Remote System");
    mshSegment.getReceivingFacility().getNamespaceID().setValue("Remote Facility");
    mshSegment.getMessageControlID().setValue(getSequenceNumber());
    mshSegment.getVersionID().getVersionID().setValue("2.5.1");
    mshSegment.getCharacterSet(0).setValue("UNICODE UTF-8");
  }

  private void createEvnSegment() throws DataTypeException
  {
    EVN evn = adtMessage.getEVN();
    evn.getEventTypeCode().setValue("A01");
  }

  private void createPidSegment() throws DataTypeException
  {
    PID pid = adtMessage.getPID();
    XPN patientName = pid.getPatientName(0);
    patientName.getFamilyName().getSurname().setValue("Söng");
    patientName.getGivenName().setValue("Jöng Ki");
    XAD patientAddress = pid.getPatientAddress(0);
    patientAddress.getStreetAddress().getStreetOrMailingAddress().setValue("History DC");
    patientAddress.getCity().setValue("Itaewon dong");
    patientAddress.getStateOrProvince().setValue("Seoul");
    patientAddress.getCountry().setValue("Korea");
  }

  private void createPv1Segment() throws DataTypeException
  {
    PV1 pv1 = adtMessage.getPV1();
    pv1.getPatientClass().setValue("I");
    PL assignedPatientLocation = pv1.getAssignedPatientLocation();
    assignedPatientLocation.getFacility().getNamespaceID().setValue("Some Treatment Facility Name");
    assignedPatientLocation.getPointOfCare().setValue("Some Point of Care");
    pv1.getAdmissionType().setValue("ALERT");
    XCN referringDoctor = pv1.getReferringDoctor(0);
    referringDoctor.getIDNumber().setValue("99999999");
    referringDoctor.getFamilyName().getSurname().setValue("Kim");
    referringDoctor.getGivenName().setValue("Hyun Jöong");
    referringDoctor.getIdentifierTypeCode().setValue("456789");
  }

  private String getCurrentTimeStamp()
  {
    return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
  }

  private String getSequenceNumber()
  {
    String facilityNumberPrefix = "1234";
    return facilityNumberPrefix.concat(getCurrentTimeStamp());
  }

}
