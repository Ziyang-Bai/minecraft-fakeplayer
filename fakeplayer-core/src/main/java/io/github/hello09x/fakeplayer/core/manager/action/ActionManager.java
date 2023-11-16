package io.github.hello09x.fakeplayer.core.manager.action;

import io.github.hello09x.fakeplayer.api.action.ActionSetting;
import io.github.hello09x.fakeplayer.api.action.ActionType;
import io.github.hello09x.fakeplayer.api.spi.ActionTicker;
import io.github.hello09x.fakeplayer.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActionManager {

    public final static ActionManager instance = new ActionManager();

    private final Map<UUID, Map<ActionType, ActionTicker>> managers = new HashMap<>();

    public ActionManager() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), this::tick, 0, 1);
    }


    public void setAction(
            @NotNull Player player,
            @NotNull ActionType action,
            @NotNull ActionSetting setting
    ) {
        var managers = this.managers.computeIfAbsent(player.getUniqueId(), key -> new HashMap<>());
        managers.put(action, Main.getBridge().createAction(player, action, setting));
    }

    public void tick() {
        var itr = managers.entrySet().iterator();
        while (itr.hasNext()) {
            var entry = itr.next();
            var player = Bukkit.getPlayer(entry.getKey());

            if (player == null) {
                // 假人下线
                itr.remove();
                for (var ticker : entry.getValue().values()) {
                    ticker.stop();
                }
                continue;
            }

            // do tick
            for (var ticker : entry.getValue().values()) {
                ticker.tick();
            }
        }
    }

}
