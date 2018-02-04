package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import org.bukkit.event.Event;

@Name("Cancel Citizen Navigation")
@Description("Cancels/Stops the navigation of a citizen.")
@Examples("cancel navigation of the last citizen")
@Since("0.1")
public class EffCancelNavigation extends Effect {
    static {
        Skript.registerEffect(EffCancelNavigation.class,
                "(cancel|stop) navigation of %citizen%",
                "(cancel|stop) %citizen%'s navigation");
    }

    private Expression<SkriptCitizen> citizen;

    @Override
    protected void execute(Event event) {
        if (citizen != null) {
            citizen.getSingle(event).getNpc().getNavigator().cancelNavigation();
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "cancel the navigation of a citizen";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        return true;
    }
}
