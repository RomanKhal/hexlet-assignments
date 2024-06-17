package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(ContactCreateDTO data) {
        var contact = toEntiy(data);
        contactRepository.save(contact);
        return toDto(contact);
    }

    private Contact toEntiy(ContactCreateDTO data) {
        var entity = new Contact();
        entity.setPhone(data.getPhone());
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        return entity;
    }

    private ContactDTO toDto(Contact data) {
        var dto = new ContactDTO();
        dto.setId(data.getId());
        dto.setFirstName(dto.getFirstName());
        dto.setLastName(dto.getLastName());
        dto.setPhone(data.getPhone());
        dto.setUpdatedAt(data.getUpdatedAt());
        dto.setCreatedAt(data.getCreatedAt());
        return dto;
    }
    // END
}
