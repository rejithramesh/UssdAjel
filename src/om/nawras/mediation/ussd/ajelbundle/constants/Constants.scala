package om.nawras.mediation.ussd.ajelbundle.constants

object CorrelatorConstants {

  val MAIN_MENU			    = "MainMenu"
  val FIRST_TRAVERSAL	  = "FIRST_TRAVERSAL"
  val SECOND_TRAVERSAL	= "SECOND_TRAVERSAL"
  val THIRD_TRAVERSAL	  = "THIRD_TRAVERSAL"
  val FOURTH_TRAVERSAL  = "FOURTH_TRAVERSAL"
  val FIFTH_TRAVERSAL   = "FIFTH_TRAVERSAL"

  val CHANGE_PLAN       = "CHANGE_PLAN"

  val SELECT_SHAHRY_KEY = "SELECT_SHAHRY_KEY"
  val SELECT_ADD_ON_KEY = "SELECT_ADDON_KEY"
}

object MenuConstants {
  val CHANGE_PLAN     = "CHANGE_PLAN"
  val BOLT_ON         = "BOLT_ON"
  val ADD_ON          = "ADD_ON"
  val BALANCE_INQUIRY = "BALANCE_INQUIRY"
  val ADD_ON_INQUIRY  = "ADD_ON_INQUIRY"

  val THREEG_DATA     = "THREEG_DATA"
  val BLACKBERRY_DATA = "BLACKBERRY_DATA"

  val SUBSCRIBE_SHAHRY_KEYS = "SUBSCRIBE_SHAHRY_KEYS"
  val UNSUBSCRIBE_SHAHRY_KEYS = "UNSUBSCRIBE_SHAHRY_KEYS"

  val DATA_BUNDLE_SELECT  = "DATA_BUNDLE_SELECT"
}

object ApplicationConstants {

  val APPLICATION_NAME         = "UssdAjelBundle"
  val POSTPAID_SERVICE_WSDL    = "POSTPAID_SERVICE_WSDL"
  val POSTPAIDSERVICE_USERNAME = "POSTPAIDSERVICE_USERNAME"
  val POSTPAIDSERVICE_PASSWORD = "POSTPAIDSERVICE_PASSWORD"

  val GetAvailableBasePlansWS         = "AjelBundle.GetAvailableBasePlans"
  val GetAvailableDataPlansWS         = "AjelBundle.GetAvailableDataPlans"
  val GetAvailableBlackberryPlansWS   = "AjelBundle.GetAvailableBlackberryPlans"
  val ValidateSubscriptionBasePlansWS = "AjelBundle.ValidateSubscriptionBasePlan"
  val ChangeSubscriptionBundlePlanWS  = "AjelBundle.ChangeSubscriptionBundlePlan"
  val CheckDataBalanceWS              = "CheckDataBalance"
  val CheckAddOnBalanceWS             = "CheckAddOnBalance"
  val GetAvailableProductsWS          = "AjelBundle.GetAvailableProducts"
  val TerminateProductWS              = "AjelBundle.TerminateProduct"
  val ValidateProductsWS              = "AjelBundle.ValidateProduct"
  val AddProductWS                    = "AjelBundle.AddProduct"
}