package edu.neu.qmjz.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.neu.qmjz.R;
import edu.neu.qmjz.bean.Person;
import edu.neu.qmjz.utils.NetworkServiceManager;

/**
 * Created by Administrator on 2015/10/27.
 */
public class LoginActivity extends AppCompatActivity{
    private EditText editName,editPassword;
    private String name,password,servantName;
    private Button btnlogin;
    private Person person;
    SharedPreferences personData;
    private SharedPreferences.Editor editor;
    private NetworkServiceManager.ConnectionListener loginListener= new NetworkServiceManager.ConnectionListener() {
        @Override
        public void onConnectionSucceeded(JSONObject result) {
            Log.e("Login", "Succcess");
            try {
                if(result.getString("serverResponse").equals("Success")){
                    Log.e("Login",result.toString());
                    getDeclareInfo();
                }else{
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConnectionFailed(String errorMessage) {
            Log.e("Login failed", "too bad " + errorMessage);
        }
    };

    private NetworkServiceManager.ConnectionListener getDeclareInfoListener= new NetworkServiceManager.ConnectionListener() {
        @Override
        public void onConnectionSucceeded(JSONObject result) {
            try {
                Log.e("Login",result.toString());
                if(result.getString("serverResponse").equals("Success")){
                    JSONObject data = result.getJSONObject("data");
                    person=new Person(data);
                    editor.putInt("id",person.getId());
                    editor.putString("servantName",person.getServantName());
                    editor.putString("servantID",person.getServantID());
                    editor.putString("careerType",person.getCareerType());
                    editor.putString("contactAddress",person.getContactAddress());
                    editor.putString("educationLevel",person.getEducationLevel());
                    editor.putString("emailAddress",person.getEmailAddress());
                    editor.putString("headPicture", person.getHeadPicture());
                    editor.putInt("holidayInMonth", person.getHolidayInMonth());
                  //  editor.putString("id",person.getI);
                    editor.putString("idCardNo",person.getIdCardNo());
                    editor.putString("isMarried", String.valueOf(person.getIsMarried()));
                    editor.putString("isStayHome",person.getIsStayHome());
                    editor.putString("loginPassword",person.getLoginPassword());
                    editor.putString("phoneNo",person.getPhoneNo());
                    editor.putString("qqNumber",person.getQqNumber());
                   // editor.putString("realLatitude",person.getRe);
                 //   editor.putString("realLongitude",person.getCareerType());
                 //   editor.putString("registerDate",person.getR);
                  //  editor.putFloat("registerLatitude",Float.parseFloat(person.getRegisterLatitude()));
                  //  editor.putString("registerLongitude",person.getCareerType());
                  //  editor.putString("servantAge",person.getServantA);
                //    editor.putString("servantBirthday",person.getS);
                 //   editor.putString("servantCategory",person.getServantC);
                    editor.putString("servantCity",person.getServantCity());
                    editor.putString("servantCounty",person.getServantCounty());
                    editor.putString("servantGender",person.getServantGender());
                    editor.putString("servantHonors",person.getServantHonors());
                    editor.putString("servantIntro",person.getServantIntro());
                    editor.putString("servantMobil",person.getServantMobil());
                    editor.putString("servantNationality",person.getServantNationality());
                    editor.putString("servantProvince",person.getServantProvince());
               //     editor.putString("servantSalary",person.getServant);
               //     editor.putString("servantScore",person.getServantS);
              //      editor.putString("servantState",person.getServantS);
              //      editor.putString("servantStatus",person.getCareerType());
                    editor.putString("serviceArea",person.getServiceArea());
                    editor.putString("serviceItems",person.getServiceItems());
                    editor.putString("trainingIntro",person.getTrainingIntro());
                    editor.putFloat("workYears",person.getWorkYears());
                    editor.commit();
                    Log.d("Login:",getSharedPreferences("shareDate",0).getString("servantName",""));
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    LoginActivity.this.finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConnectionFailed(String errorMessage) {
            Log.e("GetDeclareInfo", "failed to get Declare info!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Login", "success");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editName=(EditText)findViewById(R.id.userNameText);
        editPassword=(EditText)findViewById(R.id.passwdText);
        btnlogin=(Button)findViewById(R.id.btnLogin);
        personData = getSharedPreferences("shareData", 0);
        editor=personData.edit();
        btnlogin.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
              name=editName.getText().toString();
              password=editPassword.getText().toString();
              loginMessage();
           }
        });

	    if(!personData.getString("servantID","").equals("")){
		    startActivity(new Intent(LoginActivity.this, MainActivity.class));
		    LoginActivity.this.finish();
	    }
    }
    private void loginMessage(){
        NetworkServiceManager serviceManager =
                new NetworkServiceManager("http://219.216.65.182:8080/NationalService/"+"MobileServantInfoAction?" +
                        "operation=_login");
        serviceManager.addParameter("servantID", name);
        serviceManager.addParameter("loginPassword", password);
        serviceManager.setConnectionListener(loginListener);
        serviceManager.sendAction();
//        try {
//            JSONObject params = new JSONObject();
//            params.put("servantID", name);
//            params.put("loginPassword", password);
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                    StringRequest.Method.POST,
//                    "http://219.216.65.182:8080/NationalService/MobileServantInfoAction?operation=_login",
//                    params,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            Log.d("json",response.toString());
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.d("Error", error.toString());
//                        }
//                    }){
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    HashMap<String, String> headers = new HashMap<String, String>();
//                    headers.put("Accept", "application/json");
//                    headers.put("Content-Type", "application/json; charset=UTF-8");
//
//                    return headers;
//                }
//            };
//
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            requestQueue.add(jsonObjectRequest);
//            requestQueue.start();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
    private void getDeclareInfo(){
        Log.e("getDeclareInfo:","getDeclareInfo");
        NetworkServiceManager serviceManager =
                new NetworkServiceManager("http://219.216.65.182:8080/NationalService/"+"MobileServantInfoAction?"+
                "operation=_queryByServantID");
        serviceManager.addParameter("servantID", name);
        serviceManager.setConnectionListener(getDeclareInfoListener);
        serviceManager.sendAction();
    }
}
