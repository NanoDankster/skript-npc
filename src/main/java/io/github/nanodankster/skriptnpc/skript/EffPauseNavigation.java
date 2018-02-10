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

@Name("Pause/Continue Citizen Navigation")
@Description("Pauses/Continues the navigation of a citizen.")
@Examples("pause navigation of the last citizen")
@Since("0.1")
public class EffPauseNavigation extends Effect {
    static {
        Skript.registerEffect(EffPauseNavigation.class,
                "(0¦pause|1¦continue) navigation of %citizen%",
                "(0¦pause|1¦continue) %citizen%'s navigation");
    }

    private Expression<SkriptCitizen> citizen;
    private Boolean pause = true;

    @Override
    protected void execute(Event event) {
        if (citizen != null) {
            if (citizen.getSingle(event) != null) {
                citizen.getSingle(event).getNpc().getNavigator().setPaused(pause);
            }
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "pause/continue the navigation of a citizen";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        if (parseResult.mark == 1) {
            pause = false;
        }
        return true;
    }
}
