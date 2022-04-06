package com.forezp.controller;

import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.OrderDO;
import com.forezp.pojo.dto.OrderDTO;
import com.forezp.pojo.vo.OrderVo;
import com.forezp.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 18:40
 * Version 1.0
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultModel<List<OrderVo>> selectList(
                                                 HttpServletRequest request, HttpServletResponse response){
        List<OrderVo> orderVoList = new ArrayList<>();
        List<OrderDO> orderDOList = orderService.getList();
        BeanUtils.copyProperties(orderDOList, orderVoList);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success").setData(orderVoList);
    }

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/create")
    public ResultModel<OrderVo> create(@RequestBody OrderDTO orderDTO,
                                       HttpServletRequest request, HttpServletResponse response){
        OrderVo orderVo = new OrderVo();
        OrderDO orderDO = orderService.create(orderDTO);
        BeanUtils.copyProperties(orderDO, orderVo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success").setData(orderVo);
    }

    /**
     * 更新订单
     * @param orderDTO
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/update")
    public ResultModel<OrderVo> update(@RequestBody OrderDTO orderDTO,
                                       HttpServletRequest request, HttpServletResponse response){
        OrderVo orderVo = new OrderVo();
        OrderDO orderDO = orderService.update(orderDTO);
        BeanUtils.copyProperties(orderDO, orderVo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success").setData(orderVo);
    }

    /**
     * 我的订单
     * @param userInfo
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/list_by_user")
    public ResultModel<List<OrderVo>> getOrderByUserId(@CurrentUser UserInfo userInfo,
                                       HttpServletRequest request, HttpServletResponse response){
        List<OrderVo> orderVoList = new ArrayList<>();
        List<OrderDO> orderDOList = orderService.listByUserId(userInfo.getId());
        BeanUtils.copyProperties(orderDOList, orderVoList);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success").setData(orderVoList);
    }


    /**
     * 删除订单
     * @param orderDTO
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/delete")
    public ResultModel deleteById(@RequestBody OrderDTO orderDTO,
                                  HttpServletRequest request, HttpServletResponse response){
        orderService.deleteById(orderDTO.getId());
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }

}
