package com.oyr.sell.controller;

import com.oyr.sell.converter.OrderFormToOrderDTO;
import com.oyr.sell.dto.OrderDTO;
import com.oyr.sell.enums.ResultEnum;
import com.oyr.sell.exception.SellException;
import com.oyr.sell.form.OrderForm;
import com.oyr.sell.service.BuyerService;
import com.oyr.sell.service.OrderService;
import com.oyr.sell.utils.ResultVoUtils;
import com.oyr.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 欧阳荣
 * 2018/3/13 20:41
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm form, BindingResult bindingResult){
        //判断参数是否正确
        if(bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确：{}", form);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        //判断购物车是否为空
        OrderDTO orderDTO = OrderFormToOrderDTO.converter(form);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        //写入数据库
        OrderDTO dto = service.create(orderDTO);

        //返回结果
        Map<String, String> map = new HashMap<>();
        map.put("orderId", dto.getOrderId());
        return ResultVoUtils.ok(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(String openid,
                                         @RequestParam(value="page", defaultValue = "0") Integer page,
                                         @RequestParam(value="size", defaultValue = "10") Integer size){
        Page<OrderDTO> dtoPage = service.findList(openid, new PageRequest(page, size));
        return ResultVoUtils.ok(dtoPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(String openid, String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtils.ok(orderDTO);
    }


    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(String openid, String orderId){
        buyerService.cancel(openid, orderId);
        return ResultVoUtils.ok();
    }
}
