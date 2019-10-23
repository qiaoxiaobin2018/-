package cn.itcast.cstm.Service;

import cn.itcast.cstm.Dao.CustomerDao;
import cn.itcast.cstm.Dao.CustomerException;
import cn.itcast.cstm.Domain.Customer;
import cn.itcast.cstm.Domain.PageBean;

import java.util.List;

/**
 * 业务层
 * */
public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();
    /*
    * 添加客户
    * */
    public void add(Customer customer) throws CustomerException {
        try {
            customerDao.add(customer);
        } catch (CustomerException e) {
            throw new CustomerException(e);
        }
    }
    /*
    * 查询所有
    * */
    public List<Customer> findAll() throws CustomerException {
        return customerDao.findAll();
    }
    /*
    * 返回要编辑的客户信息
    * */
    public Customer load(String cid) throws CustomerException {
        return  customerDao.load(cid);
    }
    /*
    * 编辑客户信息
    * */
    public void edit(Customer customer) throws CustomerException {
        customerDao.edit(customer);
    }
    /*
    * 删除客户
    * */
    public void delete(String cid) throws CustomerException{
        customerDao.delete(cid);
    }
    /*
    * 高级搜索
    * */
//    public List<Customer> search(Customer customer) throws CustomerException {
//        return customerDao.search(customer);
//    }

    public PageBean<Customer> newFindAll(int pageNum, int pageSize) throws CustomerException {
        return customerDao.newFindAll(pageNum,pageSize);
    }
    /*
     * 高级搜索
     * */
    public PageBean<Customer> search(Customer customer, int pageNum, int pageSize) throws CustomerException {
        return customerDao.search(customer, pageNum, pageSize);
    }
}
