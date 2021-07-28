package com.example.moviesapptask.utilities.enums

enum class UserSavedDataKeys(val key: String) {
    ID("ID"), ACCESS_TOKEN("TOKEN"),
    FIRST_ONCE("FIRST_ONCE"), LANGUAGE("LANGUAGE"), FCM_TOKEN("fcm_token"), LAST_NOTIFICATION_ID("lastNotificationId"), REMEMBER_ME(
        "REMEMBER_ME"),
    COUNTRY_ID("COUNTRY_ID"),CITY_ID("CITY_ID")
}

enum class Role(val id: String) {
    CLIENT("0"),
    COMPANY("1");

    companion object {
        fun value(ofId: String) = values().find { it.id == ofId }
    }
}
