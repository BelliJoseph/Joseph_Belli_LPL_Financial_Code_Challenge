package com.example.lplfinancialcodechallenge.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.lplfinancialcodechallenge.R
import com.example.lplfinancialcodechallenge.data.domain.PostDomainModel
import com.example.lplfinancialcodechallenge.domain.UiState
import com.example.lplfinancialcodechallenge.viewmodel.PostsViewModel

@Composable
fun PostsRoute(
    modifier: Modifier,
    viewModel: PostsViewModel = hiltViewModel()
) {
    val postState = viewModel.postsState.collectAsStateWithLifecycle().value

    when (postState) {
        UiState.LOADING -> LoadingScreen(modifier = modifier)

        is UiState.ERROR -> ErrorScreen(
            modifier = modifier,
            error = postState.error
        )

        is UiState.SUCCESS -> PostsScreen(
            modifier = modifier,
            postList = postState.response,
            updatePost = { post, path ->
                viewModel.updatePost(
                    post = post,
                    imagePath = path
                )
            }
        )

    }
}

@Composable
private fun PostsScreen(
    modifier: Modifier,
    postList: List<PostDomainModel>,
    updatePost: (post: PostDomainModel, imagePath: String) -> Unit,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 24.dp)
    ) {
        items(
            postList,
            key = { post ->
                post.id
            }
        ) {
            PostItem(
                post = it,
                updatePost = updatePost
            )
        }
    }
}

@Composable
private fun PostItem(
    post: PostDomainModel,
    updatePost: (post: PostDomainModel, imagePath: String) -> Unit,
) {
    val pickMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            updatePost(post, uri.toString())
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = {
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp),
                contentDescription = post.name,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(post.image)
                    .placeholder(R.drawable.person)
                    .error(R.drawable.person)
                    .transformations(CircleCropTransformation())
                    .build()
            )

            Column {

                Row(modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)) {
                    Text(modifier = Modifier.weight(1f), text = post.name)
                    Text(modifier = Modifier.weight(1f), text = post.email)
                }

                Text(
                    modifier = Modifier.padding(vertical = 2.dp),
                    text = post.id.toString()
                )

                Text(
                    modifier = Modifier.padding(top = 2.dp, bottom = 4.dp),
                    text = post.body
                )
            }
        }

    }
}

@Composable
private fun LoadingScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(
    modifier: Modifier,
    error: Exception
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = error.message.toString(),
            color = Color.Red,
            fontSize = 20.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PostsScreenPreview() {
    PostsScreen(
        modifier = Modifier,
        postList = listOf(
            PostDomainModel(
                id = 1,
                name = "Bill Smith",
                email = "bill.smith@gmail.com",
                body = "long body message shortened",
            ),
            PostDomainModel(
                id = 2,
                name = "Sally Johnson",
                email = "sally.johnson@aol.com",
                body = "another long body message shortened",
            ),
            PostDomainModel(
                id = 3,
                name = "Linda Snyder",
                email = "linda.snyder@email.com",
                body = "another even longer body message shortened",
            ),
        ),
        updatePost = { post, image -> }
    )
}

@Preview(showBackground = true)
@Composable
fun PostItemPreview() {
    PostItem(
        post = PostDomainModel(
            id = 1,
            name = "Bill Smith",
            email = "bill.smith@gmail.com",
            body = "long body message shortened",
        ),
        updatePost = { post, domain -> }
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen(modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        modifier = Modifier,
        error = Exception("Something went wrong")
    )
}