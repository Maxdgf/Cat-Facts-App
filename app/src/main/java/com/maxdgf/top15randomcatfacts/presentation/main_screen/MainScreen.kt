package com.maxdgf.top15randomcatfacts.presentation.main_screen


import android.widget.Toast
import androidx.compose.foundation.basicMarquee

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import org.koin.androidx.compose.koinViewModel

import com.maxdgf.top15randomcatfacts.R
import com.maxdgf.top15randomcatfacts.presentation.main_screen.component.FactItem
import com.maxdgf.top15randomcatfacts.presentation.main_screen.component.LanguageItem
import com.maxdgf.top15randomcatfacts.presentation.main_screen.utils.ClipboardManager
import com.maxdgf.top15randomcatfacts.presentation.main_screen.utils.ShareManager


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen() {
    val viewModel: MainViewModel = koinViewModel()

    val facts by viewModel.allCatFacts.collectAsState()
    var selectLanguageDialogState by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        painter = painterResource(R.drawable.cat_icon),
                        contentDescription = "cat icon",
                        tint = Color.White
                    )
                },
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        maxLines = 1,
                        softWrap = false,
                        fontSize = 20.sp,
                        modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE)
                    )
                },
                actions = {
                    IconButton(onClick = { selectLanguageDialogState = true }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_language_24),
                            contentDescription = "change language button"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = viewModel::loadCatFacts,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (val factsState = facts) {
                is CatFactsResult.Content -> {
                    if (selectLanguageDialogState) {
                        val selectedLanguageCode by viewModel.selectedLanguage.collectAsState()

                        AlertDialog(
                            title = { Text(text = "Select facts language") },
                            onDismissRequest = { selectLanguageDialogState = false },
                            text = {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    val options = remember {
                                        listOf(
                                            Pair("English", "eng"), Pair("German", "ger"),
                                            Pair("Bengali", "ben"), Pair("Spanish", "esp"),
                                            Pair("Portuguese", "por"), Pair("Italian", "ita"),
                                            Pair("Russian", "rus"), Pair("Chinese", "zho"),
                                            Pair("Urdu", "urd"), Pair("Korean", "kor"),
                                            Pair("Filipino", "fil"), Pair("Czech", "ces"),
                                            Pair("Ukrainian", "ukr")
                                        )
                                    }

                                    LazyColumn(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(120.dp),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        itemsIndexed(items = options) { index, language ->
                                            LanguageItem(
                                                name = language.first,
                                                isSelected = selectedLanguageCode == language.second,
                                                isElevated = (index + 1) % 2 == 0,
                                                onClick = {
                                                    if (selectedLanguageCode != language.second)
                                                        viewModel.selectLanguage(language.second)
                                                    selectLanguageDialogState = false
                                                }
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(text = "The changes will take effect after the facts are updated.")
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = { selectLanguageDialogState = false }) {
                                    Text(text = "Ok")
                                }
                            }
                        )
                    }

                    val context = LocalContext.current

                    val shareManager = remember { ShareManager(context) }
                    val clipboardManager = remember { ClipboardManager(context) }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item(key = "top spacer") {
                            Spacer(modifier = Modifier.height(5.dp))
                        }

                        itemsIndexed(
                            items = factsState.list,
                            key = { index, fact -> fact.hashCode() + index }
                        ) { index, fact ->
                            val number = index + 1

                            FactItem(
                                number = number,
                                fact = fact,
                                isElevated = number % 2 == 0,
                                onClick = { clipboardManager.setTextToClipboard(fact) },
                                onShare = {
                                    if (!shareManager.shareMessage(fact))
                                        Toast.makeText(context, "Unable to share fact!", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }

                        item(key = "bottom spacer") {
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }

                is CatFactsResult.Error -> Text(text = factsState.message ?: "Something went wrong.")
                CatFactsResult.Initial -> {}
            }
        }
    }
}
