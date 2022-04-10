package com.forezp.controller;
import com.forezp.service.CommentService;
import com.forezp.service.DocumentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName FlightController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:15
 * Version 1.0
 */
@RestController
@RequestMapping("/flight")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private DocumentRestService documentRestService;

}
