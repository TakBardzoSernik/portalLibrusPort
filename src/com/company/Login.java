package com.company;
import com.company.category.Oceny;
import com.company.misc.fHttp;
import com.company.gui.GuiManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import javax.swing.*;


public class Login {
    public void setUser(String user) {
        this.user = user;
        GuiManager.setUserName(user);
    }

    public Oceny oceny;
    protected String user;
    public HttpClient client;


    //function login - returns true on success, false on fail;
    public boolean newLogin(String username, char[] password) throws IOException {
        //Empty inputs
        if (username.length() == 0 || password.length == 0){
            JOptionPane.showMessageDialog(null, "please complete form.");
            return false;
        }

        //--------------------login process--------------------
         HttpResponse response;

        //#1 request - Get cookie
        fHttp.sendNoResp(client, new HttpGet("https://api.librus.pl/OAuth/Authorization?client_id=46&response_type=code&scope=mydata"));

        //#2 request - POST login data
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("action", "login"));
        params.add(new BasicNameValuePair("login", username));
        params.add(new BasicNameValuePair("pass", String.valueOf(password)));

        HttpPost post = new HttpPost("https://api.librus.pl/OAuth/Authorization?client_id=46");
        post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        response = client.execute(post);

        //check login results
        if(fHttp.responseStatusCode(response) != HttpStatus.SC_OK){
            //LOGIN FAILED
            JOptionPane.showMessageDialog(null, "Wrong username or password");
            fHttp.consume(response);
            return false;
        }
        fHttp.consume(response);

        //LOGIN SUCCESS
        //#3 request - cookies
        fHttp.sendNoResp(client, new HttpGet("https://api.librus.pl/OAuth/Authorization/Grant?client_id=46"));

        //close window
        GuiManager.closeLoginWindow();

        return true;
    }
    public Login(){
        client = HttpClients.createDefault();
        GuiManager.openLoginWindow(this);

        //TODO: login with saved OAuth if possible
    }
}
