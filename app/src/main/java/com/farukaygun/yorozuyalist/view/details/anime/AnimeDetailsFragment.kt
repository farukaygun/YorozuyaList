package com.farukaygun.yorozuyalist.view.details.anime

import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.adapter.RelatedAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentAnimeDetailsBinding
import com.farukaygun.yorozuyalist.model.MyListStatus
import com.farukaygun.yorozuyalist.model.anime.AnimeDetails
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.downloadFromUrl
import com.farukaygun.yorozuyalist.util.formatDate
import com.farukaygun.yorozuyalist.util.formatInt
import com.farukaygun.yorozuyalist.util.formatMediaType
import com.farukaygun.yorozuyalist.util.formatSource
import com.farukaygun.yorozuyalist.util.formatStatus
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collectLatest

class AnimeDetailsFragment : BaseFragment<FragmentAnimeDetailsBinding>() {
	private val viewModelAnimeDetails: AnimeDetailsViewModel by viewModels()
	override val isAppbarVisible: Boolean = false
	override fun getViewBinding(): FragmentAnimeDetailsBinding =
		FragmentAnimeDetailsBinding.inflate(layoutInflater)

	private lateinit var relatedAdapter: RelatedAdapter
	private var isShowMore = false

	private var animeId: Int = 0
	private var numEpisodes: Int = 0
	private var myListStatus: MyListStatus? = null


	override fun start() {
		binding.toolBar.setNavigationOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() } // back

		animeId = arguments?.getInt("id") ?: 0
		// add/edit fab
		binding.fabAdd.setOnClickListener {
			val bottomSheetAddAnimeFragment =
				BottomSheetAddAnimeFragment(animeId, numEpisodes, myListStatus)
			bottomSheetAddAnimeFragment.show(parentFragmentManager, "Add")
		}

		// synopsis more button
		binding.textViewMore.setOnClickListener {
			isShowMore = !isShowMore
			when (isShowMore) {
				true -> {
					binding.textViewMore.text = getString(R.string.less)
					binding.textViewMore.setCompoundDrawablesWithIntrinsicBounds(
						0,
						0,
						0,
						R.drawable.ic_round_arrow_drop_up_36
					)
					binding.textViewSynopsis.maxLines = Int.MAX_VALUE
				}

				false -> {
					binding.textViewMore.text = getString(R.string.more)
					binding.textViewMore.setCompoundDrawablesWithIntrinsicBounds(
						0,
						0,
						0,
						R.drawable.ic_round_arrow_drop_down_36
					)
					binding.textViewSynopsis.maxLines = 6
				}
			}
		}

		lifecycleLaunch {
			viewModelAnimeDetails.animeDetails.collectLatest { it ->
				when (it) {
					is ResponseHandler.Loading -> binding.circularProgressBar.show()
					is ResponseHandler.Success -> {
						binding.circularProgressBar.hide()
						it.data?.let { details ->
							updateUi(details)
						}
					}

					is ResponseHandler.Error -> Toast.makeText(
						context,
						"${it.message}",
						Toast.LENGTH_SHORT
					).show()

					else -> {}
				}
			}
		}
	}

	// TODO: Idk why I should use Coroutine at line 81-89.
	private fun updateUi(details: AnimeDetails) {
		numEpisodes = details.numEpisodes
		myListStatus = details.myListStatus

		relatedAdapter = RelatedAdapter(0, details.related)
		binding.recyclerViewRelatedAnime.adapter = relatedAdapter

		lifecycleLaunch {
			if (myListStatus?.status.isNullOrEmpty())
				binding.fabAdd.setImageDrawable(
					ResourcesCompat.getDrawable(
						resources,
						R.drawable.ic_round_add_24,
						context?.theme
					)
				)
			else
				binding.fabAdd.setImageDrawable(
					ResourcesCompat.getDrawable(
						resources,
						R.drawable.ic_baseline_edit_24,
						context?.theme
					)
				)

			binding.textViewStartDate.formatDate(details.startDate)
			binding.textViewEndDate.formatDate(details.endDate)
		}

		binding.shapeableImageView.downloadFromUrl(details.mainPicture.large)
		binding.textViewName.text = details.title
		binding.textViewEpisodes.formatMediaType(details.mediaType, details.numEpisodes)
		binding.textViewScore.text = if (details.mean == 0.0) "??" else details.mean.toString()
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
			.let { binding.textViewStudios.text = it }

		binding.textViewSource.formatSource(details.source)
		binding.textViewDuration.text =
			getString(R.string.minutes, details.averageEpisodeDuration.div(60))
	}
}