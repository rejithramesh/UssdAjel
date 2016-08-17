package om.nawras.mediation.ussd.ajelbundle.entrypoint

import om.nawras.mediation.ussd.ajelbundle.constants.{ApplicationConstants, CorrelatorConstants, MenuConstants}
import om.nawras.mediation.ussd.dao.ApplicationStringsDAO
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import org.apache.commons.logging.LogFactory


object MainMenu {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  val TITLE           = "MainMenu.Title"
  val CHANGE_PLAN     = "MainMenu.ChangePlan"
  val BALANCE_INQUIRY = "MainMenu.BalanceInquiry"
  val SHAHRY_KEYS     = "ShahryKeys.Title"
  val ADDON_KEYS      = "AddOnKeys.Title"
  val ADDON_BALANCE   = "MainMenu.AddOnBalance"

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering Main menu(updated)...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val locale = UssdUtils.getLocale(request.getLanguage)

    val asDao = new ApplicationStringsDAO

    val response        = new UssdMOResponse
    val simpleReference = new SimpleReference
    val menu            = new Menu
    val title           = new Title

    val titleText = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, TITLE, locale)
    val changePlanText = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, CHANGE_PLAN, locale)
    val boltOnText = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, SHAHRY_KEYS, locale)
    val addOnText  = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, ADDON_KEYS, locale)
    val balanceInquiryText = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, BALANCE_INQUIRY, locale)
    val addOnBalanceText = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, ADDON_BALANCE, locale)

    val list = List(
      (changePlanText, MenuConstants.CHANGE_PLAN),
      (boltOnText, MenuConstants.BOLT_ON),
      (addOnText, MenuConstants.ADD_ON),
      (balanceInquiryText, MenuConstants.BALANCE_INQUIRY),
      (addOnBalanceText, MenuConstants.ADD_ON_INQUIRY))

    title.setValue(titleText)

    var i = 1
    list.foreach(f => {
      val menuItem = new MenuItem
      menuItem.setDisplay(f._1)
      menuItem.setOption(i.toString)
      menuItem.setValue(f._2)
      menu.getItems.add(menuItem)
      i += 1
    })

    menu.setUnicode(UssdUtils.isUnicode(locale))
    menu.setTitle(title)

    val message = new MessageContent

    message.setMenu(menu)
    message.setType(ContentType.MENU)

    simpleReference.setCorrelator(CorrelatorConstants.FIRST_TRAVERSAL + ":;")

    response.setMessage(message)
    response.setReference(simpleReference)

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
