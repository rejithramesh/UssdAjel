package om.nawras.mediation.ussd.ajelbundle.entrypoint

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import om.nawras.mediation.ussd.ajelbundle.constants.{ApplicationConstants, MenuConstants, CorrelatorConstants}
import om.nawras.mediation.ussd.ajelbundle.csi.client.{GetAvailableBlackberryPlansWS, GetAvailableDataPlansWS}
import scala.collection.JavaConversions._
import om.nawras.mediation.ussd.dao.ApplicationStringsDAO

object DataBundleSelect {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  val SELECT_3G_BUNDLE  = "DataBundleSelect.3G"
  val BLACKBERRY_BUNDLE = "DataBundleSelect.Blackberry"

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val eventSource  = UssdUtils.stripLeading968(UssdUtils.getMsisdnFromAddress(request.getAddress))
    val response     = new UssdMOResponse
    val menu         = new Menu

    val reference    = new SimpleReference

    val locale    = UssdUtils.getLocale(request.getLanguage)

    //pull list from csi
    //we'll just use a dummy value for now
    val data = request.getInput.split(":").reverse.head match {
      case MenuConstants.THREEG_DATA => GetAvailableDataPlansWS.getAvailableDataProducts(eventSource, request.getInput.split(":").head, locale)
      case MenuConstants.BLACKBERRY_DATA => GetAvailableBlackberryPlansWS.getAvailableBlackberryPlans(eventSource, request.getInput.split(":").head, locale)
    }

    val message = data._1.intValue match {
      case 0 =>

        if (log.isDebugEnabled) {
          log.debug("received a response code of["+data._1.intValue+"]")
        }

        var i = 1
        data._3.foreach(f => {
          val mi = new MenuItem
          mi.setDisplay(f.getProductName)
          mi.setOption(i.toString)
          mi.setValue(request.getInput+":"+f.getProductIdentifier+":"+f.getID+":"+f.getMRC)
          menu.getItems.add(mi)
          i=i+1
        })

        val titleMessage = request.getInput.split(":").reverse.head match {
          case MenuConstants.THREEG_DATA => new ApplicationStringsDAO().getAppText(ApplicationConstants.APPLICATION_NAME, SELECT_3G_BUNDLE, locale)
          case MenuConstants.BLACKBERRY_DATA =>  new ApplicationStringsDAO().getAppText(ApplicationConstants.APPLICATION_NAME, BLACKBERRY_BUNDLE, locale)
        }

        val title = new Title
        val m = new MessageContent
        title.setValue(titleMessage)

        menu.setTitle(title)
        menu.setUnicode(UssdUtils.isUnicode(locale))

        m.setMenu(menu)
        m.setType(ContentType.MENU)

        reference.setCorrelator(request.getCorrelator + CorrelatorConstants.FOURTH_TRAVERSAL + ":" + MenuConstants.DATA_BUNDLE_SELECT + ";")
        response.setBreakSession(false)

        m
      case _ =>

        if (log.isDebugEnabled) {
          log.debug("received a response code of["+data._1.intValue+"]")
        }

        val notification = new Notification
        notification.setMessage(data._2)
        notification.setUnicode(UssdUtils.isUnicode(locale))

        val m = new MessageContent

        m.setNotification(notification)
        m.setType(ContentType.NOTIFICATION)

        response.setBreakSession(false)

        m
    }


    response.setMessage(message)
    response.setReference(reference)

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
