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
}
