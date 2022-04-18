package in.satish.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.satish.constants.AppConstants;
import in.satish.entity.Contact;
import in.satish.props.AppProperties;
import in.satish.service.ContactService;

@RestController
public class ContactRestController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private AppProperties app;
	
	@PostMapping("/contact")
	public String saveContact(@RequestBody Contact contact) {
		
		boolean status = contactService.saveContact(contact);
		
		Map<String, String> message = app.getMessages();
		if(status) {
			return message.get(AppConstants.CONTACT_SAVE_SUCC);
		}
		
		else {
			return message.get(AppConstants.CONTACT_SAVE_FAIL);
		}
		
	}
	
	@GetMapping("/contacts")
	public List<Contact> getAllContacts(){
		
		return contactService.getAllContacts();
		
	}
	
	
	@GetMapping("/contact/{cid}")
	public Contact editContact(@PathVariable("cid") Integer contactId) {
		return contactService.getContactById(contactId);
		
	}
	
	@DeleteMapping("contact/{cid}")
	public String deleteContact(@PathVariable("cid") Integer contactId) {
		boolean status = contactService.deleteContactById(contactId);
		
		Map<String, String> message = app.getMessages();
		
		if(status) { return message.get(AppConstants.CONTACT_DEL_SUCC); }
		else { return message.get(AppConstants.CONTACT_DEL_FAIL); }
	}
	

}
