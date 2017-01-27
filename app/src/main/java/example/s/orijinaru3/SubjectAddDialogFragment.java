package example.s.orijinaru3;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

/**
 * Created by s on 2016/09/30.
 */

public class SubjectAddDialogFragment extends DialogFragment {
    EditText editText2;


    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);


    }

    NoticeDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {

//            throw new ClassCastException(activity.toString()
//                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.subject_add, null));
        editText2 = (EditText)dialog.findViewById(R.id.editText2);
        return builder
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogPositiveClick(SubjectAddDialogFragment.this);
                    }
                })
                .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     //   SubjectAddDialogFragment.this.getDialog().cancel();

                        dismiss();
                    }
                })
                .create();
    }

//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        editText2 = (EditText) getView().findViewById(R.id.editText2);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//
////        btok = (Button) getView().findViewById(R.id.button3);
////        btcancel = (Button) getView().findViewById(R.id.button4);
//
////        dialog = new Dialog(getActivity());
//
////        public static SubjectAddDialogFragment newInstance() {
////            SubjectAddDialogFragment fragment = new SubjectAddDialogFragment();
////            return fragment;
////        }
////        btok.setOnClickListener(okButtonClickListener);
//
//
////        btcancel.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                dialog.dismiss();
////            }
////        });
//
////        return dialog;
//
//        builder.setView(inflater.inflate(R.layout.subject_add, null))
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        mListener.onDialogPositiveClick(SubjectAddDialogFragment.this);
//                    }
//                })
//                .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        SubjectAddDialogFragment.this.getDialog().cancel();
////                        mListener.onDialogPositiveClick(SubjectAddDialogFragment.this);
//                        dismiss();
//                    }
//                });
//        return builder.create();
//    }

}
//    public void setOnOkButtonClickListener(View.OnClickListener listener) {
//        this.okButtonClickListener = listener;
//    }

