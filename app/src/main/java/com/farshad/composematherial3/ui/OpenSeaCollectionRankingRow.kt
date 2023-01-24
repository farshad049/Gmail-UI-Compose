package com.farshad.composematherial3.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.math.BigDecimal
import java.math.RoundingMode


data class OpenSeaCollection(
    val name: String,
    val image: String,
    val rank: Int,
    val isVerified: Boolean,
    val tradeVolumeLast24Hours: BigDecimal,
    val tradePercentageChangLast24Hours: BigDecimal,
    val expandableState: Map<String, String>
)

@Composable
fun OpenSeaCollectionRow(openSeaCollection:OpenSeaCollection){
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val textColor = Color.White
    val change = openSeaCollection.tradePercentageChangLast24Hours.newScale(0)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(color = Color(0xFF202225))
            .padding(vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(text = openSeaCollection.rank.toString(), color = textColor)
            Surface(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(75.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.LightGray
            ) {
                AsyncImage(
                    model = openSeaCollection.image,
                    contentDescription ="dasd",
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = openSeaCollection.name ,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    if (openSeaCollection.isVerified){
                        Image(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(18.dp),
                            imageVector = Icons.Filled.Verified,
                            contentDescription = "is verified?",
                            colorFilter = ColorFilter.tint(color = Color.White)
                        )
                    }
                }
                TextButton(onClick = { isExpanded = !isExpanded}) {
                    Text(text = if (isExpanded) "- less" else "+ more", color = textColor)
                }

            }
            Column(
                modifier = Modifier.padding(start = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                val volume = openSeaCollection.tradeVolumeLast24Hours.newScale()
                Text(
                    text = "$volume ETH" ,
                    maxLines = 1,
                    color = textColor
                )

                Text(
                    text = "$change %",
                    color = if (change > BigDecimal.ZERO) Color.Green else Color.Red
                )
            }

        }

        AnimatedVisibility(visible = isExpanded, exit = fadeOut()) {
            Column() {
                Spacer(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                )

                Row() {
                    ExpandableAreaItem(
                        label = "24h%",
                        value = openSeaCollection.tradePercentageChangLast24Hours.newScale().toString() + " %",
                        modifier = Modifier.weight(1f),
                        valueColor = if (change > BigDecimal.ZERO) Color.Green else Color.Red
                    )
                    openSeaCollection.expandableState.forEach{mapEntry ->
                        ExpandableAreaItem(
                            label = mapEntry.key,
                            value = mapEntry.value,
                            modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}


@Composable
fun ExpandableAreaItem(
    label: String,
    value: String,
    valueColor: Color = Color.White,
    modifier: Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = label, color = Color.LightGray, fontWeight = FontWeight.Light)
        Text(text = value, color = valueColor)
    }

}



@Preview(showBackground = true)
@Composable
fun OpesSeasRowPreview(){
    val data = OpenSeaCollection(
        name = "Mutang ape yach the more of the ath hdh djsd kskl",
        image = "https://img.freepik.com/free-vector/hand-drawn-skull-silhouette-illustration_23-2149650781.jpg?w=2000",
        rank = 1,
        isVerified = true,
        tradeVolumeLast24Hours = BigDecimal(57.52),
        tradePercentageChangLast24Hours = BigDecimal(59.36),
        expandableState = mapOf(
            "Floor Price" to  "15.66 ETH",
            "Owner" to "14.65",
            "Assets" to "19.36"
        )
    )
    Column() {
        OpenSeaCollectionRow(data)
        OpenSeaCollectionRow(data)
        OpenSeaCollectionRow(data)
        OpenSeaCollectionRow(data)
    }

}







private fun BigDecimal.newScale(newScale: Int = 2): BigDecimal{
    return this.setScale(newScale, RoundingMode.HALF_UP)
}
