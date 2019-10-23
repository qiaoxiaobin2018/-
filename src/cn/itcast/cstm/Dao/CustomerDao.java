package cn.itcast.cstm.Dao;

import cn.itcast.cstm.Domain.Customer;
import cn.itcast.cstm.Domain.PageBean;
import jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 持久层
 * */
public class CustomerDao {
    //DBUtils
    private TxQueryRunner txQueryRunner = new TxQueryRunner();
    /*
    * 添加客户
    * */
    public void add(Customer customer) throws CustomerException {
        String  sql = "INSERT INTO customer VALUES(?,?,?,?,?,?,?)";
        Object[] params = {customer.getCid(),customer.getCname(),customer.getGender(),customer.getBirthday(),
                customer.getCellphone(),customer.getEmail(),customer.getDescription()};
        //执行
        try {
            txQueryRunner.update(sql,params);
        } catch (Exception e) {
            throw new CustomerException("添加用户出错！");
        }

    }
    /*
     * 查询所有
     * */
    public List<Customer> findAll() throws CustomerException {
        String  sql = "SELECT * FROM customer";
        //执行
        try {
            List<Customer> list =   txQueryRunner.query(sql,new BeanListHandler<Customer>(Customer.class));
            System.out.println("**查询所有**");
            System.out.println(list.get(0));
            return list;
        } catch (Exception e) {
            throw new CustomerException("查询用户出错！");
        }

    }
    /*
    * 返回要编辑的客户信息
    * */
    public Customer load(String cid) throws CustomerException {
        String sql = "SELECT * FROM customer WHERE cid=?";
        Object[] params = {cid};
        try {
            return txQueryRunner.query(sql, new BeanHandler<Customer>(Customer.class),params);
        } catch (SQLException e) {
            throw new CustomerException("查询客户信息出错！");
        }
    }
    /*
     * 编辑客户信息
     * */
    public void edit(Customer customer) throws CustomerException {
        String sql = "UPDATE customer SET cname=?,gender=?,birthday=?,cellphone=?,email=?,description=? WHERE cid=?";
        Object[] params = {customer.getCname(),customer.getGender(),customer.getBirthday(),customer.getCellphone(),customer.getEmail(),customer.getDescription(),customer.getCid()};
        try {
            txQueryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new CustomerException(e.getMessage());
        }
    }
    /*
     * 删除客户
     * */
    public void delete(String cid) throws CustomerException {
        String sql = "DELETE FROM customer WHERE cid=?";
        Object[] params = {cid};
        try {
            txQueryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new CustomerException("删除客户出错！");
        }
    }
    /*
    * 高级搜索
    * */
    public List<Customer> search(Customer customer) throws CustomerException {
        /*
        * 给出sql模板
        * */
        StringBuilder sql = new StringBuilder("SELECT * FROM customer WHERE 1=1 ");
        /*
        * 逐个判断条件，若条件不为空，则将条件添加到sql中
        * */
        List<Object> list = new ArrayList<Object>();
        String cname = customer.getCname();
        if(!(cname == null || cname.trim().isEmpty())){
            sql.append("and cname like ?");
            list.add("%"+cname+"%");
        }
        String gender = customer.getGender();
        if(!(gender == null || gender.trim().isEmpty())){
            sql.append("and gender=?");
            list.add(gender);
        }
        String cellphone = customer.getCellphone();
        if(!(cellphone == null || cellphone.trim().isEmpty())){
            sql.append("and cellphone like ?");
            list.add("%"+cellphone+"%");
        }
        String email = customer.getEmail();
        if(!(email == null || email.trim().isEmpty())){
            sql.append("and email like ?");
            list.add("%"+email+"%");
        }

        try {
            System.out.println("**准备查询**");
            System.out.println(sql.toString());
            return txQueryRunner.query(sql.toString(), new BeanListHandler<Customer>(Customer.class),list.toArray());
        } catch (SQLException e) {
            throw new CustomerException("查询用户出错！");
        }
    }
    /*
    * 实现分页的查询所有
    * */
    public PageBean<Customer> newFindAll(int pageNum, int pageSize) throws CustomerException {
        /*
        * 1.创建PageBean对象pb
        * 2.设置PageNum和PageSize
        * 3.得到totalRecord，设置到pb中
        * 4.得到BeanList,设置到pb中
        * 5.返回pb
        * */
        PageBean<Customer> pb = new PageBean<Customer>();
        pb.setPageNum(pageNum);
        pb.setPageSize(pageSize);
        /*
        * 得到totalRecord
        * */
        String sql = "SELECT COUNT(*) FROM customer";
        try {
//            Number number = (Number)txQueryRunner.query(sql,new ScalarHandler());
            Number number = txQueryRunner.query(sql,new ScalarHandler<Number>());
            int totalRecord = number.intValue();
            pb.setTotalRecords(totalRecord);
        } catch (SQLException e) {
            throw new CustomerException("获取totalRecord出错！");
        }
        /*
        * 得到BeanList
        * */
        String sqll = "SELECT * FROM customer ORDER BY cname LIMIT ?,?";
        try {
            List<Customer> beanList = txQueryRunner.query(sqll, new BeanListHandler<Customer>(Customer.class), (pageNum - 1) * pageSize, pageSize);
            pb.setBeanList(beanList);
        } catch (SQLException e) {
            throw new CustomerException("获取beanList出错！");
        }
        /*
        * 返回PB
        * */
        return pb;

    }

    public PageBean<Customer> search(Customer customer, int pageNum, int pageSize) throws CustomerException {
        /*
        * 1.创建pageBean对象
        * 2.设置pageNum,pageSize
        * 3.得到totalRecord
        * 4.得到beanList
        * 5.设置totalRecord和beanList到pageBean中
        * 6.返回pageBean
        * */
        PageBean<Customer> pageBean = new PageBean<>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        /*
        * 得到totalRecord
        * */
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM customer WHERE 1=1 ");
        StringBuilder sqll = new StringBuilder("SELECT * FROM customer WHERE 1=1 ");
        /*
         * 逐个判断条件，若条件不为空，则将条件添加到sql中
         * */
        List<Object> list = new ArrayList<Object>();
        String cname = customer.getCname();
        if(!(cname == null || cname.trim().isEmpty())){
            sql.append("and cname like ?");
            sqll.append("and cname like ?");
            list.add("%"+cname+"%");
        }
        String gender = customer.getGender();
        if(!(gender == null || gender.trim().isEmpty())){
            sql.append("and gender=?");
            sqll.append("and gender=?");
            list.add(gender);
        }
        String cellphone = customer.getCellphone();
        if(!(cellphone == null || cellphone.trim().isEmpty())){
            sql.append("and cellphone like ?");
            sqll.append("and cellphone like ?");
            list.add("%"+cellphone+"%");
        }
        String email = customer.getEmail();
        if(!(email == null || email.trim().isEmpty())){
            sql.append("and email like ?");
            sqll.append("and email like ?");
            list.add("%"+email+"%");
        }

        int totalRecord = 0;
        try {
            System.out.println("**准备查询**");
            Number number =  txQueryRunner.query(sql.toString(), new ScalarHandler<Number>(),list.toArray());
            totalRecord = number.intValue();
            pageBean.setTotalRecords(totalRecord);
        } catch (SQLException e) {
            throw new CustomerException("得到totalRecord出错！");
        }
        /*
        * 得到beanList
        * */
        sqll.append(" LIMIT ?,? ");
        list.add((pageNum - 1)*pageSize);
        list.add(pageSize);
        try {
            List<Customer> customerList = txQueryRunner.query(sqll.toString(), new BeanListHandler<Customer>(Customer.class), list.toArray());
            pageBean.setBeanList(customerList);
            return pageBean;
        } catch (SQLException e) {
            throw new CustomerException("得到beanList出错！");
        }

    }
}
