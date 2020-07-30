package com.example.hp.blogapp;

import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

public class Query extends AppCompatActivity {
    private TextView txtUser;
    private TextView edit1;
    private TextView edit2;

    String content[]=new String[100];
    int ll;
    String title1[]=new String[100];
    String querystring="electric current";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        txtUser=(TextView)findViewById(R.id.txt);
        edit1=(TextView)findViewById(R.id.txt1);
        edit2=(TextView)findViewById(R.id.txtt2);
        new GetDataSync().execute();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String kkk=bundle.getString("Msg");
            Log.e("hhhvh", "onCreate:aagfdhgfhc "+kkk);
            querystring=kkk;
        }
        // String ppp = bundle.getString("Msg");

        if(querystring=="")
            querystring="current";

    }

    String saldo = "";

    public class GetDataSync extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            try {
                getData();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            int l=saldo.length();
            for(int i=0;i<l;i++)
            {
                if(saldo.charAt(i)=='t'&&saldo.charAt(i+1)=='a'&&saldo.charAt(i+2)=='g'&&saldo.charAt(i+3)=='s')
                {
                    saldo=saldo.substring(i+8,l-3);
                    break;
                }
            }
            String xx=":::::Related Words:::::\n\n";
            String yy="";
            String tt1=":::::Related Tag:::::\n\n";
            for(int pp=1;pp<ll;pp++)
            {
                xx=xx+title1[pp] +":\n"+content[pp].replaceAll("(\\<.*?>)*","") +"\n\n";
               yy=yy+content[pp];
               tt1=tt1+"<h1>"+title1[pp]+"</h1>";

            }
            String htmlTextStr = Html.fromHtml(tt1).toString();


            txtUser.setText(title1[0]);
            edit1.setText(content[0].replaceAll("(\\<.*?>)*",""));
            edit2.setText(htmlTextStr);
        }
    }

    private void getData() throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("http://10.1.34.121/iitjee/common_file/messages.php?value="+querystring);
        try {
            Log.e("herllo","hello _json");
            String response = json.toString(4);

            Log.e("AAAAAAAAA %s", response);
            saldo = response;
            JSONArray k=json.getJSONArray("tags");
            Log.e("ppppppp%s", k.toString());
            String str[]=new String[100];
            ll=k.length();
            for(int i=0;i<k.length();i++)
            {

                JSONObject p=k.getJSONObject(i);
                Iterator<String> title= p.keys();
                String t=title.next();
                content[i]=p.get(t).toString();
                title1[i]=t;
                Log.e("qqqqqq%s", p.get(t)+"\n");
            }


        } catch (JSONException e) {

            e.printStackTrace();
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
        finally {
            is.close();
        }
    }
}
