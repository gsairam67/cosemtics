package DAO;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import model.Customer;
 
@Repository
public class customerDAOImpl  {
     
    private static final Logger logger = LoggerFactory.getLogger(customerDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
 
    public void addCustomer(Customer c)
    {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(c);
        logger.info("Customer saved successfully, Customer Details="+c);
    }
 
    public void updateCustomer(Customer c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
        logger.info("Customer updated successfully, Customer Details="+c);
    }
 
    @SuppressWarnings("unchecked")
   
    public List<Customer> listCustomer() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Customer> CustomerList = session.createQuery("from Customer").list();
        for(Customer c : CustomerList)
        {
            logger.info("Customer List::"+c);
        }
        return CustomerList;
    }
     
    public Customer getCustomerById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Customer c = (Customer) session.load(Customer.class, new Integer(id));
        logger.info("Customer loaded successfully, Person details="+c);
        return c;
    }
 
    public void removeCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Customer c = (Customer) session.load(Customer.class, new Integer(id));
        if(null != c){
            session.delete(c);
        }
        logger.info("Customer deleted successfully, Customer details="+c);
    }
}