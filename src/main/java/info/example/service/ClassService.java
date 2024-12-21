package info.example.service;

import info.example.dao.ClassInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    @Autowired
    private ClassInfoDAO classInfoDAO;

    public String getClassInfoName(int class_info_idx){
        return classInfoDAO.getClassInfoName(class_info_idx);
    }
}
