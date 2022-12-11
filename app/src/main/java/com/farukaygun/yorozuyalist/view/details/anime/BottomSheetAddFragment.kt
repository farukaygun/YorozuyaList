package com.farukaygun.yorozuyalist.view.details.anime

import android.annotation.SuppressLint
import android.text.InputFilter
import android.text.Spanned
import android.util.Range
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.FragmentBottomSheetAddBinding
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


class BottomSheetAddFragment(
	private val animeId: Int,
	private val numEpisodes: Int,
	private val myListStatus: MyListStatus?,
) : BaseBottomSheetDialog<FragmentBottomSheetAddBinding>() {

	private val viewModelAnimeDetails: AnimeDetailsViewModel by viewModels()
	override fun getViewBinding(): FragmentBottomSheetAddBinding =
		FragmentBottomSheetAddBinding.inflate(layoutInflater)
	private lateinit var statusArray: Array<String>
	private lateinit var scoreArray: Array<String>

	@SuppressLint("SetTextI18n")
	override fun start() {
		binding.buttonCancel.setOnClickListener { dismiss() }

		binding.buttonApply.setOnClickListener { updateUserAnimeList() }

		binding.buttonIncrease.setOnClickListener {
			val numEpisode = binding.editTextEpisode.text.toString().toIntOrNull() ?: 0
			binding.editTextEpisode.setText("${numEpisode + 1}")
		}

		binding.buttonDecrease.setOnClickListener {
			val numEpisode = binding.editTextEpisode.text.toString().toIntOrNull() ?: 0
			binding.editTextEpisode.setText("${numEpisode - 1}")
		}

		lifecycleLaunch {
			viewModelAnimeDetails.updateUserList.collectLatest {
				when(it) {
					is ResponseHandler.Success -> println("update result: $it")
					is ResponseHandler.Error -> println("error update: $it")
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
		(binding.autoCompleteTextViewStatus as MaterialAutoCompleteTextView).setSimpleItems(statusArray)


		scoreArray = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
		(binding.autoCompleteTextViewScore as MaterialAutoCompleteTextView).setSimpleItems(scoreArray)

		// editTextEpisodes min max value filter
		binding.editTextEpisode.filters = arrayOf(InputFilterMinMax(0, numEpisodes))

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
		binding.editTextEpisode.setText("${myListStatus?.numEpisodesWatched}")
	}

	private fun initEditUi() {
		when (myListStatus?.status) {
			WATCHING -> binding.autoCompleteTextViewStatus.setText(getString(R.string.watching),false)
			COMPLETED -> binding.autoCompleteTextViewStatus.setText(getString(R.string.completed),false)
			ON_HOLD -> binding.autoCompleteTextViewStatus.setText(getString(R.string.on_hold),false)
			DROPPED -> binding.autoCompleteTextViewStatus.setText(getString(R.string.dropped),false)
			PTW -> binding.autoCompleteTextViewStatus.setText(getString(R.string.plan_to_watch),false)
		}
		binding.autoCompleteTextViewScore.setText(myListStatus?.score.toString(), false)
		binding.textInputLayoutEpisodes.suffixText = "/ $numEpisodes"
		binding.editTextEpisode.setText("${myListStatus?.numEpisodesWatched}")
	}

	private fun updateUserAnimeList() {
		viewModelAnimeDetails.updateUserAnimeList(
			animeId,
			binding.autoCompleteTextViewStatus.text.toString().lowercase(),
			binding.autoCompleteTextViewScore.text.toString().toInt(),
			binding.editTextEpisode.text.toString().toInt()
		)
	}
}