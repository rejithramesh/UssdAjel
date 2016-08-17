package om.nawras.mediation.ussd.ajelbundle.csi.client

import org.apache.commons.logging.LogFactory
import java.util.Locale
import javax.xml.ws.Holder
import om.nawras.mediation.ussd.dao._
import java.sql.Date
import om.nawras.mediation.ussd.dao.ApplicationLog
import om.nawras.mediation.ussd.dao.ApplicationDAO
import om.nawras.mediation.ussd.dao.ApplicationLogDAO
import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants

object ValidateSubscriptionBasePlanWS {

  val CLASS_NAME                             = this.getClass.getName
  val LOG_VALIDATE_SUBSCRIPTION_BASE_PLAN    = LogFactory.getLog(CLASS_NAME + ".validateSubscriptionBasePlan()")

  def validateSubscriptionBasePlan(eventSource: String, baseProductIdentifier: String, locale: Locale) = {

    val log = LOG_VALIDATE_SUBSCRIPTION_BASE_PLAN

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug("inputs eventSource["+eventSource+"]")
      log.debug("inputs baseProductIdentifier["+baseProductIdentifier+"]")
      log.debug("inputs locale["+locale+"]")
    }

    val responseCode        = new Holder[java.lang.Integer]
    val responseMessage     = new Holder[String]
    val isDataBundled       = new Holder[java.lang.Boolean]
    val noChangeOptionText  = new Holder[String]

    try {

      PostpaidServiceConfig.getWs.validateSubscriptionBasePlan(eventSource, baseProductIdentifier, locale.getLanguage,
        PostpaidServiceConfig.getApiUserName, PostpaidServiceConfig.getApiPassword, isDataBundled, noChangeOptionText, responseCode, responseMessage)

    } catch {
      case e: Exception =>

        if (log.isErrorEnabled()) {

          val errorMessage = new StringBuilder("caught [Exception] in [GetAvailableDataPlansWS] with message [")
            .append(e.getMessage()).append("] with cause [").append(e.getCause())
            .append("]").toString

          log.error(errorMessage, e)
        }

        responseCode.value = new Integer(1)
        responseMessage.value = new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.ValidateSubscriptionBasePlansWS, 1, locale)

    } finally {

      val inputObject = new StringBuilder("ValidateSubscriptionBasePlanWS inputs eventsource[")
        .append(eventSource).append("], locale[")
        .append(locale).append("], baseProductIdentifierp")
        .append(baseProductIdentifier).append("]").toString()

      val logDao = new ApplicationLogDAO
      val app    = new ApplicationDAO().getApplicationByName(ApplicationConstants.APPLICATION_NAME)

      val appLog = new ApplicationLog

      appLog.setApiCalled(ApplicationConstants.ValidateSubscriptionBasePlansWS)
      appLog.setApplication(app)
      appLog.setEventSource(eventSource)
      appLog.setSourceNumber(eventSource)
      appLog.setInputObject(inputObject)
      appLog.setOutputObject((responseCode.value, responseMessage.value, isDataBundled.value, noChangeOptionText.value).toString)
      appLog.setResponseCode(responseCode.value)
      appLog.setTransactionDate(new Date(System.currentTimeMillis))

      logDao.save(appLog)

    }

    val isDb:Boolean = if (isDataBundled.value == null) false else isDataBundled.value
    val nct  = if (noChangeOptionText.value == null) "" else noChangeOptionText.value

    val response = (responseCode.value, responseMessage.value, isDb, nct)

    if (log.isDebugEnabled) {
      log.debug("outputs response["+response+"]")
      log.debug("Exiting...")
    }

    response
  }
}
