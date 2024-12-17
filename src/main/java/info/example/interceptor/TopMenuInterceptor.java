package info.example.interceptor;

import info.example.beans.ClassInfoBean;
import info.example.service.TopMenuService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TopMenuInterceptor implements HandlerInterceptor {

    public TopMenuService topMenuService;

    public TopMenuInterceptor(TopMenuService topMenuService) {
        this.topMenuService = topMenuService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        List<ClassInfoBean> topMenuList = topMenuService.getTopMenuList();
        request.setAttribute("topMenuList", topMenuList);
        return true;
    }
}
