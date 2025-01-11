package info.example.dao;

import info.example.beans.ClassInfoBean;
import info.example.mapper.ClassInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassInfoDAO {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    public String getClassInfoName(int class_info_idx){
        return classInfoMapper.getClassInfoName(class_info_idx);
    }
    
    public ClassInfoBean getClassInfoDetail(int class_info_idx) {
    	return classInfoMapper.getClassInfoDetail(class_info_idx);
    }
}
