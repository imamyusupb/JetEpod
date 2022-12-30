package com.seinoindomobil.jetepod.domain.model

import com.seinoindomobil.jetepod.R


class OnBoarding(
    val image:Int,
    val description:Int,
){
    companion object{
        fun getData():List<OnBoarding>{
            return listOf(
                OnBoarding(R.drawable.img_slider_board1,R.string.OnBoardingText1),
                OnBoarding(R.drawable.img_slider_board2,R.string.OnBoardingText2),
                OnBoarding(R.drawable.img_slider_board3,R.string.OnBoardingText3),
                OnBoarding(R.drawable.img_slider_board4,R.string.OnBoardingText4),
                OnBoarding(R.drawable.img_slider_board5,R.string.OnBoardingText5),
                OnBoarding(R.drawable.img_slider_board6,R.string.OnBoardingText6),
                )
        }
    }
}