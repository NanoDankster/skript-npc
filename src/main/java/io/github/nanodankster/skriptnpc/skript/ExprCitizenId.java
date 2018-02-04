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
import org.bukkit.event.Event;

@Name("Citizen ID")
@Description("Returns the ID of a citizen.")
@Examples("set {_x} to npc id of the last citizen")
@Since("0.1")
public class ExprCitizenId extends SimpleExpression<Number> {
    static {
        Skript.registerExpression(ExprCitizenId.class, Number.class, ExpressionType.PROPERTY,
                "(citizen|npc) id of %citizen%",
                "%citizen%'s (citizen|npc) id");
    }

    private Expression<SkriptCitizen> citizen;

    @Override
    protected Number[] get(Event event) {
        return new Number[]{citizen.getSingle(event).getId()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "npc id of a citizen";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        return true;
    }
}
