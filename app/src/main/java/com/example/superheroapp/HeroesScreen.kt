package com.example.superheroapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroapp.model.Hero
import com.example.superheroapp.model.HeroesRepository.heroes
import com.example.superheroapp.ui.theme.SuperHeroAppTheme

@Composable
fun SuperHeroesApp() {
    Scaffold (
        topBar = { HeroesTopAppBar() }
    ) {it ->
        LazyColumn(contentPadding = it){
            items(heroes) {
                HeroItem(
                    hero = it,
                    modifier = Modifier
                )
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge)
        },
        modifier = modifier
    )
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(72.dp)
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
            .clip(MaterialTheme.shapes.medium)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.padding(start = 16.dp)
        ) {
            HeroInformation(
                heroName = hero.nameRes,
                heroDescription = hero.descriptionRes,
                modifier = modifier.weight(3f)
            )

            HeroImage(
                heroImage = hero.imageRes,
                heroDescription = hero.descriptionRes,
                modifier = modifier.weight(1f)
            )
        }

    }
}

@Composable
fun HeroInformation(
    @StringRes heroDescription: Int,
    @StringRes heroName: Int,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier.padding(end = 16.dp)
    ) {
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.displaySmall,

            )
        Text(
            text = stringResource(heroDescription),
            style = MaterialTheme.typography.bodyLarge,
        )
    }


}

@Composable
fun HeroImage(
    @DrawableRes heroImage: Int,
    @StringRes heroDescription: Int,
    modifier: Modifier = Modifier,
){
    Box (
        modifier = modifier
            .padding(16.dp)
            .height(72.dp)
            .width(72.dp)
            .clip(MaterialTheme.shapes.small)
    ){
        Image(
            modifier = modifier,
            painter = painterResource(heroImage),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(heroDescription)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeroPreview() {
    SuperHeroAppTheme {
        SuperHeroesApp()
    }
}
