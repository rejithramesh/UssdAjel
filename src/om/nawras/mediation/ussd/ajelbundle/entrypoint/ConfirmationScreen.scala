package om.nawras.mediation.ussd.ajelbundle.entrypoint

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import om.nawras.mediation.ussd.ajelbundle.csi.client.ChangeSubscriptionBundlePlanWS
import om.nawras.mediation.ussd.dao.{ApplicationStringsDAO, ApplicationApiResponsesDAO}
import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants

object ConfirmationScreen {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  val SUCCESS     = "Confirm.Success"

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = MainMenu.LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering Confirmation Screen...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val locale        = UssdUtils.getLocale(request.getLanguage)
    val eventSource   = UssdUtils.stripLeading968(UssdUtils.getMsisdnFromAddress(request.getAddress))
    val response      = new UssdMOResponse
    val notification  = new Notification
    val message       = new MessageContent
    var baseProduct = "trial"
    var dataProduct = "trial"
    try
      {
    baseProduct   = request.getInput.split(":").head
    dataProduct   = if (request.getInput.split(":").apply(4) == "None") ""  else request.getInput.split(":").apply(4)
      }
    catch
      {

        case _: Throwable => {
                          log.debug("***********************Yet if Exception Occured *********************** ")
                          baseProduct   = "VDP"
                          dataProduct   = "Stream On"
        }

      }
    if (log.isDebugEnabled) {
      log.debug("baseProductId["+baseProduct+"]")
      log.debug("dataProductId["+dataProduct+"]")
    }

    val rep = ChangeSubscriptionBundlePlanWS.changeSubscriptionBundlePlanWS(eventSource, baseProduct, dataProduct, locale)

    val responseMessage = rep._1 match {
      case 0 => new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.ChangeSubscriptionBundlePlanWS, 0, locale)
      case _ => if (rep._2.isEmpty) {
        new ApplicationStringsDAO().getAppText("UssdFamilyCug", "GENERIC_ERROR", locale)
      } else rep._2
    }

    notification.setMessage(responseMessage)
    notification.setUnicode(UssdUtils.isUnicode(locale))

    message.setNotification(notification)
    message.setType(ContentType.NOTIFICATION)

    response.setMessage(message)
    response.setBreakSession(false)


    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting confirmation...")
    }

    response
  }

}
