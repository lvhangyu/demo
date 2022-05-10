package com.forezp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.mapper.AbsenceMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.AbsenceDao;
import com.forezp.pojo.dto.AbsenceDto;
import com.forezp.pojo.vo.AbsenceVo;
import com.forezp.service.AbsenceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AbsenceServiceImpl implements AbsenceService {
    @Autowired
    private AbsenceMapper absenceMapper;
    @Override
    public AbsenceVo create(AbsenceDto absenceDto, UserInfo userInfo) {
        AbsenceDao absenceDao = new AbsenceDao();
        BeanUtils.copyProperties(absenceDto, absenceDao);
        absenceDao.setUserId(userInfo.getId());
        absenceDao.setUserName(userInfo.getUsername());
        absenceDao.setUserNumber(userInfo.getNumber());
        absenceDao.setCtime(new Date());
        absenceDao.setMtime(new Date());
        absenceDao.setStatus(0);
        absenceMapper.insert(absenceDao);
        AbsenceVo absenceVo = new AbsenceVo();
        BeanUtils.copyProperties(absenceDao, absenceVo);
        return absenceVo;
    }

    @Override
    public void delete(Long id) {
        absenceMapper.deleteById(id);
    }

    @Override
    public AbsenceVo update(AbsenceDto absenceDto) {
        AbsenceDao absenceDao = new AbsenceDao();
        BeanUtils.copyProperties(absenceDto, absenceDao);
        absenceDao.setMtime(new Date());
        absenceMapper.updateById(absenceDao);
        AbsenceVo absenceVo = new AbsenceVo();
        BeanUtils.copyProperties(absenceDao, absenceVo);
        return absenceVo;
    }

    @Override
    public List<AbsenceVo> query() {
        List<AbsenceDao> absenceDaoList = absenceMapper.selectList(null);
        List<AbsenceVo> absenceVoList = BeanUtil.copyToList(absenceDaoList, AbsenceVo.class, null);
        return absenceVoList;
    }

    @Override
    public List<AbsenceVo> search(Date absenceTime, String majorName, String reason, Integer status, Integer key, Integer order) {
        return null;
    }

    @Override
    public List<AbsenceVo> myAbsence(UserInfo userInfo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userInfo.getId());
        List<AbsenceDao> absenceDaoList = absenceMapper.selectList(queryWrapper);
        List<AbsenceVo> absenceVoList = BeanUtil.copyToList(absenceDaoList, AbsenceVo.class, null);
        return absenceVoList;
    }

    @Override
    public List<AbsenceVo> search(String absenceClazz) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("absence_clazz", absenceClazz);
        List<AbsenceDao> absenceDaoList = absenceMapper.selectList(queryWrapper);
        List<AbsenceVo> absenceVoList = BeanUtil.copyToList(absenceDaoList, AbsenceVo.class, null);
        return absenceVoList;
    }

    @Override
    public List<AbsenceVo> queryByMajorId(Long majorId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("major_id", majorId);
        List<AbsenceDao> absenceDaoList = absenceMapper.selectList(queryWrapper);
        List<AbsenceVo> absenceVoList = BeanUtil.copyToList(absenceDaoList, AbsenceVo.class, null);
        return absenceVoList;
    }
}
