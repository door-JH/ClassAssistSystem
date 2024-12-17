package info.example.beans;

import org.springframework.web.multipart.MultipartFile;

public class SubjectDataBean {

	private int subject_data_idx;
	private MultipartFile subject_data_file;
	private String subject_data_filename;
	private int subject_data_contents_idx;
	private String subject_data_date;

	public int getClass_upload_idx() {
		return subject_data_idx;
	}

	public void setClass_upload_idx(int subject_data_idx) {
		this.subject_data_idx = subject_data_idx;
	}

	public MultipartFile getClass_upload_file() {
		return subject_data_file;
	}

	public void setClass_upload_file(MultipartFile subject_data_file) {
		this.subject_data_file = subject_data_file;
	}

	public String getClass_upload_filename() {
		return subject_data_filename;
	}

	public void setClass_upload_filename(String subject_data_filename) {
		this.subject_data_filename = subject_data_filename;
	}

	public int getClass_upload_contents_idx() {
		return subject_data_contents_idx;
	}

	public void setClass_upload_contents_idx(int subject_data_contents_idx) {
		this.subject_data_contents_idx = subject_data_contents_idx;
	}

	public String getClass_upload_date() {
		return subject_data_date;
	}

	public void setClass_upload_date(String subject_data_date) {
		this.subject_data_date = subject_data_date;
	}

}
