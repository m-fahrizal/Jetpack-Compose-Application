package com.example.submissionjetpack.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.submissionjetpack.R
import com.example.submissionjetpack.ui.theme.JetIndomieTheme

@Composable
fun ProfileScreen(
    imageResId: Int,
    name: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageResId),
            contentDescription = "about_page",
            modifier
                .size(200.dp)
                .clip(shape = CircleShape)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier.height(16.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier.height(8.dp))

        Text(
            text = email,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfileContentPreview() {
    JetIndomieTheme {
        ProfileScreen(
            imageResId = R.drawable.foto,
            stringResource(R.string.name),
            stringResource(R.string.email)
        )
    }
}