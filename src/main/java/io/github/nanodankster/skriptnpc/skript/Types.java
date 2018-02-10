package io.github.nanodankster.skriptnpc.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Converter;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.Converters;
import io.github.nanodankster.skriptnpc.util.EnumClassInfo;
import io.github.nanodankster.skriptnpc.util.SkriptCitizen;
import net.citizensnpcs.util.PlayerAnimation;
import org.bukkit.entity.Entity;

public class Types {
    static {
        Classes.registerClass(new ClassInfo<>(SkriptCitizen.class, "citizen")
                .user("citizens?")
                .parser(new Parser<SkriptCitizen>() {
                    @Override
                    public SkriptCitizen parse(String s, ParseContext context) {
                        return null;
                    }
                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }
                    @Override
                    public String toString(SkriptCitizen skriptCitizen, int i) {
                        return skriptCitizen.toString();
                    }
                    @Override
                    public String toVariableNameString(SkriptCitizen skriptCitizen) {
                        return skriptCitizen.toString();
                    }
                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }
                })
        );

        // This is how you use the 131 lines of code you've written in 8 hours.
        Classes.registerClass(new EnumClassInfo<>(PlayerAnimation.class, "npcanimation").getClassInfo());

        Converters.registerConverter(SkriptCitizen.class, Entity.class, (Converter<SkriptCitizen, Entity>) skriptCitizen -> {
            if (skriptCitizen.getNpc().isSpawned()) {
                return skriptCitizen.getNpc().getEntity();
            }
            return null;
        });
    }
}
