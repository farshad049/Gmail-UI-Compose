package com.farshad.composematherial3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class GmailRow (
    val sender : Sender,
    val subject: String,
    val body: String,
    val deliveryDate: String,
    val isUnread: Boolean,
    val isStarred: Boolean
        ){
    data class Sender(
        val name: String,
        val color: Color
    )
}


@Composable
fun GmailRowItem( gmailRow: GmailRow, modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.End
        ) {
            Surface(
                shape = CircleShape,
                modifier = Modifier.size(30.dp),
                color = gmailRow.sender.color
            ) {
                Text(text = gmailRow.sender.name.first().toString(), modifier= Modifier.wrapContentSize())
            }
        }

        val fontWeight = if (gmailRow.isUnread) FontWeight.Bold else FontWeight.Light
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1F)
        ) {
            Text(
                text = gmailRow.sender.name ,
                fontSize = 17.sp, fontWeight = fontWeight
            )
            Text(
                text = gmailRow.subject,fontSize = 17.sp ,
                fontWeight = fontWeight
            )
            Text(
                text = gmailRow.body,
                fontWeight = FontWeight.Light,
                maxLines = 1,
                fontSize = 17.sp,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(
            modifier =Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = gmailRow.deliveryDate ,fontSize = 17.sp)
            //Spacer(modifier = Modifier.weight(1f))
            Image(
                imageVector = if (gmailRow.isStarred) Icons.Filled.Star else Icons.Filled.StarOutline,
                contentDescription = "is starred"
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GmailRowItemPreview(){
    val sender = GmailRow.Sender(
        name = "farshad",
        color = Color.Blue.copy(alpha = 0.2f)
    )

    val gmailRow = GmailRow(
        sender= sender,
        subject = "thanks for stopping by",
        body = "don't forget to like, comment and subscribe to help me grow",
        deliveryDate = "jan 10",
        isUnread = false,
        isStarred = false
    )

    Column() {
        GmailRowItem(
            gmailRow = gmailRow.copy(isStarred = true)
        )
        GmailRowItem(
            gmailRow = gmailRow.copy(isUnread = true)
        )
        GmailRowItem(
            gmailRow = gmailRow.copy(isUnread = true)
        )
        GmailRowItem(
            gmailRow = gmailRow
        )


    }

}