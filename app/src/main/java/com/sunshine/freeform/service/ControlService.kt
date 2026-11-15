package com.sunshine.freeform.service

import android.annotation.SuppressLint
import android.os.SystemClock
import android.view.*
import com.sunshine.freeform.IControlService

import com.sunshine.freeform.bean.MotionEventBean
import com.sunshine.freeform.systemapi.InputManager
import com.sunshine.freeform.systemapi.ServiceManager
import com.sunshine.freeform.utils.ShellUtils
import rikka.shizuku.ShizukuBinderWrapper
import rikka.shizuku.SystemServiceHelper

/**
 * @author sunshine
 * @date 2021/3/17
 */

class ControlService : IControlService.Stub() {
    private var inputManager: InputManager? = null

    /**
     * 初始化服务
     */
    @SuppressLint("PrivateApi")
    override fun init(): Boolean {
        val serviceManager = ServiceManager()
        inputManager = serviceManager.getInputManager()

        return inputManager != null
    }

    override fun execShell(command: String, useRoot: Boolean): Boolean {
        return ShellUtils.execCommand(command, useRoot).result == 0
    }

    /**
     * 触摸事件
     */
    override fun touch(motionEventBean: MotionEventBean) {
        val count = motionEventBean.xArray.size
        val pointerProperties: Array<MotionEvent.PointerProperties?> = arrayOfNulls(count)
        val pointerCoords: Array<MotionEvent.PointerCoords?> = arrayOfNulls(count)
        for (i in 0 until count) {
            pointerProperties[i] = MotionEvent.PointerProperties()
            pointerProperties[i]!!.id = i
            pointerProperties[i]!!.toolType = MotionEvent.TOOL_TYPE_FINGER

            pointerCoords[i] = MotionEvent.PointerCoords()
            pointerCoords[i]!!.apply {
                orientation = 0f
                pressure = 1f
                size = 1f
                x = motionEventBean.xArray[i]
                y = motionEventBean.yArray[i]
            }
        }

        val motionEvent = MotionEvent.obtain(
            SystemClock.uptimeMillis(),
            SystemClock.uptimeMillis(),
            motionEventBean.action,
            count,
            pointerProperties,
            pointerCoords,
            0,
            0,
            1.0f,
            1.0f,
            -1,
            0,
            InputDevice.SOURCE_TOUCHSCREEN,
            0
        )

        inputManager!!.injectInputEvent(motionEvent, motionEventBean.displayId)

        motionEvent.recycle()
    }

    /**
     * Move whatever is on [displayId] (virtual freeform display) onto the default display (0).
     * Tries several shell forms because output differs across Android versions.
     */
    override fun moveStack(displayId: Int): Boolean {
        return try {
            val list = ShellUtils.execCommand("am stack list", false)
            val out = ((list.successMsg ?: "") + "\n" + (list.errorMsg ?: ""))
            val stackIds = linkedSetOf<String>()
            for (line in out.split('\n')) {
                if (!Regex("displayId=" + displayId + "(\\D|$)").containsMatchIn(line)) {
                    continue
                }
                val m = Regex("(?:RootTask|Stack|Task)\\s+id=(\\d+)").find(line)
                    ?: Regex("id=(\\d+)").find(line)
                if (m != null) {
                    stackIds.add(m.groupValues[1])
                }
            }
            if (stackIds.isEmpty()) {
                val legacy = ShellUtils.execCommand(
                    "am stack list | grep displayId=$displayId",
                    false
                ).successMsg
                if (!legacy.isNullOrBlank()) {
                    for (part in legacy.trim().split(Regex("\\s+"))) {
                        if (part.startsWith("id=")) {
                            val digits = part.removePrefix("id=").filter { it.isDigit() }
                            if (digits.isNotEmpty()) stackIds.add(digits)
                        }
                    }
                }
            }
            var any = false
            for (sid in stackIds) {
                if (sid.isEmpty()) continue
                val r1 = ShellUtils.execCommand("am display move-stack $sid 0", false).result
                val r2 = ShellUtils.execCommand("am stack move-stack $sid 0", false).result
                if (r1 == 0 || r2 == 0) any = true
            }
            any
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 点击返回
     */
    override fun pressBack(displayId: Int) {
        val down = KeyEvent(
            SystemClock.uptimeMillis(),
            SystemClock.uptimeMillis(),
            KeyEvent.ACTION_DOWN,
            KeyEvent.KEYCODE_BACK,
            0
        )
        val up = KeyEvent(
            SystemClock.uptimeMillis(),
            SystemClock.uptimeMillis(),
            KeyEvent.ACTION_UP,
            KeyEvent.KEYCODE_BACK,
            0
        )

        try {
            KeyEvent::class.java.getMethod("setSource", Int::class.javaPrimitiveType)
                .invoke(down, InputDevice.SOURCE_KEYBOARD)
            inputManager!!.injectInputEvent(down, displayId)

            KeyEvent::class.java.getMethod("setSource", Int::class.javaPrimitiveType)
                .invoke(up, InputDevice.SOURCE_KEYBOARD)
            inputManager!!.injectInputEvent(up, displayId)
        } catch (e: Exception) {

        }
    }
}