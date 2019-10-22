package cn.itcast.cstm.Dao;

import cn.itcast.cstm.Domain.Customer;
import jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
            throw new CustomerException("修改客户信息出错！");
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
}
