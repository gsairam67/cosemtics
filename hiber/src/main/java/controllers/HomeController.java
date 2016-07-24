package controllers;

import java.io.BufferedOutputStream;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

import DAO.productDAOImpl;
import DAO.productServices;
import model.Product;

@EnableWebMvc
@Controller
public class HomeController {
	
	@Autowired  private JavaMailSender mailSender;
	
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
    
   
    @RequestMapping("/FileUploadSuccess")
    public String showFileUploadSuccess(){
    	
    	return "FileUploadSuccess";
    }
    
    @RequestMapping("/login")
    public String showlogin()
    {
    	return "login";
    }
    @RequestMapping("/send")
    public String showSend()
    {
    	return "send";
    }
    @RequestMapping("/sent")
    public String showSent()
    {
    	return "sent";
    }
    
    
    
    @RequestMapping("/productDescription")
    public String showproductDesc()
    {
    	return "productDescription";
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
     
    
    @RequestMapping("/productTable")
	@ResponseBody
	public ModelAndView showHome()
	{
	List<Product> listtojsp=new ArrayList<Product>();
		listtojsp=productService.listProduct();
		String json = new Gson().toJson(listtojsp);  // converting list into Google Gson object which is a string/
		System.out.println(json);
		ModelAndView mv=new ModelAndView("productTable");
		mv.addObject("myJson", json);
		return mv;
	}

    
    
    
    @RequestMapping(value="/sendEmail", method = RequestMethod.POST)
    public String doSendEmail(HttpServletRequest request) {
        // takes input from e-mail form
        String recipientAddress = request.getParameter("email");
        String fname=request.getParameter("first_name");
        String subject ="musichub :: Your Friend Recommends..."+ request.getParameter("musichub alert");
        String message = request.getParameter("comments");
        String finalmessage="Hi "+fname+", "+" "+message+"!!! "+"Check this out!!!";
         
        // prints debug info
        System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + finalmessage);
         
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(finalmessage);
         
        // sends the e-mail
        mailSender.send(email);
         
        // forwards to the view named "Result"
        return "redirect:/sent";
    }
    
    
    
    
} 
    
    
    
    
    

