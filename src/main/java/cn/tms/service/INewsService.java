package cn.tms.service;

import cn.tms.entity.News;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Gao on 2017/11/8.
 */
public interface INewsService {
    public List<News> selectList(Map<String ,Object> map);
    public int count(Map<String ,Object> map);
    public int addNews(News news);
    public News selectdan(Integer id);
}
