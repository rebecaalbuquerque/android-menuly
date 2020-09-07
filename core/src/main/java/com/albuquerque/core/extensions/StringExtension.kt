package com.albuquerque.core.extensions

import com.albuquerque.core.util.Config


fun String.capitalizeSentence(): String =
    this.toLowerCase(Config.LOCALE_BR).capitalize()