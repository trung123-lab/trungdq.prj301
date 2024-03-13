/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.util;

/**
 *
 * @author trung
 */
public class ApplicationConstants {

    public static class DispatchFeature {

        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteController";
        public static final String STARTUP_CONTROLLER = "startup";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateController";
        public static final String ADD_TO_CART_CONTROLLER = "addToCartController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
        public static final String REMOVE_FROM_CART_CONTROLLER = "removeFromCartController";
        public static final String CREATE_ACCOUNT_CONTROLLER = "createController";
        public static final String LOAD_DATA_CONTROLLER = "loadDataController";
        public static final String CHECK_OUT_CONTROLLER = "checkOutController";
        public static final String LOGOUT_CONTROLLER = "logOutController";
    }

    public class LoginFeature {

        public static final String INVALID_PAGE = "invalidPage";
        public static final String SEARCH_PAGE = "homePage";

    }

    public class SearchLastNameFeature {

        public static final String SEARCH_PAGE = "homePage";
        public static final String RESULT_PAGE = "homePage";
    }

    public class UpdateFeature {

        public static final String ERROR_PAGE = "errorPage";
    }

    public class StartUpFeature {

        public static final String LOGIN_PAGE = "";
        public static final String SEARCH_PAGE = "homePage";
    }
    public class LoadDataFeature {
        public static final String PRODUCT_PAGE = "productPage";
    }
    public class DeleteFeature {
        public static final String ERROR_PAGE = "errorPage";
    }
    public class CreateFeature {
        public static final String ERROR_PAGE_CREATE = "errorPageCreate";
        public static final String LOGIN_PAGE = "";
    }
    public class CheckOutFeature {
        public static final String SHOW_ORDERDETAIL_PAGE = "showOrderDetail";
        public static final String ERROR_PAGE = "errorPage";
    }
    
    public class LogOutFeature {
        public static final String LOGIN_PAGE = "";
    }
}
