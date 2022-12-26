package com.farukaygun.yorozuyalist.util

class CodeVerifier {
	companion object {
		private val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9') + '-' + '.' + '_' + '~'

		fun generateCodeChallenge(length: Int): String {
			return (1..length)
				.map { allowedChars.random() } // return random element from collection
				.joinToString("") // add element to new string
		}
	}
}