package om.nawras.mediation.ussd.ajelbundle.csi.client

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.dao.ApplicationConfigDAO
import java.net.URL
import java.net.MalformedURLException
import om.nawras.mediation.ussd.ajelbundle.constants.ApplicationConstants
import ws.{PostpaidServiceSoap, PostpaidService}


object PostpaidServiceConfig {
  
  val CLASS_NAME: String = getClass().getName()
  val LOG_GET_WS: Log 	 = LogFactory.getLog(CLASS_NAME + "getWs()")
  
  val acDao = new ApplicationConfigDAO()
  
  def getWebServiceURL() = {
    acDao.getConfigValue(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.POSTPAID_SERVICE_WSDL)
  }
  
  def getApiUserName() = {
    acDao.getConfigValue(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.POSTPAIDSERVICE_USERNAME)
  }
  
  def getApiPassword() = {
    acDao.getConfigValue(ApplicationConstants.APPLICATION_NAME, ApplicationConstants.POSTPAIDSERVICE_PASSWORD)
  }
  
  def getWs(): PostpaidServiceSoap = {
    val log = LOG_GET_WS
    
    if (log.isDebugEnabled) {
      log.debug("Entering menu...")
    }
    
    var ws: PostpaidServiceSoap = null
    val url = getWebServiceURL()
    
    try {
      
      if (log.isDebugEnabled) {
        log.debug("webservice URL from database is: " + url)
      }
      
      ws = new PostpaidService(new URL(url)).getPostpaidServiceSoap12
      
    } catch {
      case e: MalformedURLException => {

        if (log.isDebugEnabled) {
          log.debug("caught malformed url exception...")
        }
        
        if (log.isErrorEnabled) {
        	val errorMessage = new StringBuilder("caught [MalformedURLException] with message [")
        							     .append(e.getMessage).append("], URL retrieved from DB was [").append(url).append("] and exception [")
        							     .append(e).append("], calling default constructor instead...").toString()
        							     
        	log.error(errorMessage, e)
        }
        
        ws = new PostpaidService().getPostpaidServiceSoap12
        
      }

    }
    
    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs ws[").append(ws).append("]"))
      log.debug("Exiting...")
    }
  
    ws
  }

}