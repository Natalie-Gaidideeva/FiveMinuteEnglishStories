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

public class FragmentTextType extends DialogFragment {
    private String f;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog1)
                .setSingleChoiceItems(getResources().getStringArray(R.array.fonts), -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                f = "droid_serif.ttf";
                                break;
                            case 1:
                                f = "montserrat.ttf";
                                break;
                            case 2:
                                f = "open_sans.ttf";
                                break;
                            case 3:
                                f = "roboto_condensed_regular.ttf";
                                break;
                        }
                    }
                })
                .setPositiveButton("CHOOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(f!=null) {
                            PreferencesUtil.setPrefFont(getActivity(), f);
                            Book.setBookFont(PreferencesUtil.getPrefFont(getActivity()));
                            dialog.dismiss();
                        }
                        else {
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