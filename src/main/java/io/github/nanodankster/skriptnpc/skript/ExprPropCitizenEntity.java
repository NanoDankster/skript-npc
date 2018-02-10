package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.nanodankster.skriptnpc.SkriptNPC;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import org.bukkit.entity.Entity;

@Name("Citizen Entity")
@Description("Returns the entity of a citizen. Cannot be changed.")
@Examples("if entity of the last citizen is entity of event-citizen:")
@Since("0.1.1")
public class ExprPropCitizenEntity extends SimplePropertyExpression<SkriptCitizen, Entity> {
    static {
        SkriptNPC.registerPropExpr(ExprPropCitizenEntity.class, Entity.class, "npc entity", "citizen");
    }

    @Override
    protected String getPropertyName() {
        return "entity of citizen";
    }

    @Override
    public Entity convert(SkriptCitizen skriptCitizen) {
        if (skriptCitizen != null && skriptCitizen.getNpc().isSpawned()) {
            return skriptCitizen.getNpc().getEntity();
        }
        return null;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }
}
