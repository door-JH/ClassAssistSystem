package info.example.service;

import info.example.beans.ClassInfoBean;
import info.example.dao.TopMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopMenuService {

    @Autowired
    private TopMenuDAO topMenuDAO;
    public List<ClassInfoBean> getTopMenuList(){
        List<ClassInfoBean> topMenuList = topMenuDAO.getTopMenuList();

        return topMenuList;
    }
}
