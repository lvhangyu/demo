package com.forezp.service;

import com.forezp.pojo.dao.OrderDO;
import com.forezp.pojo.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDO> getList();

    OrderDO create(OrderDTO orderDTO);

    OrderDO update(OrderDTO orderDTO);

    List<OrderDO> listByUserId(Long userId);

    void deleteById(Long id);
}
