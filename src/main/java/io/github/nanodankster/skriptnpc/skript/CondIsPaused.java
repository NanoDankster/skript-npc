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
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import org.bukkit.event.Event;

@Name("Citizen Is Paused")
@Description("Checks if a citizen is paused.")
@Examples("if the last citizen is paused:")
@Since("0.1")
public class CondIsPaused extends Condition {
    static {
        Skript.registerCondition(CondIsPaused.class,
                "[the] %citizen% is paused");
    }

    private Expression<SkriptCitizen> citizen;

    @Override
    public boolean check(Event event) {
        return citizen.getSingle(event) != null && citizen.getSingle(event).getNpc().getNavigator().isPaused();
    }

    @Override
    public String toString(Event event, boolean b) {
        return "a citizen is paused";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        return true;
    }
}
