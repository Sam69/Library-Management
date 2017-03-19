package com.example.sameerwagh.sql;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.view.View;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.content.Context;
import android.content.Intent;
public class Main2Activity extends AsyncTask<String,Void,String>{
    private TextView statusField,roleField,t1,tt1,tt2;
    private Context context;
    private int byGetOrPost = 0;
    private int jj=0;
    //private final Context context;

    String username,password="sameer",Book1="",UserID="",bookname="",DueDate1="";

//    t1 = (TextView)findViewById(R.id.textView);

    //flag 0 means get and 1 means post.(By default it is get.)
    public Main2Activity(Context context,TextView roleField,TextView t1,int flag,String username,String password) {
        this.context = context;
       //this.statusField = statusField;
        this.roleField = roleField;
        this.t1=t1;
        byGetOrPost = flag;
        this.tt1=tt1;
        this.tt2=tt2;
        this.username=username;
        this.password=password;
        //password="sameer";

    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        if(byGetOrPost == 0)
        { //means by Get Method

            try {

               //String link = "http://192.168.43.66/login.php";
                String link = "http://192.168.43.66/login.php";

                String link2 = "http://192.168.43.66/BookRetrieve.php?bookid=";


//                String link = "http://192.168.43.66/login.php";

  //              String link2 = "http://192.168.43.66/BookRetrieve.php?bookid=";



                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while ((line = in.readLine()) != null) {
                    sb.append(line + "\n");
                    break;
                }
                in.close();

                String json = sb.toString();
                JSONObject jobj = new JSONObject(json);

                String data = "";
                try {
                    JSONObject jsonRootObject = new JSONObject(json);

                    //Get the instance of JSONArray that contains JSONObjects
                    JSONArray jsonArray = jsonRootObject.optJSONArray("Users");

                    //Iterate the jsonArray and print the info of JSONObjects
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        //int id = Integer.parseInt(jsonObject.optString("id").toString());
                       String name = jsonObject.optString("username").toString();
                        String pass = jsonObject.optString("password").toString();
                        UserID=jsonObject.optString("UserID").toString();
                        Book1=jsonObject.optString("Book1").toString();



                        if(password.matches(pass) && (username.matches(name)))
                        {
                            jj=1;

                            link2=link2+Book1;

                            if(name.matches("admin"))
                            {
                                jj=0;
                            }

                            URL url2 = new URL(link2);
                           // HttpClient client2 = new DefaultHttpClient();
                            HttpGet request2 = new HttpGet();
                            request2.setURI(new URI(link2));
                            HttpResponse response2 = client.execute(request2);
                            BufferedReader in2 = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));

                            StringBuffer sb2 = new StringBuffer("");
                            String line2="";

                            while ((line2 = in2.readLine()) != null) {
                                sb2.append(line2 + "\n");
                                break;
                            }
                            in2.close();

                            String json2 = sb2.toString();
                            JSONObject jobj2 = new JSONObject(json2);



                            try {
                                JSONObject jsonRootObject2 = new JSONObject(json2);

                                //Get the instance of JSONArray that contains JSONObjects
                                JSONArray jsonArray2 = jsonRootObject2.optJSONArray("BookRetrieval");

                                //Iterate the jsonArray and print the info of JSONObjects
                                for (int j = 0; j < jsonArray2.length(); j++)
                                {
                                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);

                                    //int id = Integer.parseInt(jsonObject.optString("id").toString());
                                    bookname = jsonObject2.optString("BookName").toString();
                                    DueDate1=jsonObject.optString("DueDate1").toString();

                                }
                            }
                            catch(JSONException e)
                            {

                            }
                           // username=pass;
                           // return "gcgggh";
                        }

                        //float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                        data += "User " + i + " : \n user= " + name+ " \n password= " + pass+"\n\n" ;
                    }
                    // output.setText(data);
                }
                catch(JSONException e)
                {
                  //  Log.e("log_tag", "Error parsing data "+e.toString());
                   // Toast.makeText(getApplicationContext(), "Error Parsing",  Toast.LENGTH_LONG).show();
                }

                //return data;
                //username+=password;
                if(jj==1)
                {

                    return "user";

                }
                else if(jj==0)
                {
                    return "admin";
                }//return "gh";

            }

            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }return  null;
        }


    @Override
    protected void onPostExecute(String result){
        //this.statusField.setText("Login Successful");
        Intent intent = new Intent(context, Main3Activity.class);
        if(result.matches("user"))
        {
            //bookname=bookname+Book1;
            //this.roleField.setText("login success!!");//result);
           // this.t1.setText(result);
            int userid=Integer.parseInt(UserID);
            int book1=Integer.parseInt(Book1);

            intent.putExtra("key1", username); // getText() SHOULD NOT be static!!!
            intent.putExtra("key2",password);
            intent.putExtra("key3",userid);
            intent.putExtra("key4",book1);
            intent.putExtra("key5",bookname);
            intent.putExtra("key6",DueDate1);
            intent.putExtra("admin_flag",false);



            context.startActivity(intent);
            //finish();
        }
        else if(result.matches("admin"))
        {


            //New Activity for admin

            int userid=Integer.parseInt(UserID);
            int book1=Integer.parseInt(Book1);

            intent.putExtra("key1", "Admin Saheb"); // getText() SHOULD NOT be static!!!
            intent.putExtra("key2",password);
            intent.putExtra("key3",userid);
            intent.putExtra("key4",book1);
            intent.putExtra("key5",bookname);
            intent.putExtra("key6",DueDate1);
            intent.putExtra("admin_flag",true);




            context.startActivity(intent);



        }
    }
}