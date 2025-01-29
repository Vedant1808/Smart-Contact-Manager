package com.io.scm.service;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.io.scm.entity.Contact;
import com.io.scm.entity.User;

public interface ContactService {

    public Contact save(Contact contact);

    public Contact update(Contact contact);

    public List<Contact> getAll();

    public Contact getById(String id);

    public void delete(String id);

    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    public Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy,
            String order, User user);

    public List<Contact> getByUserId(String userId);

    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction);

    public long countByUserId(String userId);

    public long countByContactFavourite(String userId);

    public long countByLinkedInLinkNotNull(String userId);

    public long countByEmailNotNull(@Param("userId") String userId);

    public String findMostContactedPerson(@Param("userId") String userId);

    public String findRecentlyAddedContact(@Param("userId") String userId);

}
