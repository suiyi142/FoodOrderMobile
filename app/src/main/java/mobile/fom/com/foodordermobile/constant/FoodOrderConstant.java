package mobile.fom.com.foodordermobile.constant;

public class FoodOrderConstant {

    //商家或者用户状态
    public static final String CHOICE_STATE = "choiceState";
    public static final int STATE_NULL = 0;
    public static final int STATE_BUSINESS = 1;
    public static final int STATE_USER = 2;

    //登录页面保存的KEY
    public static final String AUTO_LOGIN_STATE = "auto_login_state";
    public static final String REMEMBER_PASSWORD = "remember_password";
    public static final String REMEMBER_ACCOUNT = "remember_account";
    public static final String REMEMBER_PASSWORD_STATE = "remember_password_state";

    //服务器交互
    public static final String SERVER_ADDRESS = "http://10.0.2.2:8080/foodOrderServer/";

    //-----------------------商家--------------------------
    //获取商家列表
    public static final String B_FIND_BUSINESS = "BusinessServlet?method=findBusiness";
    //商家注册
    public static final String B_BUSINESS_REGISTER = "BusinessServlet?method=register";
    //商家登录
    public static final String B_BUSINESS_LOGIN = "BusinessServlet?method=login";

    //------------------------用户--------------------------
    //用户登录
    public static final String U_USER_LOGIN = "UserServlet?method=login";
    //用户注册
    public static final String U_USER_REGISTER = "UserServlet?method=register";
}
