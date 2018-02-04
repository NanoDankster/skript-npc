package io.github.nanodankster.skriptnpc.util;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.PlayerAnimation;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SkriptCitizen {

    private static SkriptCitizen lastCitizen;

    private Integer id;
    private String name;
    private EntityType type;
    private NPC npc;

    public SkriptCitizen(NPC npc, EntityType type) {
        this.id = npc.getId();
        this.name = npc.getFullName();
        this.type = type;
        this.npc = npc;
    }

    public SkriptCitizen(NPC npc) {
        this.id = npc.getId();
        this.name = npc.getFullName();
        this.type = npc.getEntity().getType();
        this.npc = npc;
    }

    public NPC getNpc() {
        return npc;
    }

    public EntityType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public static void animateCitizen(SkriptCitizen citizen, PlayerAnimation animation) {
        if (citizen.getType().equals(EntityType.PLAYER))
        animation.play((Player) citizen.getNpc().getEntity());
    }

    public static SkriptCitizen[] getAllCitizens() {
        ArrayList<SkriptCitizen> list = new ArrayList<>();
        for (NPC npc : CitizensAPI.getNPCRegistry().sorted()) {
            list.add(new SkriptCitizen(npc));
        }
        return list.toArray(new SkriptCitizen[list.size()]);
    }

    public static void setLastCitizen(SkriptCitizen citizen) {
        lastCitizen = citizen;
    }

    public static SkriptCitizen getLastCitizen() {
        return lastCitizen;
    }
}
