package com.forezp.service.impl;
import java.util.Date;

import com.forezp.mapper.DocumentMapper;
import com.forezp.pojo.dao.DocumentDao;
import com.forezp.pojo.dto.DocumentDto;
import com.forezp.service.DocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public DocumentDao save(DocumentDto documentDto) {
        DocumentDao documentDao = new DocumentDao();
        BeanUtils.copyProperties(documentDto, documentDao);
        documentMapper.insert(documentDao);
        return documentDao;
    }

    @Override
    public void delete(Long id) {
        documentMapper.deleteById(id);
    }

    @Override
    public DocumentDao update(DocumentDto documentDto) {
        DocumentDao documentDao = new DocumentDao();
        documentDao.setMtime(new Date());
        BeanUtils.copyProperties(documentDto, documentDao);
        documentMapper.updateById(documentDao);
        return documentDao;
    }

    @Override
    public List<DocumentDao> list() {
        List<DocumentDao> documentDaoList = documentMapper.selectList(null);
        return documentDaoList;
    }
}
