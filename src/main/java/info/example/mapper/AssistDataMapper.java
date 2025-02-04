package info.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import info.example.beans.AssistDataBean;

public interface AssistDataMapper {
	
	@Select("Select "
			+ "assist_data_idx, assist_data_filename, assist_data_contents_idx, assist_data_date "
			+ "from assist_data_table "
			+ "where assist_data_contents_idx = #{assist_contents_idx} "
			+ "order by assist_data_idx ")
	List<AssistDataBean> getAssistDataList(int assist_contents_idx);
	
	@Insert("Insert into assist_data_table " +
			"(assist_data_filename, assist_data_contents_idx, assist_data_date) " +
			"values " + "(#{assist_data_filename}," +
			"(SELECT COUNT(*) from assist_contents_table), " +
			"DATE_FORMAT(SYSDATE(), '%Y-%m-%d %H:%i:%s'))")
	void addAssistDataInfo(AssistDataBean writeAssistDataBean);


	@Select("Select assist_data_idx, assist_data_filename, assist_data_contents_idx, assist_data_date " +
			"from assist_data_table " +
			"where assist_data_idx = #{assist_data_idx} " +
			"and assist_data_contents_idx = #{assist_data_contents_idx}")
	AssistDataBean getAssistDataInfo(AssistDataBean assistDataBean);

	@Delete("Delete from assist_data_table " +
			"where assist_data_contents_idx = #{assist_contents_idx}")
	void deleteAssistDataInfo(int assist_contents_idx);

	@Select("Select count(*) " +
			"from assist_data_table " +
			"where assist_data_contents_idx = #{assist_contents_idx}")
	int getAssistDataCount(int assist_contents_idx);

}
