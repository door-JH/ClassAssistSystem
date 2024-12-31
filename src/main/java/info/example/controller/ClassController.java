package info.example.controller;

import info.example.beans.AssistContentsBean;
import info.example.beans.ClassMenuBean;
import info.example.service.AssistContentsService;
import info.example.service.ClassMenuService;
import info.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        
        AssistContentsBean assistContentsBean = new AssistContentsBean();
        assistContentsBean.setAssist_contents_idx(0);
        assistContentsBean.setAssist_contents_info_idx(class_info_idx);
        assistContentsBean.setAssist_contents_title("테스트 컨텐츠 제목");
        assistContentsBean.setAssist_contents_text("테스트 컨텐츠 내용 입니다");
        assistContentsBean.setAssist_contents_writer_idx(1);
        assistcontentsservice.addAssistContentsInfo(assistContentsBean);
        
        List<AssistContentsBean> assistContentsList = assistcontentsservice.getAssistContentsList(class_info_idx);
        model.addAttribute("assistContentsList", assistContentsList);
        
        return "class/main";
    }
}