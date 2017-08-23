package com.cn.idataitech.inuyasha.model.page;

import com.cn.idataitech.inuyasha.model.session.IndexPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index page
 */
@Controller
public class IndexPage {

    @Autowired
    @Qualifier("indexPageService")
    private IndexPageService indexPageService;

    @RequestMapping(value = "/**/*.html")
    public void html() {
    }

    @RequestMapping(value = {"/", "/index.html"})
    public Object index() {
        return indexPageService.getIndex();
    }

}
