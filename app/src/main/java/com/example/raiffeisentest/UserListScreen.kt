package com.example.raiffeisentest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.raiffeisentest.models.*
import com.example.raiffeisentest.repository.UserRepository
import com.example.raiffeisentest.view_models.MyViewModelFactory
import com.example.raiffeisentest.view_models.UserViewModel1


@Composable
internal fun UserListScreen(
    repository: UserRepository,
) {
    val viewModel: UserViewModel1 = viewModel(factory = MyViewModelFactory(repository))
    val uiState by viewModel.uiState.collectAsState()

    UserListMain(uiState = uiState)
}

@Composable
private fun UserListMain(
    uiState: UserListScreenState
) {
    when (uiState) {
        is UserListScreenState.Loading -> {
            LoadingComponent()
        }
        is UserListScreenState.Error -> {}
        is UserListScreenState.Success -> {
            SuccessComponent(uiState)
        }
    }
}

@Composable
private fun SuccessComponent(state: UserListScreenState.Success) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(state.users.results) { index, user ->
            ListItemComponent(user)
        }
    }
}

@Composable
fun ListItemComponent(user: User) {
    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.picture.thumbnail)
                    .crossfade(false)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(50.dp)
                    .height(50.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Text(
                        text = user.name.first,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                    )

                    Spacer(Modifier.weight(1f))

                    Image(
                        alignment = Alignment.TopEnd,
                        painter = painterResource(id = R.drawable.ic_baseline_attachment_24),
                        contentDescription = "",
                    )

                    Text(
                        text = user.dob.getTime(),
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                    )
                }

                Text(
                    text = stringResource(id = R.string.age_and_nat, user.dob.age, user.nat),
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_star_border_24),
                        contentDescription = "",
                        modifier = Modifier.wrapContentWidth(Alignment.End)
                    )
                }
            }
        }
        Divider(color = Color.Black, thickness = 0.2.dp, modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
fun LoadingComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(width = 64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Preview(name = "SuccessPreview", showBackground = true, device = Devices.PIXEL_3_XL)
@Composable
private fun UserListPreview() {
    val user = User(
        gender = "female",
        name = NameModel(
            title = "Miss",
            first = "Laura",
            last = "Woods"
        ),
        picture = Pictures(
            large = "https://randomuser.me/api/portraits/women/88.jpg",
            medium = "https://randomuser.me/api/portraits/med/women/88.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/women/88.jpg"
        ),
        nat = "IE",
        dob = DateOfBirth(
            date = "1967-07-23T09:18:33.666Z",
            age = 56
        ),
        login = LoginModel(
            uuid = "9f07341f-c7e6-45b7-bab0-af6de5a4582d"
        )
    )

    val infoModel = InfoModel(
        seed = "",
        results = 1,
        page = 1
    )
    val usersModel = UsersModel(
        results = arrayListOf(user, user, user, user, user, user, user, user, user, user),
        info = infoModel
    )
    val state = UserListScreenState.Success(
        users = usersModel,
        lastCalledPage = 1
    )
    MaterialTheme {
        UserListMain(uiState = state)
    }
}