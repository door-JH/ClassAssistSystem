package info.example.beans;

import org.springframework.web.multipart.MultipartFile;

public class AssistDataBean {

	private int assist_data_idx;

	private String Assist_data_filename;
	
	private MultipartFile Assist_data_file;
	
	private int Assist_data_contents_idx;

	public String getAssist_data_filename() {
		return Assist_data_filename;
	}

	public void setAssist_data_filename(String assist_data_filename) {
		Assist_data_filename = assist_data_filename;
	}

	public MultipartFile getAssist_data_file() {
		return Assist_data_file;
	}

	public void setAssist_data_file(MultipartFile assist_data_file) {
		Assist_data_file = assist_data_file;
	}

	public int getAssist_data_contents_idx() {
		return Assist_data_contents_idx;
	}

	public void setAssist_data_contents_idx(int assist_data_contents_idx) {
		Assist_data_contents_idx = assist_data_contents_idx;
	}

	public int getAssist_data_idx() {
		return assist_data_idx;
	}

	public void setAssist_data_idx(int assist_data_idx) {
		this.assist_data_idx = assist_data_idx;
	}
}
