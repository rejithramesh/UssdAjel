package om.nawras.mediation.ussd.ajelbundle.entrypoint

import org.apache.commons.logging.LogFactory
import om.nawras.mediation.ussd.telenity.service._
import om.nawras.mediation.ussd.ajelbundle.constants.CorrelatorConstants
import om.nawras.mediation.ussd.ajelbundle.traversals._


object UssdDelegate {

  val CLASS_NAME  = this.getClass.getName
  val LOG_EXECUTE = LogFactory.getLog(CLASS_NAME + ".execute()")



  def execute(request: UssdMORequest): UssdMOResponse = {

    val log = LOG_EXECUTE

    if (log.isDebugEnabled) {
      log.debug("Entering.....")
      log.debug(new StringBuilder("inputs request[").append(request).append("]"))
    }

    val correlator = request.getCorrelator.split(";").last.split(":")
    //request.setAddress("tel:96895015427")

    if (log.isDebugEnabled) {
      log.debug("first element in correlator: " + correlator.head)
      log.debug("last element in correlator: " + correlator.last)

      var lis =request.getContextParameters
      try
      {
        var atb = lis.get(0)
        log.debug("input is: "+atb.getValue)
        log.debug("CORRELATOR HEAD MATCHING ::: "+correlator.head.compareToIgnoreCase(CorrelatorConstants.THIRD_TRAVERSAL))
      }
      catch
        {

          case _: Throwable => log.debug("Exception Occured *********************** ")
        }

    }

    val response = correlator.head match {

      case CorrelatorConstants.FIRST_TRAVERSAL => FirstTraversal.execute(request)
      case CorrelatorConstants.SECOND_TRAVERSAL => SecondTraversal.execute(request)
      case CorrelatorConstants.THIRD_TRAVERSAL => ThirdTraversal.execute(request)
      case CorrelatorConstants.FOURTH_TRAVERSAL => FourthTraversal.execute(request)
      case CorrelatorConstants.FIFTH_TRAVERSAL => FifthTraversal.execute(request)
      case _ => MainMenu.execute(request)
    }


    if (log.isDebugEnabled) {
      log.debug(new StringBuilder("outputs response[").append(response).append("]"))
      log.debug("Exiting...")
    }

    response
  }
}

