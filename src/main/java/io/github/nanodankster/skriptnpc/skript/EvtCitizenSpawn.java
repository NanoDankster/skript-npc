package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import org.bukkit.event.Event;

public class EvtCitizenSpawn extends SkriptEvent {
    static {
        Skript.registerEvent("Citizen Spawn", EvtCitizenCreate.class, NPCSpawnEvent.class,
                "(citizen|npc) spawn")
                .description("Called when a citizen is spawned.")
                .examples("on citizen spawn:");
        EventValues.registerEventValue(NPCSpawnEvent.class, SkriptCitizen.class, new Getter<SkriptCitizen, NPCSpawnEvent>() {
            @Override
            public SkriptCitizen get(NPCSpawnEvent npcSpawnEvent) {
                return new SkriptCitizen(npcSpawnEvent.getNPC());
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof NPCSpawnEvent;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "citizen spawn event";
    }
}
