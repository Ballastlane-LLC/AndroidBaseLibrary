package com.ballastlane.ballastlanebaseapp.app.ui.main.events

import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ballastlane.android.baselibrary.common.base.BaseFragment
import com.ballastlane.android.baselibrary.common.base.busevents.alert.EventAlertDialog
import com.ballastlane.android.baselibrary.common.base.busevents.progress.EventProgressDialog
import com.ballastlane.android.baselibrary.common.base.busevents.snackbar.EventSnackbarMessage
import com.kogimobile.baselibrary.sample.R
import org.greenrobot.eventbus.EventBus

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

open class FrgEvents : BaseFragment(), EventHandlerEvents {

//    private var binding: FrgEventsBinding? = null

    override fun initVars() {

    }

//    fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View {
//        this.binding = DataBindingUtil.inflate<FrgEventsBinding>(inflater, R.layout.frg_events, container, false)
//        this.binding!!.setEventHandler(this)
//     return this.binding!!.getRoot()

//    }

    override fun initViews() {}

    override fun initListeners() {}

    override fun onClickEventSnackBar() {
        sendEventSnackbar()
    }

    override fun onClickEventProgress() {
        sendEventProgress()
    }

    override fun onClickEventAlert() {
        sendEventAlertDialog()
    }

    private fun sendEventSnackbar() {
        EventBus.getDefault()
                .post(
                        EventSnackbarMessage
                                .builder
                                .withMessage(R.string.frg_events_message_snackbar)
                                .build()
                )
    }

    private fun sendEventProgress() {
        EventBus.getDefault()
                .post(
                        EventProgressDialog
                                .builder
                                .withMessageId(R.string.frg_events_message_progress)
                                .withCancelable(true)
                                .build()
                )
    }

    private fun sendEventProgressDismiss() {
        EventBus.getDefault()
                .post(
                        EventProgressDialog
                                .builder
                                .dismiss()
                                .build()
                )
    }

    private fun sendEventAlertDialog() {
        EventBus.getDefault()
                .post(
                        EventAlertDialog.builder
                                .withTitleId(R.string.frg_events_title_alert_dialog)
                                .withMesageId(R.string.frg_events_message_alert_dialog)
                                .withPositiveButton(
                                        R.string.frg_events_message_positive_alert_dialog,
                                        DialogInterface.OnClickListener { dialog, which -> }
                                )
                                .withNegativeButton(
                                        R.string.frg_events_message_negative_alert_dialog,
                                        DialogInterface.OnClickListener { dialog, which -> }
                                )
                                .build()
                )
    }

    companion object {

        fun newInstance(): FrgEvents {
            val frg = FrgEvents()
            val args = Bundle()
            frg.arguments = args
            return frg
        }
    }

}