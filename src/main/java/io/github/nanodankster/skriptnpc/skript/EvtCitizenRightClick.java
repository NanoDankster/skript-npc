package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.Event;

public class EvtCitizenRightClick extends SkriptEvent {
    static {
        Skript.registerEvent("Citizen Right Click", EvtCitizenRightClick.class, NPCRightClickEvent.class,
                "(citizen|npc) right[ ]click")
                .description("Called when a citizen is right clciked.")
                .examples("on citizen rightclick:");
        EventValues.registerEventValue(NPCRightClickEvent.class, SkriptCitizen.class, new Getter<SkriptCitizen, NPCRightClickEvent>() {
            @Override
            public SkriptCitizen get(NPCRightClickEvent npcRightClickEvent) {
                return new SkriptCitizen(npcRightClickEvent.getNPC());
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof NPCRightClickEvent;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "citizen right click event";
    }
}
