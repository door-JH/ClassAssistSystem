package info.example.mapper;

import org.apache.ibatis.annotations.Select;

import info.example.beans.ClassInfoBean;

public interface ClassInfoMapper {
    @Select("select class_info_name " +
            "from class_info_table " +
            "where class_info_idx = #{class_info_idx}")
    String getClassInfoName(int class_info_idx);
    
    @Select("select class_info_year, class_info_semester, class_info_idx, class_info_name " +
    		"from class_info_table " +
    		"where class_info_idx = #{class_info_idx}")
    ClassInfoBean getClassInfoDetail(int class_info_idx);
}
