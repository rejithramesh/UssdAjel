package om.nawras.mediation.ussd.ajelbundle.entrypoint

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import om.nawras.mediation.ussd.ajelbundle.constants.{ApplicationConstants, CorrelatorConstants}
import om.nawras.mediation.ussd.dao.ApplicationStringsDAO

object ConfirmSelection {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  val CONFIRMATION_MESSAGE = "ConfirmSelection.Title"
  val YES                  = "ConfirmSelection.Yes"
  val NO                   = "ConfirmSelection.No"

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering Coonfirm selection...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val eventSource    = UssdUtils.stripLeading968(UssdUtils.getMsisdnFromAddress(request.getAddress))
    val response  = new UssdMOResponse
    val menu      = new Menu
    val title     = new Title
    val message   = new MessageContent
    val reference = new SimpleReference

    val locale    = UssdUtils.getLocale(request.getLanguage)

    val asDao = new ApplicationStringsDAO()

    val titleText = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, CONFIRMATION_MESSAGE, locale)
    val yesText   = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, YES, locale)
    val noText    = asDao.getAppText(ApplicationConstants.APPLICATION_NAME, NO, locale)

    //pull list from csi
    //we'll just use a dummy value for now
    title.setValue(titleText)

    val plan1 = (yesText, request.getInput+":"+"CONFIRM")
    val plan2 = (noText,  request.getInput+":"+"DECLINE")

    val list = List(plan1, plan2)

    var i = 1

    list.foreach( f => {
      val mi = new MenuItem
      mi.setDisplay(f._1)
      mi.setOption(i.toString)
      mi.setValue(request.getInput+":"+f._2)
      menu.getItems.add(mi)
      i=i+1
    })

    menu.setTitle(title)
    menu.setUnicode(UssdUtils.isUnicode(locale))

    message.setMenu(menu)
    message.setType(ContentType.MENU)

    reference.setCorrelator(request.getCorrelator + CorrelatorConstants.FIFTH_TRAVERSAL + ":;")

    response.setBreakSession(false)
    response.setMessage(message)
    response.setReference(reference)

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
