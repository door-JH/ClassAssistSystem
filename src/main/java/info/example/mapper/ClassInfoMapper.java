package info.example.mapper;

import org.apache.ibatis.annotations.Select;

public interface ClassInfoMapper {
    @Select("select class_info_name " +
            "from class_info_table " +
            "where class_info_idx = #{class_info_idx}")
    String getClassInfoName(int class_info_idx);
}
