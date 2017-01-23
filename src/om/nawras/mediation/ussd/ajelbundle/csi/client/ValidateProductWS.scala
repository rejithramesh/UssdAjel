package om.nawras.mediation.ussd.ajelbundle.csi.client

import java.util.Locale
import javax.xml.ws.Holder
import om.nawras.mediation.ussd.dao._
import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants
import java.sql.Date
import org.apache.commons.logging.LogFactory

object ValidateProductWS {

  val LOG_EXECUTE = LogFactory.getLog(this.getClass.getName)

  def execute(eventSource: String, productIdentifier: String, operation: String, locale: Locale) = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering Validate...")
      log.debug("inputs eventSource["+eventSource+"]")
      log.debug("inputs baseProductIdentifier["+productIdentifier+"]")
      log.debug("inputs locale["+locale+"]")
    }

    val responseCode        = new Holder[java.lang.Integer]
    val responseMessage     = new Holder[String]

    try {

      PostpaidServiceConfig.getWs.validateProduct(eventSource, productIdentifier, operation: String, locale.getLanguage,
        PostpaidServiceConfig.getApiUserName, PostpaidServiceConfig.getApiPassword, responseCode, responseMessage)

    } catch {
      case e: Exception =>

        if (log.isErrorEnabled()) {

          val errorMessage = new StringBuilder("caught [Exception] in [ValidateProductWS] with message [")
            .append(e.getMessage()).append("] with cause [").append(e.getCause())
            .append("]").toString

          log.error(errorMessage, e)
        }

        responseCode.value = new Integer(1)
        responseMessage.value = new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.ValidateProductsWS, 1, locale)

    } finally {

      val inputObject = new StringBuilder("ValidateProductWS inputs eventsource[")
        .append(eventSource).append("], locale[")
        .append(locale).append("], baseProductIdentifierp")
        .append(productIdentifier).append("]").toString()

      val logDao = new ApplicationLogDAO
      val app    = new ApplicationDAO().getApplicationByName(ApplicationConstants.APPLICATION_NAME)

      val appLog = new ApplicationLog

      appLog.setApiCalled(ApplicationConstants.ValidateProductsWS)
      appLog.setApplication(app)
      appLog.setEventSource(eventSource)
      appLog.setSourceNumber(eventSource)
      appLog.setInputObject(inputObject)
      appLog.setOutputObject((responseCode.value, responseMessage.value).toString)
      appLog.setResponseCode(responseCode.value)
      appLog.setTransactionDate(new Date(System.currentTimeMillis))

      logDao.save(appLog)

    }

    val response = (responseCode.value, responseMessage.value)

    if (log.isDebugEnabled) {
      log.debug("outputs response["+response+"]")
      log.debug("Exiting...")
    }

    response
  }

}
