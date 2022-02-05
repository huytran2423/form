/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.form;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rocke
 */
@Controller
public class WebController {
    
    @GetMapping("/form")
    public String getForm(Model model){
        //System.out.println("get to getForm()");
        String url = "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers";
        RestTemplate restTemplate = new RestTemplate();
        String strresult = restTemplate.getForObject(url, String.class);
        //System.out.print("length: " + strresult.length());
        
        //Parse json string to object
        String[] objarray = strresult.replace("},{","}###{").split("###");
        ArrayList<String> managerList = new ArrayList<String>();
        for(int i=0; i < objarray.length; i++)
        {
            JsonObject jsonObj = new Gson().fromJson(objarray[i].replace('[', ' ').replace(']', ' '), JsonObject.class);                        
            String jur = "" + jsonObj.get("jurisdiction");            
            try
            {
                Integer.parseInt(jur.replaceAll("\"", ""));
            }
            catch(Exception e)
            {
                managerList.add((jsonObj.get("jurisdiction") + " - " + jsonObj.get("lastName") + ", " + jsonObj.get("firstName")).replaceAll("\"", ""));
            }           
        }
        
        Collections.sort(managerList);
        /*
        JSONArray array = new JSONArray(strresult);
        for(int i=0; i < array.length(); i++)   
        {  
            JSONObject object = array.getJSONObject(i);    
        } 
        */
        
        Member member = new Member();
        model.addAttribute("member", member);
        model.addAttribute("managerList", managerList);
        return("form");      
    }
            
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("member") Member member, Model model) {
        System.out.println(member);
        
        //Validation        
        StringBuffer errorMessage = new StringBuffer();
        if(member.getfirstName().length() == 0)
            errorMessage.append("First Name can not be blank or has any number. ");
        else
        if (Pattern.matches("[a-zA-Z]+", member.getfirstName()) == false) {
            errorMessage.append("First Name field only accepts letters. ");
        }
        
        if(member.getlastName().length() == 0)
            errorMessage.append("Last Name can not be blank or has any number. ");
        else
        if(Pattern.matches("[a-zA-Z]+", member.getlastName()) == false) {
            errorMessage.append("Last Name field only accepts letters. ");
        }
        
        if(member.getsupervisor().length() == 0)
            errorMessage.append("Supervisor can not be blank. Please choose one. ");
        
        if(member.getemail_notified() && member.getemail().length() == 0)
            errorMessage.append("Email field can not be blank if it's preffered. ");
        
        if(member.getemail().length() > 0 && Pattern.matches("^(.+)@(\\S+)$", member.getemail()) == false)
        {
            errorMessage.append("Email field is wrong. Please re-enter. ");
        }
        
        if(member.getphone_notified() && member.getphone().length() == 0)
            errorMessage.append("Phone field can not be blank if it's preffered. ");
        
        if(member.getphone().length() > 0 && Pattern.matches("^\\d{10}$", member.getphone()) == false)
        {
            errorMessage.append("Phone number is wrong. Please re-enter 10 digits. ");
        }
                        
        if(errorMessage.length() > 0)
        {
            model.addAttribute("errorMessage", errorMessage);
            System.out.println("Error: " + errorMessage.toString());
            return "register_fail";
        }
        
        return "register_success";
    }
}
