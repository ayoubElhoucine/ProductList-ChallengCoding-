package com.example.challengcodingapp.ui.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.challengcodingapp.model.Item
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    item: Item,
    onBack: () -> Unit,
) {
    val expandDetails = viewModel.expandDetails.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        (if (item.image?.large.isNullOrBlank()) item.image?.small else item.image?.large)?.let { image ->
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    imageModel = image,
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray.copy(0.5f))
                        )
                    }
                )
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = item.name,
            color = Color.Black,
            fontWeight = FontWeight.W600,
            fontSize = 18.sp,
            maxLines = 2
        )
        item.description?.let { description ->
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = description,
                color = Color.Black,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                maxLines = 2
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight()
                .fillMaxWidth()
                .animateContentSize()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.Black.copy(alpha = 0.05f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier.clickable(onClick = viewModel::toggleExpand)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "More details",
                        fontWeight = FontWeight.W600,
                    )
                    Icon(
                        imageVector = if (expandDetails.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                if (expandDetails.value) {
                    DetailsItem(title = "Price", value = "${item.price} DZD")
                    item.brand?.name?.let { DetailsItem(title = "Brand", value = it) }
                    item.isProductSet?.let { DetailsItem(title = "Is product set", value = if (it) "YES" else "NO") }
                    item.isSpecialBrand?.let { DetailsItem(title = "Is special brand", value = if (it) "YES" else "NO") }
                }
            }
        }
    }
}

@Composable
private fun DetailsItem(
    title: String,
    value: String,
) {
    Row {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = Color.Black,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            maxLines = 1
        )
        Text(
            text = value,
            color = Color.Black,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
            maxLines = 1
        )
    }
}