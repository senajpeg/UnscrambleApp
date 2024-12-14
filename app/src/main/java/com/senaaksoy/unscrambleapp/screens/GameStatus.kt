package com.senaaksoy.unscrambleapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senaaksoy.unscrambleapp.R

@Composable
fun GameStatus(
    score:Int,
    modifier: Modifier=Modifier.padding(16.dp)){
    Text(
        text = stringResource(id = R.string.score,score),
        fontSize = 16.sp,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color(0xFF817D7D))
            .padding(8.dp),
        )

}