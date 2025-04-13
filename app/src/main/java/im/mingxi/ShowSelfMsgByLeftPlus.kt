package im.mingxi

import io.github.qauxv.base.annotation.FunctionHookEntry
import io.github.qauxv.base.annotation.UiItemAgentEntry
import io.github.qauxv.dsl.FunctionEntryRouter
import io.github.qauxv.hook.CommonSwitchFunctionHook
import io.github.qauxv.util.Initiator
import io.github.qauxv.util.xpcompat.XC_MethodHook
import io.github.qauxv.util.xpcompat.XposedBridge

@FunctionHookEntry
@UiItemAgentEntry
object ShowSelfMsgByLeftPlus : CommonSwitchFunctionHook() {

    override val name = "自己消息居左 Plus"

    override val description = "将自己的消息滑至左边"

    override val uiItemLocation: Array<String> = FunctionEntryRouter.Locations.Auxiliary.CHAT_CATEGORY

    override fun initOnce(): Boolean {
        val chatItemClass = io.github.qauxv.util.Initiator.loadClass("com.tencent.mobileqq.activity.aio.ChatItemAnimLayout")
        XposedBridge.hookMethod(chatItemClass.declaredMethod("setFrom", Boolean::class.java), object: XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                param.args[0] = false
            }
        })
        return true
    }
}
