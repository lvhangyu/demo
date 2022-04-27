package com.forezp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.forezp.mapper.ClazzMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.ClazzDao;
import com.forezp.pojo.vo.ClazzVo;
import com.forezp.service.ClazzService;
import com.forezp.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public ClazzVo importClazz(ByteArrayInputStream byteArrayInputStream, UserInfo userInfo, String fileType, String majorNumber, String majorName) {
        ClazzVo clazzVo = new ClazzVo();
        List<List<String>> dataList = ExcelUtil.readExcel(byteArrayInputStream, fileType);
        System.out.println("**excel data:"+dataList);
        if(CollectionUtils.isEmpty(dataList)) {
            System.out.println("datalist is empty");
            return clazzVo;
        }
        ClazzDao clazzDao = parseExeclData(dataList);
        clazzMapper.insert(clazzDao);

        return null;
    }

    private ClazzDao parseExeclData(List<List<String>> dataList) {
        ClazzDao clazzDao = new ClazzDao();
        Map<String, Map<String, Map<String, String>>> contentMap = new HashMap(16);
        Map<String, Map<String, String>> dayMap = new HashMap<>(16);
        Map<String, String> amMap = new HashMap<>(16);
        Map<String, String> pmMap = new HashMap<>(16);
        String[] dayStrs = {"Mon","Tues","Wed","Thur","Fri"};

        for (int i = 0; i <5 ; i++) {
            dayMap.clear();
            amMap.clear();
            pmMap.clear();
            amMap.put("class_1", dataList.get(2).get(i+1));
            amMap.put("class_2", dataList.get(8).get(i+1));
            dayMap.put("am", amMap);
            pmMap.put("class_3", dataList.get(14).get(i+1));
            pmMap.put("class_4", dataList.get(21).get(i+1));
            dayMap.put("pm", pmMap);
            System.out.println(dayMap);
            contentMap.put(dayStrs[i], dayMap);
        }
        clazzDao.setContent(contentMap.toString());
        return clazzDao;
    }


}
