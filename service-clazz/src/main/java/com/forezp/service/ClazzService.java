package com.forezp.service;

import com.alibaba.fastjson.JSONObject;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.vo.ClazzVo;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ClazzService {
    ClazzVo importClazz(ByteArrayInputStream byteArrayInputStream, UserInfo userInfo, String fileType, String majorNumber, String majorName);
}
