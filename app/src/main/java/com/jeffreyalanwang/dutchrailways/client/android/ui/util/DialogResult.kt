package com.jeffreyalanwang.dutchrailways.client.android.ui.util

import kotlinx.serialization.Serializable

@Serializable
data class DialogResult<R, T>(val value: R, val tag: T)