package om.nawras.mediation.ussd.ajelbundle.traversals

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service.{UssdMOResponse, UssdMORequest}
import om.nawras.mediation.ussd.ajelbundle.entrypoint._
import om.nawras.mediation.ussd.ajelbundle.constants.CorrelatorConstants

object SecondTraversal {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val traversal = request.getCorrelator.split(";").last.split(":")

    if (log.isDebugEnabled) {
      log.debug("first element in traversal: " + traversal.head)
      log.debug("last element in traversal: " + traversal.last)
    }

    val response = traversal.last match {
      case CorrelatorConstants.CHANGE_PLAN        => SelectDataPlan.execute(request)
      case CorrelatorConstants.SELECT_SHAHRY_KEY  => SubscribeUnsubscribeShahryKeys.execute(request)
      case CorrelatorConstants.SELECT_ADD_ON_KEY  => SubscribeUnsubscribeShahryKeys.execute(request)
      case _ => MainMenu.execute(request)
    }

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
