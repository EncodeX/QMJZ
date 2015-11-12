package edu.neu.qmjz.utils;

/**
 * Created by Administrator on 2015/11/6.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import edu.neu.qmjz.R;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/6/1
 * Project: Strangers
 * Package: com.neu.strangers.tools
 */
public class UploadUtils {
    private static final String TAG = "upload";
    private static final int TIME_OUT = 1000 * 1000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码
    private JSONObject mJSONBuilder;
    private ConnectionListener mConnectionListener;
    private Context context;

    private String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
    private String PREFIX = "--", LINE_END = "\r\n";

    public UploadUtils(Context context){
        this.mJSONBuilder=new JSONObject();
        this.context=context;
    }

    public  String uploadFile(File file, String RequestURL) throws JSONException {
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型

        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("Charset", CHARSET); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);

            if (file != null) {
                Log.e("UploadUtils","File Not Null!");
                DataOutputStream dos = new DataOutputStream(
                        conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);

                sb.append("Content-Disposition: form-data; name=\"upload\"; filename=\""
                        + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="
                        + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                     Log.e(TAG, "uploadFile before InputStream ");
                    Log.e(TAG, "uploadFile before2 InputStream ");
                InputStream is=context.getResources().getAssets().open("ic_test2.png");
                     Log.e(TAG, "uploadFile after InputStream ");
               // InputStream is = new FileInputStream(file);

                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                    Log.e(TAG, "uploadFile data" + mJSONBuilder.toString());
                dos.write(LINE_END.getBytes());
                addFormField(mJSONBuilder, dos);

                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();
                dos.write(end_data);
                dos.flush();
                InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder strBuffer = new StringBuilder();
                String line;
                if(conn.getResponseCode()==200){
                    while ((line = bufferedReader.readLine()) != null) {
                        strBuffer.append(line);
                    }
                    return strBuffer.toString();
                }
            }else{
                Log.v("Upload","文件为空");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "uploadFile exception:"+e);
        }
        return null;
    }
    public void sendPhoto(Context context){
        File file = getFileDir(context, "ic_test2");
        new UploadImage(file.getPath()+".png").execute();
    }
    private File getFileDir(Context context, String dirName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            if(context.getExternalCacheDir() != null){
                cachePath = context.getExternalCacheDir().getPath();
            }else{
                cachePath = context.getCacheDir().getPath();
            }
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        Log.e("Account::path",cachePath);
        return new File(cachePath + File.separator + dirName);
    }
    private class UploadImage extends AsyncTask<String,Void,JSONObject> {
        private String imagePath;

        public UploadImage(String imagePath) {
            this.imagePath = imagePath;
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                StringBuilder stringBuilder = new StringBuilder("http://219.216.65.182:8080/NationalService" +
                        "/MoblieServantRegisteAction?operation=_update");
                File file = new File(imagePath);

                String result = uploadFile(file, stringBuilder.toString());
                Log.e("sendPhotoResult"," "+result.toString()+" ");
                return new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                if(jsonObject!=null){
                    String imageUrl = jsonObject.getString("upload");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void addParameter(String name, String value){
        try {
            mJSONBuilder.put(name,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setConnectionListener(ConnectionListener connectionListener) {
        this.mConnectionListener = connectionListener;
    }
    public interface ConnectionListener{
        void onConnectionSucceeded(JSONObject result);
        void onConnectionFailed(String errorMessage);
    }
    private void addFormField(JSONObject params, DataOutputStream output) throws JSONException {
        StringBuilder sb = new StringBuilder();
        Iterator iterator = params.keys();
        while(iterator.hasNext()){
            String key = (String) iterator.next();
            String value =params.getString(key);
            sb.append(PREFIX + BOUNDARY + LINE_END);
           // sb.append("Content-Disposition: form-data; name=/\"" + key + "/"+ LINE_END);
               //sb.append("Content-Disposition: form-data; name=\""+ key + "\"/r/n/r/n");
                sb.append("Content-Disposition: form-data; name=\""+ key + "\""+LINE_END);
                sb.append(LINE_END);
            sb.append(value + LINE_END);
        }
        Log.e("uploadDataInfo",sb.toString());
        try{
            output.write(sb.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}