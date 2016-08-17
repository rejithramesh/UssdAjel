package om.nawras.mediation.ussd.ajelbundle.entrypoint

import om.nawras.mediation.ussd.ajelbundle.constants.MenuConstants
import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import om.nawras.mediation.ussd.ajelbundle.csi.client.{CheckAddOnBalanceWS, CheckDataBalanceWS}
import om.nawras.mediation.ussd.dao.ApplicationStringsDAO


object BalanceInquiry {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = MainMenu.LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val locale        = UssdUtils.getLocale(request.getLanguage)
    val eventSource   = UssdUtils.stripLeading968(UssdUtils.getMsisdnFromAddress(request.getAddress))
    val response      = new UssdMOResponse
    val notification  = new Notification
    val message       = new MessageContent

    val b = request.getInput.trim() match {
      case MenuConstants.BALANCE_INQUIRY => CheckDataBalanceWS.checkDataBalance(eventSource, locale)
      case _ => CheckAddOnBalanceWS.execute(eventSource, locale)

    }

    val balance = b._1 match {
      case 0 => b._2
      case _ => if (b._2.isEmpty) {
        new ApplicationStringsDAO().getAppText("UssdFamilyCug", "GENERIC_ERROR", locale)
      } else b._2
    }

    notification.setMessage(balance)
    notification.setUnicode(UssdUtils.isUnicode(locale))

    message.setNotification(notification)
    message.setType(ContentType.NOTIFICATION)

    response.setMessage(message)
    response.setBreakSession(false)

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
