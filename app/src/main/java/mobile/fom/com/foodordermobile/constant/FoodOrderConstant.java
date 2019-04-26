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
    //获取历史订单
    public static final String B_BUSINESS_GET_OLD_ORDER = "BusinessServlet?method=getOldOrder";
    //获取新订单
    public static final String B_BUSINESS_GET_NEW_ORDER = "BusinessServlet?method=getNewOrder";
    //获取所有商品
    public static final String B_BUSINESS_GET_ALL_GOODS = "BusinessServlet?method=findGoods";
    //添加商品
    public static final String B_BUSINESS_ADD_GOODS = "BusinessServlet?method=addGoods";
    //修改商品
    public static final String B_BUSINESS_UPDATE_GOODS = "BusinessServlet?method=updateGoods";
    //删除商品
    public static final String B_BUSINESS_DELETE_GOODS = "BusinessServlet?method=deleteGoods";
    //接单
    public static final String B_BUSINESS_RECEIPT_ORDER = "BusinessServlet?method=receipt";
    //拒单
    public static final String B_BUSINESS_REFUSE_ORDER = "BusinessServlet?method=refuse";
    //核销
    public static final String B_BUSINESS_USED_ORDER = "BusinessServlet?method=used";
    //获取订单条目
    public static final String B_BUSINESS_GET_ORDER_ITEM = "BusinessServlet?method=getOderItem";

    //------------------------用户--------------------------
    //用户登录
    public static final String U_USER_LOGIN = "UserServlet?method=login";
    //用户注册
    public static final String U_USER_REGISTER = "UserServlet?method=register";
    //获取商家列表
    public static final String U_FIND_BUSINESS = "/UserServlet?method=findBusiness";
    //获取商品列表
    public static final String U_FIND_GOODS = "/UserServlet?method=findGoods";
    //提交订单
    public static final String U_COMMIT_ORDER = "/UserServlet?method=sendOrder";
    //获取用户姓名
    public static final String U_GET_USER_NAME = "UserServlet?method=findUserById";
}
