package com.wuyong.cloud.order.controller;

import com.google.common.collect.Lists;
import com.wuyong.cloud.order.VO.ResultVO;
import com.wuyong.cloud.order.converter.OrderForm2OrderDTOConverter;
import com.wuyong.cloud.order.dataobject.OrderDetail;
import com.wuyong.cloud.order.dto.OrderDTO;
import com.wuyong.cloud.order.enums.OrderStatusEnum;
import com.wuyong.cloud.order.enums.PayStatusEnum;
import com.wuyong.cloud.order.enums.ResultEnum;
import com.wuyong.cloud.order.exception.OrderException;
import com.wuyong.cloud.order.form.OrderForm;
import com.wuyong.cloud.order.service.OrderService;
import com.wuyong.cloud.order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 廖师兄
 * 2017-12-10 16:36
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1. 参数检验
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create() {
        /*if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }*/
        // orderForm -> orderDTO
        /*OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
*/

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("JianGUo");
        orderDTO.setBuyerAddress("安徽省");
        orderDTO.setBuyerOpenid("9999999");
        orderDTO.setBuyerPhone("13365731570");
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductQuantity(2);

        orderDTO.setOrderDetailList(Lists.newArrayList(orderDetail));

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }


    @RequestMapping("test")
    public String test(){
        return "hello";
    }
}
