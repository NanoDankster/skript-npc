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
import net.citizensnpcs.util.PlayerAnimation;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Animate Citizen")
@Description("Makes a citizen do the given animation.")
@Examples("make the last citizen sneak")
@Since("0.1")
public class EffAnimateCitizen extends Effect {
    static {
        Skript.registerEffect(EffAnimateCitizen.class,
                "make %citizen% [do] [anim[ation]] %npcanimation%");
    }

    private Expression<SkriptCitizen> citizen;
    private Expression<PlayerAnimation> animation;

    @Override
    protected void execute(Event event) {
        if (citizen.getSingle(event) != null && animation.getSingle(event) != null) {
            if (citizen.getSingle(event).getType().equals(EntityType.PLAYER)) {
                animation.getSingle(event).play((Player) citizen.getSingle(event).getNpc().getEntity());
            }
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "make a citizen do an animation";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        animation = (Expression<PlayerAnimation>) expressions[1];
        return true;
    }
}
