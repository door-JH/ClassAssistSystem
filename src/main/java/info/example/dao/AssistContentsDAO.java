package info.example.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import info.example.beans.AssistContentsBean;
import info.example.mapper.AssistContentsMapper;

@Repository
public class AssistContentsDAO {
	
	@Autowired
	private AssistContentsMapper assistContentsMapper;
	
	public void addAssistContentsInfo(AssistContentsBean writeAssistContentsBean) {
		
		//for(int i=0; i < 100; i++) {
			assistContentsMapper.addAssistContentsInfo(writeAssistContentsBean);
		//}
	}
	
	public List<AssistContentsBean> getAssistContentsList(int class_info_idx, RowBounds rowBounds) {
		return assistContentsMapper.getAssistContentsList(class_info_idx, rowBounds);
	}
	
	public AssistContentsBean getAssistContentsInfo(int assist_contents_idx) {
		return assistContentsMapper.getAssistContentsInfo(assist_contents_idx);
	}

	public void modifyAssistContentsInfo(AssistContentsBean modifyAssistContentsBean) {
		assistContentsMapper.modifyAssistContentsInfo(modifyAssistContentsBean);
	}

	public void deleteAssistContentsInfo(int assist_contents_idx) {
		assistContentsMapper.deleteAssistContentsInfo(assist_contents_idx);
	}
}
