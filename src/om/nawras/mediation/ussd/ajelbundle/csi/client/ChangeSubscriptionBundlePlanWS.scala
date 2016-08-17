package om.nawras.mediation.ussd.ajelbundle.csi.client

import org.apache.commons.logging.LogFactory
import java.util.Locale
import javax.xml.ws.Holder
import om.nawras.mediation.ussd.dao._
import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants
import java.sql.Date

object ChangeSubscriptionBundlePlanWS {

  val CLASS_NAME                             = this.getClass.getName
  val LOG_CHANGE_SUBSCRIPTION_BUNDLE_PLAN    = LogFactory.getLog(CLASS_NAME + ".changeSubscriptionBundlePlan()")

  def changeSubscriptionBundlePlanWS(eventSource: String, baseProductIdentifier: String, dataProductIdentifier: String,
                                     locale: Locale): (Int, String) = {

    val log = LOG_CHANGE_SUBSCRIPTION_BUNDLE_PLAN

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug("inputs eventSource["+eventSource+"]")
      log.debug("input dataProductIdentifier["+dataProductIdentifier+"]")
      log.debug("inputs baseProductIdentifier["+baseProductIdentifier+"]")
      log.debug("inputs locale["+locale+"]")
    }

    val responseCode    = new Holder[java.lang.Integer]
    val responseMessage = new Holder[String]

    try {

      PostpaidServiceConfig.getWs.changeSubscriptionBundlePlan(eventSource, baseProductIdentifier, dataProductIdentifier, locale.getLanguage,
                                                         PostpaidServiceConfig.getApiUserName, PostpaidServiceConfig.getApiPassword, responseCode, responseMessage)

    } catch {

        case e: Exception =>

          if (log.isErrorEnabled()) {

            val errorMessage = new StringBuilder("caught [Exception] in [ChangeSubscriptionBundlePlanWS] with message [")
              .append(e.getMessage()).append("] with cause [").append(e.getCause())
              .append("]").toString

            log.error(errorMessage, e)
          }

          responseCode.value = new Integer(1)
          responseMessage.value = new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.ChangeSubscriptionBundlePlanWS, 1, locale)

    } finally {

      val inputObject = new StringBuilder("ChangeSubscriptionBundlePlanWS inputs eventsource[")
        .append(eventSource).append("], locale[")
        .append(locale).append("], baseProductIdentifier")
        .append(baseProductIdentifier).append("], dataProductIdentifier[")
        .append(dataProductIdentifier).append("]").toString()

      val logDao = new ApplicationLogDAO
      val app    = new ApplicationDAO().getApplicationByName(ApplicationConstants.APPLICATION_NAME)

      val appLog = new ApplicationLog

      appLog.setApiCalled(ApplicationConstants.ChangeSubscriptionBundlePlanWS)
      appLog.setApplication(app)
      appLog.setEventSource(eventSource)
      appLog.setSourceNumber(eventSource)
      appLog.setInputObject(inputObject)
      appLog.setOutputObject((responseCode.value, responseMessage.value).toString)
      appLog.setResponseCode(responseCode.value)
      appLog.setTransactionDate(new Date(System.currentTimeMillis))

      logDao.save(appLog)

    }

    val response = (responseCode.value.intValue, responseMessage.value)

    if (log.isDebugEnabled) {
      log.debug("outputs response["+response+"]")
      log.debug("Exiting...")
    }

    response
  }

}