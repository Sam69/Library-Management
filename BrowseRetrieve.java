package com.example.sameerwagh.sql;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

/**
 * Created by sameerwagh on 18/08/16.
 */
public class BrowseRetrieve extends AsyncTask<String,Void,String> {


    private TextView statusField,roleField,t1,tt1,tt2;
    ListView listview;
    private Context context;
    private View rootView;
    private int byGetOrPost = 0;
    private int jj=0;

    int  resource;
    //private final Context context;

    String username,password="sameer";
    String Book[][];//=new String[5][20];


//    t1 = (TextView)findViewById(R.id.textView);

    //flag 0 means get and 1 means post.(By default it is get.)
    public BrowseRetrieve(Context context, TextView tt1,ListView listview) {
        this.context = context;
        this.rootView=rootView;
        //this.statusField = statusField;
        this.listview = listview;
        this.resource=resource;
        this.t1=t1;
       // byGetOrPost = flag;
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
        //if(byGetOrPost == 0)
        { //means by Get Method

            try {
               // String link = "http://192.168.1.124/login2.php";
               String link = "http://192.168.43.66/login2.php";


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
                    JSONArray jsonArray = jsonRootObject.optJSONArray("list");

                        String Heading[]={"BookName","Author","BookID","TotalCopies","CopiesLeft"};
                            jj=jsonArray.length();
                        Book = new String[5][jsonArray.length()+1];
                    for(int i=0;i<5;i++)
                        Book[i][0]=Heading[i];

                    //Iterate the jsonArray and print the info of JSONObjects

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        //int id = Integer.parseInt(jsonObject.optString("id").toString());
                        String BookName = jsonObject.optString("BookName").toString();
                        String Author = jsonObject.optString("Author").toString();
                        String BookID = jsonObject.optString("BookID").toString();
                        String TotalCopies = jsonObject.optString("TotalCopies").toString();
                        String CopiesLeft = jsonObject.optString("CopiesLeft").toString();

                            data=Author;


                        Book[0][i+1]=BookName;
                        Book[1][i+1]=Author;
                        Book[2][i+1]=BookID;
                        Book[3][i+1]=TotalCopies;
                        Book[4][i+1]=CopiesLeft;


                    }

                }
                catch(JSONException e)
                {
                     Log.e("log_tag", "Error parsing data "+e.toString());
                    // Toast.makeText(getApplicationContext(), "Error Parsing",  Toast.LENGTH_LONG).show();
                }

                //return data;
                //username+=password;
                if(jj==98)
                {

                    return "lppo";

                }
                else {
                    return username;
                }//return "gh";

            }

            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }//return  null;
    }


    @Override
    protected void onPostExecute(String result){
        String ss="";


       for(int i=1;i<jj;i++)
       {    ss=ss+Book[0][i];
           tt1.setText(ss);
       }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.activity_listview,Book[0]);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listview.setAdapter(adapter);
        //listview.setOnClickListener();

        listview.setOnItemClickListener(  new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Object o = listview.getItemAtPosition(position);
                String aa=Book[0][position];

                //prestationEco str=(prestationEco)o;//As you are using Default String Adapter
               // Toast.makeText("hello", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "hello "+aa+ Book[2][position], Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context, Main5Activity.class);
                i.putExtra("Books", Book[0]);
                i.putExtra("Authors", Book[1]);
                i.putExtra("BookId", Book[2]);
                i.putExtra("TotalCopies", Book[3]);
                i.putExtra("CopiesLeft", Book[4]);
                i.putExtra("count",position);
                context.startActivity(i);



            }
        });

    }
}
