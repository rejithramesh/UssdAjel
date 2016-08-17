package om.nawras.mediation.ussd.ajelbundle.entrypoint

import om.nawras.mediation.ussd.telenity.service._
import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.ajelbundle.constants.MenuConstants
import om.nawras.ussd.utils.UssdUtils
import om.nawras.mediation.ussd.ajelbundle.csi.client.{TerminateProductWS, AddProductWS}

object ApplyShahryKey {

  val CLASS_NAME = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  def doApplyChoice(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val locale      = UssdUtils.getLocale(request.getLanguage)
    val eventSource = UssdUtils.stripLeading968(UssdUtils.getMsisdnFromAddress(request.getAddress))

    val sessionData = request.getInput.split(",")
    val productId   = sessionData.head
    val productName = sessionData.last

    if (log.isDebugEnabled) {
      log.debug("productId: " + productId)
      log.debug("productName: " + productName)
    }

    val correlator = request.getCorrelator.split(";").last.split(":").last.trim

    val applyChoiceResult = correlator match {
      case MenuConstants.SUBSCRIBE_SHAHRY_KEYS    => AddProductWS.execute(eventSource, productId, locale)
      case MenuConstants.UNSUBSCRIBE_SHAHRY_KEYS  => TerminateProductWS.execute(eventSource, productId, locale)
    }

    val resultMessage: String = applyChoiceResult._2

    val notification = new Notification
    notification.setMessage(resultMessage)
    notification.setUnicode(UssdUtils.isUnicode(locale))

    val content = new MessageContent
    content.setNotification(notification)
    content.setType(ContentType.NOTIFICATION)

    val response = new UssdMOResponse
    response.setBreakSession(false)
    response.setMessage(content)

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

  def execute(request: UssdMORequest): UssdMOResponse = {
    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val response = doApplyChoice(request)

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
