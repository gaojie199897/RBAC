package cn.tms.service.impl;

import cn.tms.dao.IChinamathDAO;
import cn.tms.entity.Chinamath;
import cn.tms.service.IChinamathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mr.Gao on 2017/11/6.
 */
@Service
public class ChinamathServiceImpl implements IChinamathService {
    @Resource(name = "IChinamathDAO")
    IChinamathDAO dao;
    public List<Chinamath> select(){
        List<Chinamath> chinamaths = dao.treeSelect();
        return chinamaths;
    }
}
