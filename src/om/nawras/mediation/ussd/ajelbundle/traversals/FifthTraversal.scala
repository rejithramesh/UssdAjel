package om.nawras.mediation.ussd.ajelbundle.traversals

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service.{UssdMOResponse, UssdMORequest}
import om.nawras.mediation.ussd.ajelbundle.entrypoint.{ConfirmationScreen, MainMenu}

object FifthTraversal {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering Fifth Traversal ...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val response = request.getInput.split(":").reverse.head match {
      case "CONFIRM" => ConfirmationScreen.execute(request)
      case "DECLINE" => MainMenu.execute(request)
    }

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
