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
import org.bukkit.event.Event;

@Name("Citizen Name")
@Description("Returns a citizen's name")
@Examples("set {_x} to the npc name of the last citizen")
@Since("0.1")
public class ExprCitizenName extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprCitizenName.class, String.class, ExpressionType.SIMPLE,
                "[the] (citizen|npc) name of %citizen%",
                "[the] %citizen%'s (citizen|npc) name");
    }

    private Expression<SkriptCitizen> citizen;

    @Override
    protected String[] get(Event event) {
        if (citizen.getSingle(event) != null) {
            return new String[]{citizen.getSingle(event).getNpc().getName()};
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
        return "name of a citizen";
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
                citizen.getSingle(e).getNpc().setName((String) delta[0]);
                break;
        }
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}
