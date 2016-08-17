package om.nawras.mediation.ussd.ajelbundle.csi.client

import org.apache.commons.logging.LogFactory
import java.util.Locale
import javax.xml.ws.Holder
import ws.ArrayOfUSSDProduct
import om.nawras.mediation.ussd.dao._
import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants
import java.sql.Date

case class BasePlansResponse(responseCode: Int, responseMessage: String, products: ArrayOfUSSDProduct)
object GetAvailableBasePlansWS {

  val CLASS_NAME                   = this.getClass.getName
  val LOG_GET_AVAILABLE_BASE_PLANS = LogFactory.getLog(CLASS_NAME + ".getAvailableBasePlans()")

  def getAvailableBasePlans(eventSource: String, locale: Locale) = {

    val log = LOG_GET_AVAILABLE_BASE_PLANS

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug("inputs eventSource["+eventSource+"]")
      log.debug("inputs locale["+locale+"]")
    }

    val responseCode    = new Holder[java.lang.Integer]
    val responseMessage = new Holder[String]
    val basePlans       = new Holder[ArrayOfUSSDProduct]

    try {

      PostpaidServiceConfig.getWs.getAvailableBasePlans(eventSource, locale.getLanguage,
                                                 PostpaidServiceConfig.getApiUserName, PostpaidServiceConfig.getApiPassword, basePlans, responseCode, responseMessage)

    } catch {

      case e: Exception =>

        if (log.isErrorEnabled()) {

          val errorMessage = new StringBuilder("caught [Exception] in [GetAvailableBasePlansWS] with message [")
            .append(e.getMessage()).append("] with cause [").append(e.getCause())
            .append("]").toString

          log.error(errorMessage, e)
        }

        responseCode.value = new Integer(1)
        responseMessage.value = new ApplicationApiResponsesDAO().getResponseText(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.GetAvailableBasePlansWS, 1, locale)

    } finally {

      try {

        val inputObject = new StringBuilder("GetAvailableBasePlansWS inputs eventsource[")
          .append(eventSource).append("], locale[")
          .append(locale).append("]").toString()

        val logDao = new ApplicationLogDAO
        val app    = new ApplicationDAO().getApplicationByName(ApplicationConstants.APPLICATION_NAME)

        val appLog = new ApplicationLog

        appLog.setApiCalled(ApplicationConstants.GetAvailableBasePlansWS)
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
    val result = BasePlansResponse(responseCode.value.intValue, responseMessage.value, p)

    if (log.isDebugEnabled) {
      log.debug("outputs result["+result+"]")
      log.debug("Exiting...")
    }

    result
  }
}
