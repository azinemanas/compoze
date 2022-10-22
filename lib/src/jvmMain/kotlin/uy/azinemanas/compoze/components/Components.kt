package uy.azinemanas.compoze.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReusableComposeNode
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.gui2.*
import uy.azinemanas.compoze.LanternaApplier

@Composable
fun Panel(direction: Direction, content: @Composable () -> Unit) {
    ReusableComposeNode<Container, LanternaApplier>(
        factory = { Panel().also {
            it.layoutManager = LinearLayout(direction)
        } },
        update = {

        },
        content = content)
}

@Composable
fun Text(s: String) {
    ReusableComposeNode<Label, LanternaApplier>({ Label(s) }) {
        set(s) {
            text = it
        }
    }
}

@Composable
fun Empty() {
    ReusableComposeNode<EmptySpace, LanternaApplier>({ EmptySpace() }) {

    }
}

@Composable
fun Combobox(l: List<String>) {
    ReusableComposeNode<ComboBox<String>, LanternaApplier>({ ComboBox(l) }) {
        set(l) {
            clearItems()
            l.forEach { addItem(it) }
        }
    }
}

@Composable
fun Checkbox(s: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    ReusableComposeNode<CheckBox, LanternaApplier>({ CheckBox(s).also { it.isChecked = checked } }) {
        set(s) {
            label = it
        }
        set(checked) {
            isChecked = it
        }
        set(onChange) {
            addListener {
                onChange(it)
            }
        }
    }
}

@Composable
fun TextBox(s: String, onChange: (String) -> Unit) {
    ReusableComposeNode<TextBox, LanternaApplier>({ TextBox(s) }) {
        set(onChange) {
            setTextChangeListener { newText, changedByUserInteraction ->
                onChange(newText)
            }
        }
        set(s) {
            text = it
        }
    }
}

@Composable
fun Button(
    label: String,
    onClick: () -> Unit = {}) {

    ReusableComposeNode<Button, LanternaApplier>(
        factory = {
            com.googlecode.lanterna.gui2.Button(label)
        },
        update = {
            set(label) {
                this.label = it
            }
            set(onClick) {
                addListener {
                    onClick()
                }
            }
        }
    )
}
