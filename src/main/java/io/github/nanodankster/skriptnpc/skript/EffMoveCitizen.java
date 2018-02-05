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
import net.citizensnpcs.api.ai.Navigator;
import org.bukkit.Location;
import org.bukkit.event.Event;

@Name("Move Citizen")
@Description("Makes a citizen move to a specific location with optional speed.")
@Examples("make the last citizen move to player's location at speed 2")
@Since("0.1")
public class EffMoveCitizen extends Effect {
    static {
        Skript.registerEffect(EffMoveCitizen.class,
                "make %citizen% (go|move|travel) to [the] %location% [(at|with) speed %-number%]");
    }

    private Expression<SkriptCitizen> citizen;
    private Expression<Location> location;
    private Expression<Number> speed;

    @Override
    protected void execute(Event event) {
        if (citizen.getSingle(event) != null && location.getSingle(event) != null) {
            Number moveSpeed;
            if (speed != null && speed.getSingle(event) != null) {
                moveSpeed = speed.getSingle(event);
            } else {
                moveSpeed = 1;
            }
            Navigator nav = citizen.getSingle(event).getNpc().getNavigator();
            nav.cancelNavigation();
            nav.getDefaultParameters().baseSpeed(moveSpeed.floatValue());
            nav.setTarget(location.getSingle(event));
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "make a citizen travel to a location with optional speed";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        location = (Expression<Location>) expressions[1];
        speed = (Expression<Number>) expressions[2];
        return true;
    }
}
