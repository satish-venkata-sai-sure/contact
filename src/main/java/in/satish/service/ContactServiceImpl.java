package in.satish.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.satish.entity.Contact;
import in.satish.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;
	
	@Override
	public boolean saveContact(Contact contact) {
		// TODO Auto-generated method stub
		contact.setActiveSw("Y");
		Contact sa = contactRepo.save(contact);
		if(sa.getContactId()!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<Contact> getAllContacts() {
		// TODO Auto-generated method stub
		Contact contact = new Contact();
		contact.setActiveSw("Y");
		Example<Contact> example = Example.of(contact);
		
		
		return contactRepo.findAll(example);
	}

	@Override
	public Contact getContactById(Integer contactId) {
		// TODO Auto-generated method stub
		Optional<Contact> findById = contactRepo.findById(contactId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		// TODO Auto-generated method stub
		Optional<Contact> findById = contactRepo.findById(contactId);
		if(findById.isPresent()) {
			Contact contact= findById.get();
			contact.setActiveSw("N");
			contactRepo.save(contact);
			return true;
		}
		return false;
	}

}
