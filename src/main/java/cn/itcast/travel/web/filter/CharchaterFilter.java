package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决全站乱码问题，处理所有的请求
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //获取请求方法
        String method = request.getMethod();
        //处理响应乱码
        //response.setContentType("text/html;charset=utf-8");
        //设置编码
        request.setCharacterEncoding("utf-8");//设置请求的编码方式，必须在读取请求参数或者输入之前，否则无效；
        response.setContentType("text/html");//设置响应的内容类型，这个一定要设置否则，页面中文现实乱码！！！
        response.setCharacterEncoding("utf-8");//设置响应的编码方式，告诉浏览器我发送的内容编码类型为utf-8;
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
