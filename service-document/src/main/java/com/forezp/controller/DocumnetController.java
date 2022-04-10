package com.forezp.controller;

import com.forezp.service.CommentRestService;
import com.forezp.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 18:40
 * Version 1.0
 */
@RestController
public class DocumnetController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private CommentRestService commentRestService;



}
