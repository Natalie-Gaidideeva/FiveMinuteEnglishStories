package com.ngaid.fiveminenglishstories.menuItems;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.ngaid.fiveminenglishstories.Book;
import com.ngaid.fiveminenglishstories.PreferencesUtil;
import com.ngaid.fiveminenglishstories.R;

public class FragmentTextSize extends DialogFragment {
    private Integer s;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog2)
                .setSingleChoiceItems(getResources().getStringArray(R.array.sizes), -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        s = Integer.valueOf(getResources().getStringArray(R.array.sizes)[i]);
                    }
                })
                .setPositiveButton("CHOOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(s!=null) {
                            PreferencesUtil.setPrefSize(getActivity(), s);
                            Book.setBookSize(PreferencesUtil.getPrefSize(getActivity()));
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(getActivity(),"NOTHING WAS CHOSEN!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
