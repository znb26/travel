package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");//设置请求的编码方式，必须在读取请求参数或者输入之前，否则无效；
        response.setContentType("text/html");//设置响应的内容类型，这个一定要设置否则，页面中文现实乱码！！！
        response.setCharacterEncoding("utf-8");//设置响应的编码方式，告诉浏览器我发送的内容编码类型为utf-8;


        //调用service查询所有
        List<Category> cs = service.findAll();
        //序列化json返回
        /*ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),cs);*/
        writeValue(cs,response);

    }


    public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
