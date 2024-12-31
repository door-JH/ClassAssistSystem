package info.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.example.beans.AssistContentsBean;
import info.example.beans.ClassMenuBean;
import info.example.dao.AssistContentsDAO;
import info.example.dao.ClassMenuDAO;

@Service
public class AssistContentsService {
	
	@Autowired
	private AssistContentsDAO assistcontentsdao;
	
	public void addAssistContentsInfo(AssistContentsBean assistContentsBean) {
		assistcontentsdao.addAssistContentsInfo(assistContentsBean);
	}
	
	public List<AssistContentsBean> getAssistContentsList(int class_info_idx){
		return assistcontentsdao.getAssistContentsList(class_info_idx);
	}
}
