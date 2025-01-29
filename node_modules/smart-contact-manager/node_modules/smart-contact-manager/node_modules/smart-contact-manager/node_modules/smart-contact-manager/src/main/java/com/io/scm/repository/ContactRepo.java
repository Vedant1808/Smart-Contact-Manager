package com.io.scm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.io.scm.entity.Contact;
import com.io.scm.entity.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    // find the contact by user by custom finder method
    public Page<Contact> findByUser(User user, Pageable pageable);

    // find the contact by user by custom query methods
    @Query("SELECT c FROM Contact c WHERE c.user.id=:userId")
    public List<Contact> findByUserId(@Param("userId") String userId);

    public Page<Contact> findByUserAndNameContaining(User user, String nameKeyword, Pageable pageable);

    public Page<Contact> findByUserAndEmailContaining(User user, String emailKeyword, Pageable pageable);

    public Page<Contact> findByUserAndPhoneNumberContaining(User user, String phoneKeyword, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Contact c WHERE c.user.userId = :userId")
    long countByUserId(@Param("userId") String userId);

    @Query("SELECT COUNT(c) FROM Contact c WHERE c.favourite = true AND c.user.userId=:userId")
    long countByContactFavourite(@Param("userId") String userId);

    @Query("SELECT COUNT(c) FROM Contact c WHERE c.linkedInLink IS NOT NULL AND c.linkedInLink <> '' AND c.user.userId=:userId")
    long countByLinkedInLinkNotNull(@Param("userId") String userId);

    @Query("SELECT COUNT(c) FROM Contact c WHERE c.email IS NOT NULL AND c.email <> '' AND c.user.userId=:userId")
    long countByEmailNotNull(@Param("userId") String userId);

    @Query("SELECT c.name FROM Contact c WHERE c.user.userId=:userId ORDER BY c.email DESC LIMIT 1")
    String findMostContactedPerson(@Param("userId") String userId);

    @Query("SELECT c.name FROM Contact c WHERE c.user.userId=:userId ORDER BY c.id DESC LIMIT 1")
    String findRecentlyAddedContact(@Param("userId") String userId);

}
