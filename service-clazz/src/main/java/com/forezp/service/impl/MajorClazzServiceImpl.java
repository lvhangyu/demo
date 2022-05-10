package com.forezp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.forezp.mapper.MajorClazzMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.MajorClazzDao;
import com.forezp.pojo.vo.MajorClazzVo;
import com.forezp.pojo.vo.MajorVo;
import com.forezp.service.MajorClazzService;
import com.forezp.util.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.util.*;

@Service
public class MajorClazzServiceImpl implements MajorClazzService {
    @Autowired
    private MajorClazzMapper majorClazzMapper;

    @Override
    public MajorClazzVo importMajorClazz(ByteArrayInputStream byteArrayInputStream, UserInfo userInfo, String fileType, String majorNumber, String majorName) {
        MajorClazzVo majorClazzVo = new MajorClazzVo();
        List<List<String>> dataList = ExcelUtil.readExcel(byteArrayInputStream, fileType);
        System.out.println("**excel data:"+dataList);
        if(CollectionUtils.isEmpty(dataList)) {
            System.out.println("datalist is empty");
            return majorClazzVo;
        }
        MajorClazzDao majorClazzDao = parseExeclData(dataList);
        majorClazzDao.setNumber(majorNumber);
        majorClazzDao.setName(majorName);
        majorClazzDao.setCtime(new Date());
        majorClazzDao.setMtime(new Date());
        majorClazzMapper.insert(majorClazzDao);
        BeanUtils.copyProperties(majorClazzDao, majorClazzVo);
        return majorClazzVo;
    }

    @Override
    public List<MajorClazzVo> query() {
        List<MajorClazzVo> majorClazzVoList = new ArrayList<>(16);
        List<MajorClazzDao> majorClazzDaoList = majorClazzMapper.selectList(null);
        majorClazzVoList = BeanUtil.copyToList(majorClazzDaoList, MajorClazzVo.class, null);
        return majorClazzVoList;
    }

    @Override
    public MajorClazzVo myclass(UserInfo userInfo) {
        MajorClazzVo majorClazzVo = new MajorClazzVo();
        MajorClazzDao majorClazzDao = majorClazzMapper.selectById(userInfo.getMajorId());
        BeanUtils.copyProperties(majorClazzDao, majorClazzVo);
        return majorClazzVo;
    }

    @Override
    public List<MajorVo> majors() {
        List<MajorVo> majorVoList = new ArrayList<>(16);
        List<MajorClazzDao> majorClazzDaoList = majorClazzMapper.selectList(null);
        majorVoList = BeanUtil.copyToList(majorClazzDaoList, MajorVo.class, null);
        return majorVoList;
    }

    private MajorClazzDao parseExeclData(List<List<String>> dataList) {
        MajorClazzDao majorClazzDao = new MajorClazzDao();
        Map<String, Map<String, Map<String, String>>> contentMap = new HashMap(16);
        Map<String, Map<String, String>> dayMap = new HashMap<>(16);
        Map<String, String> amMap = new HashMap<>(16);
        Map<String, String> pmMap = new HashMap<>(16);
        String[] dayStrs = {"Mon","Tues","Wed","Thur","Fri"};

        for (int i = 0; i <5 ; i++) {
            dayMap.clear();
            amMap.clear();
            pmMap.clear();
            amMap.put("class_1", dataList.get(2).get(i+1).trim().replace("\n",""));
            amMap.put("class_2", dataList.get(8).get(i+1).trim().replace("\n",""));
            dayMap.put("am", amMap);
            pmMap.put("class_3", dataList.get(14).get(i+1).trim().replace("\n",""));
            pmMap.put("class_4", dataList.get(21).get(i+1).trim().replace("\n",""));
            dayMap.put("pm", pmMap);
            System.out.println(dayMap);
            contentMap.put(dayStrs[i], dayMap);
        }
        String jsonString = JSONObject.toJSONString(contentMap, SerializerFeature.DisableCircularReferenceDetect);
        majorClazzDao.setContent(jsonString);
        return majorClazzDao;
    }


}
