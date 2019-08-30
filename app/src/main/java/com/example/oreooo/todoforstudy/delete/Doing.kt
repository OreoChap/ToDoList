package com.example.oreooo.todoforstudy.delete

class Doing private constructor()  {
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = Doing()
    }

}