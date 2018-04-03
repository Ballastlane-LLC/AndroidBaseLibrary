package com.ballastlane.ballastlanebaseapp.app.ui.main.recyclerview

import android.arch.lifecycle.ViewModel
import android.util.Pair
import com.ballastlane.ballastlanebaseapp.entities.Item
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class ViewModelRecyclerView : ViewModel() {

    private val MIN_RANDOM_THEMES_TO_GENERATE = 10
    private val MAX_RANDOM_THEMES_TO_GENERATE = 20
    private val MAX_LOAD_FETCH_ITEMS_DELAY = 1//Seconds
    private val MAX_LOAD_MORE_FETCH_ITEMS_DELAY = 2//Seconds

    val listItems: Single<Pair<List<Item>, Boolean>>
        get() = Single
                .just<List<Item>>(generateRandomThemeArray())
                .delay(MAX_LOAD_FETCH_ITEMS_DELAY.toLong(), TimeUnit.SECONDS)
                .flatMap<Pair<List<Item>, Boolean>>(getMapperFunc(true))
                .observeOn(AndroidSchedulers.mainThread())


    val moreListItems: Single<Pair<List<Item>, Boolean>>
        get() = Single
                .just<List<Item>>(generateRandomThemeArray())
                .delay(MAX_LOAD_MORE_FETCH_ITEMS_DELAY.toLong(), TimeUnit.SECONDS)
                .flatMap<Pair<List<Item>, Boolean>>(getMapperFunc(false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

    private fun getMapperFunc(hasMoreToload: Boolean): Function<List<Item>, Single<Pair<List<Item>, Boolean>>> {
        return object : Function<List<Item>, Single<Pair<List<Item>, Boolean>>> {
            @Throws(Exception::class)
            override fun apply(items: List<Item>): Single<Pair<List<Item>, Boolean>> {
                return Single.just<Pair<List<Item>, Boolean>>(Pair<List<Item>, Boolean>(items, hasMoreToload))
            }
        }
    }

    private fun generateRandomThemeArray(): List<Item> {
        val items = ArrayList<Item>()
        val min = MIN_RANDOM_THEMES_TO_GENERATE
        val max = MAX_RANDOM_THEMES_TO_GENERATE
        val amount = Random().nextInt(max - min + 1) + min
        for (i in 0 until amount) {
            items.add(Item())
        }
        return items
    }

}
