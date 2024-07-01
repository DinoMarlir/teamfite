package de.airblocks.teamfive.base.player.permissions.impl

import de.airblocks.teamfive.base.player.PlayerRepository
import de.airblocks.teamfive.base.player.permissions.PermissionLayer
import java.util.UUID

class PlayerPermissionLayer(val uuid: UUID): PermissionLayer(uuid) {

    override fun hasPermission(permission: String): Boolean {
        return PlayerRepository.getPlayerOrCreate(uuid).permissions[permission] ?: false
    }

    override fun isDisabled(permission: String): Boolean {
        return !(PlayerRepository.getPlayerOrCreate(uuid).permissions[permission] ?: return true)
    }
}