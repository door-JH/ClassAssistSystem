package info.example.service;

import info.example.beans.AssistContentsBean;
import info.example.beans.AssistDataBean;
import info.example.dao.AssistContentsDAO;
import info.example.dao.AssistDataDAO;
import info.example.dao.ClassInfoDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    @Autowired
    private ClassInfoDAO classInfoDAO;
    
    @Autowired
    private AssistContentsDAO assistContentsDAO;
    
    @Autowired
    private AssistDataDAO  assistDataDAO;
    
    public String getClassInfoName(int class_info_idx){
        return classInfoDAO.getClassInfoName(class_info_idx);
    }
    
    public AssistContentsBean getAssistContentsInfo(int assist_contents_idx) {
    	return assistContentsDAO.getAssistContentsInfo(assist_contents_idx);
    }
    
    public List<AssistDataBean> getAssistDataList(int assist_contents_idx){
    	return assistDataDAO.getAssistDataList(assist_contents_idx);
    }
}
