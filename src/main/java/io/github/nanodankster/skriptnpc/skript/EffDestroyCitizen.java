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

@Name("Destroy Citizen")
@Description("Destroys a citizen.")
@Examples("destroy the last created citizen")
@Since("0.1")
public class EffDestroyCitizen extends Effect {
    static {
        Skript.registerEffect(EffDestroyCitizen.class,
                "destroy %citizen%");
    }

    private Expression<SkriptCitizen> citizen;

    @Override
    protected void execute(Event event) {
        if (citizen.getSingle(event) != null) {
            citizen.getSingle(event).getNpc().destroy();
            if (citizen.getSingle(event) == SkriptCitizen.getLastCitizen()) {
                SkriptCitizen.setLastCitizen(null);
            }
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "destroy a citizen";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        return true;
    }
}
