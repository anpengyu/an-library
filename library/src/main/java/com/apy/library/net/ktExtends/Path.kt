

@file:Suppress("NOTHING_TO_INLINE")

package com.apy.library.net.ktExtends

import android.graphics.Path
import androidx.annotation.RequiresApi


/**
 * Create by JohnRambo at 2018/7/13
 */

@RequiresApi(19)
inline operator fun Path.plus(p: Path): Path {
    return Path(this).apply {
        op(p, Path.Op.UNION)
    }
}

@RequiresApi(19)
inline operator fun Path.minus(p: Path): Path {
    return Path(this).apply {
        op(p, Path.Op.DIFFERENCE)
    }
}

@RequiresApi(19)
inline infix fun Path.and(p: Path) = this + p

@RequiresApi(19)
inline infix fun Path.or(p: Path): Path {
    return Path().apply {
        op(this@or, p, Path.Op.INTERSECT)
    }
}

@RequiresApi(19)
inline infix fun Path.xor(p: Path): Path {
    return Path(this).apply {
        op(p, Path.Op.XOR)
    }
}
