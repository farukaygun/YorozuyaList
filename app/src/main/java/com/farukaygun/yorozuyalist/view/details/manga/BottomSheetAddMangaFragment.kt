package com.farukaygun.yorozuyalist.view.details.manga

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.FragmentBottomSheetAddMangaBinding
import com.farukaygun.yorozuyalist.model.MyListStatus
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.Constants
import com.farukaygun.yorozuyalist.util.InputFilterMinMax
import com.farukaygun.yorozuyalist.view.base.BaseBottomSheetDialog
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.flow.collectLatest


class BottomSheetAddMangaFragment(
	private val mangaId: Int,
	private val numChapters: Int,
	private val myListStatus: MyListStatus?,
) : BaseBottomSheetDialog<FragmentBottomSheetAddMangaBinding>() {

	private val viewModelMangaDetails: MangaDetailsViewModel by viewModels()
	override fun getViewBinding(): FragmentBottomSheetAddMangaBinding =
		FragmentBottomSheetAddMangaBinding.inflate(layoutInflater)

	private lateinit var statusArray: Array<String>
	private lateinit var scoreArray: Array<String>

	private var status = ""
	private var score = 0
	private var numReadedChapters = 0


	@SuppressLint("SetTextI18n")
	override fun start() {
		binding.buttonCancel.setOnClickListener { dismiss() }

		binding.buttonApply.setOnClickListener {
			// Score 0 means is unset.
			status = binding.autoCompleteTextViewStatus.text.toString().lowercase()
				.replace(" ", "_")
			score = binding.autoCompleteTextViewScore.text.toString().toInt()
			numReadedChapters = binding.editTextChapter.text.toString().toInt()

			viewModelMangaDetails.updateUserMangaList(
				mangaId = mangaId,
				status = status,
				score = score,
				numReadedChapters = numReadedChapters
			)
		}

		binding.buttonIncrease.setOnClickListener {
			val numChapters = binding.editTextChapter.text.toString().toIntOrNull() ?: 0
			binding.editTextChapter.setText("${numChapters + 1}")
		}

		binding.buttonDecrease.setOnClickListener {
			val numChapters = binding.editTextChapter.text.toString().toIntOrNull() ?: 0
			binding.editTextChapter.setText("${numChapters - 1}")
		}

		binding.buttonDelete.setOnClickListener { viewModelMangaDetails.deleteUserMangaList(mangaId) }

		// update response
		lifecycleLaunch {
			viewModelMangaDetails.updateUserList.collectLatest {
				when (it) {
					is ResponseHandler.Success -> {
						// update my list status if update success
						myListStatus?.let { myListStatus ->
							myListStatus.status = status
							myListStatus.score = score
							myListStatus.numChaptersRead = numReadedChapters
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
			viewModelMangaDetails.deleteUserList.collectLatest {
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
			getString(R.string.reading),
			getString(R.string.completed),
			getString(R.string.on_hold),
			getString(R.string.dropped),
			getString(R.string.plan_to_read)
		)
		(binding.autoCompleteTextViewStatus as MaterialAutoCompleteTextView).setSimpleItems(
			statusArray
		)

		scoreArray = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
		(binding.autoCompleteTextViewScore as MaterialAutoCompleteTextView).setSimpleItems(
			scoreArray
		)

		// editTextEpisodes min max value filter
		binding.editTextChapter.filters = arrayOf(InputFilterMinMax(0, Int.MAX_VALUE))

		if (myListStatus?.status.isNullOrEmpty()) {
			initAddUi()
		} else {
			initEditUi()
		}
	}

	private fun initAddUi() {
		binding.autoCompleteTextViewStatus.setText(statusArray.first(), false)
		binding.autoCompleteTextViewScore.setText(scoreArray.first(), false)
		binding.textInputLayoutEpisodes.suffixText = "/ $numChapters"
		binding.editTextChapter.setText("0")
	}

	private fun initEditUi() {
		when (myListStatus?.status) {
			Constants.READING -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.reading),
				false
			)

			Constants.COMPLETED -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.completed),
				false
			)

			Constants.ON_HOLD -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.on_hold),
				false
			)

			Constants.DROPPED -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.dropped),
				false
			)

			Constants.PTR -> binding.autoCompleteTextViewStatus.setText(
				getString(R.string.plan_to_read),
				false
			)
		}
		binding.autoCompleteTextViewScore.setText(myListStatus?.score.toString(), false)
		binding.textInputLayoutEpisodes.suffixText = "/ $numChapters"
		binding.editTextChapter.setText(myListStatus?.numChaptersRead.toString().ifEmpty { "0" })
	}
}