package dev.bluemethyst.starport.data

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val name: String,
    val path: String,
    val workingDir: String,
    val runCommand: String
)
