import cn.itcast.commons.CommonUtils;
import cn.itcast.cstm.Dao.CustomerDao;
import cn.itcast.cstm.Dao.CustomerException;
import cn.itcast.cstm.Domain.Customer;
import org.junit.Test;

public class customerTest {
    /*
    * 添加300条记录
    * */
    @Test
    public void fun1() throws CustomerException {
        CustomerDao customerDao = new CustomerDao();
        long start = System.currentTimeMillis();
        for(int i = 1;i<=300;i ++){
            Customer customer = new Customer();
            customer.setCid(CommonUtils.uuid());
            customer.setCname("cstm_"+i);
            customer.setBirthday("1921-07-01");
            customer.setCellphone("138"+i);
            customer.setGender(i%2==0?"男":"女");
            customer.setEmail("cstm_"+i+"@163.com");
            customer.setDescription("我是客户！");
            customerDao.add(customer);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end - start)+" 毫秒！");
    }

}
