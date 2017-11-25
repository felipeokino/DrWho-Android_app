package v1.localhost.drwho.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import v1.localhost.drwho.login.SingletonUser;

/**
 * Created by felipe on 25/11/17.
 */

public class AlertDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Seja bem-vindo: " + SingletonUser.getInstance().getUsuario().getName() +
                    "\nSeu numero de acesso é: " + SingletonUser.getInstance().getUsuario().getId())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            return builder.create();
        }

}
