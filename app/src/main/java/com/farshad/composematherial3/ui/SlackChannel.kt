package com.farshad.composematherial3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Tag
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class SlackChannel(
    val name: String,
    val isPrivate: Boolean = false,
    val hasNewMessages: Boolean = false,
    val taggedMessageCount: Int = 0
)

@Composable
fun SlackChannelUi(slackChannel: SlackChannel) {
    val icon = if (slackChannel.isPrivate) Icons.Filled.Lock else Icons.Filled.Tag
    val fontWeight = if(slackChannel.hasNewMessages) FontWeight.Bold else FontWeight.Normal

    Surface(shape = RoundedCornerShape(10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(imageVector = icon, contentDescription = "chanel-icon")

            Text(text = slackChannel.name, fontWeight = fontWeight, modifier = Modifier.padding(start = 8.dp))

            if (slackChannel.taggedMessageCount > 0){
                Text(
                    text = slackChannel.taggedMessageCount.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp).drawBehind {
                        drawCircle(
                            color = Color.Red,
                            radius = this.size.width * 1.5f
                        )
                    }
                )
            }

        }
    }

}


@Composable
fun DisplaySpace() {
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .background(Color.Transparent)
    )
}


@Preview(showBackground = false)
@Composable
fun SlackChannelUiPreview() {
    Column {
        SlackChannelUi(slackChannel = SlackChannel(name = "channel-one", isPrivate = true))
        DisplaySpace()
        SlackChannelUi(slackChannel = SlackChannel(name = "channel-two", isPrivate = false, hasNewMessages = true))
        DisplaySpace()
        SlackChannelUi(slackChannel = SlackChannel(name = "channel-one", isPrivate = true))
        DisplaySpace()
        SlackChannelUi(slackChannel = SlackChannel(name = "channel-two", isPrivate = false, hasNewMessages = true, taggedMessageCount = 2))
        DisplaySpace()
        SlackChannelUi(slackChannel = SlackChannel(name = "channel-two", isPrivate = false, hasNewMessages = true))
        DisplaySpace()
        SlackChannelUi(slackChannel = SlackChannel(name = "channel-two", isPrivate = false, hasNewMessages = true, taggedMessageCount = 5))
    }

}



