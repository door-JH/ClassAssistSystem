package info.example.controller;

import info.example.beans.ClassMenuBean;
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

        return "class/main";
    }
}