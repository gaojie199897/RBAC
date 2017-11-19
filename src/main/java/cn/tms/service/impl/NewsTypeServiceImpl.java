package cn.tms.service.impl;

import cn.tms.dao.INewsTypeDAO;
import cn.tms.entity.NewsType;
import cn.tms.service.INewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Gao on 2017/11/8.
 */
@Service
public class NewsTypeServiceImpl implements INewsType {
    @Autowired
    INewsTypeDAO dao;
    public List<NewsType> combobox() {
        return dao.combobox();
    }
}
