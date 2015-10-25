package edu.neu.qmjz.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.neu.qmjz.R;
import edu.neu.qmjz.utils.NetUtils;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/21
 * Project: QMJZ
 * Package: edu.neu.qmjz.fragment
 */
public class AccountFragment extends Fragment{
	@InjectView(R.id.text_city)
	TextView text_city;
	@InjectView(R.id.text_ID)
	TextView text_ID;
	@InjectView(R.id.text_location)
	TextView text_location;
	@InjectView(R.id.text_name)
	TextView text_name;
	@InjectView(R.id.text_phone)
	TextView text_phone;
	@InjectView(R.id.text_tele_number)
	TextView text_tele_number;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_account, container, false);
		queryFromService();
		ButterKnife.inject(this, rootView);
		text_city.setText("");
		return rootView;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void queryFromService(){
		String urlPath = "192.168.0.6:8080/FamilyServiceSystem/MobileServantInfoAction?operation= _query";
		URL url;
		try {
			url = new URL(urlPath);
			JSONObject client = new JSONObject();
			client.put("servantID：", "服务人员的用户名");
			String content = String.valueOf(client);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(content.getBytes());
			os.close();

			int code = conn.getResponseCode();
			if (code == 200) {
				InputStream is = conn.getInputStream();
				String json = NetUtils.readString(is);
				JSONObject jsonObject = new JSONObject(json);
				//Person p= new Person();
				String username =jsonObject.getString("servantName");
				String servantProvince = jsonObject.getString("servantProvince");
			} else {
				Toast.makeText(getActivity(),"数据提交失败",Toast.LENGTH_SHORT);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
