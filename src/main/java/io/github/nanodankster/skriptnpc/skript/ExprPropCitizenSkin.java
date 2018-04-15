package io.github.nanodankster.skriptnpc.skript;

import java.util.Arrays;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import io.github.nanodankster.skriptnpc.SkriptNPC;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.skin.SkinnableEntity;

@Name("Citizen Skin")
@Description("Returns the skin name of a citizen. Can be set and reset to \"Steve\" skin.")
@Examples("set skin of the last npc to \"Nano\"")
@Since("0.1.1")
public class ExprPropCitizenSkin extends SimplePropertyExpression<SkriptCitizen, String> {

    static {
        SkriptNPC.registerPropExpr(ExprPropCitizenSkin.class, String.class, "[npc] skin [name]", "citizens");
    }

    @Override
    @Nullable
    public String convert(SkriptCitizen skriptCitizen) {
        NPC npc = skriptCitizen.getNpc();
        return npc.isSpawned() ? ((SkinnableEntity) npc).getSkinName() : null;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "npc skin";
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET)
            return CollectionUtils.array(String.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && delta == null)
            return;
        String skin = mode == Changer.ChangeMode.SET ? (String) delta[0] : "Steve";
        Arrays.stream(getExpr().getArray(e))
                .map(SkriptCitizen::getNpc)
                .filter(NPC::isSpawned)
                .forEach(npc -> ((SkinnableEntity) npc).setSkinName(skin));
    }
}
