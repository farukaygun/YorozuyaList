package com.farukaygun.yorozuyalist.view.details.anime

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.adapter.RelatedAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentAnimeDetailsBinding
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.*
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collectLatest

class AnimeDetailsFragment : BaseFragment<FragmentAnimeDetailsBinding>() {
    private val viewModelAnimeDetails: AnimeDetailsViewModel by viewModels()
    override val isAppbarVisible: Boolean = false
    override fun getViewBinding(): FragmentAnimeDetailsBinding = FragmentAnimeDetailsBinding.inflate(layoutInflater)

    private lateinit var relatedAdapter: RelatedAdapter
    private var isShowMore = false

    override fun start() {
        binding.textViewMore.setOnClickListener {
            isShowMore = !isShowMore
            when(isShowMore) {
                true -> {
                    binding.textViewMore.text = getString(R.string.less)
                    binding.textViewMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_round_arrow_drop_up_36)
                    binding.textViewSynopsis.maxLines = Int.MAX_VALUE
                }
                false -> {
                    binding.textViewMore.text = getString(R.string.more)
                    binding.textViewMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_round_arrow_drop_down_36)
                    binding.textViewSynopsis.maxLines = 6
                }
            }
        }

        val animeId = arguments?.getInt("id") ?: 0
        viewModelAnimeDetails.getAnimeDetails(animeId)
        lifecycleLaunch {
            viewModelAnimeDetails.animeDetails.collectLatest { it ->
                when(it) {
                    is ResponseHandler.Loading -> binding.circularProgressBar.show()
                    is ResponseHandler.Success -> {
                        binding.circularProgressBar.hide()
                        it.data?.let { details ->
                            relatedAdapter = RelatedAdapter(0, details.related)
                            binding.recyclerViewRelatedAnime.adapter = relatedAdapter

                            binding.shapeableImageView.downloadFromUrl(details.mainPicture.large)
                            binding.textViewName.text = details.title
                            binding.textViewEpisodes.formatMediaType(details.mediaType, details.numEpisodes)
                            binding.textViewScore.text = details.mean.toString()
                            binding.textViewScoringUsers.formatInt(details.numScoringUsers)
                            binding.textViewStatus.formatStatus(details.status)

                            val chipGroup = binding.chipGroup
                            details.genres.map { genre ->
                                val chip = Chip(chipGroup.context)
                                chip.text = genre.name

                                chipGroup.addView(chip)
                            }

                            binding.textViewSynopsis.text = details.synopsis
                            binding.textViewRank.text = details.rank.toString()
                            binding.textViewPopularity.text = details.popularity.toString()
                            binding.textViewNumListUsers.formatInt(details.numListUsers)
                            binding.textViewEn.text = details.alternativeTitles.en
                            binding.textViewJp.text = details.alternativeTitles.ja
                            details.studios.joinToString(",") { it.name }
                                .let {
                                    binding.textViewStudios.text = it
                                }
                            binding.textViewSource.formatSource(details.source)
                            binding.textViewDuration.text = getString(R.string.minutes, details.averageEpisodeDuration.div(60))
                            binding.textViewStartDate.formatDate(details.startDate)
                            binding.textViewEndDate.formatDate(details.endDate)
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }

}