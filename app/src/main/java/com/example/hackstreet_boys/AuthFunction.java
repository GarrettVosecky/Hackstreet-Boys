package com.example.hackstreet_boys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.concurrent.atomic.AtomicReference;

public class AuthFunction {
    static boolean AlertSignOut(Context context)
    {
        AtomicReference<Boolean> bool = new AtomicReference<>(false);
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set the message show for the Alert time
        builder.setMessage("Are you sure you want to sign out?");

        builder.setTitle("Confirmation");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            bool.set(true);
            dialog.cancel();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();

        return bool.get();
    }
}
