package controllers;
 
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);
public  MultipartResolver multipartResolver;

	
	private void initMultipartResolver(ApplicationContext context)
	  {
	    try
	    {
	      this.multipartResolver = ((MultipartResolver)context.getBean("multipartResolver", MultipartResolver.class));
	      if (this.logger.isDebugEnabled()) {
	        this.logger.debug("Using MultipartResolver [" + this.multipartResolver + "]");
	      }
	    }
	    catch (NoSuchBeanDefinitionException ex)
	    {
	      this.multipartResolver = null;
	      if (this.logger.isDebugEnabled())
	        this.logger.debug("Unable to locate MultipartResolver with name 'multipartResolver': no multipart request handling provided");
	    }
	  }
	@RequestMapping(value = "/FileUploadSuccess", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				System.out.println("IN is empty");
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				String rootPath = "E:/workspace/hiber/src/main/resources/fileuploads";
				File dir = new File(rootPath + File.separator + file);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());
				
				
				return "You successfully uploaded file=" + file.getOriginalFilename();
			} catch (Exception e) {
				return "You failed to upload " + file + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + file
					+ " because the file was empty.";
		}
	}
}    
     