package mobile.fom.com.foodordermobile.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.bean.Order;
import mobile.fom.com.foodordermobile.bean.OrderItem;
import mobile.fom.com.foodordermobile.bean.User;
import mobile.fom.com.foodordermobile.model.model.BusinessModel;
import mobile.fom.com.foodordermobile.model.IBusinessModel;
import mobile.fom.com.foodordermobile.model.IModelCallBack;
import mobile.fom.com.foodordermobile.view.IBusinessAllGoodsView;
import mobile.fom.com.foodordermobile.view.IBusinessChangePasswordView;
import mobile.fom.com.foodordermobile.view.IBusinessLoginView;
import mobile.fom.com.foodordermobile.view.IBusinessOrderView;
import mobile.fom.com.foodordermobile.view.IBusinessRegisterView;
import mobile.fom.com.foodordermobile.view.IBusinessGoodsView;
import mobile.fom.com.foodordermobile.view.IOrderView;

public class BusinessPresenter {
    private IBusinessModel mBusinessModel;
    private IBusinessLoginView mBusinessLoginView;
    private IBusinessRegisterView mBusinessRegisterView;
    private IBusinessOrderView mBusinessOrderView;
    private IBusinessGoodsView mBusinessGoodsView;
    private IBusinessAllGoodsView mBusinessAllGoodsView;
    private IOrderView orderView;
    private IBusinessChangePasswordView mBusinessChangePasswordView;

    public BusinessPresenter(IBusinessLoginView mBusinessView) {
        this.mBusinessLoginView = mBusinessView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessOrderView mBusinessOrderView) {
        this.mBusinessOrderView = mBusinessOrderView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessRegisterView mBusinessRegisterView) {
        this.mBusinessRegisterView = mBusinessRegisterView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessGoodsView mBusinessGoodsView) {
        this.mBusinessGoodsView = mBusinessGoodsView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessAllGoodsView mBusinessAllGoodsView) {
        this.mBusinessAllGoodsView = mBusinessAllGoodsView;
        getBusinessModel();
    }

    public BusinessPresenter(IOrderView orderView) {
        this.orderView = orderView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessChangePasswordView mBusinessChangePasswordView) {
        this.mBusinessChangePasswordView = mBusinessChangePasswordView;
        getBusinessModel();
    }

    /*
    懒汉式创建model
    */
    private void getBusinessModel() {
        if (mBusinessModel == null)
            mBusinessModel = new BusinessModel();
    }

    /**
     * 查询商家
     */
    public void findBusiness() {
        mBusinessModel.findBusiness(new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                ArrayList<Business> list = gson.fromJson(msg, new TypeToken<ArrayList<Business>>() {
                }.getType());
                if (list.size() == 0)
                    mBusinessLoginView.BusinessZero();
                else
                    mBusinessLoginView.setBusinessList(list);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessLoginView.setFailedMsg(msg);
            }
        });
    }

    /**
     * 商家注册
     *
     * @param business 商家bean
     */
    public void businessRegister(Business business) {
        mBusinessModel.register(business, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1"))
                    mBusinessRegisterView.registerSuccess();
                else mBusinessRegisterView.registerFailed(msg);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessRegisterView.registerFailed(msg);
            }
        });
    }

    /**
     * 商家登录
     *
     * @param b_id     商家id
     * @param password 商家密码
     */
    public void businessLogin(final String b_id, String password) {
        mBusinessModel.login(b_id, password, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("0"))
                    mBusinessLoginView.showLoginErrorMsg(b_id, "登录失败");
                else {
                    Gson gson = new Gson();
                    Business business = gson.fromJson(msg, Business.class);
                    mBusinessLoginView.toBusiness(business);
                }
            }

            @Override
            public void onFailure(String msg) {
                mBusinessLoginView.showLoginErrorMsg(b_id, msg);
            }
        });
    }

    /*
    获取历史订单
     */
    public void getOldOrder(String b_id) {
        mBusinessModel.getOldOrder(b_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                List<Order> orderList = gson.fromJson(msg, new TypeToken<List<Order>>() {
                }.getType());
                mBusinessOrderView.showOrder(orderList);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessOrderView.showError(msg);
            }
        });
    }

    /*
    获取新订单
     */
    public void getNewOrder(String b_id) {
        mBusinessModel.getNewOrder(b_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                List<Order> orderList = gson.fromJson(msg, new TypeToken<List<Order>>() {
                }.getType());
                mBusinessOrderView.showOrder(orderList);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessOrderView.showError(msg);
            }
        });
    }

    /*
    增加商品
     */
    public void addGoods(String b_id, String g_name, String g_price, String g_other) {
        mBusinessModel.addGoods(b_id, g_name, g_price, g_other, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1")) {
                    mBusinessGoodsView.changeSuccess("添加成功");
                } else {
                    mBusinessGoodsView.changeFailed(msg);
                }

            }

            @Override
            public void onFailure(String msg) {
                mBusinessGoodsView.changeFailed(msg);
            }
        });
    }

    /*
    查找所有商品
     */
    public void getAllGoods(String b_id) {
        mBusinessModel.getAllGoods(b_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                List<Goods> goods = gson.fromJson(msg, new TypeToken<List<Goods>>() {
                }.getType());
                mBusinessAllGoodsView.setGoodsList(goods);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessAllGoodsView.showErrorMsg(msg);
            }
        });
    }

    /*
    修改goods
     */
    public void updateGoods(Goods goods) {
        mBusinessModel.updateGoods(goods, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1")) {
                    mBusinessGoodsView.changeSuccess("修改成功");
                } else {
                    mBusinessGoodsView.changeFailed(msg);
                }
            }

            @Override
            public void onFailure(String msg) {
                mBusinessGoodsView.changeFailed(msg);
            }
        });
    }

    /*
    删除goods
     */
    public void deleteGoods(String g_id) {
        mBusinessModel.deleteGoods(g_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1")) {
                    mBusinessGoodsView.changeSuccess("删除成功");
                } else {
                    mBusinessGoodsView.changeFailed(msg);
                }
            }

            @Override
            public void onFailure(String msg) {
                mBusinessGoodsView.changeFailed(msg);
            }
        });
    }

    /*
    接单
     */
    public void receiptOrder(String o_id) {
        mBusinessModel.receiptOrder(o_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1")) {
                    orderView.changeSuccess("接单成功");
                } else {
                    orderView.changeFailed(msg);
                }
            }

            @Override
            public void onFailure(String msg) {
                orderView.changeFailed(msg);
            }
        });
    }

    /*
    拒单
     */
    public void refuseOrder(String o_id) {
        mBusinessModel.refuseOrder(o_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1")) {
                    orderView.changeSuccess("拒单成功");
                } else {
                    orderView.changeFailed(msg);
                }
            }

            @Override
            public void onFailure(String msg) {
                orderView.changeFailed(msg);
            }
        });
    }

    /*
    核销
    */
    public void usedOrder(String o_id) {
        mBusinessModel.usedOrder(o_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1")) {
                    orderView.changeSuccess("核销成功");
                } else {
                    orderView.changeFailed(msg);
                }
            }

            @Override
            public void onFailure(String msg) {
                orderView.changeFailed(msg);
            }
        });
    }

    public void deleteOrder(String o_id){
        mBusinessModel.deleteOrder(o_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1")) {
                    orderView.changeSuccess("删除成功");
                } else {
                    orderView.changeFailed("删除失败");
                }
            }

            @Override
            public void onFailure(String msg) {
                orderView.changeFailed(msg);
            }
        });
    }


    public void getOrderItem(String o_id) {
        mBusinessModel.getOrderItem(o_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("0")) {
                    orderView.changeFailed("数据库错误");
                } else {
                    Gson gson = new Gson();
                    List<OrderItem> list = gson.fromJson(msg, new TypeToken<List<OrderItem>>() {
                    }.getType());
                    orderView.setOrderItem(list);
                }
            }

            @Override
            public void onFailure(String msg) {
                orderView.changeFailed(msg);
            }
        });
    }


    public void getUserName(String u_id) {
        mBusinessModel.getUserName(u_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                User user = gson.fromJson(msg, User.class);
                orderView.setUserName(user.getName());
            }

            @Override
            public void onFailure(String msg) {
                orderView.changeFailed(msg);
            }
        });
    }

    /**
     * 修改密码
     */
    public void changePassword(final String b_id, String address, String newPassword) {
        mBusinessModel.changePassword(b_id, address, newPassword, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                switch (msg) {
                    case "1":
                        mBusinessChangePasswordView.changeFailed("没有这个商家");
                        break;
                    case "2":
                        mBusinessChangePasswordView.changeFailed("商家地址错误");
                        break;
                    case "3":
                        mBusinessChangePasswordView.changeSuccess();
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                mBusinessChangePasswordView.changeFailed(msg);
            }
        });
    }

}
