package com.ralphmarondev.notes.navigation

import kotlinx.serialization.Serializable

object Routes{
    @Serializable
    data object Home

    @Serializable
    data object NewNote

    @Serializable
    data class NoteDetails(val id: Int)

    @Serializable
    data class UpdateNote(val id: Int)
}