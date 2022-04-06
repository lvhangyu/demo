package com.forezp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.mapper.OrderMapper;
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
    public OrderDO create(OrderDTO orderDTO) {
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderDTO, orderDO);
        orderMapper.insert(orderDO);
        return orderDO;
    }

    @Override
    public OrderDO update(OrderDTO orderDTO) {
        OrderDO orderDO = orderMapper.selectById(orderDTO.getId());
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
