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

@Name("Last Created Citizen")
@Description("Returns the last created citizen.")
@Examples("set {_x} to the last created citizen")
@Since("0.1")
public class ExprLastCitizen extends SimpleExpression<SkriptCitizen> {
    static {
        Skript.registerExpression(ExprLastCitizen.class, SkriptCitizen.class, ExpressionType.SIMPLE,
                "[the] last[ly] [created] (citizen|npc)");
    }

    @Override
    protected SkriptCitizen[] get(Event event) {
        if (SkriptCitizen.getLastCitizen() != null) {
            return new SkriptCitizen[]{SkriptCitizen.getLastCitizen()};
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
        return "last created npc";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
