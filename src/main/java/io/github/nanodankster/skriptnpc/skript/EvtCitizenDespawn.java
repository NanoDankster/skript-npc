package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.api.event.NPCDespawnEvent;
import org.bukkit.event.Event;

public class EvtCitizenDespawn extends SkriptEvent {
    static {
        Skript.registerEvent("Citizen Despawn", EvtCitizenCreate.class, NPCDespawnEvent.class,
                "(citizen|npc) despawn")
                .description("Called when a citizen is despawned.")
                .examples("on citizen despawn:");
        EventValues.registerEventValue(NPCDespawnEvent.class, SkriptCitizen.class, new Getter<SkriptCitizen, NPCDespawnEvent>() {
            @Override
            public SkriptCitizen get(NPCDespawnEvent npcDespawnEvent) {
                return new SkriptCitizen(npcDespawnEvent.getNPC());
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof NPCDespawnEvent;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "citizen despawn event";
    }
}
