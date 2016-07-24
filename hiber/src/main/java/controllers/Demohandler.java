package controllers;

	import org.springframework.binding.message.MessageBuilder;
	import org.springframework.binding.message.MessageContext;
	import org.springframework.stereotype.Component;
	 
	
	 
	@Component
	public class Demohandler {
	 
		public UserBean initFlow(){
			return new UserBean();
		}
	 
		public String validateDetails(UserBean userBean,MessageContext messageContext){
			String status = "success";
			if(userBean.getQty()==null){
				messageContext.addMessage(new MessageBuilder().error().source("qty").defaultText("Quantity cannot be Empty").build());
				status = "failure";
			}
			return status;
			/*if(userBean.getEmail().isEmpty()){
				messageContext.addMessage(new MessageBuilder().error().source(
						"email").defaultText("Email cannot be Empty").build());
				status = "failure";
			}
			if(userBean.getAge()==null){
				messageContext.addMessage(new MessageBuilder().error().source("age").defaultText("Age cannot be Empty").build());
				status = "failure";
			}
			return status;
		}*/
	}
	}


