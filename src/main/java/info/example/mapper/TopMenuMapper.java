package info.example.mapper;

import info.example.beans.ClassInfoBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TopMenuMapper {
    @Select(
            "select CLASS_INFO_IDX, CLASS_INFO_NAME from CLASS_INFO_TABLE order by CLASS_INFO_IDX"
    )
    List<ClassInfoBean> getTopMenuList();
}
