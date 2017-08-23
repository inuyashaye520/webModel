package com.cn.idataitech.inuyasha.model.session;

import org.springframework.stereotype.Service;

/**
 * Index page service
 */
@Service("indexPageService")
public class IndexPageService {

    private String index;

    public Object getIndex() {
        return "redirect:/admin/index.html";
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
