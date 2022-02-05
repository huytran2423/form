/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.form;

/**
 *
 * @author rocke
 */
public class Member {
    //@NotEmpty
    private String firstname;
    private String lastname;
    //@Email
    private String email;
    private String phone;
    private boolean email_notified;
    private boolean phone_notified;
    private String supervisor;
    
    public String getfirstName()
    {
        return(firstname);
    }
    
    public void setfirstName(String firstname)
    {
        this.firstname = firstname;
    }
    
    public String getlastName()
    {
        return(lastname);
    }
    
    public void setlastName(String lastname)
    {
        this.lastname = lastname;
    }
    
    public String getemail()
    {
        return(email);
    }
    
    public void setemail(String email)
    {
        this.email = email;
    }
    
    public String getphone()
    {
        return(phone);
    }
    
    public void setphone(String phone)
    {
        this.phone = phone;
    }
    
    public Boolean getemail_notified()
    {
        return(email_notified);
    }
    
    public void setemail_notified(Boolean email_notified)
    {
        this.email_notified = email_notified;
    }
    
    public Boolean getphone_notified()
    {
        return(phone_notified);
    }
    
    public void setphone_notified(Boolean phone_notified)
    {
        this.phone_notified = phone_notified;
    }
    
    public String getsupervisor()
    {
        return(supervisor);
    }
    
    public void setsupervisor(String supervisor)
    {
        this.supervisor = supervisor;
    }
    
    @Override
    public String toString() {
        return "Member [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", phone="
                + phone + ", email notified=" + email_notified + ", phone notified=" + phone_notified + ", supervisor=" + supervisor;
    }
}
