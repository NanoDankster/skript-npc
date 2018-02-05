package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.nanodankster.skriptnpc.SkriptNPC;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Entity;

@Name("Entity Citizen")
@Description("Returns the citizen of an entity. Cannot be changed.")
@Examples("if citizen of target entity is the last citizen:")
@Since("0.1.1")
public class ExprPropEntityCitizen extends SimplePropertyExpression<Entity, SkriptCitizen> {
    static {
        SkriptNPC.registerPropExpr(ExprPropEntityCitizen.class, SkriptCitizen.class, "citizen", "entity");
    }

    @Override
    protected String getPropertyName() {
        return "citizen of entity";
    }

    @Override
    public SkriptCitizen convert(Entity entity) {
        if (entity != null) {
            if (CitizensAPI.getNPCRegistry().getNPC(entity) != null) {
                return new SkriptCitizen(CitizensAPI.getNPCRegistry().getNPC(entity));
            }
        }
        return null;
    }

    @Override
    public Class<? extends SkriptCitizen> getReturnType() {
        return SkriptCitizen.class;
    }
}
