package edu.neu.qmjz.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/25
 * Project: QMJZ
 * Package: edu.neu.qmjz.handler
 */
public class NetworkServiceManager {
	private JSONObject mJSONBuilder;
	private String mUrl;
	private ConnectionListener mConnectionListener;

	public NetworkServiceManager(String url) {
		this.mJSONBuilder = new JSONObject();
		this.mUrl = url;
	}

	public void setConnectionListener(ConnectionListener connectionListener) {
		this.mConnectionListener = connectionListener;
	}

	public void addParameter(String name, String value){
		try {
			mJSONBuilder.put(name,value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void sendAction(){
		String query = mJSONBuilder.toString();
		new NetworkConnection(mUrl,query).execute();
	}

	public interface ConnectionListener{
		void onConnectionSucceeded(JSONObject result);
		void onConnectionFailed(String errorMessage);
	}

	private class NetworkConnection extends AsyncTask<String, Integer, JSONObject>{
		private URL mUrl;
		private String mQuery;
		private HttpURLConnection mConnection;

		public NetworkConnection(String url, String query) {
			try {
				this.mUrl = new URL(url);
				this.mQuery = query;
				mConnection = (HttpURLConnection) mUrl.openConnection();
				mConnection.setReadTimeout(15000);
				mConnection.setConnectTimeout(15000);
				mConnection.setRequestProperty("Content-Type", "application/json");
				mConnection.setRequestProperty("Accept", "application/json");
				mConnection.setRequestMethod("POST");
				mConnection.setDoInput(true);
				mConnection.setDoOutput(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected JSONObject doInBackground(String... strings) {
			try {
				Log.v("Network Connection", "Posting...");
				// send post action
				mConnection.connect();

				OutputStream outputStream = mConnection.getOutputStream();
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
				bufferedWriter.write(mQuery);
				bufferedWriter.flush();
				bufferedWriter.close();
				outputStream.close();

				// receive response
				int responseCode = mConnection.getResponseCode();
				if(responseCode == HttpURLConnection.HTTP_OK){
					Log.v("Network Connection","Response OK");
					String line;
					StringBuilder stringBuilder = new StringBuilder();
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
					while ((line = bufferedReader.readLine())!=null){
						stringBuilder.append(line);
					}

					String response = stringBuilder.toString();
					return new JSONObject(response);
				}else{
					Log.v("Network Connection","Response: "+ responseCode);
					mConnectionListener.onConnectionFailed("Connection Error. Response: " + responseCode);
					return null;
				}
			} catch (IOException | JSONException e) {
				Log.v("Network Connection","Exception caught");
				mConnectionListener.onConnectionFailed("Exception happened. See log for more information.");
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(JSONObject jsonObject) {
			if(jsonObject == null){
				mConnectionListener.onConnectionFailed("JSONObject is null");
				return;
			}
			if(mConnectionListener != null) mConnectionListener.onConnectionSucceeded(jsonObject);
		}
	}
}

