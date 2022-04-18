package com.forezp.controller;
import java.math.BigDecimal;
import java.util.Date;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.forezp.exception.MyException;
import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.OrderDO;
import com.forezp.pojo.dto.OrderDTO;
import com.forezp.pojo.vo.OrderVo;
import com.forezp.service.RestFlightService;
import com.forezp.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private RestFlightService restFlightService;

    /**
     * 订单列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultModel<List<OrderVo>> selectList(
                                                 HttpServletRequest request, HttpServletResponse response){
        List<OrderVo> orderVoList = new ArrayList<>();
        List<OrderDO> orderDOList = orderService.getList();
        orderVoList =  BeanUtil.copyToList(orderDOList, OrderVo.class, null);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success").setData(orderVoList);
    }

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/create")
    public ResultModel<OrderVo> create(@CurrentUser UserInfo userInfo,
                                       @RequestBody OrderDTO orderDTO,
                                       HttpServletRequest request, HttpServletResponse response){
        OrderVo orderVo = new OrderVo();
        OrderDO orderDO = orderService.create(orderDTO, userInfo);
        BeanUtils.copyProperties(orderDO, orderVo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success").setData(orderVo);
    }

    public static void main(String[] args) {
        OrderDTO dto = new OrderDTO();
        dto.setId(0L);
        dto.setUserId(0L);
        dto.setFlightId(0L);
        dto.setOrderCode("");
        dto.setUserName("");
        dto.setUserNumber("");
        dto.setMobile("");
        dto.setBoardingTime(new Date());
        dto.setBoardingRoom("");
        dto.setTicketType(0);
        dto.setTicketPrice(new BigDecimal("0"));
        System.out.println(JSONObject.toJSONString(dto));
    }

    /**
     * 更新订单
     * @param status
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/update/{id}")
    public ResultModel<OrderVo> update(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long id,
            @RequestParam("status") Integer status,
                                       HttpServletRequest request, HttpServletResponse response) throws MyException {
        OrderVo orderVo = new OrderVo();
        OrderDO orderDO = orderService.update(userInfo, id, status);
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
    @GetMapping("/list_by_user")
    public ResultModel<List<OrderVo>> getOrderByUserId(@CurrentUser UserInfo userInfo,
                                       HttpServletRequest request, HttpServletResponse response){
        List<OrderVo> orderVoList = new ArrayList<>();
        List<OrderDO> orderDOList = orderService.listByUserId(userInfo.getId());
        orderVoList =  BeanUtil.copyToList(orderDOList, OrderVo.class, null);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success").setData(orderVoList);
    }

    /**
     * 订单详情
     * @return
     */
    @GetMapping("/details")
    public ResultModel orderDetails(@CurrentUser UserInfo userInfo,
                                    HttpServletRequest request, HttpServletResponse response){
//        restFlightService.getQuery(request.getHeader("userInfo"));

        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }


    /**
     * 删除订单
     * @param id
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResultModel deleteById(@PathVariable("id") Long id,
                                  HttpServletRequest request, HttpServletResponse response){
        orderService.deleteById(id);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }

}
