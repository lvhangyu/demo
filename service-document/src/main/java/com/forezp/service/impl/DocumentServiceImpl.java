package com.forezp.service.impl;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.mapper.CollectionMapper;
import com.forezp.mapper.DocumentMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.CollectionDao;
import com.forezp.pojo.dao.DocumentDao;
import com.forezp.pojo.dto.DocumentDto;
import com.forezp.pojo.vo.DocumentVo;
import com.forezp.pojo.vo.PostVo;
import com.forezp.service.DocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Map map = new HashMap();
        map.put("note_id", documentId);
        map.put("user_id", userInfo.getId());
        map.put("type", 1);
        collectionMapper.deleteByMap(map);
    }

    @Override
    public DocumentVo info(UserInfo userInfo, Long documentId) {
        DocumentDao documentDao = documentMapper.selectById(documentId);
        CollectionDao collectionDao = new CollectionDao();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("note_id", documentDao.getId());
        queryWrapper.eq("type", 1);
        queryWrapper.eq("user_id", userInfo.getId());
        boolean collected = collectionMapper.exists(queryWrapper);
        DocumentVo documentVo = new DocumentVo();
        BeanUtils.copyProperties(documentDao, documentVo);
        documentVo.setCollected(collected);
        return documentVo;
    }

    @Override
    public List<DocumentDao> collected(UserInfo userInfo) {
        List<DocumentDao> documentDaoList = documentMapper.selectList(null);
        List<DocumentDao> documentDaoCollctedList = new ArrayList<>(16);
        QueryWrapper queryWrapper = new QueryWrapper();
        for (DocumentDao documentDao:documentDaoList) {
            queryWrapper.eq("note_id", documentDao.getId());
            queryWrapper.eq("type", 1);
            queryWrapper.eq("user_id", userInfo.getId());
            boolean collected = collectionMapper.exists(queryWrapper);
            if (collected){
                documentDaoCollctedList.add(documentDao);
            }
            queryWrapper.clear();
        }
        return documentDaoCollctedList;
    }
}
