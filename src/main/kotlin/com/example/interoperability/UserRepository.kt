package com.example.interoperability

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>