package om.nawras.mediation.ussd.ajelbundle.entrypoint

import om.nawras.mediation.ussd.ajelbundle.constants.{ApplicationConstants, CorrelatorConstants, MenuConstants}
import om.nawras.mediation.ussd.dao.ApplicationStringsDAO
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import org.apache.commons.logging.LogFactory

object SubscribeUnsubscribeShahryKeys {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  val SUBSCRIBE_TEXT = "ShahryKeys.Subscribe"
  val UNSUBSCRIBE_TEXT = "ShahryKeys.Unsubscribe"

  def execute(request: UssdMORequest): UssdMOResponse = {
    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val locale      = UssdUtils.getLocale(request.getLanguage)
    val splitInput  = request.getInput.split(":")
    val productId   = splitInput.head.trim
    val productName = splitInput.last.trim

    val asDao       = new ApplicationStringsDAO

    val response        = new UssdMOResponse
    val simpleReference = new SimpleReference
    val menu            = new Menu
    val title           = new Title
    val content         = new MessageContent

    val titleText       = productName
    val subscribeText   = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, SUBSCRIBE_TEXT, locale)
    val unsubscribeText = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, UNSUBSCRIBE_TEXT, locale)

    if (log.isDebugEnabled) {
      log.debug("productId: " + productId)
      log.debug("productName" + productName)
    }

    val menuList = List(
      (subscribeText, MenuConstants.SUBSCRIBE_SHAHRY_KEYS),
      (unsubscribeText, MenuConstants.UNSUBSCRIBE_SHAHRY_KEYS))

    title.setValue(titleText)
    menu.setUnicode(UssdUtils.isUnicode(locale))

    menuList.zipWithIndex.foreach {
      case (mi, index) =>
        val menuItem = new MenuItem
        menuItem.setDisplay(mi._1)
        menuItem.setOption((index+1).toString)
        menuItem.setValue(mi._2)
        menu.getItems.add(menuItem)
    }

    val sessionData = productId + "," + productName
    val correlator  = request.getCorrelator + CorrelatorConstants.THIRD_TRAVERSAL + ":" + sessionData + ";"

    simpleReference.setCorrelator(correlator)

    content.setMenu(menu)
    content.setType(ContentType.MENU)

    response.setMessage(content)
    response.setBreakSession(false)
    response.setReference(simpleReference)

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
