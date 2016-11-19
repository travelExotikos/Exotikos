package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.exotikosteam.exotikos.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

public class CardViewFragment <T extends Fragment> extends Fragment implements ExpandableCard {

    private View rootView;

    private String cardName;
    private String relativeTime;
    private T  f;

    // Bindings
    @BindView(R.id.tvCardName) TextView tvCardName;
    @BindView(R.id.tvTime) TextView tvTime;
    @BindView(R.id.rlCardContents)
    ExpandableRelativeLayout rlCardContents;
    @BindView(R.id.cards)
    FrameLayout card;
    // Event topics
    private final PublishSubject<CardViewFragment> titleClickSubject = PublishSubject.create();

    public static CardViewFragment newInstance(String name, String time,
                                               boolean isCollapsed) {

        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("time", time);
        args.putBoolean("collapsed", isCollapsed);

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

        cardName = getArguments().getString("name");
        relativeTime = getArguments().getString("time");

        tvCardName.setText(cardName);
        tvTime.setText(relativeTime);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.cards, f, cardName);
        ft.commit();

        setupListeners();
    }

    private void setupListeners() {
        tvCardName.setOnClickListener(v -> titleClickSubject.onNext(CardViewFragment.this));
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
