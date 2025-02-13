package com.bymarcin.openglasses.surface.widgets.core.luafunction;

import com.bymarcin.openglasses.surface.widgets.core.attribute.IItemable;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;

import com.bymarcin.openglasses.lua.LuaFunction;
import com.bymarcin.openglasses.surface.Widget;

public class GetItem extends LuaFunction{

    @Override
    public Object[] call(Context context, Arguments arguments) {
        Widget widget = getSelf().getWidget();
        if(widget instanceof IItemable){
            return new Object[]{((IItemable) widget).getItem().getUnlocalizedName()};
        }
        throw new RuntimeException("Component does not exists!");
    }

    @Override
    public String getName() {
        return "getItem";
    }
}
