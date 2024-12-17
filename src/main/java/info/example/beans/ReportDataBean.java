package info.example.beans;

import org.springframework.web.multipart.MultipartFile;

public class ReportDataBean {

	private int report_data_idx;
	private MultipartFile report_data_file;
	private String report_data_filename;
	private int report_data_content_idx;
	private String report_data_date;
	
	public int getReport_data_idx() {
		return report_data_idx;
	}
	public void setReport_data_idx(int report_data_idx) {
		this.report_data_idx = report_data_idx;
	}
	public MultipartFile getReport_data_file() {
		return report_data_file;
	}
	public void setReport_data_file(MultipartFile report_data_file) {
		this.report_data_file = report_data_file;
	}
	public String getReport_data_filename() {
		return report_data_filename;
	}
	public void setReport_data_filename(String report_data_filename) {
		this.report_data_filename = report_data_filename;
	}
	public int getReport_data_content_idx() {
		return report_data_content_idx;
	}
	public void setReport_data_content_idx(int report_data_content_idx) {
		this.report_data_content_idx = report_data_content_idx;
	}
	public String getReport_data_date() {
		return report_data_date;
	}
	public void setReport_data_date(String report_data_date) {
		this.report_data_date = report_data_date;
	}

}
