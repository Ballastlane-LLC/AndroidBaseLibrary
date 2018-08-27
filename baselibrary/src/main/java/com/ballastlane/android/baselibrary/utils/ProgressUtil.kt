package com.ballastlane.android.baselibrary.utils

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.ballastlane.android.baselibrary.R


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 11/28/17.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

fun AlertDialog.Builder.showConfirmation(
        title: Int,
        message: Int,
        listener: DialogInterface.OnClickListener): AlertDialog? {
    return this
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.yes, listener)
            .setNegativeButton(R.string.no, null)
            .show()
}

