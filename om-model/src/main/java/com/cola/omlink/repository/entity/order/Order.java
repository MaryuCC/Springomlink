package com.cola.omlink.repository.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("order")
@Schema(description = "Order entity")
public class Order extends BaseEntity {
    @Schema(description = "order_no")
    private String orderNo;
    @Schema(description = "project_id")
    private Long projectId;
    @Schema(description = "customer_id")
    private Long customerId;
    @Schema(description = "customer name")
    private String customerName;
    @Schema(description = "original_price")
    private Object originalPrice;
    @Schema(description = "total_price")
    private Object totalPrice;
    @Schema(description = "status 0-pending 1-confirmed 2-completed 3-canceled")
    private Integer status;
    @Schema(description = "cancel_time")
    private Timestamp cancelTime;
}
