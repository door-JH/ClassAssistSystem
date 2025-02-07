package info.example.service;

import java.util.List;

import info.example.beans.PageBean;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import info.example.beans.AssistContentsBean;
import info.example.beans.ClassMenuBean;
import info.example.dao.AssistContentsDAO;
import info.example.dao.ClassMenuDAO;

@Service
@PropertySource("/WEB-INF/properties/paging.properties")
public class AssistContentsService {
	
	@Autowired
	private AssistContentsDAO assistcontentsdao;

	@Value("${page.listcnt}")
	private int page_listcnt;

	@Value("${page.paginationcnt}")
	private int page_paginationcnt;

	public void addAssistContentsInfo(AssistContentsBean assistContentsBean) {
		assistcontentsdao.addAssistContentsInfo(assistContentsBean);
	}
	
	public List<AssistContentsBean> getAssistContentsList(int class_info_idx, int page){

		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);

		return assistcontentsdao.getAssistContentsList(class_info_idx, rowBounds);
	}

	public void deleteAssistContentsInfo(int assist_contents_idx){
		assistcontentsdao.deleteAssistContentsInfo(assist_contents_idx);
	}

	public PageBean getAssistContentsCnt(int class_info_idx, int currentPage){
		int assist_contents_cnt = assistcontentsdao.getAssistContentsCnt(class_info_idx);
		PageBean pageBean = new PageBean(assist_contents_cnt, currentPage, page_listcnt, page_paginationcnt);
		return pageBean;
	}



}
