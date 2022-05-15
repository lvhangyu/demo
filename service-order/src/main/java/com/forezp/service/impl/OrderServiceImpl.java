package com.forezp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.exception.MyException;
import com.forezp.mapper.OrderMapper;
import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.OrderDO;
import com.forezp.pojo.dto.OrderDTO;
import com.forezp.pojo.vo.OrderVo;
import com.forezp.service.OrderService;
import com.forezp.service.RestFlightService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestFlightService restFlightService;

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<OrderVo> getList() {
        List<OrderDO> orderDOList = orderMapper.selectList(null);
        List<OrderVo> orderVoList = new ArrayList<>();
        orderVoList =  BeanUtil.copyToList(orderDOList, OrderVo.class, null);
        for (OrderVo orderVo : orderVoList){
            ResultModel resultModel = restFlightService.info(orderVo.getFlightId());
            orderVo.setFlightInfo(resultModel.getData());
        }
        return orderVoList;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderVo create(OrderDTO orderDTO, UserInfo userInfo) throws MyException {
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderDTO, orderDO);
        orderDO.setUserId(userInfo.getId());
        orderMapper.insert(orderDO);
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(orderDO, orderVo);
        ResultModel resultModel = restFlightService.info(orderVo.getFlightId());
        ResultModel resultModel1 = restFlightService.setSeat(orderVo.getFlightId(), orderVo.getSeatNumber(), userInfo.getId(), userInfo.getUsername(), userInfo.getUserNumber());
        if(resultModel1.getCode() == 400){
            throw new MyException(resultModel1.getCode(), resultModel1.getMsg());
        }
        orderVo.setFlightInfo(resultModel.getData());
        return orderVo;
    }

    @Override
    public OrderVo update(UserInfo userInfo, Long id, Integer status) throws MyException {
        OrderDO orderDO = orderMapper.selectById(id);
        if(!userInfo.getId().equals(orderDO.getUserId())){
            throw new MyException(400 ,"无权限操作");
        }
        orderDO.setStatus(status);
        orderMapper.updateById(orderDO);
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(orderDO, orderVo);
        ResultModel resultModel = restFlightService.info(orderVo.getFlightId());
        orderVo.setFlightInfo(resultModel.getData());
        return orderVo;
    }

    @Override
    public List<OrderVo> listByUserId(Long userId) {
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<OrderDO> orderDOList = orderMapper.selectList(wrapper);
        List<OrderVo> orderVoList = new ArrayList<>();
        orderVoList =  BeanUtil.copyToList(orderDOList, OrderVo.class, null);
        for (OrderVo orderVo : orderVoList){
            ResultModel resultModel = restFlightService.info(orderVo.getFlightId());
            orderVo.setFlightInfo(resultModel.getData());
        }
        return orderVoList;
    }

    @Override
    public void deleteById(Long id) {
        orderMapper.deleteById(id);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancel(Long id) throws MyException {
        //更新订单状态
        OrderDO orderDO = orderMapper.selectById(id);
        orderDO.setStatus(1);
        orderMapper.updateById(orderDO);
        //更新航班座位
        ResultModel resultModel1 = restFlightService.seatCancel(orderDO.getFlightId(), orderDO.getSeatNumber());
        if(resultModel1.getCode() == 400){
            throw new MyException(resultModel1.getCode(), resultModel1.getMsg());
        }
    }
}
