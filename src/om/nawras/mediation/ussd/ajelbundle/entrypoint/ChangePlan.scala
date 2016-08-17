package om.nawras.mediation.ussd.ajelbundle.entrypoint

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import om.nawras.mediation.ussd.ajelbundle.constants.{ApplicationConstants, CorrelatorConstants}
import om.nawras.mediation.ussd.ajelbundle.csi.client.{GetAvailableBasePlansWS, GetAvailableProductsWS}
import om.nawras.mediation.ussd.dao.ApplicationStringsDAO
import scala.collection.JavaConversions._

object ChangePlan {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  val AJEL_BUNDLE_SELECT = "ChangePlan.SelectAjelBundle"

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val locale      = UssdUtils.getLocale(request.getLanguage)
    val eventSource = UssdUtils.stripLeading968(UssdUtils.getMsisdnFromAddress(request.getAddress))

    val result = GetAvailableBasePlansWS.getAvailableBasePlans(eventSource, locale)

    val response        = new UssdMOResponse
    val simpleReference = new SimpleReference

    val message = result.responseCode match {
      case 0 =>

        if (log.isDebugEnabled) {
          log.debug("received a response code of["+result.responseCode+"]")
        }

        var i = 1
        val menu = new Menu
        result.products.getUSSDProduct.foreach(p => {
          val menuItem = new MenuItem
          menuItem.setDisplay(p.getProductName)
          menuItem.setOption(i.toString)
          menuItem.setValue(p.getProductIdentifier+":"+p.getID+":"+p.getMRC)
          menu.getItems.add(menuItem)
          i += 1
        })

        val title = new Title()
        title.setValue(new ApplicationStringsDAO().getAppText(ApplicationConstants.APPLICATION_NAME, AJEL_BUNDLE_SELECT, locale))
        menu.setTitle(title)
        menu.setUnicode(UssdUtils.isUnicode(locale))

        val m = new MessageContent
        m.setMenu(menu)
        m.setType(ContentType.MENU)

        simpleReference.setCorrelator(request.getCorrelator + CorrelatorConstants.SECOND_TRAVERSAL + ":" + CorrelatorConstants.CHANGE_PLAN + ";")

        response.setReference(simpleReference)
        response.setBreakSession(false)

        m
      case _ =>

        if (log.isDebugEnabled) {
          log.debug("received a response code of["+result.responseCode+"]")
        }

        val notification = new Notification
        notification.setMessage(result.responseMessage)
        notification.setUnicode(UssdUtils.isUnicode(locale))

        val m = new MessageContent
        m.setNotification(notification)
        m.setType(ContentType.NOTIFICATION)

        response.setBreakSession(false)

        m
    }

    response.setMessage(message)
    response.setReference(simpleReference)

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
