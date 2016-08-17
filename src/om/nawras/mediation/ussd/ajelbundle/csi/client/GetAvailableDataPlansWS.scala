package om.nawras.mediation.ussd.ajelbundle.csi.client

import org.apache.commons.logging.LogFactory
import java.util.Locale
import ws.{USSDProduct, ArrayOfUSSDProduct}
import javax.xml.ws.Holder
import om.nawras.mediation.ussd.dao._
import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants
import java.sql.Date
import java.util

object GetAvailableDataPlansWS {

  val CLASS_NAME                      = this.getClass.getName
  val LOG_GET_AVAILABLE_DATA_PLANS    = LogFactory.getLog(CLASS_NAME + ".getAvailableDataPlans()")

  def getAvailableDataProducts(eventSource: String, baseProductIdentifier: String, locale: Locale) = {

    val log = LOG_GET_AVAILABLE_DATA_PLANS

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug("inputs eventSource["+eventSource+"]")
      log.debug("inputs baseProductIdentifier["+baseProductIdentifier+"]")
      log.debug("inputs locale["+locale+"]")
    }

    val products        = new Holder[ArrayOfUSSDProduct]
    val responseCode    = new Holder[java.lang.Integer]
    val responseMessage = new Holder[String]

    try {

      PostpaidServiceConfig.getWs.getAvailableDataPlans(eventSource, baseProductIdentifier, locale.getLanguage,
        PostpaidServiceConfig.getApiUserName, PostpaidServiceConfig.getApiPassword, products, responseCode, responseMessage)

    } catch {
      case e: Exception =>

        if (log.isErrorEnabled()) {

          val errorMessage = new StringBuilder("caught [Exception] in [GetAvailableDataPlansWS] with message [")
            .append(e.getMessage()).append("] with cause [").append(e.getCause())
            .append("]").toString

          log.error(errorMessage, e)
        }

        responseCode.value = new java.lang.Integer(1)
        responseMessage.value = new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.GetAvailableDataPlansWS, 1, locale)

    } finally {

        val inputObject = new StringBuilder("GetAvailableDataPlansWS inputs eventsource[")
          .append(eventSource).append("], locale[")
          .append(locale).append("], baseProductIdentifierp")
          .append(baseProductIdentifier).append("]").toString()

        val logDao = new ApplicationLogDAO
        val app    = new ApplicationDAO().getApplicationByName(ApplicationConstants.APPLICATION_NAME)

        val appLog = new ApplicationLog()

        appLog.setApiCalled(ApplicationConstants.GetAvailableDataPlansWS)
        appLog.setApplication(app)
        appLog.setEventSource(eventSource)
        appLog.setSourceNumber(eventSource)
        appLog.setInputObject(inputObject)
        appLog.setOutputObject((responseCode.value, responseMessage.value, if (products.value != null) products.value.getUSSDProduct else new util.ArrayList[USSDProduct]()).toString)
        appLog.setResponseCode(responseCode.value)
        appLog.setTransactionDate(new Date(System.currentTimeMillis))

        logDao.save(appLog)

    }

    val product = if (products.value != null) products.value.getUSSDProduct else new util.ArrayList[USSDProduct]()
    val response = (responseCode.value, responseMessage.value,  product)

    if (log.isDebugEnabled) {
      log.debug("outputs response["+response+"]")
      log.debug("Exiting...")
    }

    response
  }
}
