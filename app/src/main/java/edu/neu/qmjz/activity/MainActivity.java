package edu.neu.qmjz.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.neu.qmjz.R;

public class MainActivity extends AppCompatActivity {

	@InjectView(R.id.app_bar)
	Toolbar mToolBar;
	ActionBar mAppBar;
	@InjectView(R.id.app_bar_tab_layout)
	TabLayout mAppBarTabLayout;
	@InjectView(R.id.view_pager)
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.inject(this);

		setSupportActionBar(mToolBar);
		mAppBar = getSupportActionBar();

		mAppBarTabLayout.setupWithViewPager(mViewPager);
//		mAppBarTabLayout.addTab(mAppBarTabLayout.newTab().setIcon(R.mipmap.ic_launcher));
//		mAppBarTabLayout.addTab(mAppBarTabLayout.newTab().setIcon(R.mipmap.ic_launcher));
//		mAppBarTabLayout.addTab(mAppBarTabLayout.newTab().setIcon(R.mipmap.ic_launcher));
	}
}
