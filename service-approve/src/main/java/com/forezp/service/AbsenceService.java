package com.forezp.service;

import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dto.AbsenceDto;
import com.forezp.pojo.vo.AbsenceVo;

import java.util.Date;
import java.util.List;

public interface AbsenceService {
    AbsenceVo create(AbsenceDto absenceDto, UserInfo userInfo);
    void delete(Long id);
    AbsenceVo update(AbsenceDto absenceDto);
    List<AbsenceVo> query();
    List<AbsenceVo> search(Date absenceTime, String majorName, String reason, Integer status, Integer key, Integer order);

    List<AbsenceVo> myAbsence(UserInfo userInfo);

    List<AbsenceVo> search(String absenceClazz);

    List<AbsenceVo> queryByMajorId(Long majorId);
}
