package info.example.controller;

import info.example.beans.*;
import info.example.service.AssistContentsService;
import info.example.service.AssistDataService;
import info.example.service.ClassMenuService;
import info.example.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/class")
public class ClassController {

	private static final Logger log = LoggerFactory.getLogger(ClassController.class);

	@Autowired
    private ClassService classService;

    @Autowired
    private ClassMenuService classMenuService;
    
    @Autowired
    private AssistContentsService assistcontentsservice;
    
    @Autowired
    private AssistDataService assistDataService;

	@Value("${path.class.data.win}")
	private String path_class_data;

    @GetMapping("/main")
    public String main(@RequestParam("class_info_idx") int class_info_idx,
                       @RequestParam("class_menu_idx") int class_menu_idx,
					   @RequestParam(value = "page", defaultValue = "1") int page, Model model) {

        model.addAttribute("class_info_idx", class_info_idx);

        model.addAttribute("class_menu_idx", class_menu_idx);

        String classInfoName = classService.getClassInfoName(class_info_idx);
        model.addAttribute("classInfoName", classInfoName);

        String classMenuName = classMenuService.getClassMenuName(class_menu_idx);
        model.addAttribute("classMenuName", classMenuName);

        List<ClassMenuBean> classMenuList = classMenuService.getClassMenuList();
        model.addAttribute("classMenuList", classMenuList);
        
        List<AssistContentsBean> assistContentsList = assistcontentsservice.getAssistContentsList(class_info_idx, page);
        model.addAttribute("assistContentsList", assistContentsList);

		PageBean pageBean = assistcontentsservice.getAssistContentsCnt(class_info_idx, page);
		model.addAttribute("pageBean", pageBean);

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
    		//writeAssistDataBean.setAssist_data_contents_idx(writeAssistContentsBean.getAssist_contents_idx());
    		
    		assistDataService.addAssistDataInfo(writeAssistDataBean, column_name);
    	}
    	
    	
    	return "board/write_success";
    }

	@GetMapping("/download")
	public void downlad(HttpServletRequest req, HttpServletResponse res,
						@RequestParam("class_info_idx") int class_info_idx,
						@RequestParam("class_menu_idx") int class_menu_idx,
						@RequestParam("assist_contents_idx") int assist_contents_idx,
						@RequestParam("assist_data_idx") int assist_data_idx, Model model) throws IOException {

		model.addAttribute("class_info_idx", class_info_idx);
		model.addAttribute("class_menu_idx", class_menu_idx);
		model.addAttribute("assist_contents_idx", assist_contents_idx);
		//model.addAttribute("loginUserBean", loginUserBean);

		AssistDataBean tempAssistDataBean = new AssistDataBean();
		tempAssistDataBean.setAssist_data_idx(assist_data_idx);
		tempAssistDataBean.setAssist_data_contents_idx(assist_contents_idx);

		AssistDataBean assistDataBean = assistDataService.addAssistDataInfo(tempAssistDataBean);

		String fileName = assistDataBean.getAssist_data_filename();
		log.info("Mapping - download {}",fileName);

		String pathClassData = path_class_data;
		log.info("Mapping - download {}", pathClassData);

		File f = new File(pathClassData + "/" + fileName);

		fileName = URLEncoder.encode(fileName, "UTF-8");

		res.setContentLength((int) f.length());
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		res.setHeader("Content-Transfer-Encoding", "binary");
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Expires", "0");

		FileInputStream in = new FileInputStream(f);
		OutputStream out = res.getOutputStream();
		byte[] buf = new byte[1024];
		while(true){
			int count = in.read(buf);
			if(count == -1){
				break;
			}
			out.write(buf, 0, count);
		}
		in.close();
		out.close();

	}

	@GetMapping("/modify")
	public String modify(@RequestParam("class_info_idx") int class_info_idx,
						 @RequestParam("class_menu_idx") int class_menu_idx,
						 @RequestParam("assist_contents_idx") int assist_contents_idx,
						 @ModelAttribute("modifyAssistContentsBean") AssistContentsBean modifyAssistContentsBean,
						 Model model) {

		model.addAttribute("class_info_idx", class_info_idx);
		model.addAttribute("class_menu_idx", class_menu_idx);
		model.addAttribute("assist_contents_idx", assist_contents_idx);

		String classInfoName = classService.getClassInfoName(class_info_idx);
		model.addAttribute("classInfoName", classInfoName);

		String classMenuName = classMenuService.getClassMenuName(class_menu_idx);
		model.addAttribute("classMenuName", classMenuName);

		AssistContentsBean tempAssistContentsBean = classService.getAssistContentsInfo(assist_contents_idx);
		modifyAssistContentsBean.setAssist_contents_writer_name(tempAssistContentsBean.getAssist_contents_writer_name());
		modifyAssistContentsBean.setAssist_contents_date(tempAssistContentsBean.getAssist_contents_date());
		modifyAssistContentsBean.setAssist_contents_title(tempAssistContentsBean.getAssist_contents_title());
		modifyAssistContentsBean.setAssist_contents_text(tempAssistContentsBean.getAssist_contents_text());
		modifyAssistContentsBean.setAssist_contents_writer_idx(tempAssistContentsBean.getAssist_contents_writer_idx());
		modifyAssistContentsBean.setAssist_contents_info_idx(tempAssistContentsBean.getAssist_contents_info_idx());
		modifyAssistContentsBean.setAssist_contents_idx(tempAssistContentsBean.getAssist_contents_idx());

		List<AssistDataBean> readAssistDataList = classService.getAssistDataList(class_info_idx);
		model.addAttribute("readAssistDataList", readAssistDataList);



		return "class/modify";
	}

	@PostMapping("/modify_post")
	public String modify_post(@Valid @ModelAttribute("modifyAssistContentsBean") AssistContentsBean modifyAssistContentsBean,
							  MultipartHttpServletRequest multipartRequest, HttpServletRequest req,
							  BindingResult result ,Model model) {

		if(result.hasErrors()) {
			return "class/modify";
		}

		log.info("modify_post - {}", req.toString());

		classService.modifyAssistContentsInfo(modifyAssistContentsBean);

		model.addAttribute("class_info_idx", req.getParameter("class_info_idx"));
		model.addAttribute("class_menu_idx", req.getParameter("class_menu_idx"));
		model.addAttribute("assist_contents_idx", modifyAssistContentsBean.getAssist_contents_idx());

		String classInfoName = classService.getClassInfoName(modifyAssistContentsBean.getAssist_contents_info_idx());
		model.addAttribute("classInfoName", classInfoName);

		String classMenuName = classMenuService.getClassMenuName(modifyAssistContentsBean.getAssist_contents_info_idx());
		model.addAttribute("classMenuName", classMenuName);

		ClassInfoBean classInfoBean = classService.getClassInfoDetail(Integer.parseInt(req.getParameter("class_info_idx")));

		String column_name = classInfoBean.getClass_info_year() + "-" + classInfoBean.getClass_info_semester() + "-"
				+ classInfoBean.getClass_info_idx() + "-" + classInfoBean.getClass_info_name();

		Iterator<String> it = multipartRequest.getFileNames();

		boolean clearAssistDataTable = false;

		while (it.hasNext()) {

			if(!clearAssistDataTable){
				//assistDataService.deleteAssistDataInfo(modifyAssistContentsBean.getAssist_contents_idx());
				//clearAssistDataTable = true;
			}

			String paramfName = it.next();

			MultipartFile mFile = multipartRequest.getFile(paramfName);
			String oName = mFile.getOriginalFilename();

			AssistDataBean writeAssistDataBean = new AssistDataBean();
			writeAssistDataBean.setAssist_data_filename(oName);
			writeAssistDataBean.setAssist_data_file(mFile);
			writeAssistDataBean.setAssist_data_contents_idx(modifyAssistContentsBean.getAssist_contents_idx());

			assistDataService.addAssistDataInfo(writeAssistDataBean, column_name);
			log.info("assistDatService: {}", assistDataService.toString());

		}

		return "board/modify_success";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("class_info_idx") int class_info_idx, @RequestParam("class_menu_idx") int class_menu_idx,
						 @RequestParam("assist_contents_idx") int assist_contents_idx, Model model) {

		classService.deleteAssistContentsInfo(assist_contents_idx);
		assistDataService.deleteAssistDataInfo(assist_contents_idx);

		model.addAttribute("class_info_idx", class_info_idx);
		model.addAttribute("class_menu_idx", class_menu_idx);

		return "board/delete_success";
	}

}