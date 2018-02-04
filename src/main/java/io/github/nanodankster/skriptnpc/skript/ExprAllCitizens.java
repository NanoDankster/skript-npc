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

@Name("All Citizens")
@Description("Returns all citizens.")
@Examples("set {_x::*} to all created citizens")
@Since("0.1")
public class ExprAllCitizens extends SimpleExpression<SkriptCitizen> {
    static {
        Skript.registerExpression(ExprAllCitizens.class, SkriptCitizen.class, ExpressionType.SIMPLE,
                "all [created] (citizen|npc)s");
    }

    @Override
    protected SkriptCitizen[] get(Event event) {
        return SkriptCitizen.getAllCitizens();
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends SkriptCitizen> getReturnType() {
        return SkriptCitizen.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "all citizens";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
