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
import net.citizensnpcs.npc.skin.SkinnableEntity;
import org.bukkit.event.Event;

@Name("Citizen Skin")
@Description("Returns the skin name of a citizen. Can be set and reset to \"Steve\" skin.")
@Examples("set skin of the last npc to \"Nano\"")
@Since("0.1.1")
public class ExprPropCitizenSkin extends SimplePropertyExpression<SkriptCitizen, String> {
    static {
        SkriptNPC.registerPropExpr(ExprPropCitizenSkin.class, String.class, "skin [name]", "citizen");
    }

    @Override
    protected String getPropertyName() {
        return "skin of citizen";
    }

    @Override
    public String convert(SkriptCitizen skriptCitizen) {
        if (skriptCitizen != null && skriptCitizen.getNpc().isSpawned()) {
            SkinnableEntity skinnableEntity = (SkinnableEntity) skriptCitizen.getNpc().getEntity();
            return skinnableEntity.getSkinName();
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
        if (mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array();
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        switch (mode) {
            case SET:
                SkinnableEntity skinnableEntity = (SkinnableEntity) getExpr().getSingle(e).getNpc().getEntity();
                skinnableEntity.setSkinName((String) delta[0]);
                break;
            case RESET:
            case DELETE:
                skinnableEntity = (SkinnableEntity) getExpr().getSingle(e).getNpc().getEntity();
                skinnableEntity.setSkinName("Steve");
                break;
        }
    }
}
