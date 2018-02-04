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
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

@Name("Entity Citizen")
@Description("Returns the citizen of an entity.")
@Examples("set {_x} to the citizen of target entity")
@Since("0.1")
public class ExprEntityCitizen extends SimpleExpression<SkriptCitizen> {
    static {
        Skript.registerExpression(ExprEntityCitizen.class, SkriptCitizen.class, ExpressionType.SIMPLE,
                "[the] (citizen|npc) of %entity%",
                "[the] %entity%'s (citizen|npc)");
    }

    private Expression<Entity> entity;

    @Override
    protected SkriptCitizen[] get(Event event) {
        return new SkriptCitizen[]{new SkriptCitizen(CitizensAPI.getNPCRegistry().getNPC(entity.getSingle(event)))};
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
        return "citizen of an entity";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        entity = (Expression<Entity>) expressions[0];
        return true;
    }
}
