package com.company;
import com.company.category.Oceny;
import com.company.misc.fHttp;
import com.company.gui.LoginForm;

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
import com.company.gui.MainWindow;

import javax.swing.*;


public class Login {
    public void setUser(String user) {
        this.user = user;
        JLabel label = (JLabel)MainWindow.getComponentByName(Main.mainWindow.getContentPane(), "user_section");
        label.setText(user);
        Main.mainWindow.revalidate();
    }

    public Oceny oceny;
    protected String user;
    public JFrame form;
    public HttpClient client;


    private JFrame createLoginForm(){
        JFrame loginForm = new JFrame("LoginForm");
        loginForm.setContentPane(new LoginForm(this).loginFormPanel);
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginForm.setTitle("Librus Login");
        loginForm.setLocationRelativeTo(null);
        loginForm.pack();
        loginForm.setVisible(true);
        return loginForm;
    }

    //function login - returns true on success, false on fail;
    public boolean newLogin(String username, char[] password) throws IOException {
        //Empty inputs
        if (username.length() == 0 || password.length == 0){
            JOptionPane.showMessageDialog(null, "please complete form.");
            return false;
        }

        //--------------------login process--------------------
        // #1 request
        HttpUriRequest request = RequestBuilder.get()
                .setUri("https://portal.librus.pl/rodzina/synergia/loguj")
                .setHeader("Referer", "https://portal.librus.pl/rodzina")
                .build();

        //Send request
        HttpResponse response = client.execute(request);

        //Find url for necessary cookie site url
        String content = fHttp.responseBody(response);
        int srcLoc = content.indexOf("https://synergia.librus.pl/loguj/portalRodzina");
        String cookieUrl = content.substring(srcLoc, content.indexOf('"', srcLoc + 1));

        content = null;
        fHttp.consume(response);

        //#2 request - Get cookie
        HttpGet get = new HttpGet(cookieUrl);
        fHttp.sendNoResp(client, get);

        //#3 request - Get cookie
        fHttp.sendNoResp(client, new HttpGet("https://api.librus.pl/OAuth/Authorization?client_id=46&response_type=code&scope=mydata"));

        //#4 request - POST login data
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
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
        //#5 request - cookies
        fHttp.sendNoResp(client, new HttpGet("https://api.librus.pl/OAuth/Authorization/Grant?client_id=46"));

        //close window
        form.dispose();

        return true;
    }
    public Login(){
        client = HttpClients.createDefault();
        form = createLoginForm();

        //TODO: login with saved OAuth if possible
    }
}
