package controllers;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import org.slf4j.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import DAO.productDAOImpl;
import DAO.productServices;
import model.Product;

@EnableWebMvc
@Controller
public class HomeController {
	
	@Autowired
	  public productServices productService;
	
	@Qualifier(value="productService")
    public void setPersonService(productServices ps){
        this.productService = ps;
    }
	
    @RequestMapping("/")
    public String showIndex()
    {
    	return "index";
    }
    
    @RequestMapping("/index")
    public String showIndex1()
    {
    	return "index";
    }
    
    @RequestMapping("/Register")
    public String showRegister()
    {
    	return "Register";
    }
    
    @RequestMapping("/done")
    public String showdone()
    {
    	return "done";
    } 
    @RequestMapping("/FileUploadSuccess")
    public String showFileUploadSuccess(){
    	
    	return "FileUploadSuccess";
    }
    
    @RequestMapping("/login")
    public String showlogin()
    {
    	return "login";
    }
      
    //For add and update person both
    @RequestMapping(value= "/AddProduct", method = RequestMethod.GET)
    public String addProduct(@ModelAttribute("product") Product p){
         
        if(p.getId() == 0){
            //new person, add it
            this.productService.addProduct(p);
        }else{
            //existing person, call update
            this.productService.updateProduct(p);
        }
         
        return "/AddProduct";
     }
    /*@RequestMapping("/edit/{id}")
    public String updateProduct(@ModelAttribute("product") Product p)
    {
        this.productService.updateProduct(p);
        return "redirect:/Products";
    }
    */
    @RequestMapping("/UpdProduct")
    public String updateProduct(@ModelAttribute("product") Product p)
    {
    	this.productService.updateProduct(p);
        return "index";
    }
    @RequestMapping("/ProductDescription")
    public String showProductDescription()
    {
    	return "ProductDescription";
    }
    
    @RequestMapping(value="/Products",method=RequestMethod.GET )
    public String listProducts(Model model)
    {
        model.addAttribute("product", new Product());
        model.addAttribute("listProduct", this.productService.listProduct());
        return "Products";
    }
       
    @RequestMapping("delete/{id}")
    public String removeProduct(@PathVariable("id") int id)
    {
        this.productService.removeProduct(id);
        return "redirect:/Products";
    }
    
   
    @RequestMapping("/details/{id}")
    public String getProductById(@PathVariable("id") int id,Model model) 
    {
    	model.addAttribute("product", this.productService.getProductById(id));    
        model.addAttribute("listProduct",this.productService.listProduct());
        return "redirect:/ProductDescription";
    }
 
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }
 
    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "denied";
    }
 
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "logout";
    } 
 
       
} 
    
    
    
    
    

