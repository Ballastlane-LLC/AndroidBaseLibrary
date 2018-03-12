package com.ballastlane.android.baselibrary.common.utils

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.ballastlane.android.baselibrary.R


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */


fun showConfirmation(context: Context, title: Int, message: Int, listener: DialogInterface.OnClickListener): AlertDialog? {
    return AlertDialog.Builder(context)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.yes, listener)
            .setNegativeButton(R.string.no, null)
            .show()
}

