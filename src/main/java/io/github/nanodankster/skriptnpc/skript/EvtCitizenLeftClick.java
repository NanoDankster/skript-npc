package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import org.bukkit.event.Event;

public class EvtCitizenLeftClick extends SkriptEvent {
    static {
        Skript.registerEvent("Citizen Left Click", EvtCitizenLeftClick.class, NPCLeftClickEvent.class,
                "(citizen|npc) left[ ]click")
                .description("Called when a citizen is left clciked.")
                .examples("on citizen leftclick:");
        EventValues.registerEventValue(NPCLeftClickEvent.class, SkriptCitizen.class, new Getter<SkriptCitizen, NPCLeftClickEvent>() {
            @Override
            public SkriptCitizen get(NPCLeftClickEvent npcLeftClickEvent) {
                return new SkriptCitizen(npcLeftClickEvent.getNPC());
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof NPCLeftClickEvent;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "citizen left click event";
    }
}
