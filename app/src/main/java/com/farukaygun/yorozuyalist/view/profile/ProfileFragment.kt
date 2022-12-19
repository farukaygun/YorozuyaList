package com.farukaygun.yorozuyalist.view.profile

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
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
    override fun getViewBinding(): FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)

    override fun start(savedInstanceState: Bundle?) {
        viewModelProfile.getUser()
        lifecycleLaunch {
            viewModelProfile.userData.collectLatest {
                when(it) {
                    //is ResponseHandler.Loading -> binding.progressBarProfile.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        it.data?.userAnimeStatistics?.let { statistics ->
                            withContext(Dispatchers.Main) {
                                binding.imageViewPicture.downloadFromUrl(it.data.picture)
                                binding.textUsername.text = it.data.name
                                binding.textLocation.text = it.data.location
                                binding.textBirthday.formatDate(it.data.birthday)
                                binding.textJoinedDate.formatDate(it.data.joinedAt)
                                binding.textWatching.append(" (" + it.data.userAnimeStatistics.numItemsWatching.toString() + ")")
                                binding.textOnHold.append(" (" + it.data.userAnimeStatistics.numItemsOnHold.toString() + ")")
                                binding.textDropped.append(" (" + it.data.userAnimeStatistics.numItemsDropped.toString() + ")")
                                binding.textCompleted.append(" (" + it.data.userAnimeStatistics.numItemsCompleted.toString() + ")")
                                binding.textPlanToWatch.append(" (" + it.data.userAnimeStatistics.numItemsPlanToWatch.toString() + ")")
                                binding.textDay.append(it.data.userAnimeStatistics.numDays.toString())
                                binding.textEpisodes.append(it.data.userAnimeStatistics.numEpisodes.toString())
                                binding.textMeanScore.append(it.data.userAnimeStatistics.meanScore.toString())
                            }
                            viewModelProfile.drawChart(binding.donutProgressView, statistics)
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }
}