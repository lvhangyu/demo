package com.forezp.service.impl;

import com.forezp.mapper.DocumentMapper;
import com.forezp.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;

}
