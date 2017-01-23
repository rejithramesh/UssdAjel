package om.nawras.mediation.ussd.ajelbundle.traversals

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service.{UssdMORequest, UssdMOResponse}
import om.nawras.mediation.ussd.ajelbundle.constants.MenuConstants
import om.nawras.mediation.ussd.ajelbundle.entrypoint._

object ThirdTraversal {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Third Traversal -- Entering..."+request.getInput.split(":").reverse.head)
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val response = request.getInput.split(":").reverse.head match {

      case MenuConstants.THREEG_DATA => DataBundleSelect.execute(request)

      case MenuConstants.BLACKBERRY_DATA => DataBundleSelect.execute(request)

      case MenuConstants.SUBSCRIBE_SHAHRY_KEYS => ValidateSubUnsubShahryKeys.execute(request)

      case MenuConstants.UNSUBSCRIBE_SHAHRY_KEYS => ValidateSubUnsubShahryKeys.execute(request)

      case "4" => {
                    val  req =request
                    req.setInput("VDP:Stream On'")
                    req.setCorrelator("FIRST_TRAVERSAL:;SECOND_TRAVERSAL:SELECT_ADDON_KEY;")
                    SubscribeUnsubscribeShahryKeys.execute(req)
                    }

      case "None" => ConfirmSelection.execute(request) //take to the confirm screen

      case _ => MainMenu.execute(request)
    }

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
