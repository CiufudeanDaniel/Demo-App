package com.example.raiffeisentest

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raiffeisentest.models.*
import com.example.raiffeisentest.repository.UserRepository
import com.example.raiffeisentest.view_models.MyViewModelFactory
import com.example.raiffeisentest.view_models.UserViewModel1

private const val TAG = "UserListScreen"

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
        items(state.users.results) { user ->
            ListItemComponent(user)
        }
    }
}

@Composable
fun ListItemComponent(user: User) {
    Text(text = user.name.first)
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