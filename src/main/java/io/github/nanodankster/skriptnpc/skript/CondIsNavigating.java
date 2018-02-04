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

@Name("Citizen Is Navigating")
@Description("Checks if a citizen is navigating.")
@Examples("if the last citizen is navigating:")
@Since("0.1")
public class CondIsNavigating extends Condition {
    static {
        Skript.registerCondition(CondIsNavigating.class,
                "[the] %citizen% (is (navigating|moving)|has [a] navigation)");
    }

    private Expression<SkriptCitizen> citizen;

    @Override
    public boolean check(Event event) {
        return citizen.getSingle(event) != null && citizen.getSingle(event).getNpc().getNavigator().isNavigating();
    }

    @Override
    public String toString(Event event, boolean b) {
        return "a citizen is navigating";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        return true;
    }
}
