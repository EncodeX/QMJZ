package edu.neu.qmjz.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/26
 * Project: QMJZ
 * Package: edu.neu.qmjz.view
 */
public class FilterPickerLayout extends RelativeLayout {
	public final static int STATE_CLOSE = 0;//close
	public final static int STATE_PICKING_COUNTY = 1;//picker_county
	public final static int STATE_PICKING_SERVICE_TYPE = 2;//service_type

	private View mMask;

	private float mViewHeight;
	private boolean mIsInitialized;
	private boolean mIsLayoutSlideOut;
	private DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator(4.0f);

	public FilterPickerLayout(Context context) {
		this(context, null);
	}

	public FilterPickerLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FilterPickerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setMask(View mask){
		mMask = mask;
	}

	public void slide(){
		if(!mIsInitialized){
			initView();
		}

		AnimatorSet slideAnimation;
		if(mIsLayoutSlideOut){
			slideAnimation = buildSlideAnimation(this, 0);

			if(mMask != null){
				slideAnimation.playTogether(buildAlphaAnimation(mMask, 0.0f));
			}
		}else {
			slideAnimation = buildSlideAnimation(this, -mViewHeight * 3 / 4);

			if(mMask != null){
				mMask.setVisibility(VISIBLE);
				slideAnimation.playTogether(buildAlphaAnimation(mMask, 1.0f));
			}
		}

		slideAnimation.addListener(animatorListener);
		slideAnimation.start();
	}

	public boolean isIsLayoutSlideOut(){
		return mIsLayoutSlideOut;
	}

	private void initView(){
		mViewHeight = Double.valueOf(this.getHeight()).floatValue();
		mIsInitialized = true;
	}

	private AnimatorSet buildSlideAnimation(View target, float targetPosY){

		AnimatorSet slideAnimation = new AnimatorSet();
		slideAnimation.playTogether(
				ObjectAnimator.ofFloat(target, "translationY", targetPosY)
		);
		slideAnimation.setInterpolator(mDecelerateInterpolator);

		slideAnimation.setDuration(500);
		return slideAnimation;
	}

	private AnimatorSet buildAlphaAnimation(View target, float alpha){

		AnimatorSet menuSlideAnimation = new AnimatorSet();
		menuSlideAnimation.playTogether(
				ObjectAnimator.ofFloat(target, "alpha", alpha)
		);
		menuSlideAnimation.setInterpolator(mDecelerateInterpolator);

		menuSlideAnimation.setDuration(500);
		return menuSlideAnimation;
	}


	private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
		@Override
		public void onAnimationStart(Animator animation) {

		}

		@Override
		public void onAnimationEnd(Animator animation) {
			mIsLayoutSlideOut = !mIsLayoutSlideOut;
			if(!mIsLayoutSlideOut) mMask.setVisibility(GONE);
		}

		@Override
		public void onAnimationCancel(Animator animation) {

		}

		@Override
		public void onAnimationRepeat(Animator animation) {

		}
	};
}
