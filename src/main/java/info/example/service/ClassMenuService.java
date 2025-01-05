package info.example.service;

import info.example.beans.ClassMenuBean;
import info.example.dao.ClassMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassMenuService {

	@Autowired
	private ClassMenuDAO classMenuDAO;
	
	public String getClassMenuName(int class_menu_idx) {
		return classMenuDAO.getClassMenuName(class_menu_idx);
	}
	
	public List<ClassMenuBean> getClassMenuList(){
		return classMenuDAO.getClassMenuList();
	}
}
 