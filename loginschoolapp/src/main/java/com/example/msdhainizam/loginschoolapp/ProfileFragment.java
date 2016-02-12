package com.example.msdhainizam.loginschoolapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 01/02/2016.
 */
public class ProfileFragment extends Fragment implements OnProfileDataLoaded {

    private GuardianData profileData;
    private TextView name, email, phone, childContent;
    private OnProfileDataLoaded myListener;
    private Button addChildButton;
    private FragmentManager fm;

    public  ProfileFragment() {
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.profile_layout, container, false);

        name = (TextView) view.findViewById(R.id.tvNameContent);
        email = (TextView) view.findViewById(R.id.tvEmailContent);
        phone = (TextView) view.findViewById(R.id.tvPhoneContent);
        //childContent = (TextView) view.findViewById(R.id.tvChildrenContent);
        addChildButton = (Button) view.findViewById(R.id.buttonAddChild);
        fm = getActivity().getSupportFragmentManager();

        profileData = new GuardianData();

        //load the data on the backgroud, include the progressDialog
        new TaskLoadProfile(this, getActivity()).execute();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddChildDialogFragment dialogFragment = new AddChildDialogFragment();
                dialogFragment.show(fm, "Dialog Fragment");
            }
        });
    }

    //created to load the data from the asynctask, its a listener
    @Override
    public void onProfileDataLoaded(GuardianData profileData) {
        name.setText(profileData.getName());
        email.setText(profileData.getEmail());
        phone.setText(profileData.getPhoneNumber());

        String child = "";
        ArrayList<String> childName = profileData.getChildName();
        ArrayList<String> childIC = profileData.getChildICNumber();
        if(!childName.isEmpty()) {
            for (int i = 0; i < childName.size(); i++) {
                addChildTextView(childName.get(i), childIC.get(i));
            }
        } else {
            addChildTextView("NA", "NA");
        }
    }

    private void addChildTextView(String name, String icNumber) {

        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.layoutAddChild);
        TextView tvName = new TextView(getActivity());
        TextView tvICNumber = new TextView(getActivity());
        tvName.setText(name);
        tvName.setPadding(5, 0, 5, 0);
        tvICNumber.setText(icNumber);
        tvICNumber.setPadding(5,0,5,0);

        linearLayout.addView(tvName);
        linearLayout.addView(tvICNumber);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_message_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getContext(), "action pressed!", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

interface OnProfileDataLoaded {
    public void onProfileDataLoaded(GuardianData profileData);
}