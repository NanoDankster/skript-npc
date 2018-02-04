package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.npc.skin.SkinnableEntity;
import org.bukkit.event.Event;

@Name("Citizen Skin")
@Description("Returns a citizen's skin name")
@Examples("set {_x} to the npc skin of last citizen")
@Since("0.1")
public class ExprCitizenSkin extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprCitizenSkin.class, String.class, ExpressionType.SIMPLE,
                "[the] (citizen|npc) skin [name] of %citizen%",
                "[the] %citizen%'s (citizen|npc) skin [name]");
    }

    private Expression<SkriptCitizen> citizen;

    @Override
    protected String[] get(Event event) {
        if (citizen.getSingle(event) != null) {
            if (citizen.getSingle(event).getNpc().isSpawned()) {
                SkinnableEntity skinnableEntity = (SkinnableEntity) citizen.getSingle(event).getNpc().getEntity();
                return new String[]{skinnableEntity.getSkinName()};
            }
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "skin name of a citizen";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        citizen = (Expression<SkriptCitizen>) expressions[0];
        return true;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        switch (mode) {
            case SET:
                SkinnableEntity skinnableEntity = (SkinnableEntity) citizen.getSingle(e).getNpc().getEntity();
                skinnableEntity.setSkinName((String) delta[0]);
                break;
            case RESET:
            case DELETE:
                skinnableEntity = (SkinnableEntity) citizen.getSingle(e).getNpc().getEntity();
                skinnableEntity.setSkinName("Steve");
                break;
        }
    }

    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }
}
