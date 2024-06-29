package de.airblocks.teamfive.base.commands

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.GameServerFactory
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.suggestion.SuggestionEntry
import net.minestom.server.network.player.PlayerConnection

object ServerCommand: Command("server", "swap") {

    init {
        val serverArgument = ArgumentType.String("gameserver").setSuggestionCallback { commandSender, commandContext, suggestion ->
            val current = suggestion.input.split(" ")[1]
            GameServerFactory.getAllServers().map { it.displayName }.forEach { serverName ->
                if (current == "" || current == " ") {
                    suggestion.addEntry(SuggestionEntry(serverName))
                    return@forEach
                }

                if (serverName.lowercase().startsWith(current.lowercase())) {
                    suggestion.addEntry(SuggestionEntry(serverName))
                }

                if (serverName.lowercase() == current.lowercase()) {
                    suggestion.addEntry(SuggestionEntry(current))
                }
            }
        }

        addSyntax({ sender, context ->
            val server: String = context.get(serverArgument)
            sender.sendMessage("Connecting you to GameServer $server...")
            val onlinePlayerByUuid =
                (MinecraftServer.getConnectionManager().getOnlinePlayerByUuid(sender.identity().uuid()) ?: return@addSyntax) as GamePlayer

            onlinePlayerByUuid.sendToServer(server)
        }, serverArgument)
    }
}