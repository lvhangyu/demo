package com.forezp.service.impl;
import java.util.Date;

import com.forezp.mapper.CollectionMapper;
import com.forezp.mapper.DocumentMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.CollectionDao;
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

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public DocumentDao save(DocumentDto documentDto) {
        DocumentDao documentDao = new DocumentDao();
        BeanUtils.copyProperties(documentDto, documentDao);
        documentDao.setCollections(0);
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

    @Override
    public void collect(Long documentId, UserInfo userInfo) {
        documentMapper.collect(documentId);
        CollectionDao collectionDao = new CollectionDao();
        collectionDao.setNoteId(documentId);
        collectionDao.setUserId(userInfo.getId());
        collectionDao.setType(1);
        collectionDao.setCtime(new Date());
        collectionDao.setMtime(new Date());
        collectionMapper.insert(collectionDao);
    }

    @Override
    public void cancelCollect(Long documentId, UserInfo userInfo) {
        documentMapper.cancelCollect(documentId);
        CollectionDao collectionDao = new CollectionDao();
        collectionDao.setNoteId(documentId);
        collectionDao.setUserId(userInfo.getId());
        collectionDao.setType(1);
        collectionMapper.deleteById(collectionDao);
    }
}
