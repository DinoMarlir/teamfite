package de.airblocks.teamfive.dummygame

import de.airblocks.teamfive.base.games.AbstractGameMode
import de.airblocks.teamfive.base.server.GameServer
import net.kyori.adventure.text.Component

class DummyGame: AbstractGameMode() {
    override val name: net.kyori.adventure.text.Component
        get() = Component.text("Hey")
    override val description: net.kyori.adventure.text.Component
        get() = Component.text("Hey")
    override val gameServer: (id: String, displayName: String) -> GameServer
        get() = {
            id: String, displayName: String ->
            DummyGameServer(id, displayName)
        }
    override val minPlayersToStart: Int
        get() = 11
    override val maxPlayers: Int
        get() = 14
}