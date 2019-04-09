package mobile.fom.com.foodordermobile.constant;

public class FoodOrderConstant {

    //商家或者用户状态
    public static final String CHOICE_TATE = "choiceState";
    public static final int STATE_NULL = 0;
    public static final int STATE_BUSINESS = 1;
    public static final int STATE_USER = 2;

    //服务器交互
    public static final String SERVER_ADDRESS = "http://10.0.2.2:8080/foodOrderServer/";
    //获取商家列表
    public static final String B_FIND_BUSINESS = "BusinessServlet?method=findBusiness";
    //商家注册
    public static final String B_BUSINESS_REGISTER = "BusinessServlet?method=register";
}
