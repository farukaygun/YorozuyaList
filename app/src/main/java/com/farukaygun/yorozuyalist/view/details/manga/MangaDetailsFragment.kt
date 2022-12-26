package com.farukaygun.yorozuyalist.view.details.manga

import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.adapter.RelatedAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentMangaDetailsBinding
import com.farukaygun.yorozuyalist.model.MyListStatus
import com.farukaygun.yorozuyalist.model.manga.MangaDetails
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.*
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collectLatest

class MangaDetailsFragment : BaseFragment<FragmentMangaDetailsBinding>() {
	private val viewModelMangaDetails: MangaDetailsViewModel by viewModels()
	override val isAppbarVisible: Boolean = false
	override fun getViewBinding(): FragmentMangaDetailsBinding =
		FragmentMangaDetailsBinding.inflate(layoutInflater)

	private lateinit var relatedAdapter: RelatedAdapter
	private var isShowMore = false

	private var mangaId: Int = 0
	private var numChapters: Int = 0
	private var myListStatus: MyListStatus? = null


	override fun start() {
		binding.toolBar.setNavigationOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() } // back

		mangaId = arguments?.getInt("id") ?: 0
		// add/edit fab
		binding.fabAdd.setOnClickListener {
			val bottomSheetAddMangaFragment =
				BottomSheetAddMangaFragment(mangaId, numChapters, myListStatus)
			bottomSheetAddMangaFragment.show(parentFragmentManager, "Add")
		}

		binding.textViewMore.setOnClickListener {
			isShowMore = !isShowMore
			when (isShowMore) {
				true -> {
					binding.textViewMore.text = getString(R.string.less)
					binding.textViewMore.setCompoundDrawablesWithIntrinsicBounds(
						0,
						0,
						0,
						R.drawable.ic_round_arrow_drop_up_36)
					binding.textViewSynopsis.maxLines = Int.MAX_VALUE
				}
				false -> {
					binding.textViewMore.text = getString(R.string.more)
					binding.textViewMore.setCompoundDrawablesWithIntrinsicBounds(
						0,
						0,
						0,
						R.drawable.ic_round_arrow_drop_down_36)
					binding.textViewSynopsis.maxLines = 6
				}
			}
		}

		lifecycleLaunch {
			viewModelMangaDetails.mangaDetails.collectLatest { it ->
				when (it) {
					is ResponseHandler.Loading -> binding.circularProgressBar.show()
					is ResponseHandler.Success -> {
						binding.circularProgressBar.hide()
						it.data?.let { details ->
							updateUI(details)
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

	// TODO: Idk why I should use Coroutine at line 82-90.
	private fun updateUI(details: MangaDetails) {
		numChapters = details.numChapters
		myListStatus = details.myListStatus

		relatedAdapter = RelatedAdapter(1, details.relatedManga)
		binding.recyclerViewRelatedManga.adapter = relatedAdapter

		lifecycleLaunch {
			if (myListStatus?.status.isNullOrEmpty())
				binding.fabAdd.setImageDrawable(ResourcesCompat.getDrawable(resources,
					R.drawable.ic_round_add_24,
					context?.theme))
			else
				binding.fabAdd.setImageDrawable(ResourcesCompat.getDrawable(resources,
					R.drawable.ic_baseline_edit_24,
					context?.theme))

			binding.textViewStartDate.formatDate(details.startDate)
			binding.textViewEndDate.formatDate(details.endDate)
		}

		binding.shapeableImageView.downloadFromUrl(details.mainPicture.large)
		binding.textViewName.text = details.title
		binding.textViewEpisodes.formatMediaType(details.mediaType, details.numChapters)
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
		details.authors.joinToString(",") { it.node.firstName + " " + it.node.lastName }
			.let { binding.textViewAuthors.text = it }
		//binding.textViewSource.formatSource(details.source)
		details.serialization.joinToString(",") { it.node.name }
			.let { binding.textViewSerialization.text = it }
	}

}