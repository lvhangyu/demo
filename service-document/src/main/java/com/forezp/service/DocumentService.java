package com.forezp.service;

import com.forezp.pojo.dao.DocumentDao;
import com.forezp.pojo.dto.DocumentDto;

import java.util.List;

public interface DocumentService {

    DocumentDao save(DocumentDto documentDto);

    void delete(Long id);

    DocumentDao update(DocumentDto documentDto);

    List<DocumentDao> list();
}
