package cn.itcast.cstm.web.Servlet;

import MyServlets.BaseServlet;
import cn.itcast.commons.CommonUtils;
import cn.itcast.cstm.Dao.CustomerException;
import cn.itcast.cstm.Domain.Customer;
import cn.itcast.cstm.Domain.PageBean;
import cn.itcast.cstm.Service.CustomerService;
import org.omg.CORBA.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * web层
 * */
@WebServlet(name = "CustomerServlet",urlPatterns = "/customerServlet")
public class CustomerServlet extends BaseServlet {
        private CustomerService customerService = new CustomerService();

        /*
        * 添加客户
        * */
        public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                /*
                * 1.封装表单数据到Customer对象中
                * 2.补全cid,使用uuid
                * 3.使用servlce方法完成添加工作
                * 4.向request域中保存信息
                * 5.转发到msg.jsp中
                * */
                System.out.println("**** addServlet1 ****");
                Customer customer = CommonUtils.toBean(req.getParameterMap(),Customer.class);
                customer.setCid(CommonUtils.uuid());
                //调用service方法
                try {
                        customerService.add(customer);
                        req.setAttribute("msg","添加用户成功！");
                        System.out.println("**** addServlet2 ****");
                        //转发
                        return "f:/msg.jsp";
                } catch (CustomerException e) {
                        req.setAttribute("msg","添加用户失败！");
                        //转发
                        return "f:/msg.jsp";
                }


        }
        /*
         * 查询所有客户
         * */
        public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CustomerException {
                /*
                 * 1. 调用service得到所有客户
                 * 2. 保存到request域中
                 * 3. 转发到list.jsp中
                 * */
                req.setAttribute("customerList",customerService.findAll());
                return "f:/list.jsp";
        }
    /*
     * 查询所有客户
     * */
    public String newFindAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CustomerException {
        /**
         * 1.  获取页面传递的PageNum;
         * 2. 给定PageSize的值
         * 3. 使用上述两个参数调用service中的方法，得到PageBean，保存到Request域中
         * 4. 转发到list.jsp中
         * */
        int pageNum = getPageNum(req);
        int pageSize = 10;
        PageBean<Customer> pageBean = customerService.newFindAll(pageNum,pageSize);
        req.setAttribute("pageBean",pageBean);
        return "f:/list.jsp";
    }
    /*
    * 获取PageNum
    * */
    public int getPageNum(HttpServletRequest req){
        String pageNum = req.getParameter("pageNum");
        if(pageNum == null || pageNum.trim().isEmpty()){
            return  1;
        }
        return Integer.parseInt(pageNum);

    }
    /*
     * 编辑之前的加载工作，把要编辑的客户信息加载到edit.jsp页面中
     * */
    public String preEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CustomerException {
        /*
         * 1. 得到cid
         * 2. 调用service方法得到customer对象
         * 3. 把 customer保存到request域中
         * 4. 转发到edit.jsp中
         * */
        String cid = req.getParameter("cid");
        req.setAttribute("customer",customerService.load(cid));
        return "f:/edit.jsp";
    }
    /*
    * 编辑客户信息
    * */
    public String edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * 1. 封装客户信息
         * 2. 调用service的edit方法
         * 3. 转发到msg.jsp中
         * */
        Customer customer = CommonUtils.toBean(req.getParameterMap(), Customer.class);
        try {
            customerService.edit(customer);
            req.setAttribute("msg","修改成功！");
            return "f:/msg.jsp";
        } catch (CustomerException e) {
            req.setAttribute("msg","修改失败！");
            return "f:/msg.jsp";
        }
    }
    /*
    * 删除客户
    * */
    public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String cid = req.getParameter("cid");
        try {
            customerService.delete(cid);
            req.setAttribute("msg","删除成功！");
            return "f:/msg.jsp";
        } catch (CustomerException e) {
            req.setAttribute("msg","删除失败！");
            return "f:/msg.jsp";
        }

    }
    /**
     * 高级搜索
     * */
    public String search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        /*
        * 1.封装查询条件
        * 2.调用service的search方法
        * 3.若成功，保存结果到request域中，转发到list.jsp中
        * 4.若失败，转发到msg.jsp中，并提示出错信息！
        * */
        Customer customer = CommonUtils.toBean(req.getParameterMap(),Customer.class);
        try {
            List<Customer> list = customerService.search(customer);
            req.setAttribute("customerList",list);
            return "f:/list.jsp";
        } catch (CustomerException e) {
            req.setAttribute("msg","查询失败！");
            return "f:/msg.jsp";
        }
    }
}
