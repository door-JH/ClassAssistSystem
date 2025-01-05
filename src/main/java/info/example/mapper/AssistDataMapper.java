package info.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import info.example.beans.AssistDataBean;

public interface AssistDataMapper {
	
	@Select("Select "
			+ "assist_data_idx, assist_data_filename, assist_data_contents_idx, assist_data_date "
			+ "from assist_data_table "
			+ "where assist_data_contents_idx = #{assist_contents_idx} "
			+ "order by assist_data_idx ")
	List<AssistDataBean> getAssistDataList(int assist_contents_idx);
	
	
}
