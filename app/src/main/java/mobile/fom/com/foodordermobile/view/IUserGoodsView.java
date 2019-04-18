package mobile.fom.com.foodordermobile.view;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.bean.Goods;

public interface IUserGoodsView {
    void setGoodsList(ArrayList<Goods> goodsList);

    void showGetGoodsFailed(String msg);
}
