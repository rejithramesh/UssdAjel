package om.nawras.mediation.ussd.ajelbundle.entrypoint

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import om.nawras.mediation.ussd.ajelbundle.constants.{ApplicationConstants, CorrelatorConstants, MenuConstants}
import om.nawras.mediation.ussd.ajelbundle.csi.client.ValidateProductWS
import java.util.Locale
import om.nawras.mediation.ussd.dao.ApplicationStringsDAO

object ValidateSubUnsubShahryKeys {

  val CLASS_NAME = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  val SUBSCRIBE_SHAHRY_CONFIRM_TITLE    = "ShahryKeys.AddOperation"
  val UNSUBSCRIBE_SHAHRY_CONFIRM_TITLE  = "ShahryKeys.RemoveOperation"
  val YES                               = "ConfirmSelection.Yes"

  private def doValidate(eventSource: String, choice: String, productIdentifier: String, productName: String, locale: Locale, requestCorrelator: String): UssdMOResponse = {

    val validateResult = choice match {
      case MenuConstants.SUBSCRIBE_SHAHRY_KEYS    =>
        ValidateProductWS.execute(eventSource, productIdentifier, "Add", locale)
      case MenuConstants.UNSUBSCRIBE_SHAHRY_KEYS  =>
        ValidateProductWS.execute(eventSource, productIdentifier, "Terminate", locale)
    }

    val response: UssdMOResponse = validateResult._1.toInt match {
      case 0 =>

        val sessionData = productIdentifier + "," + productName
        val menu = new Menu
        val title = new Title
        val asDao = new ApplicationStringsDAO

        title.setValue(validateResult._2)

        val menuItem = new MenuItem
        menuItem.setDisplay(asDao.getAppText(ApplicationConstants.APPLICATION_NAME, YES, locale))
        menuItem.setOption("1")
        menuItem.setValue(sessionData)

        menu.getItems.add(menuItem)
        menu.setTitle(title)

        menu.setUnicode(UssdUtils.isUnicode(locale))

        val simpleReference = new SimpleReference
        simpleReference.setCorrelator(requestCorrelator + CorrelatorConstants.FOURTH_TRAVERSAL + ":" + choice + ";")

        val content = new MessageContent
        content.setMenu(menu)
        content.setType(ContentType.MENU)

        val response = new UssdMOResponse
        response.setBreakSession(false)
        response.setMessage(content)
        response.setReference(simpleReference)

        response

      case _ =>
        val notification = new Notification
        notification.setMessage(validateResult._2)
        notification.setUnicode(UssdUtils.isUnicode(locale))

        val content = new MessageContent
        content.setNotification(notification)
        content.setType(ContentType.NOTIFICATION)

        val response = new UssdMOResponse
        response.setBreakSession(false)
        response.setMessage(content)

        response
    }

    response
  }

  def execute(request: UssdMORequest): UssdMOResponse = {
    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val locale      = UssdUtils.getLocale(request.getLanguage)
    val eventSource = UssdUtils.stripLeading968(UssdUtils.getMsisdnFromAddress(request.getAddress))

    val sessionData = request.getCorrelator.split(";").last.split(":").last.split(",")
    val productId   = sessionData.head
    val productName = sessionData.last

    if (log.isDebugEnabled) {
      log.debug("productId: " + productId)
      log.debug("productName: " + productName)
      log.debug("Input: " + request.getInput)
    }

    val response: UssdMOResponse = request.getInput match {
      case MenuConstants.SUBSCRIBE_SHAHRY_KEYS   => doValidate(eventSource, MenuConstants.SUBSCRIBE_SHAHRY_KEYS, productId, productName, locale, request.getCorrelator)
      case MenuConstants.UNSUBSCRIBE_SHAHRY_KEYS => doValidate(eventSource, MenuConstants.UNSUBSCRIBE_SHAHRY_KEYS, productId, productName, locale, request.getCorrelator)
      case "4" =>  doValidate(eventSource, MenuConstants.SUBSCRIBE_SHAHRY_KEYS, productId, productName, locale, request.getCorrelator)
      case _ => MainMenu.execute(request)
    }

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }
}
