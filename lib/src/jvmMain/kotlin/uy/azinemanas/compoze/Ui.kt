package uy.azinemanas.compoze

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.gui2.*
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.MouseCaptureMode
import com.googlecode.lanterna.terminal.Terminal
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

fun textUI(terminal: Terminal, content: @Composable () -> Unit) {

    val screen: Screen = TerminalScreen(terminal)
    screen.startScreen()

    val gui = MultiWindowTextGUI(screen, DefaultWindowManager(), EmptySpace(TextColor.ANSI.BLUE))

    val root = Panel()

    val window = BasicWindow()
    window.component = root

    val clock = DefaultMonotonicFrameClock
    val context = MainScope().coroutineContext + clock
    val recomp = Recomposer(context)

    val applier = LanternaApplier(root)
    val comp = Composition(applier, recomp)
    MainScope().launch(context) {
        recomp.runRecomposeAndApplyChanges()
    }

    Snapshot.registerGlobalWriteObserver{
        MainScope().launch {
            Snapshot.sendApplyNotifications()
        }
    }

    comp.setContent(content)

    gui.addWindowAndWait(window)
}
