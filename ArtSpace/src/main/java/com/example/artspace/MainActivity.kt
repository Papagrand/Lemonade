package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.artspace.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme  {
                    ArtSpaceApp()
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceApp() {
    var currentStep by remember { mutableStateOf(1) }

    Scaffold() { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> {
                    ArtSpaceTextAndImage(
                        textLabelResourceId = R.string.munk_name,
                        drawableResourceId = R.drawable.scream,
                        contentDescriptionResourceId = R.string.edward_munk_content_description,
                        onBackClick = { currentStep=4 },
                        onForwardClick = {currentStep=2}
                    )
                }
                2 -> {
                    ArtSpaceTextAndImage(
                        textLabelResourceId = R.string.wrubel_name,
                        drawableResourceId = R.drawable.demon,
                        contentDescriptionResourceId = R.string.michael_wrubel_content_description,
                        onBackClick = { currentStep=1 },
                        onForwardClick = {currentStep=3}
                    )
                }
                3 -> {
                    ArtSpaceTextAndImage(
                        textLabelResourceId = R.string.vereshyagin_name,
                        drawableResourceId = R.drawable.war,
                        contentDescriptionResourceId = R.string.vasily_vereshyagin_content_description,
                        onBackClick = { currentStep=2 },
                        onForwardClick = {currentStep=4}
                    )
                }
                4 -> {
                    ArtSpaceTextAndImage(
                        textLabelResourceId = R.string.klimt_name,
                        drawableResourceId = R.drawable.women,
                        contentDescriptionResourceId = R.string.gustav_klimt_content_description,
                        onBackClick = { currentStep=3 },
                        onForwardClick = {currentStep=1}
                    )
                }
            }
        }
    }
}


@Composable
fun ArtSpaceTextAndImage(
            textLabelResourceId: Int,
            drawableResourceId: Int,
            contentDescriptionResourceId: Int,
            onBackClick: () -> Unit,
            onForwardClick: () -> Unit,
            modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(40.dp)
            .wrapContentSize(align = Alignment.Center)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(4.dp, shape = CutCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(id = contentDescriptionResourceId ),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                )
            }
            // Часть А: Заголовок и изображение с эффектом тени
            Text(text = stringResource(id = textLabelResourceId), style = MaterialTheme.typography.bodyLarge)

            Text(text = "Название картины", style = MaterialTheme.typography.bodyLarge)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick =  onBackClick ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                        Text(text = "Назад")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(onClick = onForwardClick) {
                        Text(text = "Вперед")
                        Icon(Icons.Default.ArrowForward, contentDescription = null)
                    }
                }
            }

        }
    }
}
