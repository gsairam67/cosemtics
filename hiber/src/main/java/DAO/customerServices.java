package DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Customer;
import DAO.customerDAOImpl;
 
@Service
public class customerServices {
     
	@Autowired
    private customerDAOImpl customerDAO;
 
    public void setPersonDAO(productDAOImpl productDAO) {
        this.customerDAO = customerDAO;
    }
 
   
    @Transactional
    public void addCustomer(Customer c) {
        this.customerDAO.addCustomer(c);
    }
 
    @Transactional
    public void updateCustomer(Customer c) {
        this.customerDAO.updateCustomer(c);
    }
 
    @Transactional
    public List<Customer> listcustomer() {
        return this.customerDAO.listCustomer();
    }
   
    @Transactional
    public Customer getProductById(int id) {
        return this.customerDAO.getCustomerById(id);
    }
  
    @Transactional
    public void removeCustomer(int id) 
    {
        this.customerDAO.removeCustomer(id);
    }
}