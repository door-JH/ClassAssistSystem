package info.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import info.example.beans.AssistDataBean;
import info.example.mapper.AssistDataMapper;

@Repository
public class AssistDataDAO {
	
	@Autowired
	private AssistDataMapper assistDataMapper;
	
	public List<AssistDataBean> getAssistDataList(int assist_contents_idx){
		return assistDataMapper.getAssistDataList(assist_contents_idx);
	}
	
	public void addAssistDataInfo(AssistDataBean writeAssistDataBean) {
		assistDataMapper.addAssistDataInfo(writeAssistDataBean);
	}

	public AssistDataBean getAssistDataInfo(AssistDataBean assistDataBean) {
		return assistDataMapper.getAssistDataInfo(assistDataBean);
	}

	public void deleteAssistDataInfo(int assist_contents_idx){
		assistDataMapper.deleteAssistDataInfo(assist_contents_idx);
	}

	public int getAssistDataCount(int assist_contents_idx){
		return assistDataMapper.getAssistDataCount(assist_contents_idx);
	}

}
