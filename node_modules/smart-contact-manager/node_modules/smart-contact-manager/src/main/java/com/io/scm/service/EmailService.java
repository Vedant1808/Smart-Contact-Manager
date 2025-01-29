package com.io.scm.service;

public interface EmailService {

    //
    void sendEmail(String to, String subject, String body);

    void sendEmail(String from, String to, String subject, String body);

    //
    void sendEmailWithHtml();

    //
    void sendEmailWithAttachment();

}
