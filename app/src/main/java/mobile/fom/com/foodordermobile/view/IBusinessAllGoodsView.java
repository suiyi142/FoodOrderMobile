package mobile.fom.com.foodordermobile.view;

import java.util.List;

import mobile.fom.com.foodordermobile.bean.Goods;

public interface IBusinessAllGoodsView {

    /**
     * 设置刷新列表
     *
     * @param goodsList
     */
    void setGoodsList(List<Goods> goodsList);

    /**
     * 展示错误信息
     *
     * @param msg
     */
    void showErrorMsg(String msg);
}
