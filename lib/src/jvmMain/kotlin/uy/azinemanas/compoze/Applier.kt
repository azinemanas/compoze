package uy.azinemanas.compoze

import androidx.compose.runtime.AbstractApplier
import com.googlecode.lanterna.gui2.Component
import com.googlecode.lanterna.gui2.Container
import com.googlecode.lanterna.gui2.Panel

class LanternaApplier(root: Container) : AbstractApplier<Component>(root) {

    override fun insertBottomUp(index: Int, instance: Component) {
        val container = current as Panel
        //println("insertBottomUp $index ${container.childrenList.size}")
        container.addComponent(index, instance)
    }

    override fun insertTopDown(index: Int, instance: Component) {
        //println("insertTopDown $index $instance $current")
        //(current as ZGroupNode).childrens.add(index, instance)
    }

    override fun move(from: Int, to: Int, count: Int) {
        println("move $from $to $count")
        (current as Container).childrenList.move(from, to, count)
    }

    override fun onClear() {
        println("onClear")
        val container = current as Panel
        container.removeAllComponents()
    }

    override fun remove(index: Int, count: Int) {
        //println("remove $index $count")
        val container = current as Panel
        val sublist = container.childrenList.subList(index, index+count)
        for (r in sublist) {
            container.removeComponent(r)
        }
    }
}