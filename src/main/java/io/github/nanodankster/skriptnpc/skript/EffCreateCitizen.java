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
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.event.Event;

@Name("Create Citizen")
@Description("Creates a citizen with name, type and optional location.")
@Examples("create npc named \"Nano\" with type player at player's location")
@Since("0.1")
public class EffCreateCitizen extends Effect {
    static {
        Skript.registerEffect(EffCreateCitizen.class,
                "create [a] (citizen|npc) [(named|with name)] %string% [and] [with] [entity] type %entitytype% [at %-location%]");
    }

    private Expression<String> name;
    private Expression<ch.njol.skript.entity.EntityType> type;
    private Expression<Location> location;

    @Override
    protected void execute(Event event) {
        if (name.getSingle(event) != null && type.getSingle(event) != null) {
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(convert(type.getSingle(event).toString()), name.getSingle(event));
            if (location.getSingle(event) != null) {
                npc.spawn(location.getSingle(event));
            }
            SkriptCitizen.setLastCitizen(new SkriptCitizen(npc, convert(type.getSingle(event).toString())));
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "create a citizen with name, type and optional location";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) expressions[0];
        type = (Expression<ch.njol.skript.entity.EntityType>) expressions[1];
        location = (Expression<Location>) expressions[2];
        return true;
    }

    private org.bukkit.entity.EntityType convert(String type) {
        return org.bukkit.entity.EntityType.valueOf(type.toUpperCase().replace(" ", "_").trim());
    }
}
