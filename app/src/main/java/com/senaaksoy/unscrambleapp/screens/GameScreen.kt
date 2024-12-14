package com.senaaksoy.unscrambleapp.screens

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senaaksoy.unscrambleapp.R
import com.senaaksoy.unscrambleapp.viewModel.GameViewModel

@Composable
fun GameScreen(viewModel: GameViewModel = viewModel()) {
    val gameUiState by viewModel.uiState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = stringResource(id = R.string.unscramble),
            modifier = Modifier.padding(4.dp),
            fontSize = 18.sp
        )
        GameLayout(
            onUserGuessChanged = { viewModel.upDateUserGuess(it) },
            wordCount = gameUiState.currentWordCount,
            userGuess = viewModel.userGuess,
            onKeyboardDone = { viewModel.checkUserGuess() },
            currentScrambleWord = gameUiState.currentScrambledWord,
            isGuessWrong = gameUiState.isGuessedWordWrong,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Button(
            onClick = { viewModel.checkUserGuess() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.submit),
                fontSize = 16.sp
            )
        }
        OutlinedButton(
            onClick = { viewModel.skipWord() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.skip)
            )
        }
        GameStatus(score = gameUiState.score)
        if (gameUiState.isGameOver) {
            ShowDialog(
                score =gameUiState.score,
                onPlayAgain = {viewModel.resetgame()}
            )
        }

    }
}

@Composable
fun ShowDialog(
    score: Int,
    onPlayAgain: () -> Unit
) {
    val activity = (LocalContext.current as Activity)
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { onPlayAgain() }) {
                Text(text = stringResource(id = R.string.play_again))
            }
        },
        dismissButton = {
            TextButton(onClick = { activity.finish() }) {
                Text(text = stringResource(id = R.string.exit))
            }
        },
        title = { Text(text = stringResource(id = R.string.congratulations)) },
        text = { Text(text = stringResource(id = R.string.you_scored, score)) })

}