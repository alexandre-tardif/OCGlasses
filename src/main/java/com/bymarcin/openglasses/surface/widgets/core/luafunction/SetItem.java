package com.bymarcin.openglasses.surface.widgets.core.luafunction;

import com.bymarcin.openglasses.surface.widgets.core.attribute.IItemable;
import li.cil.oc.api.internal.Database;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;

import com.bymarcin.openglasses.lua.LuaFunction;
import com.bymarcin.openglasses.surface.Widget;
import li.cil.oc.api.network.Environment;
import net.minecraft.item.ItemStack;

public class SetItem extends LuaFunction{

    @Override
    public Object[] call(Context context, Arguments arguments) {
        Widget widget = getSelf().getWidget();
        if(widget instanceof IItemable){
            Environment env = context.node().network().node(arguments.checkString(0)).host();
            if (env instanceof Database) {
                ItemStack itemStack = ((Database) env).getStackInSlot(arguments.checkInteger(1) - 1);
                ((IItemable) widget).setItem(itemStack);
                getSelf().getTerminal().updateWidget(getSelf().getWidgetRef());
                return null;
            }
            throw new RuntimeException("Not a database");

        }
        throw new RuntimeException("Component does not exists!");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "setItem";
    }

}
