package com.forezp.service;

import com.forezp.exception.MyException;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.OrderDO;
import com.forezp.pojo.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDO> getList();

    OrderDO create(OrderDTO orderDTO, UserInfo userInfo);

    OrderDO update(UserInfo userInfo, Long id, Integer status) throws MyException;

    List<OrderDO> listByUserId(Long userId);

    void deleteById(Long id);
}
