package om.nawras.mediation.ussd.ajelbundle.entrypoint

import javax.jws.WebService

import om.nawras.mediation.ussd.telenity.service.{ReceiveUssd, UssdMORequest, UssdMOResponse}

@WebService(serviceName = "ReceiveUssdService",
            endpointInterface = "om.nawras.mediation.ussd.telenity.service.ReceiveUssd",
            targetNamespace = "http://www.telenity.com/wsdl/parlayx/ussd/receive/v1_0/service")
class ReceiveUssdImpl extends ReceiveUssd {

  def processUssdMORequest(ussdMORequest: UssdMORequest): UssdMOResponse =
  {
      UssdDelegate.execute(ussdMORequest)
  }

  def closeSession(sessionId: String) =
  {

  }
}
