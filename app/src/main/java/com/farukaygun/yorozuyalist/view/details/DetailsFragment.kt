package com.farukaygun.yorozuyalist.view.details

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.adapter.RelatedAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentDetailsBinding
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.downloadFromUrl
import com.farukaygun.yorozuyalist.util.formatDate
import com.farukaygun.yorozuyalist.util.formatMediaType
import com.farukaygun.yorozuyalist.util.formatStatus
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collectLatest

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private val viewModelDetails: DetailsViewModel by viewModels()
    override val isAppbarVisible: Boolean = false
    override fun getViewBinding(): FragmentDetailsBinding = FragmentDetailsBinding.inflate(layoutInflater)
    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var relatedAdapter: RelatedAdapter
    private var isShowMore = false

    override fun start() {
        viewModelDetails.getAnimeDetails(args.id)
        println(args.id)

        binding.textViewMore.setOnClickListener {
            isShowMore = !isShowMore
            when(isShowMore) {
                true -> {
                    binding.textViewMore.text = "Less"
                    binding.textViewMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_round_arrow_drop_up_36)
                    binding.textViewSynopsis.maxLines = Int.MAX_VALUE
                }
                false -> {
                    binding.textViewMore.text = "More"
                    binding.textViewMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_round_arrow_drop_down_36)
                    binding.textViewSynopsis.maxLines = 6
                }
            }
        }

        lifecycleLaunch {
            viewModelDetails.animeDetails.collectLatest { it ->
                when(it) {
                    is ResponseHandler.Loading -> binding.circularProgressBar.show()
                    is ResponseHandler.Success -> {
                        binding.circularProgressBar.hide()
                        it.data?.let { details ->
                            relatedAdapter = RelatedAdapter(details.relatedAnime)
                            binding.recyclerViewRelatedAnime.adapter = relatedAdapter

                            binding.shapeableImageView.downloadFromUrl(details.mainPicture.large)
                            binding.textViewName.text = details.title
                            binding.textViewEpisodes.formatMediaType(details.mediaType, details.numEpisodes)
                            binding.textViewScore.text = details.mean.toString()
                            binding.textViewScoringUsers.text = details.numScoringUsers.toString()
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
                            binding.textViewNumListUsers.text = details.numListUsers.toString()
                            binding.textViewEn.text = details.alternativeTitles.en
                            binding.textViewJp.text = details.alternativeTitles.ja
                            binding.textViewStartDate.formatDate(details.startDate)
                            binding.textViewEndDate.formatDate(details.endDate)
                            println("studios: " + details.studios)
                            details.studios.joinToString(",") {
                                println("name: " + it.name)
                                it.name
                            }.let {
                                println("let: " + it)
                                binding.textViewStudios.text = it
                            }
                            binding.textViewSource.text = details.source
                            binding.textViewDuration.text = details.averageEpisodeDuration.toString()
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }

}