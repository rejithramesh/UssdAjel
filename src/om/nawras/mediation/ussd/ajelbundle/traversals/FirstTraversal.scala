package om.nawras.mediation.ussd.ajelbundle.traversals

import om.nawras.mediation.ussd.telenity.service.{UssdMOResponse, UssdMORequest}
import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.ajelbundle.constants.MenuConstants
import om.nawras.mediation.ussd.ajelbundle.entrypoint.{ManageShahryKeys, MainMenu, ChangePlan, BalanceInquiry}


object FirstTraversal {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    if (log.isDebugEnabled) {
      log.debug("current input is: " + request.getInput)
    }

    val response = request.getInput match {

      case MenuConstants.CHANGE_PLAN     => ChangePlan.execute(request)

      case MenuConstants.BOLT_ON | MenuConstants.ADD_ON  => ManageShahryKeys.execute(request)

      case MenuConstants.BALANCE_INQUIRY | MenuConstants.ADD_ON_INQUIRY => BalanceInquiry.execute(request)

      case _ => MainMenu.execute(request)

    }

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
