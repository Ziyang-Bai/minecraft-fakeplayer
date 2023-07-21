package io.github.hello09x.fakeplayer.command.player.control;

import io.github.hello09x.fakeplayer.command.player.AbstractCommand;
import io.github.hello09x.fakeplayer.manager.FakePlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class InvseeCommand extends AbstractCommand {

    private final FakePlayerManager manager = FakePlayerManager.instance;

    public final static InvseeCommand instance = new InvseeCommand(
            "打开假人背包",
            "/fp open [假人名称]",
            "fakeplayer.control"
    );

    public InvseeCommand(@NotNull String description, @NotNull String usage, @Nullable String permission) {
        super(description, usage, permission);
    }

    @Override
    protected boolean execute(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(text("你不是玩家...", RED));
            return true;
        }

        var target = getTarget(sender, args);
        if (target == null) {
            return false;
        }

        manager.openInventory(p, target);
        return true;
    }
}