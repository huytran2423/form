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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rocke
 */
@RestController
public class APIController {
    @GetMapping("/api/supervisors")
    public List<String> getManagers()
    {
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
        
        return managerList;
    }
}


