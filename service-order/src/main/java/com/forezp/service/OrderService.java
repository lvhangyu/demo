package com.forezp.service;

import com.forezp.exception.MyException;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.OrderDO;
import com.forezp.pojo.dto.OrderDTO;
import com.forezp.pojo.vo.OrderVo;

import java.util.List;

public interface OrderService {

    List<OrderVo> getList();

    OrderVo create(OrderDTO orderDTO, UserInfo userInfo) throws MyException;

    OrderVo update(UserInfo userInfo, Long id, Integer status) throws MyException;

    List<OrderVo> listByUserId(Long userId);

    void deleteById(Long id);

    void cancel(Long id) throws MyException;
}
