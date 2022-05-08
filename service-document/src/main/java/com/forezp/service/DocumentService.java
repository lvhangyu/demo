package com.forezp.service;

import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.DocumentDao;
import com.forezp.pojo.dto.DocumentDto;
import com.forezp.pojo.vo.DocumentVo;
import com.forezp.pojo.vo.PostVo;

import java.util.List;

public interface DocumentService {

    DocumentDao save(DocumentDto documentDto);

    void delete(Long id);

    DocumentDao update(DocumentDto documentDto);

    List<DocumentDao> list();

    void collect(Long documentId, UserInfo userInfo);

    void cancelCollect(Long documentId, UserInfo userInfo);

    DocumentVo info(UserInfo userInfo, Long documentId);

    List<DocumentDao> collected(UserInfo userInfo);
}
