package com.farukaygun.yorozuyalist.view.profile

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.FragmentProfileBinding
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.downloadFromUrl
import com.farukaygun.yorozuyalist.util.formatDate
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
	private val viewModelProfile: ProfileViewModel by viewModels()
	override val isAppbarVisible: Boolean = true
	override fun getViewBinding(): FragmentProfileBinding =
		FragmentProfileBinding.inflate(layoutInflater)


	override fun start() {
		lifecycleLaunch {
			viewModelProfile.userData.collectLatest {
				when (it) {
					//is ResponseHandler.Loading -> binding.progressBarProfile.visibility = View.VISIBLE
					is ResponseHandler.Success -> {
						it.data?.userAnimeStatistics?.let { statistics ->
							withContext(Dispatchers.Main) {
								binding.imageViewPicture.downloadFromUrl(it.data.picture)
								binding.textUsername.text = it.data.name
								binding.textLocation.text = it.data.location
								binding.textBirthday.formatDate(it.data.birthday)
								binding.textJoinedDate.formatDate(it.data.joinedAt)
								binding.textWatching.text = getString(R.string.watching_num, it.data.userAnimeStatistics.numItemsWatching.toString())
								binding.textOnHold.text = getString(R.string.on_hold_num, it.data.userAnimeStatistics.numItemsOnHold.toString())
								binding.textDropped.text = getString(R.string.dropped_num, it.data.userAnimeStatistics.numItemsDropped.toString())
								binding.textCompleted.text = getString(R.string.completed_num, it.data.userAnimeStatistics.numItemsCompleted.toString())
								binding.textPlanToWatch.text = getString(R.string.plan_to_watch_num, it.data.userAnimeStatistics.numItemsPlanToWatch.toString())
								binding.textDay.text = getString(R.string.days_num, it.data.userAnimeStatistics.numDays.toString())
								binding.textEpisodes.text = getString(R.string.episodes_num, it.data.userAnimeStatistics.numEpisodes.toString())
								binding.textMeanScore.text = getString(R.string.mean_num, it.data.userAnimeStatistics.meanScore.toString())
							}
							viewModelProfile.drawChart(binding.donutProgressView, statistics)
						}
					}
					is ResponseHandler.Error -> Toast.makeText(context,
						"${it.message}",
						Toast.LENGTH_SHORT).show()
					else -> {}
				}
			}
		}
	}
}