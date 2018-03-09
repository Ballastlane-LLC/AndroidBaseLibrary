package com.ballastlane.android.baselibrary.common.base

import com.ballastlane.android.baselibrary.common.base.busevents.alert.EventAlertDialog
import com.ballastlane.android.baselibrary.common.base.busevents.progress.EventProgressDialog
import com.ballastlane.android.baselibrary.common.base.busevents.snackbar.EventSnackbarMessage

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
interface BaseEventBusListener {

    fun onProgressDialogEvent(event: EventProgressDialog)

    fun onAlertDialogEvent(alert: EventAlertDialog)

    fun onSnackbarMessageEvent(event: EventSnackbarMessage)

}