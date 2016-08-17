package om.nawras.mediation.ussd.ajelbundle.entrypoint

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.ussd.utils.UssdUtils
import om.nawras.mediation.ussd.dao.ApplicationStringsDAO
import om.nawras.mediation.ussd.ajelbundle.csi.client.GetAvailableProductsWS
import scala.collection.JavaConversions._
import om.nawras.mediation.ussd.ajelbundle.constants.{MenuConstants, ApplicationConstants, CorrelatorConstants}

object ManageShahryKeys {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")

  val MENU_TITLE = "ShahryKeys.Title"
  val ADD_ONS_TITLE = "AddOnKeys.Title"

  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering...")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val locale = UssdUtils.getLocale(request.getLanguage)
    val eventSource = UssdUtils.stripLeading968(UssdUtils.getMsisdnFromAddress(request.getAddress))

    val productType = request.getInput.trim match {
      case MenuConstants.BOLT_ON => "BO"
      case _ => "RA"
    }

    val products = GetAvailableProductsWS.execute(eventSource, productType, locale)

    val asDao = new ApplicationStringsDAO

    val simpleReference = new SimpleReference
    val content         = new MessageContent

    val title           = new Title

    val response: UssdMOResponse = products.responseCode match {
      case 0 =>

        val menu            = new Menu
        val response        = new UssdMOResponse

        products.products.getUSSDProduct.zipWithIndex.foreach {
          case (product, index) =>
            val menuItem = new MenuItem()
            menuItem.setDisplay(product.getProductName)
            menuItem.setOption((index+1).toString)
            menuItem.setValue(product.getProductIdentifier+":"+product.getProductName)
            menu.getItems.add(menuItem)
        }

        val titleText = request.getInput.trim match {
          case MenuConstants.BOLT_ON => asDao.getAppText(ApplicationConstants.APPLICATION_NAME, MENU_TITLE, locale)
          case _ => asDao.getAppText(ApplicationConstants.APPLICATION_NAME, ADD_ONS_TITLE, locale)
        }

        title.setValue(titleText)
        menu.setTitle(title)
        menu.setUnicode(UssdUtils.isUnicode(locale))

        val corr = request.getInput.trim match {
          case MenuConstants.BOLT_ON => request.getCorrelator+CorrelatorConstants.SECOND_TRAVERSAL + ":" + CorrelatorConstants.SELECT_SHAHRY_KEY + ";"
          case MenuConstants.ADD_ON => request.getCorrelator+CorrelatorConstants.SECOND_TRAVERSAL + ":" + CorrelatorConstants.SELECT_ADD_ON_KEY + ";"
          case _ => ""
        }

        simpleReference.setCorrelator(corr)

        content.setMenu(menu)
        content.setType(ContentType.MENU)

        response.setReference(simpleReference)
        response.setBreakSession(false)
        response.setMessage(content)

        response

      case _ =>

        val notification = new Notification
        val response     = new UssdMOResponse
        notification.setMessage(products.responseMessage)
        notification.setUnicode(UssdUtils.isUnicode(locale))

        content.setType(ContentType.NOTIFICATION)
        content.setNotification(notification)

        response.setBreakSession(false)
        response.setMessage(content)

        response
    }

    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }

}
