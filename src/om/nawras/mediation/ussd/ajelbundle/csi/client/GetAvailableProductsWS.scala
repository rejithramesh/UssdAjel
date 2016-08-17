package om.nawras.mediation.ussd.ajelbundle.csi.client

import om.nawras.mediation.ussd.ajelbundle.csi.client.ws.ArrayOfUSSDProduct
import java.util.Locale
import javax.xml.ws.Holder
import om.nawras.mediation.ussd.dao._
import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants
import java.sql.Date
import org.apache.commons.logging.LogFactory

case class BoltOnPlansResponse(responseCode: Int, responseMessage: String, products: ArrayOfUSSDProduct)
object GetAvailableProductsWS {

  val CLASS_NAME   = this.getClass.getName
  val LOG_EXECUTE  = LogFactory.getLog(CLASS_NAME + ".execute()")

  def execute(eventSource: String, productType: String, locale: Locale) = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug("inputs eventSource["+eventSource+"]")
      log.debug("inputs productType["+productType+"]")
      log.debug("inputs locale["+locale+"]")
    }

    val responseCode    = new Holder[java.lang.Integer]
    val responseMessage = new Holder[String]
    val basePlans       = new Holder[ArrayOfUSSDProduct]

    try {

      PostpaidServiceConfig.getWs().getAvailableProducts(eventSource, productType, locale.getLanguage,
        PostpaidServiceConfig.getApiUserName(), PostpaidServiceConfig.getApiPassword(), basePlans, responseCode, responseMessage)

    } catch {

      case e: Exception =>

        if (log.isErrorEnabled) {

          val errorMessage = new StringBuilder("caught [Exception] in [GetAvailableProductsWS] with message [")
            .append(e.getMessage).append("] with cause [").append(e.getCause)
            .append("]").toString()

          log.error(errorMessage, e)
        }

        responseCode.value = new Integer(1)
        responseMessage.value = new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.GetAvailableProductsWS, 1, locale)

    } finally {

      try {

        val inputObject = new StringBuilder("GetAvailablePlansWS inputs eventsource[")
          .append(eventSource).append("], locale[")
          .append(locale).append("]").toString()

        val logDao = new ApplicationLogDAO
        val app    = new ApplicationDAO().getApplicationByName(ApplicationConstants.APPLICATION_NAME)

        val appLog = new ApplicationLog

        appLog.setApiCalled(ApplicationConstants.GetAvailableProductsWS)
        appLog.setApplication(app)
        appLog.setEventSource(eventSource)
        appLog.setSourceNumber(eventSource)
        appLog.setInputObject(inputObject)
        appLog.setOutputObject((responseCode.value, responseMessage.value, if ( basePlans.value != null) basePlans.value else new ArrayOfUSSDProduct).toString)
        appLog.setResponseCode(responseCode.value)
        appLog.setTransactionDate(new Date(System.currentTimeMillis))

        logDao.save(appLog)

      }

    }

    val p = if ( basePlans.value != null) basePlans.value else {
      val s = new ArrayOfUSSDProduct
      s.getUSSDProduct
      s
    }
    val result = BoltOnPlansResponse(responseCode.value.intValue, responseMessage.value, p)

    if (log.isDebugEnabled) {
      log.debug("outputs result["+result+"]")
      log.debug("Exiting...")
    }

    result
  }
}
