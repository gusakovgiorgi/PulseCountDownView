package com.gusakov.library

internal const val START_VALUE_DEFAULT = 10
internal const val END_VALUE_DEFAULT = 0

class CustomAttributes(
    var startValue: Int = START_VALUE_DEFAULT,
    var endValue: Int = END_VALUE_DEFAULT
)