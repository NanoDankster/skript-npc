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
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

@Name("Citizen Entity")
@Description("Returns the entity of a citizen")
@Examples("set {_x} to the npc entity of the last citizen")
@Since("0.1")
public class ExprCitizenEntity extends SimpleExpression<Entity> {
    static {
        Skript.registerExpression(ExprCitizenEntity.class, Entity.class, ExpressionType.SIMPLE,
                "[the] (citizen|npc) entity of %citizen%",
                "[the] %citizen%'s (citizen|npc) entity");
    }

    private Expression<SkriptCitizen> citizen;

    @Override
    protected Entity[] get(Event event) {
        if (citizen.getSingle(event) != null) {
            if (citizen.getSingle(event).getNpc().isSpawned()) {
                return new Entity[]{citizen.getSingle(event).getNpc().getEntity()};
            }
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "entity of a citizen";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        return true;
    }
}
