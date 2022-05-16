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
//@Table(name = "ls_seat")
//@TableName("ls_seat")
public class SeatManage {

//    /**
//     * 主键
//     */
//    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
//    @IsKey                         //actable主键注解
//    @IsAutoIncrement             //自增
//    @Column(name = "id",comment = "id")
//    private Long id;
//    @Column(name = "seat_list",comment = "座位列表")
//    private List<Seat> seatList;
//    @Column(name = "flight_id",comment = "航班id")
//    private Long flightId;
//    @Column(name = "ctime",comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
//    private Date ctime = new Date();
//    @Column(name = "mtime",comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
//    private Date mtime = new Date();
//
//
//    public static class Seat{
//        private String seatNumber;
//        private Boolean exits;
//        private Long userId;
//        private String userName;
//
//    }
}
