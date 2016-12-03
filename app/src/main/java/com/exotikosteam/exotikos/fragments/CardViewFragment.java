package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.models.trip.Flight;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

public class CardViewFragment <T extends Fragment> extends Fragment implements ExpandableCard {

    private static final String TITLE_BUNDLE = "titleId";
    private static final String TIME_BUNDLE = "time";
    private static final String IMAGE_BUNDLE = "imageId";
    private static final String COLLAPSED_BUNDLE = "collapsed";
    private static final String FLIGHT_BUNDLE = "flightB";

    private View rootView;
    private String mCardName;
    private String mRelativeTime;
    private int mImageId;
    private Flight mFlight;
    private T  f;

    // Bindings
    @BindView(R.id.tvCardName) TextView tvCardName;
    @BindView(R.id.tvTime) TextView tvTime;
    @BindView(R.id.rlCardContents)
    ExpandableRelativeLayout rlCardContents;
    @BindView(R.id.cards)
    FrameLayout card;
    @BindView(R.id.ivBackground)
    ImageView ivBackground;
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
        rootView = inflater.inflate(R.layout.fragment_parent_card, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCardName = getActivity().getString(getArguments().getInt(TITLE_BUNDLE));
        mRelativeTime = getArguments().getString(TIME_BUNDLE);
        mImageId = getArguments().getInt(IMAGE_BUNDLE);
        mFlight = Parcels.unwrap(getArguments().getParcelable(FLIGHT_BUNDLE));

        tvCardName.setText(mCardName);
        tvTime.setText(mRelativeTime);
        if (mImageId < 0) {
           // ivBackground.setImageResource(R.drawable.card_image_gradient_shape);
            Glide.with(getContext())
                    .load(mFlight.getArrivalCityImageUrl())
                    .into(ivBackground);
            ivBackground.setImageAlpha(180);
            ivBackground.setBackgroundResource(R.drawable.card_image_gradient_shape);
        } else {
           // ivBackground.setBackgroundResource(mImageId);
           // ivBackground.setImageResource(R.drawable.card_image_gradient_shape_light);
            ivBackground.setImageResource(mImageId);
            ivBackground.setImageAlpha(180);
            ivBackground.setBackgroundResource(R.drawable.card_image_gradient_shape);
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
        ivBackground .setOnClickListener(v -> titleClickSubject.onNext(CardViewFragment.this));
    }

    public PublishSubject<CardViewFragment> getTitleClickSubject() {
        return titleClickSubject;
    }

    @Override
    public void expand() {
        if (!isDetached()) {
            rlCardContents.expand();
        }
    }

    @Override
    public void collapse() {
        if (!isDetached()) {
            rlCardContents.collapse();
        }
    }

    @Override
    public void toggle() {
        if (!isDetached()) {
            rlCardContents.toggle();
        }
    }



}
