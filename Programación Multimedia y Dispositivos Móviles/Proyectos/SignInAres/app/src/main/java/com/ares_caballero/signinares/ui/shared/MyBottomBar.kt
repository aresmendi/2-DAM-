package com.ares_caballero.signinares.ui.shared
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ares_caballero.signinares.R


@Suppress("SpellCheckingInspection")
@Composable
fun MyBottomBar() {
    MyImage(R.drawable.my_profile)

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyBottomBarPreview() {
    MyBottomBar()
}
