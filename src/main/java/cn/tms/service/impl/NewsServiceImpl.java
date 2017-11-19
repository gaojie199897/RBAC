package cn.tms.service.impl;

import cn.tms.dao.INewsDAO;
import cn.tms.entity.News;
import cn.tms.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Gao on 2017/11/8.
 */
@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    INewsDAO dao;
    public List<News> selectList(Map<String ,Object> map) {
        return dao.selectList(map);
    }

    public int count(Map<String, Object> map) {
        return dao.count(map);
    }

    public int addNews(News news) {
        return dao.addNews(news);
    }

    public News selectdan(Integer id) {
        return dao.selectdan(id);
    }
}
