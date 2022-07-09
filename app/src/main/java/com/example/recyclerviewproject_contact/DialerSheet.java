package com.example.recyclerviewproject_contact;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialerSheet extends BottomSheetDialogFragment {

    private static final String EXTRA_KEY_INDEX = "628";

    OnEventListener eventListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        eventListener=(OnEventListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_container , container , false);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME ,R.style.AppBottomSheetDialogTheme);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int index = getArguments().getInt(EXTRA_KEY_INDEX);

        TextView tvFirst=getView().findViewById(R.id.tvSheetFirst);
        TextView tvFullName=getView().findViewById(R.id.tvSheettFull);
        TextView tvPhoneNumber=getView().findViewById(R.id.tvPhoneNumber);
        ImageView ivCall=getView().findViewById(R.id.ivCall);

        tvFirst.setText(ApplicationClass.contacts.get(index).getFullName().substring(0,1));
        tvFullName.setText(ApplicationClass.contacts.get(index).getFullName());
        tvPhoneNumber.setText(ApplicationClass.contacts.get(index).getPhoneNumber());


        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListener.onCallClicked(index);
            }
        });

    }

    interface OnEventListener
    {
        void onCallClicked(int index);
    }

    public static DialerSheet newInstance(int index) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_KEY_INDEX , index);
        DialerSheet fragment = new DialerSheet();
        fragment.setArguments(args);
        return fragment;
    }
}
