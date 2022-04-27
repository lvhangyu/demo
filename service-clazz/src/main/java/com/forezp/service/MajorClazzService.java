package com.forezp.service;

import com.forezp.mvc.UserInfo;
import com.forezp.pojo.vo.MajorClazzVo;
import com.forezp.pojo.vo.MajorVo;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface MajorClazzService {
    MajorClazzVo importMajorClazz(ByteArrayInputStream byteArrayInputStream, UserInfo userInfo, String fileType, String majorNumber, String majorName);

    List<MajorClazzVo> query();

    MajorClazzVo myclass(UserInfo userInfo);

    List<MajorVo> majors();
}
