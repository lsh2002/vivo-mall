package com.lsh.vivo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class OrderTableDef extends TableDef {

    /**
     * 订单

 @author lsh
     */
    public static final OrderTableDef ORDER = new OrderTableDef();

    /**
     * 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 用户id
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 创建人
     */
    public final QueryColumn CREATOR = new QueryColumn(this, "creator");

    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 订单号
     */
    public final QueryColumn ORDER_ID = new QueryColumn(this, "order_id");

    /**
     * 付款时间
     */
    public final QueryColumn PAY_TIME = new QueryColumn(this, "pay_time");

    /**
     * 更新人
     */
    public final QueryColumn MODIFIER = new QueryColumn(this, "modifier");

    /**
     * 乐观锁
     */
    public final QueryColumn REVISION = new QueryColumn(this, "revision");

    /**
     * 创建人id
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 下单时间
     */
    public final QueryColumn ORDER_TIME = new QueryColumn(this, "order_time");

    public final QueryColumn REQUEST_NO = new QueryColumn(this, "request_no");

    /**
     * 取消时间
     */
    public final QueryColumn CANCEL_TIME = new QueryColumn(this, "cancel_time");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 完成时间
     */
    public final QueryColumn FINISH_TIME = new QueryColumn(this, "finish_time");

    /**
     * 更新人id
     */
    public final QueryColumn MODIFIER_ID = new QueryColumn(this, "modifier_id");

    /**
     * 商品单价
     */
    public final QueryColumn TOTAL_PRICE = new QueryColumn(this, "total_price");

    /**
     * 发货时间
     */
    public final QueryColumn DELIVER_TIME = new QueryColumn(this, "deliver_time");

    public final QueryColumn SERVICE_TIME = new QueryColumn(this, "service_time");

    /**
     * 服务类型
     */
    public final QueryColumn SERVICE_TYPE = new QueryColumn(this, "service_type");

    /**
     * 更新时间
     */
    public final QueryColumn MODIFIER_TIME = new QueryColumn(this, "modifier_time");

    /**
     * 收货人姓名
     */
    public final QueryColumn RECEIVER_NAME = new QueryColumn(this, "receiver_name");

    /**
     * 快递单号
     */
    public final QueryColumn COURIER_NUMBER = new QueryColumn(this, "courier_number");

    /**
     * 收货人电话
     */
    public final QueryColumn RECEIVER_PHONE = new QueryColumn(this, "receiver_phone");

    /**
     * 收货人地址
     */
    public final QueryColumn RECEIVER_ADDRESS = new QueryColumn(this, "receiver_address");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, STATUS, USER_ID, CREATOR, ORDER_ID, PAY_TIME, MODIFIER, REVISION, CREATOR_ID, ORDER_TIME, REQUEST_NO, CANCEL_TIME, CREATE_TIME, FINISH_TIME, MODIFIER_ID, TOTAL_PRICE, DELIVER_TIME, SERVICE_TIME, SERVICE_TYPE, MODIFIER_TIME, RECEIVER_NAME, COURIER_NUMBER, RECEIVER_PHONE, RECEIVER_ADDRESS};

    public OrderTableDef() {
        super("", "order");
    }

}
