package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

@Name("Entity Is Citizen")
@Description("Checks if an entity is a citizen.")
@Examples("if target entity is a citizen:")
@Since("0.1.1")
public class CondIsCitizen extends Condition {
    static {
        Skript.registerCondition(CondIsCitizen.class, "" +
                "%entity% is [a] (citizen|npc)");
    }

    private Expression<Entity> entity;

    @Override
    public boolean check(Event event) {
        return entity.getSingle(event) != null && CitizensAPI.getNPCRegistry().getNPC(entity.getSingle(event)) != null;
    }

    @Override
    public String toString(Event event, boolean b) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        entity = (Expression<Entity>) expressions[0];
        return true;
    }
}
