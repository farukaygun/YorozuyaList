package com.farukaygun.yorozuyalist.view.profile

import android.app.Application
import android.graphics.Color
import app.futured.donut.DonutProgressView
import app.futured.donut.DonutSection
import com.farukaygun.yorozuyalist.model.UserAnimeStatistics
import com.farukaygun.yorozuyalist.model.User
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileViewModel(application: Application) : BaseViewModel(application) {
    private val api = Api()

    private val userDataFlow = MutableStateFlow<ResponseHandler<User>?>(null)
    val userData = userDataFlow

    fun getUser() {
        viewModelLaunch {
            userDataFlow.emit(ResponseHandler.Loading())
            api.getUser().let {
                userDataFlow.emit(it)
            }
        }
    }

    fun drawChart(donutProgressView: DonutProgressView, userAnimeStatistics: UserAnimeStatistics) {
        val watching = DonutSection(
            name = "Watching",
            color = Color.parseColor("#2db039"),
            amount = userAnimeStatistics.numItemsWatching.toFloat()
        )
        val completed = DonutSection(
            name = "Completed",
            color = Color.parseColor("#26448f"),
            amount = userAnimeStatistics.numItemsCompleted.toFloat()
        )
        val planToWatch = DonutSection(
            name = "Plan To Watch",
            color = Color.parseColor("#c3c3c3"),
            amount = userAnimeStatistics.numItemsPlanToWatch.toFloat()
        )
        val dropped = DonutSection(
            name = "Dropped",
            color = Color.parseColor("#a12f31"),
            amount = userAnimeStatistics.numItemsDropped.toFloat()
        )
        val onHold = DonutSection(
            name = "On-Hold",
            color = Color.parseColor("#f9d457"),
            amount = userAnimeStatistics.numItemsOnHold.toFloat()
        )

        donutProgressView.cap = 0f
        println(donutProgressView.cap)
        donutProgressView.submitData(listOf(
            watching,
            completed,
            planToWatch,
            dropped,
            onHold
        ))
    }
}