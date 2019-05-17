package com.tekitsolutions.remindme.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.tekitsolutions.remindme.Activity.AddReminderActivity;
import com.tekitsolutions.remindme.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class NotificationCustomDialog extends AppCompatDialogFragment {

    public OnDialogSelectorListener dialogSelectorListener;
    int checkedItem = 0;
    AddReminderActivity addReminderActivity = new AddReminderActivity();
    private String[] itemList;
    private Context context;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        itemList = context.getResources().getStringArray(R.array.notification_type);
        checkedItem = addReminderActivity.getNotificationDay();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialogBoxStyle);

        builder.setCancelable(true);
        builder.setSingleChoiceItems(itemList, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                dialogSelectorListener.onSelectedOption(i, itemList[i]);
            }
        });
        builder.setNegativeButton(R.string.cancel_btn_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {

        this.context = context;
        super.onAttach(context);
        try {
            dialogSelectorListener = (OnDialogSelectorListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements OnDialogSelectorListener");
        }
    }

    public interface OnDialogSelectorListener {
        void onSelectedOption(int index, String text);
    }
}
