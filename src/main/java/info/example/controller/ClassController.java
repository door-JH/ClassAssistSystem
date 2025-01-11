package info.example.controller;

import info.example.beans.AssistContentsBean;
import info.example.beans.AssistDataBean;
import info.example.beans.ClassInfoBean;
import info.example.beans.ClassMenuBean;
import info.example.beans.StudentBean;
import info.example.service.AssistContentsService;
import info.example.service.ClassMenuService;
import info.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    
    @PostMapping("/write_post")
    public String write_post(@Valid @ModelAttribute("writeAssistContentsBean") AssistContentsBean writeAssistContentsBean,
    							MultipartHttpServletRequest multipartRequest, HttpServletRequest request,
    							@RequestParam("class_menu_idx") int class_menu_idx, BindingResult result, Model model) {
    	
    	if(result.hasErrors()) {
    		return "class/write";
    	}
    	
    	String classInfoName = classService.getClassInfoName(writeAssistContentsBean.getAssist_contents_idx());
    	model.addAttribute("classInfoName", classInfoName);
    	
    	String classMenuName = classMenuService.getClassMenuName(class_menu_idx);
    	model.addAttribute("classMenuName", classMenuName);
    	
    	assistcontentsservice.addAssistContentsInfo(writeAssistContentsBean);
    	
    	model.addAttribute("class_info_idx", writeAssistContentsBean.getAssist_contents_info_idx());
    	model.addAttribute("class_menu_idx", class_menu_idx);
    	model.addAttribute("assist_contents_idx", writeAssistContentsBean.getAssist_contents_idx());
    	
    	
    	ClassInfoBean classInfoDetail = 
    			classService.getClassInfoDetail(writeAssistContentsBean.getAssist_contents_info_idx());
    				
    	String column_name = classInfoDetail.getClass_info_year() + "-" +
    						classInfoDetail.getClass_info_semester() + "-" +
    						classInfoDetail.getClass_info_idx() + "-" +
    						classInfoDetail.getClass_info_name();
    	
    	Iterator<String> it = multipartRequest.getFileNames();
    	
    	while(it.hasNext()) {
    		
    		String partfName = it.next();
    		MultipartFile mFile = multipartRequest.getFile(partfName);
    		String oName = mFile.getOriginalFilename();
    		
    		AssistDataBean writeAssistDataBean = new AssistDataBean();
    		writeAssistDataBean.setAssist_data_filename(oName);
    		writeAssistDataBean.setAssist_data_file(mFile);
    		writeAssistDataBean.setAssist_data_contents_idx(writeAssistContentsBean.getAssist_contents_idx());
    		
    		//assistDataSerivce.addAssistdataInfo(writeAssistDataBean, column_name);
    	}
    	
    	
    	return "class/write_success";
    }
    
}