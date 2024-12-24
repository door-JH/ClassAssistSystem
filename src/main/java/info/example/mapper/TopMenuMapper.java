package info.example.mapper;

import info.example.beans.ClassInfoBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TopMenuMapper {
    @Select(
            "select class_info_idx, class_info_name from class_info_table order by class_info_idx"
    )
    List<ClassInfoBean> getTopMenuList();
}
