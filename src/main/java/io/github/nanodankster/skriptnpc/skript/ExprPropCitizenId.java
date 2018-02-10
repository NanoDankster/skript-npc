package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.nanodankster.skriptnpc.SkriptNPC;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;

@Name("Citizen ID")
@Description("Returns the ID of a citizen. Cannot be changed.")
@Examples("if id of the event-citizen is 5:")
@Since("0.1.1")
public class ExprPropCitizenId extends SimplePropertyExpression<SkriptCitizen, Number> {
    static {
        SkriptNPC.registerPropExpr(ExprPropCitizenId.class, Number.class, "[npc] id[entifier]", "citizen");
    }

    @Override
    protected String getPropertyName() {
        return "id of citizen";
    }

    @Override
    public Number convert(SkriptCitizen skriptCitizen) {
        if (skriptCitizen != null) {
            return skriptCitizen.getNpc().getId();
        }
        return null;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }
}
