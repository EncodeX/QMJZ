package edu.neu.qmjz.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.neu.qmjz.R;
import edu.neu.qmjz.activity.LoginActivity;
import edu.neu.qmjz.bean.Declare;
import edu.neu.qmjz.utils.NetworkServiceManager;
import edu.neu.qmjz.utils.UploadUtils;

/**
 * Created by Administrator on 2015/10/25.
 */
public class AccountListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<String> list;
    private SharedPreferences personData;
    private SharedPreferences.Editor editor;
    private String[] title;
    private String[] text;
    private ArrayAdapter<String> adapter;
    public AccountListAdapter(Context context,List countryList) {
        Log.e("AccountListAdapter","AccountListAdapter()");
        this.context = context;
        this.inflater= LayoutInflater.from(context);
        this.list=countryList;
        this.personData=context.getSharedPreferences("shareData",0);
        adapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item, list);
        editor=personData.edit();
        title =new String[]{"用户名","姓名","联系电话","手机号码","所属地区","通讯地址","QQ账号","邮箱"};
        text =new String[]{personData.getString("servantID",""), personData.getString("servantName",""),
           personData.getString("phoneNo",""),personData.getString("servantMobil",""),personData.getString("servantCounty",""),
                personData.getString("contactAddress",""),personData.getString("qqNumber",""),personData.getString("emailAddress","")};
    }
    private final LayoutInflater inflater;

    private enum ITEM_TYPE{ITEM_TYPE_TEXT,ITEM_TYPE_BTN,ITEM_TYPE_AREA};
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e("AccountListAdapter","onCreateViewHolder()");
            if (viewType==ITEM_TYPE.ITEM_TYPE_TEXT.ordinal()) {
                return new AccountListViewHolder(inflater.inflate(R.layout.listview_item, parent, false));
            }else if(viewType==ITEM_TYPE.ITEM_TYPE_AREA.ordinal()){
                return new AccountAreaViewHolder(inflater.inflate(R.layout.account_list_area,parent,false));
            }
            else {
                return new AccountBtnViewHolder(inflater.inflate(R.layout.account_list_btn,parent,false));
            }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.e("AccountListAdapter","onBindViewHolder()");
        if (holder instanceof AccountListViewHolder) {
            Log.e("text","createTextHolder()"+position);
            ((AccountListViewHolder)holder).account_item_title.setText(title[position]);
            ((AccountListViewHolder)holder).account_item_text.setText(text[position]);
            ((AccountListViewHolder)holder).account_item_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    Log.e("editor", "actionBegin");
                    if (actionId == EditorInfo.IME_ACTION_SEND
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                        String value = ((AccountListViewHolder) holder).account_item_text.getText().toString();
                        refreshAccount(value, position);
                    }
                    return false;
                }
            });
        }
        if (holder instanceof AccountAreaViewHolder){
            Log.e("spinner","createSpinner()"+position);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ((AccountAreaViewHolder)holder).spinner_area.setAdapter(adapter);
            int defaultCity=0;
            Log.e("spinner0", defaultCity+" "+personData.getString("servantCounty","")+" "+"list.size()"+" "+list.size()+" ");
            for (int i=0;i<list.size();i++){
                Log.e("spinner1", defaultCity+" "+personData.getString("servantCounty",""));
                if (personData.getString("servantCounty","").equals(list.get(i))){
                    defaultCity=i;
                    Log.e("spinner2", defaultCity+" "+personData.getString("servantCounty",""));
                }
            }
            ((AccountAreaViewHolder)holder).spinner_area.setSelection(defaultCity,true);
            ((AccountAreaViewHolder)holder).spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("spinner","Selected");
                       //     ((AccountAreaViewHolder) holder).spinner_area.selec(position, true);
                   // parent.getItemAtPosition(position).toString();
                    refreshArea(id);
                    parent.setVisibility(View.VISIBLE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    parent.setVisibility(View.VISIBLE);
                }
            });
        }
        if (holder instanceof AccountBtnViewHolder){
            Log.e("Button","createBtn()"+position);
            ((AccountBtnViewHolder)holder).account_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            });
        }
    }
    @Override
    public int getItemViewType(int position) {
        Log.e("AccountListAdapter","getItemViewType()");
        if (position== title.length){
            return ITEM_TYPE.ITEM_TYPE_BTN.ordinal();
        }else if(position==4){
            return ITEM_TYPE.ITEM_TYPE_AREA.ordinal();
        }
        else{
            return ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        Log.e("AccountListAdapter","getItemCount()");
        return title.length+1;
    }

    @Override
    public long getItemId(int position) {
        Log.e("AccountListAdapter","getItemId()");return super.getItemId(position);
    }

    public static class AccountListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.account_item_title)
        TextView account_item_title;

        @Bind(R.id.account_item_text)
        EditText account_item_text;

        public AccountListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "请点击右边的方框以更改信息 " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static class AccountBtnViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.btn_sign_out)
        Button account_btn;
        public AccountBtnViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public static class AccountAreaViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.spinner_area)
        Spinner spinner_area;
        public AccountAreaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public void refreshAccount(String value,int position){
        Log.e("refresh","refreshing");
        text[position]=value;
        switch (position){
            case 0:
                editor.putString("servantID", value);
                editor.commit();
                break;
            case 1:
                editor.putString("servantName", value);
                editor.commit();
                break;
            case 2:
                editor.putString("phoneNo", value);
                editor.commit();
                break;
            case 3:
                editor.putString("servantMobil", value);
                editor.commit();
                break;
            case 5:
                editor.putString("contactAddress", value);
                editor.commit();
                break;
            case 6:
                editor.putString("qqNumber", value);
                editor.commit();
                break;
            case 7:
                editor.putString("emailAddress", value);
                editor.commit();
                break;
        }
        UploadUtils uploadUtils = new UploadUtils(context);
        addParameter(uploadUtils);
        uploadUtils.setConnectionListener(refreshInfoListener);
        uploadUtils.sendPhoto(context);
    }
    public void refreshArea(long id){
        Log.e("refreshArea","refreshing area.");
        text[4]=list.get((int)id);
        editor.putString("servantCounty", text[4]);
        editor.commit();
        UploadUtils uploadUtils = new UploadUtils(context);
        addParameter(uploadUtils);
        uploadUtils.setConnectionListener(refreshInfoListener);
        uploadUtils.sendPhoto(context);
    }
    public void addParameter(UploadUtils uploadUtils){
        uploadUtils.addParameter("id",Integer.toString(personData.getInt("id", 0)));
        uploadUtils.addParameter("servantName",personData.getString("servantName",""));
        uploadUtils.addParameter("loginPassword",personData.getString("loginPassword",""));
        uploadUtils.addParameter("phoneNo",personData.getString("phoneNo",""));
        uploadUtils.addParameter("servantMobil",personData.getString("servantMobil",""));
        uploadUtils.addParameter("servantProvince",personData.getString("servantProvince",""));
        uploadUtils.addParameter("servantCity",personData.getString("servantCity",""));
        uploadUtils.addParameter("servantCounty",personData.getString("servantCounty",""));
        uploadUtils.addParameter("contactAddress",personData.getString("contactAddress",""));
        uploadUtils.addParameter("qqNumber",personData.getString("qqNumber",""));
        uploadUtils.addParameter("emailAddress",personData.getString("emailAddress",""));
        uploadUtils.addParameter("headPicture",personData.getString("headPicture",""));
        uploadUtils.addParameter("realLatitude","43214321");
        uploadUtils.addParameter("realLongitude","23413124");
    }
    private UploadUtils.ConnectionListener refreshInfoListener= new UploadUtils.ConnectionListener() {
        @Override
        public void onConnectionSucceeded(JSONObject result) {
            try {
                if(result.getString("serverResponse").equals("Success")) {
                    Log.e("refresh","Refershing Success");
                    notifyDataSetChanged();
                }else{
                    Log.e("refresh failed",result.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConnectionFailed(String errorMessage) {

        }
    };



}
