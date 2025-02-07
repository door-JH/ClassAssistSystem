package info.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import info.example.beans.AssistContentsBean;
import info.example.beans.AssistDataBean;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

public interface AssistContentsMapper {
	
	@Insert("insert into assist_contents_table "
			+ "(assist_contents_title, assist_contents_text, "
			+ "assist_contents_writer_idx, assist_contents_info_idx, assist_contents_date) "
			+ "values "
			+ "(#{assist_contents_title}, #{assist_contents_text}, "
			+ "#{assist_contents_writer_idx}, #{assist_contents_info_idx}, "
			+ "DATE_FORMAT(SYSDATE(), '%Y-%m-%d %H:%i:%s'))")
	void addAssistContentsInfo(AssistContentsBean writeAssistContentsBean);
	
	@Select("select "
			+ "a1.assist_contents_idx, a1.assist_contents_title, "
			+ "a2.student_name as assist_contents_writer_name, a1.assist_contents_date "
			+ "from assist_contents_table a1, student_table a2 "
			+ "where a1.assist_contents_writer_idx = a2.student_idx "
			+ "and a1.assist_contents_info_idx = #{class_info_idx} "
			+ "order by a1.assist_contents_idx desc")
	List<AssistContentsBean> getAssistContentsList(int class_info_idx, RowBounds rowBounds);
	
	@Select("Select "
			+ "a2.student_name as assist_contents_writer_name, a1.assist_contents_date, "
			+ "a1.assist_contents_title, a1.assist_contents_text, a1.assist_contents_writer_idx "
			+ "from assist_contents_table a1, student_table a2 "
			+ "where a1.assist_contents_writer_idx = a2.student_idx and "
			+ "assist_contents_idx = #{assist_contents_idx}")
	AssistContentsBean getAssistContentsInfo(int assist_contents_idx);

	@Update("update assist_contents_table "
			+ "set assist_contents_title = #{assist_contents_title}, assist_contents_text = #{assist_contents_text} "
			+ "where assist_contents_idx = #{assist_contents_idx}")
	void modifyAssistContentsInfo(AssistContentsBean modifyAssistContentsBean);

	@Delete("Delete from assist_contents_table " +
			"where assist_contents_idx = #{assist_contents_idx}")
	void deleteAssistContentsInfo(int assist_contents_idx);

	@Select("select count(*) " +
			"from assist_contents_table " +
			"where assist_contents_idx = #{assist_contents_idx}")
	int getAssistContentsCnt(int assist_contents_idx);

}
