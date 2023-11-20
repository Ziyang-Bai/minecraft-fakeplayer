package io.github.hello09x.fakeplayer.v1_20_R2.spi;

import io.github.hello09x.fakeplayer.api.action.ActionSetting;
import io.github.hello09x.fakeplayer.api.action.ActionType;
import io.github.hello09x.fakeplayer.api.spi.*;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.util.Set;

public class NMSBridgeImpl implements NMSBridge {

    private final static Set<String> SUPPORTS = Set.of("1.20.2");

    @Override
    public @NotNull NMSEntity entity(@NotNull Entity entity) {
        return new NMSEntityImpl(entity);
    }

    @Override
    public @NotNull NMSServer server(@NotNull Server server) {
        return new NMSServerImpl(server);
    }

    @Override
    public @NotNull NMSServerLevel world(@NotNull World world) {
        return new NMSServerLevelImpl(world);
    }

    @Override
    public @NotNull NMSServerPlayer player(@NotNull Player player) {
        return new NMSServerPlayerImpl(player);
    }

    @Override
    public @NotNull NMSNetwork createNetwork(@NotNull InetAddress address) {
        return new NMSNetworkImpl(address);
    }

    @Override
    public boolean isSupported() {
        return SUPPORTS.contains(Bukkit.getMinecraftVersion());
    }

    @Override
    public @NotNull ActionTicker createAction(@NotNull Player player, @NotNull ActionType action, @NotNull ActionSetting setting) {
        return new ActionTickerImpl(player, action, setting);
    }

}
