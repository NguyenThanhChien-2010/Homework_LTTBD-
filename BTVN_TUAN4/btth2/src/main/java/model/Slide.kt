package com.example.btth2.model

import androidx.annotation.DrawableRes
import com.example.btth2.R

data class Slide(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val slides = listOf(
    Slide(
        "Easy Time Management",
        "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first.",
        R.drawable.img_onboard1
    ),
    Slide(
        "Increase Work Effectiveness",
        "Time management and delegation of more important tasks will give your job statistics better and always improve.",
        R.drawable.img_onboard2
    ),
    Slide(
        "Reminder Notification",
        "The advantage of this application is that it provides reminders for you so you don't forget to miss doing your assignments on time.",
        R.drawable.img_onboard3
    )
)
