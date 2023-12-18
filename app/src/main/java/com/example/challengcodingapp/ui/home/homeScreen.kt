package com.example.challengcodingapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.challengcodingapp.model.Item
import com.example.challengcodingapp.ui.components.FailView
import com.example.challengcodingapp.ui.uiState.UiState
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemDetails: (Item) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    Box(contentAlignment = Alignment.Center) {
        when (val data = uiState.value) {
            UiState.Loading -> Loading()
            is UiState.Success -> {
                (data.data as? List<Item>)?.let {
                    Success(data = data.data, onItemDetails = onItemDetails)
                } ?: run {
                    Text("Aucun résultat")
                }
            }
            is UiState.Fail -> FailView(message = data.message, onRetry = viewModel::onRetry)
            else -> Text("Aucun résultat")
        }
    }
}

@Composable
private fun Loading() {
    Box {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun Success(
    data: List<Item>,
    onItemDetails: (Item) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 20.dp)
    ) {
        itemsIndexed(data) { _, item ->
            ItemView(item = item) {
                onItemDetails(item)
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemView(
    item: Item,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            item.image?.small?.let { image ->
                GlideImage(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape),
                    imageModel = image,
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
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = item.name,
                    color = Color.Black,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    maxLines = 2
                )
                item.description?.let { description ->
                    Text(
                        text = description,
                        color = Color.Black,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        maxLines = 2
                    )
                }
            }
        }
    }
}