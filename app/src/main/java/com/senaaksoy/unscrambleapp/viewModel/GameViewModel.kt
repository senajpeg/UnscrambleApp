package com.senaaksoy.unscrambleapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.senaaksoy.unscrambleapp.data.allWords
import com.senaaksoy.unscrambleapp.data.maxwords
import com.senaaksoy.unscrambleapp.data.score_increase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    private var usedWords: MutableSet<String> = mutableSetOf()
    private lateinit var currentWord: String

    fun upDateUserGuess(newUserGuess: String) {
        userGuess = newUserGuess
    }

    fun skipWord() {
        upDateGameState(_uiState.value.score)
        upDateUserGuess("")
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(score_increase)
            upDateGameState(updatedScore)

        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        upDateUserGuess("")
    }

    private fun upDateGameState(newupdatedScore: Int) {
        if (usedWords.size == maxwords) {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = newupdatedScore,
                    isGameOver = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = newupdatedScore
                )
            }
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val newWord = word.toCharArray()
        newWord.shuffle()
        while (String(newWord) == word) {
            newWord.shuffle()
        }
        return String(newWord)
    }

    private fun pickRandomWordAndShuffle(): String {
        do {
            currentWord = allWords.random()
        } while (usedWords.contains(currentWord))
        usedWords.add(currentWord)
        return shuffleCurrentWord(currentWord)
    }

    init {
        resetgame()
    }

    fun resetgame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }
}