package com.example.raiffeisentest.adapters

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.raiffeisentest.UserListScreenState

@BindingAdapter("refreshingState")
internal fun setRefreshingState(swipeRefreshLayout: SwipeRefreshLayout, state: UserListScreenState) {
    swipeRefreshLayout.isRefreshing = state is UserListScreenState.Loading
}