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
import edu.neu.qmjz.adapter.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

	private static int[] MAIN_TAB_ICON = {
			R.drawable.ic_home,
			R.drawable.ic_description,
			R.drawable.ic_assignment,
			R.drawable.ic_person
	};

	@InjectView(R.id.app_bar)
	Toolbar mToolBar;
	ActionBar mAppBar;
	@InjectView(R.id.app_bar_tab)
	TabLayout mAppBarTab;
	@InjectView(R.id.view_pager)
	ViewPager mViewPager;

	MainPagerAdapter mMainPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.inject(this);

		setSupportActionBar(mToolBar);
		mAppBar = getSupportActionBar();

		mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mMainPagerAdapter);

		mAppBarTab.setTabMode(TabLayout.MODE_FIXED);
		mAppBarTab.setupWithViewPager(mViewPager);
		for(int i=0;i<mAppBarTab.getTabCount();i++){
			TabLayout.Tab tab = mAppBarTab.getTabAt(i);
			if(tab!=null){
				tab.setIcon(MAIN_TAB_ICON[i]);
			}
		}
	}
}
