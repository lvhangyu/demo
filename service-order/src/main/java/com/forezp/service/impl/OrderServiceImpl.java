package com.forezp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.exception.MyException;
import com.forezp.mapper.OrderMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.OrderDO;
import com.forezp.pojo.dto.OrderDTO;
import com.forezp.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<OrderDO> getList() {
        List<OrderDO> orderDOList = orderMapper.selectList(null);
        return orderDOList;
    }

    @Override
    public OrderDO create(OrderDTO orderDTO, UserInfo userInfo) {
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderDTO, orderDO);
        orderDO.setUserId(userInfo.getId());
        orderMapper.insert(orderDO);
        return orderDO;
    }

    @Override
    public OrderDO update(UserInfo userInfo, Long id, Integer status) throws MyException {
        OrderDO orderDO = orderMapper.selectById(id);
        if(!userInfo.getId().equals(orderDO.getUserId())){
            throw new MyException(400 ,"无权限操作");
        }
        orderDO.setStatus(status);
        orderMapper.updateById(orderDO);
        return orderDO;
    }

    @Override
    public List<OrderDO> listByUserId(Long userId) {
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<OrderDO> orderDOList = orderMapper.selectList(wrapper);
        return orderDOList;
    }

    @Override
    public void deleteById(Long id) {
        orderMapper.deleteById(id);
    }
}
