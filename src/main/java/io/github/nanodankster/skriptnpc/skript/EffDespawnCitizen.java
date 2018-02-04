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
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;

@Name("Despawn Citizen")
@Description("Despawns a spawned citizen.")
@Examples("despawn the last created citizen")
@Since("0.1")
public class EffDespawnCitizen extends Effect {
    static {
        Skript.registerEffect(EffDespawnCitizen.class,
                "despawn %citizen%");
    }

    private Expression<SkriptCitizen> citizen;

    @Override
    protected void execute(Event event) {
        if (citizen != null) {
            NPC npc = citizen.getSingle(event).getNpc();
            if (npc.isSpawned()) {
                npc.despawn();
            }
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "despawn a citizen";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        return true;
    }
}
