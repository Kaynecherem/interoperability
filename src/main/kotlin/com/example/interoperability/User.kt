package com.example.interoperability

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(name = "app_user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @field: NotEmpty(message = "Name is required")
    val  name: String,

    @field: NotEmpty(message = "Email should be valid")
    var email: String
)