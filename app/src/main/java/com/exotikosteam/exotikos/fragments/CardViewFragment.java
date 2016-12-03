package com.exotikosteam.exotikos.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentParentCardBinding;
import com.exotikosteam.exotikos.models.trip.Flight;

import org.parceler.Parcels;

import rx.subjects.PublishSubject;

public class CardViewFragment <T extends Fragment> extends Fragment implements ExpandableCard {

    private static final String TITLE_BUNDLE = "titleId";
    private static final String TIME_BUNDLE = "time";
    private static final String IMAGE_BUNDLE = "imageId";
    private static final String COLLAPSED_BUNDLE = "collapsed";
    private static final String FLIGHT_BUNDLE = "flightB";


    private View mRootView;
    private String mCardName;
    private String mRelativeTime;
    private int mImageId;
    private Flight mFlight;
    private T  f;
    private FragmentParentCardBinding mBinding;
    // Event topics
    private final PublishSubject<CardViewFragment> titleClickSubject = PublishSubject.create();

    public static CardViewFragment newInstance(int  titleId,
                                               int imageId,
                                               String time,
                                               Flight flight,
                                               boolean isCollapsed) {
        Bundle args = new Bundle();
        args.putInt(TITLE_BUNDLE, titleId);
        args.putString(TIME_BUNDLE, time);
        args.putBoolean(COLLAPSED_BUNDLE, isCollapsed);
        args.putInt(IMAGE_BUNDLE, imageId);
        args.putParcelable(FLIGHT_BUNDLE, Parcels.wrap(flight));

        CardViewFragment fragment = new CardViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setFragment(T fragment) {
        f = fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_card, container, false);
        mRootView = mBinding.getRoot();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCardName = getActivity().getString(getArguments().getInt(TITLE_BUNDLE));
        mRelativeTime = getArguments().getString(TIME_BUNDLE);
        mImageId = getArguments().getInt(IMAGE_BUNDLE);
        mFlight = Parcels.unwrap(getArguments().getParcelable(FLIGHT_BUNDLE));

        mBinding.tvCardName.setText(mCardName);
        mBinding.tvTime.setText(mRelativeTime);
        if (mImageId < 0) {
            Glide.with(getContext())
                    .load(mFlight.getArrivalCityImageUrl())
                    .into(mBinding.ivBackground);
            mBinding.ivBackground.setImageAlpha(180);
            mBinding.ivBackground.setBackgroundResource(R.drawable.card_image_gradient_shape);
        } else {
            mBinding.ivBackground.setImageResource(mImageId);
            mBinding.ivBackground.setImageAlpha(180);
            mBinding.ivBackground.setBackgroundResource(R.drawable.card_image_gradient_shape);
        }

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.cards, f, mCardName);
        ft.commit();

        if (getArguments().getBoolean(COLLAPSED_BUNDLE)) {
            collapse();
        } else {
            expand();
        }

        setupListeners();
    }

    private void setupListeners() {
        mBinding.ivBackground .setOnClickListener(v -> titleClickSubject.onNext(CardViewFragment.this));
    }

    public PublishSubject<CardViewFragment> getTitleClickSubject() {
        return titleClickSubject;
    }

    @Override
    public void expand() {
        if (!isDetached()) {
            mBinding.rlCardContents.expand();
            animateHintButton();
        }
    }

    @Override
    public void collapse() {
        if (!isDetached()) {
            mBinding.rlCardContents.collapse();
        }
    }

    @Override
    public void toggle() {
        if (!isDetached()) {
            mBinding.rlCardContents.toggle();
            animateHintButton();
        }
    }

    private void animateHintButton() {
        if (f != null && f.getView() != null) {
            Button btnNext = (Button) f.getView().findViewById(R.id.btnNext);
            if (btnNext != null) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(btnNext, "alpha", 0, 1);
                anim.setDuration(2000);
               // anim.start();

                float positionX = btnNext.getX() - 40;
                ObjectAnimator moveAnim = ObjectAnimator.ofFloat(btnNext, "X", positionX, positionX + 40, positionX);
                moveAnim.setDuration(3000);
                moveAnim.setInterpolator(new BounceInterpolator());

                AnimatorSet set = new AnimatorSet();
                set.playTogether(anim, moveAnim);

                set.start();
            }
        }

    }

}
