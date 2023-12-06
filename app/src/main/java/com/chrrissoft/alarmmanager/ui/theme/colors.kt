package com.chrrissoft.alarmmanager.ui.theme

import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.InputChipDefaults.inputChipColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.chrrissoft.alarmmanager.ui.components.AlertDialogColors
import androidx.compose.material3.NavigationBarItemDefaults.colors as navigationBarItemColors

@OptIn(ExperimentalMaterial3Api::class)
val centerAlignedTopAppBarColors
    @Composable get() = run {
        centerAlignedTopAppBarColors(
            containerColor = colorScheme.primaryContainer,
            navigationIconContentColor = colorScheme.primary,
            titleContentColor = colorScheme.primary,
            actionIconContentColor = colorScheme.primary,
        )
    }

val navigationBarItemColors
    @Composable get() = run {
        navigationBarItemColors(
            selectedIconColor = colorScheme.onPrimary,
            selectedTextColor = colorScheme.primary,
            indicatorColor = colorScheme.primary,
            unselectedIconColor = colorScheme.primary.copy(.5f),
            unselectedTextColor = colorScheme.primary.copy(.5f),
        )
    }

val navigationDrawerItemColors
    @Composable get() = run {
        NavigationDrawerItemDefaults.colors(
            selectedContainerColor = colorScheme.primary,
            unselectedContainerColor = colorScheme.onPrimary,
            selectedIconColor = colorScheme.onPrimary,
            unselectedIconColor = colorScheme.secondary.copy(.5f),
            selectedTextColor = colorScheme.onPrimary,
            unselectedTextColor = colorScheme.secondary.copy(.5f),
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
val inputChipColors
    @Composable get() = run {
        inputChipColors(
            containerColor = colorScheme.primaryContainer,
            labelColor = colorScheme.primary,
            leadingIconColor = colorScheme.primary,
            trailingIconColor = colorScheme.primary,
            disabledContainerColor = colorScheme.primaryContainer.copy(.5f),
            disabledLabelColor = colorScheme.secondary.copy(.5f),
            disabledLeadingIconColor = colorScheme.secondary.copy(.5f),
            disabledTrailingIconColor = colorScheme.secondary.copy(.5f),
            selectedContainerColor = colorScheme.primary,
            disabledSelectedContainerColor = colorScheme.errorContainer,
            selectedLabelColor = colorScheme.onPrimary,
            selectedLeadingIconColor = colorScheme.onPrimary,
            selectedTrailingIconColor = colorScheme.onPrimary,
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
val inputChipColorsVariant
    @Composable get() = run {
        inputChipColors(
            containerColor = colorScheme.onPrimary,
            labelColor = colorScheme.primary,
            leadingIconColor = colorScheme.primary,
            trailingIconColor = colorScheme.primary,
            disabledContainerColor = colorScheme.primaryContainer.copy(.5f),
            disabledLabelColor = colorScheme.secondary.copy(.5f),
            disabledLeadingIconColor = colorScheme.secondary.copy(.5f),
            disabledTrailingIconColor = colorScheme.secondary.copy(.5f),
            selectedContainerColor = colorScheme.primary,
            disabledSelectedContainerColor = colorScheme.errorContainer,
            selectedLabelColor = colorScheme.onPrimary,
            selectedLeadingIconColor = colorScheme.onPrimary,
            selectedTrailingIconColor = colorScheme.onPrimary,
        )
    }

val cardColors
    @Composable get() = run {
        cardColors(
            containerColor = colorScheme.primaryContainer,
            contentColor = colorScheme.onPrimaryContainer,
        )
    }

@Composable
fun textFieldColors(
    focusedTextColor: Color = colorScheme.primary,
    unfocusedTextColor: Color = colorScheme.primary,
    disabledTextColor: Color = colorScheme.primary,
    focusedContainerColor: Color = colorScheme.primaryContainer,
    unfocusedContainerColor: Color = colorScheme.primaryContainer,
    disabledContainerColor: Color = colorScheme.primaryContainer,
    cursorColor: Color = colorScheme.primary,
    focusedIndicatorColor: Color = Color.Transparent,
    unfocusedIndicatorColor: Color = Color.Transparent,
    disabledIndicatorColor: Color = Color.Transparent,
    focusedTrailingIconColor: Color = colorScheme.primary,
    unfocusedTrailingIconColor: Color = colorScheme.primary,
    disabledTrailingIconColor: Color = colorScheme.primary,
    focusedLeadingIconColor: Color = colorScheme.primary,
    unfocusedLeadingIconColor: Color = colorScheme.primary,
    disabledLeadingIconColor: Color = colorScheme.primary,
    focusedLabelColor: Color = colorScheme.primary.copy(.6f),
    unfocusedLabelColor: Color = colorScheme.primary.copy(.6f),
    disabledLabelColor: Color = colorScheme.primary.copy(.6f),
): TextFieldColors {
        return TextFieldDefaults.colors(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            disabledTextColor = disabledTextColor,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            disabledContainerColor = disabledContainerColor,
            cursorColor = cursorColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            disabledIndicatorColor = disabledIndicatorColor,
            focusedTrailingIconColor = focusedTrailingIconColor,
            unfocusedTrailingIconColor = unfocusedTrailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            focusedLeadingIconColor = focusedLeadingIconColor,
            unfocusedLeadingIconColor = unfocusedLeadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            disabledLabelColor = disabledLabelColor,
        )
    }

val textFieldDisableColors
    @Composable get() = run {
        TextFieldDefaults.colors(
            focusedTextColor = colorScheme.primary,
            unfocusedTextColor = colorScheme.primary,
            disabledTextColor = colorScheme.primary.copy(.5f),
            focusedContainerColor = colorScheme.primaryContainer,
            unfocusedContainerColor = colorScheme.primaryContainer,
            disabledContainerColor = colorScheme.primaryContainer.copy(.5f),
            cursorColor = colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTrailingIconColor = colorScheme.primary,
            unfocusedTrailingIconColor = colorScheme.primary,
            disabledTrailingIconColor = colorScheme.primary.copy(.5f),
            focusedLabelColor = colorScheme.primary.copy(.6f),
            unfocusedLabelColor = colorScheme.primary.copy(.6f),
            disabledLabelColor = colorScheme.primary.copy(.4f),
        )
    }

val alertDialogColors
    @Composable get() = run {
        AlertDialogColors.colors(
            containerColor = colorScheme.primaryContainer,
            iconContentColor = colorScheme.onPrimaryContainer,
            titleContentColor = colorScheme.onPrimaryContainer,
            textContentColor = colorScheme.onPrimaryContainer,
        )
    }

val alertDialogErrorColors
    @Composable get() = run {
        AlertDialogColors.colors(
            containerColor = colorScheme.errorContainer,
            iconContentColor = colorScheme.onErrorContainer,
            titleContentColor = colorScheme.onErrorContainer,
            textContentColor = colorScheme.onErrorContainer,
        )
    }

val filledIconButtonColors
    @Composable get() = run {
        IconButtonDefaults.filledIconButtonColors(
            containerColor = colorScheme.onPrimary,
            contentColor = colorScheme.primary
        )
    }

val filledIconButtonColorsContrast
    @Composable get() = run {
        IconButtonDefaults.filledIconButtonColors(
            containerColor = colorScheme.primaryContainer,
            contentColor = colorScheme.primary
        )
    }


val switchColors
    @Composable get() = run {
        SwitchDefaults.colors(
            uncheckedThumbColor = colorScheme.onPrimaryContainer.copy(.4f),
            uncheckedTrackColor = colorScheme.onPrimary,
            uncheckedBorderColor = colorScheme.onPrimaryContainer.copy(.4f),
            uncheckedIconColor = colorScheme.onPrimaryContainer.copy(.4f),
        )
    }

val filledIconToggleButtonColors
    @Composable get() = run {
        IconButtonDefaults.filledIconToggleButtonColors(
            containerColor = colorScheme.onPrimary,
            contentColor = colorScheme.onPrimaryContainer,
            checkedContainerColor = colorScheme.onPrimaryContainer,
            checkedContentColor = colorScheme.onPrimary,
            disabledContainerColor = colorScheme.secondaryContainer.copy(.5f),
            disabledContentColor = colorScheme.onSecondaryContainer.copy(.5f),
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
val datePickerColors: DatePickerColors
    @Composable get() = run {
        DatePickerDefaults.colors(containerColor = colorScheme.primaryContainer)
    }

@OptIn(ExperimentalMaterial3Api::class)
val timePickerColors: TimePickerColors
    @Composable get() = run {
        TimePickerDefaults.colors(
            clockDialColor = colorScheme.primaryContainer,
            clockDialSelectedContentColor = colorScheme.onPrimary,
            clockDialUnselectedContentColor = colorScheme.onPrimaryContainer,
            selectorColor = colorScheme.onPrimaryContainer,
            periodSelectorBorderColor = colorScheme.onPrimaryContainer,
            periodSelectorSelectedContainerColor = colorScheme.onPrimaryContainer,
            periodSelectorUnselectedContainerColor = Color.Transparent,
            periodSelectorSelectedContentColor = colorScheme.onPrimary,
            periodSelectorUnselectedContentColor = colorScheme.onPrimaryContainer,
            timeSelectorSelectedContainerColor = colorScheme.primaryContainer,
            timeSelectorUnselectedContainerColor = Color.Transparent,
            timeSelectorSelectedContentColor = colorScheme.onPrimaryContainer,
            timeSelectorUnselectedContentColor = colorScheme.onPrimaryContainer.copy(.5f),
        )
    }
