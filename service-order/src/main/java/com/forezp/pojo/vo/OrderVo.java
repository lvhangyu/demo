package com.forezp.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderVo {

    private Long id;
    //@Column(name = "user_id",comment = "用户id")
    private Long userId;
    //@Column(name = "flight_id",comment = "航班id")
    private Long flightId;
    private Long seatNumber;
    //@Column(name = "order_code",comment = "订单编号")
    private String orderCode;
    //@Column(name = "user_name",comment = "乘机人姓名")
    private String userName;
    //@Column(name = "user_number",comment = "乘机人身份证号")
    private String userNumber;
    //@Column(name = "user_mobile",comment = "联系方式")
    private String mobile;
    //@Column(name = "boarding_time",comment = "登机时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date boardingTime;
    //@Column(name = "boarding_room",comment = "登机室")
    private String boardingRoom;
    //@Column(name = "ticket_type",comment = "机票类型 0经济舱，1商务舱，2头等舱", type = MySqlTypeConstant.TINYINT)
    private Integer ticketType;
    //@Column(name = "ticket_price",comment = "机票价格", type = MySqlTypeConstant.DECIMAL)
    private BigDecimal ticketPrice;
    //@Column(name = "status",comment = "订单状态（0待出票 1已出票 2申请改签 3已改签 4申请退票 5已退票）", type = MySqlTypeConstant.TINYINT)
    private Integer status;
    //@Column(name = "ctime", comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime;
    //@Column(name = "mtime", comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime;
    //@Column(name = "flight_id",comment = "航班id")
    private Object flightInfo;
//    private Object seatInfo;
}
