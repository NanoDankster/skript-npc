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
import org.bukkit.Location;
import org.bukkit.event.Event;

@Name("Spawn Citizen")
@Description("Spawns a citizen at specific location.")
@Examples("spawn the last created citizen at player's location")
@Since("0.1")
public class EffSpawnCitizen extends Effect {
    static {
        Skript.registerEffect(EffSpawnCitizen.class,
                "spawn %citizen% at %location%");
    }

    private Expression<SkriptCitizen> citizen;
    private Expression<Location> location;

    @Override
    protected void execute(Event event) {
        if (citizen.getSingle(event) != null && location.getSingle(event) != null) {
            citizen.getSingle(event).getNpc().spawn(location.getSingle(event));
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "spawn a citizen at some location";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        location = (Expression<Location>) expressions[1];
        return true;
    }
}
