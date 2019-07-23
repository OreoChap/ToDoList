package com.example.oreooo.todoforstudy.Fragment

import android.os.Bundle
import android.view.View
import com.oreooo.library.MvpBase.BaseFragment

class Doing private constructor()  {



    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = Doing()
    }

}