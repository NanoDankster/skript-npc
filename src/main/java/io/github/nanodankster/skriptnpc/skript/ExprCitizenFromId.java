package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.event.Event;

@Name("Citizen from ID")
@Description("Returns a citizen from its ID.")
@Examples("set {_x} to the citizen from npc id 1")
@Since("0.1")
public class ExprCitizenFromId extends SimpleExpression<SkriptCitizen> {
    static {
        Skript.registerExpression(ExprCitizenFromId.class, SkriptCitizen.class, ExpressionType.SIMPLE,
                "[the] (citizen|npc) from id[entifier] %number%");
    }

    private Expression<Number> id;

    @Override
    protected SkriptCitizen[] get(Event event) {
        if (id != null && CitizensAPI.getNPCRegistry().getById(id.getSingle(event).intValue()) != null) {
            return new SkriptCitizen[]{new SkriptCitizen(CitizensAPI.getNPCRegistry().getById(id.getSingle(event).intValue()))};
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends SkriptCitizen> getReturnType() {
        return SkriptCitizen.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "a citizen from id";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Number>) expressions[0];
        return true;
    }
}
