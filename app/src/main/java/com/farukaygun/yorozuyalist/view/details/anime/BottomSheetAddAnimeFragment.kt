package com.farukaygun.yorozuyalist.view.details.anime

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.FragmentBottomSheetAddAnimeBinding
import com.farukaygun.yorozuyalist.model.MyListStatus
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.Constants.COMPLETED
import com.farukaygun.yorozuyalist.util.Constants.DROPPED
import com.farukaygun.yorozuyalist.util.Constants.ON_HOLD
import com.farukaygun.yorozuyalist.util.Constants.PTW
import com.farukaygun.yorozuyalist.util.Constants.WATCHING
import com.farukaygun.yorozuyalist.util.InputFilterMinMax
import com.farukaygun.yorozuyalist.view.base.BaseBottomSheetDialog
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.flow.collectLatest


class BottomSheetAddAnimeFragment(
	private val animeId: Int,
	private val numEpisodes: Int,
	private val myListStatus: MyListStatus?,
) : BaseBottomSheetDialog<FragmentBottomSheetAddAnimeBinding>() {

	private val viewModelAnimeDetails: AnimeDetailsViewModel by viewModels()
	override fun getViewBinding(): FragmentBottomSheetAddAnimeBinding =
		FragmentBottomSheetAddAnimeBinding.inflate(layoutInflater)

	private lateinit var statusArray: Array<String>
	private lateinit var scoreArray: Array<String>

	private var status = ""
	private var score = 0
	private var numWatchedEpisodes = 0


	@SuppressLint("SetTextI18n")
	override fun start() {
		binding.buttonCancel.setOnClickListener { dismiss() }

		binding.buttonApply.setOnClickListener {
			// Score 0 means is unset.
			status = binding.autoCompleteTextViewStatus.text.toString().lowercase()
				.replace(" ", "_")
			score = binding.autoCompleteTextViewScore.text.toString().toInt()
			numWatchedEpisodes = binding.editTextEpisode.text.toString().toInt()

			viewModelAnimeDetails.updateUserAnimeList(
				animeId = animeId,
				status = status,
				score = score,
				numWatchedEpisodes = numWatchedEpisodes
			)
		}

		binding.buttonIncrease.setOnClickListener {
			val numEpisode = binding.editTextEpisode.text.toString().toIntOrNull() ?: 0
			binding.editTextEpisode.setText("${numEpisode + 1}")
		}

		binding.buttonDecrease.setOnClickListener {
			val numEpisode = binding.editTextEpisode.text.toString().toIntOrNull() ?: 0
			binding.editTextEpisode.setText("${numEpisode - 1}")
		}

		binding.buttonDelete.setOnClickListener { viewModelAnimeDetails.deleteUserAnimeList(animeId) }

		// update response
		lifecycleLaunch {
			viewModelAnimeDetails.updateUserList.collectLatest {
				when (it) {
					is ResponseHandler.Success -> {
						// update my list status if update success
						myListStatus?.let { myListStatus ->
							myListStatus.status = status
							myListStatus.score = score
							myListStatus.numEpisodesWatched = numWatchedEpisodes
						}
						dismiss()
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

		// delete response
		lifecycleLaunch {
			viewModelAnimeDetails.deleteUserList.collectLatest {
				when (it) {
					is ResponseHandler.Success -> dismiss()
					is ResponseHandler.Error -> Toast.makeText(
						context,
						"${it.message}",
						Toast.LENGTH_SHORT
					).show()

					else -> {}
				}
			}
		}

		statusArray = arrayOf(
			getString(R.string.watching),
			getString(R.string.completed),
			getString(R.string.on_hold),
			getString(R.string.dropped),
			getString(R.string.plan_to_watch)
		)
		(binding.autoCompleteTextViewStatus as MaterialAutoCompleteTextView).setSimpleItems(
			statusArray
		)


		scoreArray = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
		(binding.autoCompleteTextViewScore as MaterialAutoCompleteTextView).setSimpleItems(
			scoreArray
		)

		// editTextEpisodes min max value filter
		binding.editTextEpisode.filters = arrayOf(InputFilterMinMax(0, Int.MAX_VALUE))

		if (myListStatus?.status.isNullOrEmpty()) {
			initAddUi()
		} else {
			initEditUi()
		}
	}

	private fun initAddUi() {
		binding.autoCompleteTextViewStatus.setText(statusArray.first(), false)
		binding.autoCompleteTextViewScore.setText(scoreArray.first(), false)
		binding.textInputLayoutEpisodes.suffixText = "/ $numEpisodes"
		binding.editTextEpisode.setText("${myListStatus?.numEpisodesWatched ?: 0}")
	}

	private fun initEditUi() {
		when (myListStatus?.status) {
			WATCHING -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.watching),
				false
			)

			COMPLETED -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.completed),
				false
			)

			ON_HOLD -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.on_hold),
				false
			)

			DROPPED -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.dropped),
				false
			)

			PTW -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.plan_to_watch),
				false
			)
		}
		binding.autoCompleteTextViewScore.setText(myListStatus?.score.toString(), false)
		binding.textInputLayoutEpisodes.suffixText = "/ $numEpisodes"
		binding.editTextEpisode.setText("${myListStatus?.numEpisodesWatched}")
	}
}