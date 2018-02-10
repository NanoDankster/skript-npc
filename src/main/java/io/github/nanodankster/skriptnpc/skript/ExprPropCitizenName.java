package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import io.github.nanodankster.skriptnpc.SkriptNPC;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import org.bukkit.event.Event;

@Name("Citizen Name")
@Description("Returns the name of a citizen. Can be set and reset to its ID.")
@Examples("set name of the last npc to \"Nano\"")
@Since("0.1.1")
public class ExprPropCitizenName extends SimplePropertyExpression<SkriptCitizen, String>{
    static {
        SkriptNPC.registerPropExpr(ExprPropCitizenName.class, String.class, "[npc] name", "citizen");
    }
    @Override
    protected String getPropertyName() {
        return "name of citizen";
    }

    @Override
    public String convert(SkriptCitizen skriptCitizen) {
        if (skriptCitizen != null) {
            return skriptCitizen.getNpc().getName();
        }
        return null;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        if (mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array();
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        switch (mode) {
            case SET:
                if (getExpr().getSingle(e) != null) {
                    getExpr().getSingle(e).getNpc().setName((String) delta[0]);
                }
                break;
            case RESET:
                if (getExpr().getSingle(e) != null) {
                    getExpr().getSingle(e).getNpc().setName(String.valueOf(getExpr().getSingle(e).getNpc().getId()));
                }
                break;
        }
    }
}
