package com.contactmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactmanagement.entity.Contact;
import com.contactmanagement.service.ContactService;

@Controller
public class ContactController {


	@Autowired
	private ContactService service;

	@GetMapping("/home")
	private String home() {
		return "home";
	}

	@GetMapping("/add")
	private String addContactPage() {
		return "addContact";
	}

	@PostMapping("/add")
	private String addContact(@RequestParam String firstname, @RequestParam String lastname, @RequestParam long mobileNumber,
			@RequestParam String email, @RequestParam String company, @RequestParam String group,
			/* @RequestParam CommonsMultipartFile image, */
			ModelMap map) {
//		System.out.println(image.getName());
		boolean added = service.addContact(firstname, lastname, mobileNumber, email, company, group);
		if (added) {
			map.addAttribute("added", "Contact added!");
		} else {
			map.addAttribute("added", "Contact not added!");
		}
		return "addContact";
	}

	@GetMapping("/contacts")
	private String contacts(ModelMap map) {
		List<Contact> allContacts = service.getAllContacts();
		map.addAttribute("allContacts", allContacts);
		return "contacts";
	}

	@GetMapping("/delete")
	private String delete(@RequestParam int id, ModelMap map) {
		boolean deleted = service.deleteContact(id);
		if (deleted) {
			map.addAttribute("deleted", "Contact deleted!");
		}
		List<Contact> allContacts = service.getAllContacts();
		map.addAttribute("allContacts", allContacts);
		return "contacts";
	}

	@GetMapping("/edit")
	private String editPage(@RequestParam int id, ModelMap map) {
		Contact contact = service.getContact(id);
		if (contact != null) {
			map.addAttribute("contact", contact);
			return "editPage";
		}
		map.addAttribute("msg", "Contact not found!");
		return "contacts";
	}

	@PostMapping("/edit")
	private String editContact(@RequestParam int id, ModelMap map) {
		return "contacts";
	}

	@GetMapping("/search")
	private String searchPage() {
		return "search";
	}

	@PostMapping("/search")
	private String searchContacts(@RequestParam String searchType, @RequestParam String searchValue, ModelMap map) {
		List<Contact> allContacts = service.searchContacts(searchType, searchValue);
		map.addAttribute("allContacts", allContacts);
		return "contacts";
	}
	
	@GetMapping("/searchByGroup")
	private String searchByGroup() {
		return "searchGroup";
	}

	@PostMapping("/searchByGroup")
	private String searchByGroup(@RequestParam String group, ModelMap map) {
		List<Contact> allContacts = service.searchByGroup(group);
		map.addAttribute("allContacts", allContacts);
		return "contacts";
	}
}
