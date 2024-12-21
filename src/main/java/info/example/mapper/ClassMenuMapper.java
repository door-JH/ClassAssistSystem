package info.example.mapper;

import info.example.beans.ClassMenuBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClassMenuMapper {
    @Select("select class_menu_name " +
            "from class_menu_table " +
            "where class_menu_idx = #{clasas_menu_idx}")
    String getClassMenuName(int class_menu_idx);

    @Select("select class_menu_idx, class_menu_name " +
            "from class_menu_table " +
            "order by class_menu_idx")
    List<ClassMenuBean> getClassMenuList();
}
