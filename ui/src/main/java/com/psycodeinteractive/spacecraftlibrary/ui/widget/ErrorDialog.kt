package com.psycodeinteractive.spacecraftlibrary.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.psycodeinteractive.spacecraftlibrary.ui.R
import com.psycodeinteractive.spacecraftlibrary.ui.extension.value

@Composable
fun ErrorDialog(
    title: String,
    message: String,
    setShowDialog: (Boolean) -> Unit
) {
    Dialog(
        onDismissRequest = {
            setShowDialog(false)
        }
    ) {
        Surface(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(0.9f),
            shape = dialogShape,
            color = MaterialTheme.colors.secondary
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(IntrinsicSize.Min)
                    .padding(end = contentPadding)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(startColoredSpacerWidth)
                        .background(errorColor)
                )
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .align(CenterVertically),
                    imageVector = Icons.Rounded.Close,
                    contentDescription = Icons.Rounded.Close.name,
                    tint = errorColor
                )
                Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .weight(1f)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = message,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
                Text(
                    modifier = Modifier
                        .align(CenterVertically)
                        .clickable {
                            setShowDialog(false)
                        },
                    text = R.string.close.value
                )
            }
        }
    }
}

private val errorColor = Color.Red.copy(0.7f)
private val iconSize = 30.dp
private val startColoredSpacerWidth = 10.dp
private val contentPadding = 16.dp
private val dialogShape = RoundedCornerShape(16.dp)
