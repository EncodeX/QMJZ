package edu.neu.qmjz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.neu.qmjz.R;
import edu.neu.qmjz.adapter.AccountListAdapter;
import edu.neu.qmjz.adapter.FilterPickerAdapter;
import edu.neu.qmjz.utils.NetUtils;
import edu.neu.qmjz.bean.Person;
import edu.neu.qmjz.utils.NetworkServiceManager;
import edu.neu.qmjz.view.FilterPickerLayout;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/21
 * Project: QMJZ
 * Package: edu.neu.qmjz.fragment
 */
public class AccountFragment extends Fragment{

	@Bind(R.id.account_list)
	RecyclerView account_list;


	private ArrayList<String> countryList;
	private AccountListAdapter accountListAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e("account", "onCreate");
		super.onCreate(savedInstanceState);
		countryList=new ArrayList<>();
		initData();
		accountListAdapter=new AccountListAdapter(getContext(),countryList);

	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.e("account", "onCreateView");
		ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_account, container, false);
		ButterKnife.bind(this, rootView);
		account_list.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
		account_list.setAdapter(accountListAdapter);

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

	public void initData(){
		NetworkServiceManager serviceManager =
				new NetworkServiceManager("http://219.216.65.182:8080/NationalService" +
						"/MobileCountyInfoAction?operation=_querycoveredCounty");
		serviceManager.addParameter("cityName","沈阳市");
		serviceManager.setConnectionListener(mCountyRefreshListener);
		serviceManager.sendAction();
	}
	private NetworkServiceManager.ConnectionListener mCountyRefreshListener = new NetworkServiceManager.ConnectionListener() {
		@Override
		public void onConnectionSucceeded(JSONObject result) {
			try {
				if(result.getString("serverResponse").equals("Success")){
					Log.e("countryName",result.toString());
					countryList.clear();
					JSONArray data = result.getJSONArray("data");
					for(int i=0; i<data.length(); i++){
						JSONObject temp = (JSONObject)data.get(i);

						countryList.add(temp.getString("countyName"));
					}
					Log.e("countryName",String.valueOf(countryList.size()));
					accountListAdapter.notifyDataSetChanged();
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
