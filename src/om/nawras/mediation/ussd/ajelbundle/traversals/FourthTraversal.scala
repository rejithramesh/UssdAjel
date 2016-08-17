package om.nawras.mediation.ussd.ajelbundle.traversals

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service.{UssdMOResponse, UssdMORequest}
import om.nawras.mediation.ussd.ajelbundle.entrypoint.{MainMenu, ApplyShahryKey, ConfirmSelection}
import om.nawras.mediation.ussd.ajelbundle.constants.MenuConstants

object FourthTraversal {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val correlator = request.getCorrelator.split(";").last.split(":").last

    val response = correlator match {
      case MenuConstants.DATA_BUNDLE_SELECT => ConfirmSelection.execute(request)
      case MenuConstants.SUBSCRIBE_SHAHRY_KEYS => ApplyShahryKey.execute(request)
      case MenuConstants.UNSUBSCRIBE_SHAHRY_KEYS => ApplyShahryKey.execute(request)
      case _ => MainMenu.execute(request)
    }

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
      
    }

    response
  }

}
