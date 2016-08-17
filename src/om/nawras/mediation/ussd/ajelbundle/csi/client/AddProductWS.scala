package om.nawras.mediation.ussd.ajelbundle.csi.client

import org.apache.commons.logging.LogFactory
import java.util.Locale
import javax.xml.ws.Holder
import om.nawras.mediation.ussd.dao._
import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants
import java.sql.Date

object AddProductWS {

  val LOG_EXECUTE = LogFactory.getLog(this.getClass.getName)

  def execute(eventSource: String, productIdentifier: String, locale: Locale) = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug("inputs eventSource[" + eventSource + "]")
      log.debug("inputs productIdentifier[" + productIdentifier + "]")
      log.debug("inputs locale[" + locale + "]")
    }

    val responseCode = new Holder[java.lang.Integer]
    val responseMessage = new Holder[String]

    try {
      PostpaidServiceConfig.getWs.addProduct(eventSource, productIdentifier, locale.getLanguage,
        PostpaidServiceConfig.getApiUserName, PostpaidServiceConfig.getApiPassword, responseCode, responseMessage)
    } catch {
      case e: Exception =>
        if (log.isErrorEnabled()) {

          val errorMessage = new StringBuilder("caught [Exception] in [AddProductWS] with message [")
            .append(e.getMessage()).append("] with cause [").append(e.getCause())
            .append("]").toString

          log.error(errorMessage, e)
        }

        responseCode.value = new Integer(1)
        responseMessage.value = new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.AddProductWS, 1, locale)
    } finally {

      val inputObject = new StringBuilder("AddProductWS inputs eventsource[")
        .append(eventSource).append("], locale[")
        .append(locale).append("], productIdentifier")
        .append(productIdentifier).append("]").toString()

      val logDao = new ApplicationLogDAO
      val app = new ApplicationDAO().getApplicationByName(ApplicationConstants.APPLICATION_NAME)

      val appLog = new ApplicationLog

      appLog.setApiCalled(ApplicationConstants.AddProductWS)
      appLog.setApplication(app)
      appLog.setEventSource(eventSource)
      appLog.setSourceNumber(eventSource)
      appLog.setInputObject(inputObject)
      appLog.setOutputObject((responseCode.value, responseMessage.value).toString)
      appLog.setResponseCode(responseCode.value)
      appLog.setTransactionDate(new Date(System.currentTimeMillis))

      logDao.save(appLog)

    }

    responseMessage.value = if (responseCode.value == 0) {
      new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.AddProductWS, 0, locale)
    } else {
      responseMessage.value
    }

    val response = (responseCode.value, responseMessage.value)

    if (log.isDebugEnabled) {
      log.debug("outputs response["+response+"]")
      log.debug("Exiting...")
    }

    response
  }
}
