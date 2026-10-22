package com.inci.campuscompanion.data

import com.inci.campuscompanion.data.remote.CampusApi
import com.inci.campuscompanion.model.CampusEvent

class EventsRepository(
    private val api: CampusApi
) {
    suspend fun getEvents(page: Int, limit: Int = 5): List<CampusEvent> {
        return api.getEvents(page = page, limit = limit).mapIndexed { index, dto ->
            CampusEvent(
                id = dto.id ?: "${page}_$index",
                title = dto.title ?: "Untitled Event",
                description = dto.description,
                date = dto.date ?: "Date TBA",
                location = dto.location
            )
        }
    }
}