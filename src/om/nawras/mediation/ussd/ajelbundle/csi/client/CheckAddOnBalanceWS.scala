package om.nawras.mediation.ussd.ajelbundle.csi.client

import java.sql.Date
import java.util.Locale
import javax.xml.ws.Holder

import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants
import om.nawras.mediation.ussd.ajelbundle.csi.client.ws.ArrayOfAddOnBalanceDetails
import om.nawras.mediation.ussd.dao._
import org.apache.commons.logging.LogFactory


object CheckAddOnBalanceWS {

  val CLASS_NAME              = this.getClass.getName
  val LOG_CHECK_ADD_ONBALANCE  = LogFactory.getLog(CLASS_NAME + ".checkAddOnBalance()")

  def execute(eventSource: String, locale: Locale) = {

    val log = LOG_CHECK_ADD_ONBALANCE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug("inputs eventSource["+eventSource+"]")
      log.debug("inputs locale["+locale+"]")
    }

    val responseCode    = new Holder[java.lang.Integer]
    val responseMessage = new Holder[String]
    val addOnBalance    = new Holder[ArrayOfAddOnBalanceDetails]

    try {

      PostpaidServiceConfig.getWs().checkAddOnBalance(eventSource, locale.getLanguage, true, addOnBalance, responseCode, responseMessage)

      if (log.isDebugEnabled) {
        log.debug("responseMessage out parameter["+responseMessage+"]")
      }

    } catch {
      case e: Exception =>

        if (log.isErrorEnabled) {

          val errorMessage = new StringBuilder("caught [Exception] in [CheckAddOnBalanceWS] with message [")
            .append(e.getMessage).append("] with cause [").append(e.getCause)
            .append("]").toString()

          log.error(errorMessage, e)
        }

        responseCode.value = new Integer(1)
        val r = new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.CheckDataBalanceWS, 1, locale)
        responseMessage.value = r

        if (log.isDebugEnabled) {
          log.debug("responseMessage out parameter in exception block["+r+"]")
        }

    } finally {

      val inputObject = new StringBuilder("CheckDataBalanceWS inputs eventsource[")
        .append(eventSource).append("], locale[").append(locale).append("]")
        .toString()

      val logDao = new ApplicationLogDAO
      val app    = new ApplicationDAO().getApplicationByName(ApplicationConstants.APPLICATION_NAME)

      val appLog = new ApplicationLog

      appLog.setApiCalled(ApplicationConstants.CheckAddOnBalanceWS)
      appLog.setApplication(app)
      appLog.setEventSource(eventSource)
      appLog.setSourceNumber(eventSource)
      appLog.setInputObject(inputObject)
      appLog.setOutputObject((responseCode.value, responseMessage.value).toString())
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
