package com.senaaksoy.unscrambleapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senaaksoy.unscrambleapp.R

@Composable
fun GameLayout(
    currentScrambleWord:String,

    wordCount:Int,
    isGuessWrong:Boolean,
    userGuess:String,
    onUserGuessChanged: (String)->Unit,
    onKeyboardDone :()->Unit,
    modifier: Modifier=Modifier
){

    Card(elevation = CardDefaults.cardElevation(4.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = stringResource(id = R.string.word_count, wordCount),
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color(0xFF9AA7E9))
                    .align(Alignment.End)
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = currentScrambleWord,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

            Text(
                text = stringResource(id = R.string.instructions),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value =userGuess ,
                onValueChange = onUserGuessChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                isError = isGuessWrong,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {onKeyboardDone()})
            )

        }

    }
}