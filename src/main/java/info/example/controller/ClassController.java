package info.example.controller;

import info.example.beans.AssistContentsBean;
import info.example.beans.AssistDataBean;
import info.example.beans.ClassMenuBean;
import info.example.beans.StudentBean;
import info.example.service.AssistContentsService;
import info.example.service.ClassMenuService;
import info.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private ClassMenuService classMenuService;
    
    @Autowired
    private AssistContentsService assistcontentsservice;
    
    @Autowired
    private StudentBean loginUserBean;
    
    @GetMapping("/main")
    public String main(@RequestParam("class_info_idx") int class_info_idx,
                       @RequestParam("class_menu_idx") int class_menu_idx, Model model) {

        model.addAttribute("class_info_idx", class_info_idx);

        model.addAttribute("class_menu_idx", class_menu_idx);

        String classInfoName = classService.getClassInfoName(class_info_idx);
        model.addAttribute("classInfoName", classInfoName);

        String classMenuName = classMenuService.getClassMenuName(class_menu_idx);
        model.addAttribute("classMenuName", classMenuName);

        List<ClassMenuBean> classMenuList = classMenuService.getClassMenuList();
        model.addAttribute("classMenuList", classMenuList);
        
        List<AssistContentsBean> assistContentsList = assistcontentsservice.getAssistContentsList(class_info_idx);
        model.addAttribute("assistContentsList", assistContentsList);
        
        return "class/main";
    }
    
    @GetMapping("/read")
    public String read(
    					@RequestParam("class_info_idx") int class_info_idx,
    					@RequestParam("class_menu_idx") int class_menu_idx,
    					@RequestParam("assist_contents_idx") int assist_contents_idx,
    					Model model) {
    	
    	model.addAttribute("class_info_idx", class_info_idx);
    	model.addAttribute("class_menu_idx", class_menu_idx);
    	model.addAttribute("assist_contents_idx", assist_contents_idx);
    	//model.addAttribute("loginUserBean", loginUserBean);
    	
    	String classInfoName = classService.getClassInfoName(class_info_idx);
    	model.addAttribute("classInfoName", classInfoName);
    	
    	String classMenuName = classMenuService.getClassMenuName(class_menu_idx);
    	model.addAttribute("classMenuName", classMenuName);
    	
    	AssistContentsBean readAssistContentsBean = classService.getAssistContentsInfo(assist_contents_idx);
    	model.addAttribute("readAssistContentsBean", readAssistContentsBean);
    	
    	List<AssistDataBean> readAssistDataList = classService.getAssistDataList(assist_contents_idx);
    	model.addAttribute("readAssistDataList", readAssistDataList);
    	
    		
    	return "class/read";
    }
    
    @GetMapping("/write")
    public String write(@ModelAttribute("writeAssistContentsBean") AssistContentsBean writeAssistContentsBean,
    					@RequestParam("class_info_idx") int class_info_idx,
    					@RequestParam("class_menu_idx") int class_menu_idx,
    					Model model) {
    	
    	writeAssistContentsBean.setAssist_contents_info_idx(class_info_idx);
    	model.addAttribute("class_info_idx", class_info_idx);
    	model.addAttribute("class_menu_idx", class_menu_idx);
    	
    	String classInfoName = classService.getClassInfoName(class_info_idx);
    	model.addAttribute("classInfoName", classInfoName);
    	
    	String classMenuName = classMenuService.getClassMenuName(class_menu_idx);
    	model.addAttribute("classMenuName", classMenuName);
    	
    	
    	
    	return "class/write";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}