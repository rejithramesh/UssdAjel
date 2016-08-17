package om.nawras.mediation.ussd.ajelbundle.entrypoint

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import om.nawras.mediation.ussd.ajelbundle.constants.{ApplicationConstants, CorrelatorConstants, MenuConstants}
import om.nawras.mediation.ussd.dao.ApplicationStringsDAO
import om.nawras.mediation.ussd.ajelbundle.csi.client.ValidateSubscriptionBasePlanWS
import java.util.Locale

object SelectDataPlan {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  val TITLE             = "DataPlan.Title"
  val TITLE_IS_DATA     = "DataPlan.TitleIsDataBundle"
  val TITLE_IS_NOT_DATA = "DataPlan.TitleIsNotDataBundle"
  val THREEG_DATA       = "DataPlan.3GData"
  val BB_DATA           = "DataPlan.BlackberryData"
  val NO_DATA           = "DataPlan.None"

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val eventSource = UssdUtils.stripLeading968(UssdUtils.getMsisdnFromAddress(request.getAddress))
    val productIdentifier = request.getInput.split(":").head
    val locale = UssdUtils.getLocale(request.getLanguage)

    val validate = ValidateSubscriptionBasePlanWS.validateSubscriptionBasePlan(eventSource, productIdentifier, locale)

    val asDao           = new ApplicationStringsDAO()

    val response: UssdMOResponse = validate._1.intValue match {
      case 0 =>

        val titleText = if (validate._3) asDao.getAppText(ApplicationConstants.APPLICATION_NAME, TITLE_IS_DATA, locale) else asDao.getAppText(ApplicationConstants.APPLICATION_NAME, TITLE_IS_NOT_DATA, locale)
        val tgdata =    asDao.getAppText(ApplicationConstants.APPLICATION_NAME, THREEG_DATA, locale)
        val bbdata =    asDao.getAppText(ApplicationConstants.APPLICATION_NAME, BB_DATA, locale)
        val none   =    if (!validate._4.isEmpty) validate._4 else asDao.getAppText(ApplicationConstants.APPLICATION_NAME, NO_DATA, locale)

        val menuList = List((tgdata, MenuConstants.THREEG_DATA),
          (bbdata, MenuConstants.BLACKBERRY_DATA),
          (none,   "None"))

        val menu            = new Menu
        val simpleReference = new SimpleReference
        val message         = new MessageContent

        var i = 1

        menuList.foreach(f => {
          val menuItem = new MenuItem
          menuItem.setDisplay(f._1)
          menuItem.setOption(i.toString)
          menuItem.setValue(request.getInput+":"+f._2)
          menu.getItems.add(menuItem)
          i += 1
        })

        val title = new Title()
        title.setValue(titleText)
        menu.setTitle(title)
        menu.setUnicode(UssdUtils.isUnicode(locale))

        message.setMenu(menu)
        message.setType(ContentType.MENU)

        val response = new UssdMOResponse

        response.setBreakSession(false)

        val oldc = request.getCorrelator + productIdentifier + ":"
        simpleReference.setCorrelator(oldc + CorrelatorConstants.THIRD_TRAVERSAL + ":" )

        response.setBreakSession(false)
        response.setReference(simpleReference)
        response.setMessage(message)

        response

      case 134 =>

        ConfirmSelectionBankMuscat.execute(request, validate._2)

      case 174 => //Shahry keys bolt on validation thingy

        ConfirmSelectionBankMuscat.execute(request, validate._2)

      case 200 =>

        ConfirmSelection.execute(request)

      case 201 =>

        val titleText = if (validate._3) asDao.getAppText(ApplicationConstants.APPLICATION_NAME, TITLE_IS_DATA, locale) else asDao.getAppText(ApplicationConstants.APPLICATION_NAME, TITLE_IS_NOT_DATA, locale)
        val bbdata =    asDao.getAppText(ApplicationConstants.APPLICATION_NAME, BB_DATA, locale)
        val none   =    if (!validate._4.isEmpty) validate._4 else asDao.getAppText(ApplicationConstants.APPLICATION_NAME, NO_DATA, locale)

        val menuList = List(
          (bbdata, MenuConstants.BLACKBERRY_DATA),
          (none,   "None"))

        val menu            = new Menu
        val simpleReference = new SimpleReference
        val message         = new MessageContent

        var i = 1

        menuList.foreach(f => {
          val menuItem = new MenuItem
          menuItem.setDisplay(f._1)
          menuItem.setOption(i.toString)
          menuItem.setValue(request.getInput+":"+f._2)
          menu.getItems.add(menuItem)
          i += 1
        })

        val title = new Title()
        title.setValue(titleText)
        menu.setTitle(title)
        menu.setUnicode(UssdUtils.isUnicode(locale))

        message.setMenu(menu)
        message.setType(ContentType.MENU)
        val response = new UssdMOResponse

        response.setBreakSession(false)

        val oldc = request.getCorrelator + productIdentifier + ":"
        simpleReference.setCorrelator(oldc + CorrelatorConstants.THIRD_TRAVERSAL + ":" )

        response.setBreakSession(false)
        response.setReference(simpleReference)
        response.setMessage(message)

        response

      case 202 =>

        val titleText = if (validate._3) asDao.getAppText(ApplicationConstants.APPLICATION_NAME, TITLE_IS_DATA, locale) else asDao.getAppText(ApplicationConstants.APPLICATION_NAME, TITLE_IS_NOT_DATA, locale)
        val tgdata =    asDao.getAppText(ApplicationConstants.APPLICATION_NAME, THREEG_DATA, locale)
        val none   =    if (!validate._4.isEmpty) validate._4 else asDao.getAppText(ApplicationConstants.APPLICATION_NAME, NO_DATA, locale)

        val menuList = List((tgdata, MenuConstants.THREEG_DATA),
          (none,   "None"))

        val menu            = new Menu
        val simpleReference = new SimpleReference
        val message         = new MessageContent

        var i = 1

        menuList.foreach(f => {
          val menuItem = new MenuItem
          menuItem.setDisplay(f._1)
          menuItem.setOption(i.toString)
          menuItem.setValue(request.getInput+":"+f._2)
          menu.getItems.add(menuItem)
          i += 1
        })

        val title = new Title()
        title.setValue(titleText)
        menu.setTitle(title)
        menu.setUnicode(UssdUtils.isUnicode(locale))

        message.setMenu(menu)
        message.setType(ContentType.MENU)

        val response = new UssdMOResponse

        response.setBreakSession(false)

        val oldc = request.getCorrelator + productIdentifier + ":"
        simpleReference.setCorrelator(oldc + CorrelatorConstants.THIRD_TRAVERSAL + ":" )

        response.setBreakSession(false)
        response.setReference(simpleReference)
        response.setMessage(message)

        response

      case _ =>

        val notification    = new Notification
        val message         = new MessageContent

        notification.setMessage(validate._2)
        notification.setUnicode(UssdUtils.isUnicode(locale))

        message.setNotification(notification)
        message.setType(ContentType.NOTIFICATION)

        val response = new UssdMOResponse

        response.setBreakSession(false)
        response.setMessage(message)

        response
    }

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }
}
