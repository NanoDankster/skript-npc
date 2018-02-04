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
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

@Name("Citizen Attack/Follow")
@Description("Makes a citizen attack or follow an entity.")
@Examples({
        "make the last citizen follow player",
        "make the last citizen attack player"})
@Since("0.1")
public class EffCitizenAttack extends Effect {
    static {
        Skript.registerEffect(EffCitizenAttack.class,
                "make %citizen% (0¦attack|1¦follow) %livingentity%");
    }

    private Expression<SkriptCitizen> citizen;
    private Expression<LivingEntity> entity;
    private Boolean attack = true;

    @Override
    protected void execute(Event event) {
        if (citizen != null && entity != null) {
            Navigator nav = citizen.getSingle(event).getNpc().getNavigator();
            nav.cancelNavigation();
            nav.setTarget(entity.getSingle(event), attack);
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "make a citizen attack/follow an entity";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        entity = (Expression<LivingEntity>) expressions[1];
        if (parseResult.mark == 1) {
            attack = false;
        }
        return true;
    }
}
