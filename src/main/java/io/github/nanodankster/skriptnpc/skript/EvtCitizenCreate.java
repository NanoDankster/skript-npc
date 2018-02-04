package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.api.event.NPCCreateEvent;
import org.bukkit.event.Event;

public class EvtCitizenCreate extends SkriptEvent {
    static {
        Skript.registerEvent("Citizen Create", EvtCitizenCreate.class, NPCCreateEvent.class,
                "(citizen|npc) create")
                .description("Called when a citizen is created.")
                .examples("on citizen create:");
        EventValues.registerEventValue(NPCCreateEvent.class, SkriptCitizen.class, new Getter<SkriptCitizen, NPCCreateEvent>() {
            @Override
            public SkriptCitizen get(NPCCreateEvent npcCreateEvent) {
                return new SkriptCitizen(npcCreateEvent.getNPC());
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof NPCCreateEvent;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "citizen create event";
    }
}
