package mobile.fom.com.foodordermobile.view;

import mobile.fom.com.foodordermobile.bean.Goods;

public interface IBusinessGoodsView {

    /**
     * 修改成功
     *
     * @param goods 返回修改的goods
     */
    void changeSuccess(Goods goods);

    /**
     * 修改失败
     *
     * @param msg 失败信息
     */
    void changeFailed(String msg);

}
