package info.example.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import info.example.beans.AssistDataBean;
import info.example.dao.AssistDataDAO;

@Service
@PropertySource("/WEB-INF/properties/path.properties")
public class AssistDataService {
	
	@Autowired
	private AssistDataDAO assistDataDAO;
	private String column_name;
	
	
	public void addAssistDataInfo(AssistDataBean writeAssistDataBean, String column_name) {
		
		this.column_name = column_name;
		MultipartFile upload_file = writeAssistDataBean.getAssist_data_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploaadFile(upload_file);
			writeAssistDataBean.setAssist_data_filename(file_name);
		}
		
		assistDataDAO.addAssistDataInfo(writeAssistDataBean);
		
	}
	
	@Value("${path.class.data.win}")
	private String path_class_data;
	private String path_class_data_file_name;
	private String saveUploaadFile(MultipartFile upload_file) {
		
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String file_name = column_name + "_" + sDate.format(new Date()) +
							"_" + upload_file.getOriginalFilename();
		path_class_data_file_name = path_class_data + "/" + file_name;
		
		try {
			upload_file.transferTo(new File(path_class_data_file_name));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
	
	
	
}
