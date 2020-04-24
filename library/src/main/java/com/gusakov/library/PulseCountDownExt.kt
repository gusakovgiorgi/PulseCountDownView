package com.gusakov.library

import com.gusakov.library.java.interfaces.OnCountdownCompleted

/**
 * Start countdown and invoke callback when it's ended
 */
fun PulseCountDown.start(callback: () -> Unit = {}) = start(OnCountdownCompleted { callback() })