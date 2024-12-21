package info.example.dao;

import info.example.beans.ClassMenuBean;
import info.example.mapper.ClassInfoMapper;
import info.example.mapper.ClassMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassMenuDAO {

    @Autowired
    private ClassMenuMapper classMenuMapper;

    public String getClassMenuName(int class_menu_idx) {
        return classMenuMapper.getClassMenuName(class_menu_idx);
    }

    public List<ClassMenuBean> getClassMenuList(){
        return classMenuMapper.getClassMenuList();
    }
}
