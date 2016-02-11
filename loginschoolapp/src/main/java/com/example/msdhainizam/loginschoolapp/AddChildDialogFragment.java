package com.example.msdhainizam.loginschoolapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by IGWMobileTeam on 04/02/2016.
 */
public class AddChildDialogFragment extends DialogFragment {

    public void AddChildDialogFragment() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Adding Child");
        builder.setMessage("Enter the ic number");

        final EditText editText = new EditText(getActivity());
        editText.setId(0);
        builder.setView(editText);



        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String edValue = editText.getText().toString();
                if(!edValue.isEmpty() && edValue.length() > 0) {
                    new TaskAssignChild(getActivity(), edValue).execute();
                }else {
                    Toast.makeText(getActivity(), "Do not let it empty",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        return builder.create();
    }
}
