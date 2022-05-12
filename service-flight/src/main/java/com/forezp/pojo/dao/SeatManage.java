package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Table(name = "ls_seat")
@TableName("ls_seat")
public class SeatManage {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(name = "seat_number",comment = "座位号")
    private Integer seatNumber;
    @Column(name = "flight_id",comment = "航班id")
    private Long flightId;
    @Column(name = "user_id",comment = "用户id")
    private Long userId;
    @Column(name = "user_name",comment = "用户名字")
    private String userName;
    @Column(name = "status",comment = "座位状态 0未占用 1已占用 2不可售卖")
    private Integer status;
    @Column(name = "ctime",comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime = new Date();
    @Column(name = "mtime",comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime = new Date();


}
