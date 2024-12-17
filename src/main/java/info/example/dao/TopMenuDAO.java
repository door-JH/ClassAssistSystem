package info.example.dao;

import info.example.beans.ClassInfoBean;
import info.example.mapper.TopMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopMenuDAO {

    @Autowired
    private TopMenuMapper topMenuMapper;

    public List<ClassInfoBean> getTopMenuList() {

        List<ClassInfoBean> topMenuList = topMenuMapper.getTopMenuList();

        return topMenuList;
    }


}
