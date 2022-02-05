package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接收rname线路名称
        String rname = request.getParameter("rname");



        //处理参数
        int cid = 0;
        int currentPage = 0;//当前页码
        int pageSize = 0;//每页显示条数
        if(cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }
        //查询
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize,rname);
        //序列化
        writeValue(pb,response);

    }

    /**
     * 根据id查询一个线路的详情旅游信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");//设置请求的编码方式，必须在读取请求参数或者输入之前，否则无效；
        response.setContentType("text/html");//设置响应的内容类型，这个一定要设置否则，页面中文现实乱码！！！
        response.setCharacterEncoding("utf-8");//设置响应的编码方式，告诉浏览器我发送的内容编码类型为utf-8;

        //接收id
        String rid = request.getParameter("rid");
        //调用service查询route对象
        Route route = routeService.findOne(rid);
        //转为json写回客户端
        writeValue(route,response);
    }


    /**
     * 判断当前登录用户是否收藏该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");//设置请求的编码方式，必须在读取请求参数或者输入之前，否则无效；
        response.setContentType("text/html");//设置响应的内容类型，这个一定要设置否则，页面中文现实乱码！！！
        response.setCharacterEncoding("utf-8");//设置响应的编码方式，告诉浏览器我发送的内容编码类型为utf-8;


        //获取线路id
        String rid = request.getParameter("rid");
        //获取当前登录的用户user
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //用户尚未登录
            uid = 0;
        }else {
            //用户已经登录
            uid = user.getUid();
        }
        //调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        writeValue(flag,response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取rid
        String rid = request.getParameter("rid");
        //获取当前登录的用户user
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //用户尚未登录
            //uid = 0;
            return;
        }else {
            //用户已经登录
            uid = user.getUid();
        }
        //调用Service添加收藏
        favoriteService.add(rid,uid);
    }

    public void addFavorite111(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
