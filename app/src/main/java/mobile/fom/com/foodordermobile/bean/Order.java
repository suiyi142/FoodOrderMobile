package mobile.fom.com.foodordermobile.bean;

import java.io.Serializable;

public class Order implements Serializable {
    private String o_id;
    private String b_id;
    private String u_id;
    private String price;
    private String other;
    private int state;
    public static final int STATE_NEW = 0;      //未接单
    public static final int STATE_RECEIPT = 1;  //已接单
    public static final int STATE_USED = 2;     //已核销
    public static final int STATE_REFUSE = 3;   //已拒绝

    public Order() {
        super();
    }

    @Override
    public String toString() {
        return "Order [o_id=" + o_id + ", b_id=" + b_id + ", u_id=" + u_id
                + ", price=" + price + ", other=" + other + ", state=" + state
                + "]";
    }

    public Order(String o_id, String b_id, String u_id, String price,
                 String other, int state) {
        super();
        this.o_id = o_id;
        this.b_id = b_id;
        this.u_id = u_id;
        this.price = price;
        this.other = other;
        this.state = state;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
