package uy.azinemanas.compoze.example

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.googlecode.lanterna.gui2.Direction
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.MouseCaptureMode
import com.googlecode.lanterna.terminal.Terminal
import uy.azinemanas.compoze.textUI
import uy.azinemanas.compoze.components.*

fun main() {

    val terminal: Terminal = DefaultTerminalFactory().apply {
        //setTelnetPort(1234)
        setMouseCaptureMode(MouseCaptureMode.CLICK)
    }.createTerminal()

    textUI(terminal) {

        var x by remember { mutableStateOf(1) }
        var t by remember { mutableStateOf("") }
        var b by remember { mutableStateOf(false) }

        Panel(direction = Direction.VERTICAL) {
            Text("$x $t")
            TextBox(t) { t = it }
            Button("INC") { x++ }
            Empty()
            Checkbox("Combustile", b) { b = it }
            if (b) {
                Text("$$$")
            }
            Empty()
            Combobox(listOf("Add", "Substract"))
            Empty()
            Panel(direction = Direction.HORIZONTAL) {
                Button("ACEPTAR")
                Button("CANCELAR")
            }
            Empty()
            Clock()

        }
    }
}
